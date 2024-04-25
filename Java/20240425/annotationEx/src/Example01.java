package src;

/**
 * JavaDoc Example class
 *
 * @author marco
 * @version 1.1
 * @see Example01#printMessage(String)
 *
 */
public class Example01 {

    /**
     * message parameter에 대한 설명
     */
    private String message = "hello java";

    /**
     * message parameter를 받아서 console에 출력하는 method
     *
     * @param message console에 출력할 message
     * @see Example01
     * @return message가 정상출력 되면 true 실패하면 false 반환
     */
    public boolean printMessage(String message) {
        boolean result = true;

        try {
            System.out.println(message);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }
}
