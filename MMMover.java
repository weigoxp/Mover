import java.awt.*;

public class MMMover {
    public static final int SECONDS_15 = 15000;

    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int MAX_X = (int) screenSize.getWidth();
        int MAX_Y = (int) screenSize.getHeight();

        while (true) {
            shake(robot, MAX_X/2, MAX_Y/2);
            Thread.sleep(SECONDS_15);
        }
    }

    private static void shake(Robot robot, final int x, final int y){
        int pixelJump = 3;
        int shakeRange = 50;
        int shakeCount = 5;

        int x1 = x - shakeRange;
        int x2 = x + shakeRange;
        int y1 = y + shakeRange;
        int y2 = y - shakeRange;

        final double xSqu = (x2 - x1) * (x2 - x1);
        final double ySqu = (y2 - y1) * (y2 - y1);
        final double lineLength = Math.sqrt(xSqu + ySqu);

        for(int i = 0; i< shakeCount; i++){
            double dt = 0;
            while (dt < lineLength) {
                dt += pixelJump;
                final double t = dt / lineLength;
                final int dx = (int) ((1 - t) * x1 + t * x2);
                final int dy = (int) ((1 - t) * y1 + t * y2);
                robot.mouseMove(dx, dy);
                robot.delay(1); // Increase this number if you need to delay the mouse movement
            }
            dt = lineLength;
            while (dt > 0) {
                dt -= pixelJump;
                final double t = dt / lineLength;
                final int dx = (int) ((1 - t) * x1 + t * x2);
                final int dy = (int) ((1 - t) * y1 + t * y2);
                robot.mouseMove(dx, dy);
                robot.delay(1); // Increase this number if you need to delay the mouse movement
            }
        }
    }
}