import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class TestSimpleRegexMatches {

    public static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int matches = 0;
        while (matcher.find()) {
            matches++;
        }

        return matches;
    }

    @Test
    void givenText_whenSimpleRegexMatches_thenCorrect() {
        String source = "foo";
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher(source);

        assertTrue(matcher.find()); // find() 는 한 번 찾고 그 다음을 찾는다.
    }

    @Test
    void givenText_whenSimpleRegexMatchesTwice_thenCorrect() {
        String source = "foofoo";
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher(source);

        int matches = 0;
        while (matcher.find()) {
            matches++;
        }

        assertEquals(matches, 2);
    }

    @Test
    void givenText_whenMatchesWithDotMetach_thenCorrect() {
        int matches = runTest(".", "foo"); // '.' 은. 임의의 문자 하나다.

        assertTrue(matches > 0);
    }

    @Test
    void givenRepeatedText_whenMatchesOnceWithDotMetach_thenCorrect() {
        int matches = runTest("foo.", "foofoo");

        assertEquals(1, matches);
    }

    @Test
    void givenORSet_whenMatchesAny_thenCorrect() {
        int matches = runTest("[abc]", "bd");

        assertEquals(1, matches);
    }

    @Test
    void givenORSet_whenMatchesAllCombianations_thenCorrect() {
        int matches = runTest("[bcr]at", "bat cat rat");

        assertEquals(3, matches);
    }

    @Test
    void givenNORSet_whenMatchesNon_thenCorrect() {
        int matches = runTest("[^abc]", "g");

        assertTrue(matches > 0);
    }

    @Test
    void givenUpperCaseRangewhenMatchesUpperCase_thenCorrect() {
        int matches = runTest(
                "[A-Z]", "Two Uppercase alphabets 34 overall");

        assertEquals(2, matches);
    }

    @Test
    void givenLowerCaseRangewhenMatchesLowerCase_thenCorrect() {
        int matches = runTest(
                "[a-z]", "Two Uppercase alphabets 34 overall");

        assertEquals(26, matches);
    }

    @Test
    void givenBothLowerAndUpperCaseRange_whenMatchesAllLetters_thenCorrect() {
        int matches = runTest(
                "[a-zA-Z]", "Two Uppercase alphabets 34 overall");

        assertEquals(28, matches);
    }

    @Test
    void givenTwoSets_whenMatchesUnion_thenCorrect() {
        int matcehs = runTest("[1-3[7-9]]", "123456789");

        assertEquals(6, matcehs);
    }

    @Test
    void givenOneOrManyQuantifier_whenMatches_thenCorrect() {
        int matches = runTest("\\a+", "hi");

        assertFalse(matches > 0);
    }

    @Test
    void givenBraceQuantifierWithRange_whenMatches_thenCorrect() {
        int matches = runTest("a{2,3}", "aaaa");

        assertEquals(1, matches);
    }

    @Test
    void givenBraceQuantifierWithRange_whenMatchesLazily_thenCorrect() {
        int matches = runTest("a{2,3}?", "aaaa");

        assertEquals(2, matches);
    }

    @Test
    void givenCapturingGroup_whenMatchesWithBackReference_thenCorrect() {
        int matches = runTest("(\\d\\d)\\1\\1\\1", "12121212"); // "(\\d\\d)(ab)\\2\\1" = "(\\d\\d)(ab)(ab)(\\d\\d)"

        assertEquals(1, matches);
    }

    @Test
    void givenTextAndWrongInput_whenMatchFailsAtBeginning_thenCorrect() {
        int matches = runTest("^dog", "are dogs are friendly?");

        assertFalse(matches > 0);
    }

    @Test
    void givenText_whenMatchesAtWordBoundary_thenCorrect() {
        int matches = runTest("\\bdog\\b", "a dog is friendly");

        assertTrue(matches > 0);
    } 

    @Test
    void testIntegerPattern() {
        String regex = "\\b[+-]?(0|[1-9][0-9]{0,9})\\b";
        assertEquals(1, runTest(regex, "0"));
        assertEquals(1, runTest(regex, "1"));
        assertEquals(1, runTest(regex, "123"));
        assertEquals(0, runTest(regex, "12345678901"));
        assertEquals(1, runTest(regex, "-1"));
        assertEquals(1, runTest(regex, "-123456789"));
        assertEquals(0, runTest(regex, "-01"));
    }
}
