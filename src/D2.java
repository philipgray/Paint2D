import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

// ferris

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import java.awt.Graphics;

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


public class D2 extends JPanel {
    String state = "circle";
    Color currentColor;

    int shapeWidth = 50;
    int shapeLength = 50;

    Graphics2D graphics;
    ArrayList<Shape> shapes;
    ArrayList<Color> colors;

    int clickX;
    int clickY;

    public D2() {
        setPreferredSize(new Dimension(500, 500)); // Apparently JPanel uses a layout manager and this let's the manager handle things?
        this.shapes = new ArrayList<>();
        this.colors = new ArrayList<>();
        // setSize(); // Which is why you don't want to use this.
        addMouseListener(new MouseHandler()); // This confuses me slightly.

        this.currentColor = Color.RED;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // wish someone explained why we do this
        graphics = (Graphics2D) g;

        for (int i = 0; i < shapes.size(); i++) {
            // pain
            graphics.setColor(colors.get(i)); // set border color
            graphics.fill(shapes.get(i)); // fills the shape
        }
    }


    public void createShape(Graphics2D g, String shape, int x, int y) {
        int x1 = x - (shapeWidth / 2);
        int y1 = y - (shapeLength / 2);

        if (shape.equals("rect")) {
            Shape rect = new Rectangle(x1, y1, shapeWidth, shapeLength);
            shapes.add(rect);
            colors.add(currentColor);

        } else if (shape.equals("circle")) {
            Shape circle = new Ellipse2D.Double(x1, y1, shapeWidth, shapeLength);
            shapes.add(circle);
            colors.add(currentColor);
        }
        repaint();
    }

    public void updateState(String newState) {
        this.state = newState;
    }

    public void updateColor(Color newColor) {
        this.currentColor = newColor;
    }

    public static void main(String[] args) {
        // Title Button X Offset.
        int cpx = 410;

        // Brush State for applying pixels.

        // JFrame and JPanel stuff.
        JFrame frame = new JFrame("Sprinkles Paint Demo");
        JPanel panel = new JPanel();
        D2 drawingPanel = new D2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layouts to null, allows for virtually any button location placement.
        panel.setLayout(null);
        panel.setBackground(new Color(220, 220, 220));
        drawingPanel.setLayout(null);


        frame.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        drawingPanel.setBorder(BorderFactory.createBevelBorder(0, Color.darkGray, Color.lightGray));
        drawingPanel.setSize(new Dimension(875, 580));
        drawingPanel.setVisible(true);
        drawingPanel.setLocation(110,100);
        drawingPanel.setBackground(Color.white);

        TextPanel toolStateMessage = new TextPanel();
        toolStateMessage.setLayout(null);
        toolStateMessage.setVisible(true);
        toolStateMessage.setLocation(110,685);
        toolStateMessage.setBackground(Color.white);
        toolStateMessage.setSize(new Dimension(400, 15));
        //toolStateMessage.setHorizontalTextPosition(100);

        frame.add(toolStateMessage);
        frame.add(drawingPanel);
        frame.add(panel);

        ImageIcon icon = new ImageIcon("assets/paint.png");
        frame.setIconImage(icon.getImage());

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu filemenu = new JMenu("Sprinkles Paint Version 1.0");
        menuBar.add(filemenu);

        JMenuItem saveAsItem = new JMenuItem("Credits - Alex Wills, Philip Gray, Ferris Whitney", KeyEvent.VK_T);
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, ActionEvent.ALT_MASK));
        filemenu.add(saveAsItem);

        // All Buttons
        Icon pencil = new ImageIcon("assets/pencil.png");
        JButton toolbutton = new JButton(pencil);
        toolbutton.setBounds(35, 30, 60, 45);
        panel.add(toolbutton);
        toolbutton.setBackground(Color.white);

        Icon zoomin = new ImageIcon("assets/zoomin.png");
        JButton zoominbutton = new JButton(zoomin);
        zoominbutton.setBounds(55, 600, 30, 25);
        panel.add(zoominbutton);
        zoominbutton.setBackground(Color.white);

        Icon zoomout = new ImageIcon("assets/zoomout.png");
        JButton zoomoutbutton = new JButton(zoomout);
        zoomoutbutton.setBounds(55, 630, 30, 25);
        panel.add(zoomoutbutton);
        zoomoutbutton.setBackground(Color.white);

        Icon brush = new ImageIcon("assets/brush.png");
        JButton brushbutton = new JButton(brush);
        brushbutton.setBounds(110, 30, 60, 45);
        panel.add(brushbutton);
        brushbutton.setBackground(Color.white);

        Icon spray = new ImageIcon("assets/spray.png");
        JButton spraybutton = new JButton(spray);
        spraybutton.setBounds(185, 30, 60, 45);
        panel.add(spraybutton);
        spraybutton.setBackground(Color.white);

        Icon square = new ImageIcon("assets/square.png");
        JButton squarebutton = new JButton(square);
        squarebutton.setBounds(260, 30, 60, 45);
        panel.add(squarebutton);
        squarebutton.setBackground(Color.white);

        Icon circle = new ImageIcon("assets/circle.png");
        JButton circlebutton = new JButton(circle);
        circlebutton.setBounds(335, 30, 60, 45);
        panel.add(circlebutton);
        circlebutton.setBackground(Color.white);

        Icon text = new ImageIcon("assets/text.png");
        JButton textbutton = new JButton(text);
        textbutton.setBounds(565, 30, 60, 45);
        panel.add(textbutton);
        textbutton.setBackground(Color.white);

        Icon save = new ImageIcon("assets/save.png");
        JButton savebutton = new JButton(save);
        savebutton.setBounds(35, 150, 60, 45);
        panel.add(savebutton);
        savebutton.setBackground(Color.white);

        Icon wand = new ImageIcon("assets/wand.png");
        JButton wandbutton = new JButton(wand);
        wandbutton.setBounds(35, 90, 60, 45);
        panel.add(wandbutton);
        wandbutton.setBackground(Color.white);

        Icon open = new ImageIcon("assets/open.png");
        JButton openbutton = new JButton(open);
        openbutton.setBounds(35, 210, 60, 45);
        panel.add(openbutton);
        openbutton.setBackground(Color.white);

        JButton redButton = new JButton("");
        redButton.setBounds(cpx, 30, 20, 20);
        panel.add(redButton);
        redButton.setBackground(Color.RED);

        redButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Red");
            drawingPanel.updateColor(Color.RED);
        });

        JButton orangeButton = new JButton("");
        orangeButton.setBounds(cpx + 25, 30, 20, 20);
        panel.add(orangeButton);
        orangeButton.setBackground(Color.ORANGE);

        orangeButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Orange");
            drawingPanel.updateColor(Color.ORANGE);
        });

        JButton yellowButton = new JButton("");
        yellowButton.setBounds(cpx + 50, 30, 20, 20);
        panel.add(yellowButton);
        yellowButton.setBackground(Color.YELLOW);

        yellowButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Yellow");
            drawingPanel.updateColor(Color.YELLOW);
        });

        JButton greenButton = new JButton("");
        greenButton.setBounds(cpx + 75, 30, 20, 20);
        panel.add(greenButton);
        greenButton.setBackground(Color.GREEN);

        greenButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Green");
            drawingPanel.updateColor(Color.GREEN);
        });

        JButton blueButton = new JButton("");
        blueButton.setBounds(cpx + 100, 30, 20, 20);
        panel.add(blueButton);
        blueButton.setBackground(Color.BLUE);

        blueButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Blue");
            drawingPanel.updateColor(Color.BLUE);
        });

        JButton magentaButton = new JButton("");
        magentaButton.setBounds(cpx + 125, 30, 20, 20);
        panel.add(magentaButton);
        magentaButton.setBackground(Color.MAGENTA);

        magentaButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Magenta");
            drawingPanel.updateColor(Color.MAGENTA);
        });

        JButton pinkButton = new JButton("");
        pinkButton.setBounds(cpx, 55, 20, 20);
        panel.add(pinkButton);
        pinkButton.setBackground(Color.PINK);

        pinkButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Pink");
            drawingPanel.updateColor(Color.PINK);
        });

        JButton whiteButton = new JButton("");
        whiteButton.setBounds(cpx+25, 55, 20, 20);
        panel.add(whiteButton);
        whiteButton.setBackground(Color.WHITE);

        whiteButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: White");
            drawingPanel.updateColor(Color.WHITE);
        });

        JButton blackButton = new JButton("");
        blackButton.setBounds(cpx+50, 55, 20, 20);
        panel.add(blackButton);
        blackButton.setBackground(Color.BLACK);

        blackButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Black");
            drawingPanel.updateColor(Color.BLACK);
        });

        JButton grayButton = new JButton("");
        grayButton.setBounds(cpx+75, 55, 20, 20);
        panel.add(grayButton);
        grayButton.setBackground(Color.GRAY);

        grayButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Gray");
            drawingPanel.updateColor(Color.GRAY);
        });

        JButton cyanButton = new JButton("");
        cyanButton.setBounds(cpx+100, 55, 20, 20);
        panel.add(cyanButton);
        cyanButton.setBackground(Color.CYAN);

        cyanButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Cyan");
            drawingPanel.updateColor(Color.CYAN);
        });

        JButton lightGrayButton = new JButton("");
        lightGrayButton.setBounds(cpx+125, 55, 20, 20);
        panel.add(lightGrayButton);
        lightGrayButton.setBackground(Color.LIGHT_GRAY);

        lightGrayButton.addActionListener(e -> {
            toolStateMessage.updateText("Color Selected: Light Gray");
            drawingPanel.updateColor(Color.LIGHT_GRAY);
        });


        /**
         * BUTTONS
         * BUTTON_NAME.addActionListener(new ActionListener() {
         *             public void actionPerformed(ActionEvent e) {
         *                 toolStateMessage.updateText("Tool Selected: Pencil Tool");
         *             }
         *         });
         */

        /**
         * IntelliJ thought we could do better, so we present to you the lambda!
         * https://www.w3schools.com/java/java_lambda.asp
         *
         */

        toolbutton.addActionListener(e -> toolStateMessage.updateText("Tool Selected: Pencil Tool"));

        brushbutton.addActionListener(e -> toolStateMessage.updateText("Tool Selected: Brush Tool"));

        spraybutton.addActionListener(e -> toolStateMessage.updateText("Tool Selected: Spray Tool"));

        textbutton.addActionListener(e -> toolStateMessage.updateText("Tool Selected: Text Tool"));

        squarebutton.addActionListener(e -> {
            toolStateMessage.updateText("Tool Selected: Rectangle Tool");
            drawingPanel.updateState("rect");
        });

        circlebutton.addActionListener(e -> {
            toolStateMessage.updateText("Tool Selected: Circle Tool");
            drawingPanel.updateState("circle");
        });

        frame.pack();
        frame.setJMenuBar(menuBar);
        frame.setSize(1024, 768);
        frame.setVisible(true);

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

        public void mousePressed(MouseEvent event) {
            clickX = event.getX();
            clickY = event.getY();
            // Adds a MouseMotionListener, so we can track where we are going.

            if (state.equals("rect") || state.equals("circle")) {
                createShape(graphics, state, clickX, clickY);
            } else {
                addMouseMotionListener(this);
            }
        }

        /**
         * mouseDragged might seem unimportant when we have mouseReleased,
         * however if we're trying to freeform draw or have some sort of
         * shape to visualize what size shape / item you're building, we
         * want to keep track of the X and Y constantly.
         *
         *
         public void mouseDragged(MouseEvent event) {
         endX = event.getX(); // pass to drawing
         endY = event.getY();
         }

         public void mouseReleased(MouseEvent event) {
         removeMouseMotionListener(this);

         if (state.equals("rect")) {
         // createShape(g, state,endX, endY, shapeWidth, shapeLength);
         }
         }
         **/
    } // MouseHandler

    private static class TextPanel extends JPanel {
        private mouseMovement movement;
        private Graphics graphics;
        int x1, y1, x2, y2;

        public TextPanel() {
            movement = new mouseMovement();
            this.addMouseListener(movement);
            this.addMouseMotionListener(movement);

        }

        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
        }

        public void updateText(String a){

            graphics = getGraphics();
            graphics.drawString(a, 12, 12);

        }

        // Draws lines on screen, nothing more.
        private class mouseMovement extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                graphics = getGraphics();
                repaint();
                x2 = x1;
                y2 = y1;
                graphics.drawString("Text test", 12, 12);
            }

            public void mouseDragged(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                x2 = x1;
                y2 = y1;
            }
        }
    }

}
