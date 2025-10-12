import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Jraffic extends Frame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public Jraffic() {
        super("Jraffic");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;

        g.drawLine(centerX - 50, 0, centerX - 50, HEIGHT);
        g.drawLine(centerX + 50, 0, centerX + 50, HEIGHT);

        g.drawLine(0, centerY - 50, WIDTH, centerY - 50);
        g.drawLine(0, centerY + 50, WIDTH, centerY + 50);


        for (int y = 0; y < HEIGHT; y += 20) {
            g.drawLine(centerX, y, centerX, y + 10);
        }

        for (int x = 0; x < WIDTH; x += 20) {
            g.drawLine(x, centerY, x + 10, centerY);
        }

        g.setColor(Color.RED);
        int squareSize = 40;
        g.fillRect(centerX - 90, centerY - 90, squareSize, squareSize); 
        g.fillRect(centerX + 50, centerY - 90, squareSize, squareSize); 
        g.fillRect(centerX - 90, centerY + 50, squareSize, squareSize); 
        g.fillRect(centerX + 50, centerY + 50, squareSize, squareSize); 
    }

   
}
