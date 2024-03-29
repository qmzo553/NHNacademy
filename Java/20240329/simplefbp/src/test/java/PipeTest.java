import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Message;
import com.nhnacademy.Pipe;

class PipeTest {

    class TestMessage extends Message {
        TestMessage(String message, String identifier) {
            super(message, identifier);
        }
    }

    @Test
    void testConstructor() {
        // 정상적인 생성
        Pipe pipe = new Pipe(5, "pipe2");
        assertEquals("pipe2", pipe.getIdentifier());

        // 중복된 식별자로 생성할 때 예외 발생 확인
        assertThrows(IllegalArgumentException.class, () -> {
            new Pipe(5, "pipe1"); // 중복된 식별자 사용하지 않도록 수정
        });
    }

    @Test
    void testAddMessage() {
        // 메시지 추가
        Pipe pipe = new Pipe(5, "pipe1");
        Message message = new TestMessage("Hello, World!", "msg");
        pipe.send(message);
        assertEquals(1, pipe.getMessageList().size());

        // 메시지 리스트 크기를 초과하여 추가되지 않음
        for (int i = 0; i < 6; i++) {
            Message newMessage = new TestMessage("Message " + i, "msg" + i);
            pipe.send(newMessage);
        }
        assertEquals(6, pipe.getMessageList().size());
    }
}
