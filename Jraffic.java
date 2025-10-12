import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class Jraffic extends Frame implements KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SPAWN_DELAY = 500;

    private long lastSpawnTime = 0;
    private final Random random = new Random();
    private final Color[] CAR_COLORS = { Color.CYAN, Color.ORANGE, Color.RED };

    class Car {
        int x, y, dx, dy;
        Color color;

        Car(int x, int y, int dx, int dy, Color color) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
        }

        void move() {
            x += dx;
            y += dy;
        }

        boolean isOutOfBounds() {
            return (x < -50 || x > WIDTH + 50 || y < -50 || y > HEIGHT + 50);
        }
    }

    private final List<Car> cars = new ArrayList<>();
    private final int squareSize = 40;

    public Jraffic() {
        super("Jraffic");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        addKeyListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        Timer timer = new Timer(16, e -> {
            updateCars();
            repaint();
        });
        timer.start();
    }

    private void updateCars() {
        Iterator<Car> it = cars.iterator();
        while (it.hasNext()) {
            Car car = it.next();
            car.move();
            if (car.isOutOfBounds())
                it.remove();
        }
    }

    @Override
    public void paint(Graphics g) {
        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.drawLine(centerX - 50, 0, centerX - 50, HEIGHT);
        g.drawLine(centerX + 50, 0, centerX + 50, HEIGHT);
        g.drawLine(0, centerY - 50, WIDTH, centerY - 50);
        g.drawLine(0, centerY + 50, WIDTH, centerY + 50);

        for (int y = 0; y < HEIGHT; y += 20)
            g.drawLine(centerX, y, centerX, y + 10);
        for (int x = 0; x < WIDTH; x += 20)
            g.drawLine(x, centerY, x + 10, centerY);

        g.setColor(Color.RED);
        g.fillRect(centerX - 90, centerY - 90, squareSize, squareSize);
        g.fillRect(centerX + 50, centerY - 90, squareSize, squareSize);
        g.fillRect(centerX - 90, centerY + 50, squareSize, squareSize);
        g.fillRect(centerX + 50, centerY + 50, squareSize, squareSize);

       
        for (Car car : cars) {
            g.setColor(car.color);
            g.fillRect(car.x, car.y, 30, 30);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        long now = System.currentTimeMillis();
        if (now - lastSpawnTime < SPAWN_DELAY)
            return;
        lastSpawnTime = now;

        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;

        Color randomColor = CAR_COLORS[random.nextInt(CAR_COLORS.length)];

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                cars.add(new Car(centerX + 10, HEIGHT, 0, -3, randomColor));
                break;
            case KeyEvent.VK_DOWN:
                cars.add(new Car(centerX - 40, -30, 0, 3, randomColor));
                break;
            case KeyEvent.VK_LEFT:
                cars.add(new Car(WIDTH, centerY - 40, -3, 0, randomColor));
                break;
            case KeyEvent.VK_RIGHT:
                cars.add(new Car(-30, centerY + 10, 3, 0, randomColor));
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
