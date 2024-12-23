import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class RotatingSegment extends JPanel {

    private double angle = 0;
    private double t = 0;

    public RotatingSegment() {
        new Timer(16, e -> {
            angle += 0.1;
            t += 0.05;
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth(), height = getHeight();
        g2d.translate(width / 2.0, height / 2.0);

        double segmentLength = 100;
        double x1 = -segmentLength / 2, x2 = segmentLength / 2;
        double px = x1 + (x2 - x1) * (0.5 + 0.5 * Math.sin(t)), py = 0;

        Line2D segment = new Line2D.Double(x1, 0, x2, 0);
        g2d.transform(AffineTransform.getRotateInstance(angle, px, py));

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLUE);
        g2d.draw(segment);

        g2d.setColor(Color.RED);
        g2d.fill(new Ellipse2D.Double(px - 5, py - 5, 10, 10));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab4 OOP");
        frame.add(new RotatingSegment());
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}