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
        setPreferredSize(new Dimension(500, 500)); // Apparently JPanel uses a layout manager and this let's the manager handle things?
        // setSize(); // Which is why you don't want to use this.
        addMouseListener(new MouseHandler()); // This confuses me slightly.
    }

    protected void paintComponent(Graphics g) {
        // Graphics2D g2;
        // g2 = (Graphics2D) g; // Type-casting...?
        g.fillOval(25, 25, 100, 100);
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
        int startX, startY;
        int endX, endY;

        public void mousePressed(MouseEvent event) {
            this.startX = event.getX();
            this.startY = event.getY();
            System.out.println("Started at X: "+this.startX+" Y: "+this.startY);

            // Adds a MouseMotionListener, so we can track where we are going.
            addMouseMotionListener(this);
        }

        /**
         * mouseDragged might seem unimportant when we have mouseReleased,
         * however if we're trying to freeform draw or have some sort of
         * shape to visualize what size shape / item you're building, we
         * want to keep track of the X and Y constantly.
         *
         */
        public void mouseDragged(MouseEvent event) {
            this.endX = event.getX();
            this.endY = event.getY();
        }

        public void mouseReleased(MouseEvent event) {
            System.out.println("Ended up at X: "+this.endX+" Y: "+this.endY);
            System.out.println(event.getX());
            System.out.println(event.getY());
            removeMouseMotionListener(this);
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
