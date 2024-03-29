import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Node;

class NodeTest {

    class TestNode extends Node {

        TestNode(String name, String identifier) {
            super(name, identifier);
           
        }
    }

    @Test
    void testConstructor() {
        // 정상적인 생성
        Node node1 = new TestNode("Node 1", "id1");
        assertEquals("Node 1", node1.getName());
        assertEquals("id1", node1.getIdentifier());

        // 중복된 식별자로 생성할 때 예외 발생 확인
        assertThrows(IllegalArgumentException.class, () -> {
            new TestNode("Node 2", "id1");
        });
    }

    @Test
    void testNameSetter() {
        Node node = new TestNode("Node", "id");
        
        // 이름 변경 후 확인
        node.setName("New Name");
        assertEquals("New Name", node.getName());
    }
    
    @Test
    void testGetIdentifier() {
        Node node = new TestNode("Node", "id2");
        
        // 식별자 확인
        assertEquals("id2", node.getIdentifier());
    }
}
