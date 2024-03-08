import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class TestEventFrame extends JFrame implements KeyListener, MouseMotionListener {
    public TestEventFrame() {

        addKeyListener(this);
        addMouseMotionListener(this);
    }   
     
    public static void main(String[] args) {
        TestEventFrame frame = new TestEventFrame();

        frame.setSize(600, 500);

        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("key pressed : " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse Drag : " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse Move : " + e.getX() + "," + e.getY());
    }
}
