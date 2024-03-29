import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Message;

class MessageTest {

    class TestMessage extends Message {
        TestMessage(String message, String identifier) {
            super(message, identifier);
        }
    }

    @Test
    void testConstructor() {
        // 정상적인 생성
        Message message = new TestMessage("Hello", "msg1");
        assertEquals("msg1", message.getIdentifier());

        // 중복된 식별자로 생성할 때 예외 발생 확인
        assertThrows(IllegalArgumentException.class, () -> {
            new TestMessage("Hello", "msg1");
        });
    }

    @Test
    void testCreateTime() {
        // 메시지 생성 시간 확인
        TestMessage message = new TestMessage("Hello", "msg1");
        LocalDateTime createTime = message.getCreateTime();
        assertNotNull(createTime);
        assertTrue(createTime.isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}
