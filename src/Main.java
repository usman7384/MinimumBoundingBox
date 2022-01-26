import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{
    static UI ui;
    static Screen screen;

    public static void main(String[] args)
    {
        screen = new Screen(1920, 1080);
        ui = new UI(screen);
        createOMBB(initialPoints(50));
    }

    public static void createOMBB(List<Point> points)
    {
        Polygon hull = new Polygon(ConvexHull.makeHull(points));
        Rectangle rect = RotatingCaliper.minimumBox(hull);
        screen.points = points;
        screen.hull = hull;
        screen.OMBB = rect;
        screen.AABB = RotatingCaliper.boundingBox(hull);
        screen.repaint();
    }

    public static List<Point> initialPoints(int p)
    {
        List<Point> points = new ArrayList<Point>(p);
        Random ran = new Random();

        for(int i=0;i<p;i++)
        {
            points.add(new Point(600 + ran.nextInt(400), 300 + ran.nextInt(250)));
        }
        return points;
    }
}