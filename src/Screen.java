

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

public class Screen extends JPanel 
{
    private static final long serialVersionUID = 1L;
    private int Width;
    private int Height;
    AffineTransform transform;
    Polygon hull;
    List<Point> points;
    Rectangle OMBB;
    Rectangle AABB;
    List<Rectangle> rectangles;

    public JFrame frame;

    public Screen(int width, int height)
    {
        Width = width;
        Height = height;

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Rotating Calipers");
        frame.setSize(Width, Height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        frame.add(this);
        setBackground(Color.BLACK);
        frame.setResizable(true);
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                java.awt.Rectangle b = componentEvent.getComponent().getBounds();

                componentEvent.getComponent().setBounds(b.x, b.y, b.width, b.width * 9/16);
            }
        });
    }
    
    public void paint(Graphics page)
    {
        //Resize screen
        double scaleX = getSize().getWidth() / Width;
        double scaleY = getSize().getHeight() / Height;
        Graphics2D g2d = (Graphics2D)page;
        transform = g2d.getTransform();
        
        g2d.scale(scaleX, scaleY);

        page.setColor(Color.BLACK);
        page.fillRect(0, 0, Width, Height);
        //Draw the polygons
        super.paint(page);
        
        if(points != null)
        {
            for(int i=0;i<points.size();i++)
            {
                points.get(i).draw(page);
            }
        }

        if(hull != null && Main.ui.hull.isSelected())
        {
            hull.draw(page, Color.BLUE);
        }

        if(AABB != null && Main.ui.AABB.isSelected())
        {
            AABB.draw(page, Color.YELLOW);
        }
        
        if(OMBB != null && Main.ui.OMBB.isSelected())
        {
            OMBB.draw(page, Color.RED);
        }
    }

    public Point2D screenPos(Point2D p)
    {
        Point2D ret = null;
        transform.transform(p, ret);
        return ret;
    }
}