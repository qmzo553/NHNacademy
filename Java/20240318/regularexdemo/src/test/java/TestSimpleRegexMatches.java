import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class TestSimpleRegexMatches {

    @Test
    void givenText_whenSimpleRegexMatches_thenCorrect() {
        String source = "foo";
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher(source);

        assertTrue(matcher.find());
    }
}
