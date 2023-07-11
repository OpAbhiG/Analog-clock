import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnalogClock extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 400));
        frame.setBackground(new Color(50, 50, 50));

        AnalogClock clock = new AnalogClock();
        frame.add(clock);

        frame.setVisible(true);

        new Thread(() -> {
            while (true) {
                clock.repaint();
                clock.delayAnimation();
            }
        }).start();
    }

    // Animating the clock
    private void delayAnimation() {
        try {
            // Animation delay is 1000 milliseconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Paint the clock
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the system time
        Calendar time = Calendar.getInstance();

        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);

        // 12-hour format
        if (hour > 12) {
            hour -= 12;
        }

        // Draw clock body center at (400, 200)
        g.setColor(Color.white);
        g.fillOval(300, 100, 200, 200);

        // Labeling
        g.setColor(Color.black);
        g.drawString("12", 390, 120);
        g.drawString("9", 310, 200);
        g.drawString("6", 400, 290);
        g.drawString("3", 480, 200);

        // Declaring variables to be used
        double angle;
        int x, y;

        // Second hand's angle in Radians
        angle = Math.toRadians((15 - second) * 6);

        // Position of the second hand with length 100 units
        x = (int) (Math.cos(angle) * 100);
        y = (int) (Math.sin(angle) * 100);

        // Red color second hand
        g.setColor(Color.red);
        g.drawLine(400, 200, 400 + x, 200 - y);

        // Minute hand's angle in Radians
        angle = Math.toRadians((15 - minute) * 6);

        // Position of the minute hand with length 80 units
        x = (int) (Math.cos(angle) * 80);
        y = (int) (Math.sin(angle) * 80);

        // Blue color minute hand
        g.setColor(Color.blue);
        g.drawLine(400, 200, 400 + x, 200 - y);

        // Hour hand's angle in Radians
        angle = Math.toRadians((15 - (hour * 5)) * 6);

        // Position of the hour hand with length 50 units
        x = (int) (Math.cos(angle) * 50);
        y = (int) (Math.sin(angle) * 50);

        // Black color hour hand
        g.setColor(Color.black);
        g.drawLine(400, 200, 400 + x, 200 - y);
    }
}
