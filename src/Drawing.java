import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Credit to David J. Eck for creating, "Introduction to Computer Graphics"
 * https://math.hws.edu/graphicsbook/index.html
 *
 * This book has been fundamental in helping me start building my understanding of
 * how to use graphics2D, java.awt, and swing.
 * https://math.hws.edu/graphicsbook/c2/s5.html
 *
 *
 */


public class Drawing extends JPanel {

    public Drawing() {
        setPreferredSize(new Dimension(1024, 720)); // Apparently JPanel uses a layout manager and this let's the manager handle things?
        // setSize(); // Which is why you don't want to use this.
        addMouseListener(new MouseHandler()); // This confuses me slightly.
    }

    protected void paintComponent(Graphics g) {
        // Graphics2D g2;
        // g2 = (Graphics2D) g; // Type-casting...?
        g.fillOval(100, 100, 200, 200);
    }


    /**
     * We use MouseAdapter over MouseListener, so we can only implement the
     * things that are relevant to us as opposed to implementing everything
     * needed in MouseListener.
     *
     * private class MouseHandler implements MouseListener
     *
     * https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseAdapter.html
     *
     * ------------------------------------------------------------------------------------
     *
     * There are three different actions that we're looking for:
     * mousePressed - Invoked when a mouse button has been pressed on a component.
     * mouseDragged - Invoked when a mouse button is pressed on a component and then dragged.
     * mouseReleased - Invoked when a mouse button has been released on a component.
     */
    private class MouseHandler extends MouseAdapter {
        int X;
        int Y;

        public void mousePressed(MouseEvent event) {
            this.X = event.getX();
            this.Y = event.getY();
            System.out.println("Mouse Pressed at "+this.X);
        }

        public void mouseDragged(MouseEvent event) {
        }

        public void mouseReleased(MouseEvent event) {
            System.out.println("Mouse Pressed at "+this.X);
        }


    }

    public static void main(String[] args) {
        JFrame paintWindow = new JFrame("PaintBySprinkles"); // Creating a new Window
        Drawing drawingPanel = new Drawing();

        paintWindow.setContentPane(drawingPanel);
        paintWindow.pack();

        paintWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the program when you hit the X.
        paintWindow.setVisible(true);
    }
}
