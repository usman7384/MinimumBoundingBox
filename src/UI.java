import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI
{
    Screen screen;
    JCheckBox hull;
    JCheckBox OMBB;
    JCheckBox AABB;

    public UI(Screen screen)
    {
        this.screen = screen;
        JFrame frame = screen.frame;
        hull = new JCheckBox("Convex Hull");
        OMBB = new JCheckBox("Minimum Box");
        AABB = new JCheckBox("Bounding Box");
        OMBB.setSelected(true);

        screen.add(hull);
        screen.add(OMBB);
        screen.add(AABB);
        screen.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        screen.setVisible(true);
        
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);

        frame.setVisible(true);
        screen.invalidate();
        screen.repaint();
        screen.setVisible(true);
        
        screen.addMouseListener(new MouseAdapter()
        { 
            public void mousePressed(MouseEvent e)
            { 
                onClick(e);
            } 
        });


        hull.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                screen.repaint();
            }
        });

        OMBB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                screen.repaint();
            }
        });

        AABB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                screen.repaint();
            }
        });
    }
    

    public void onClick(MouseEvent e)
    {
        java.awt.geom.Point2D p2d = e.getPoint();
        Point point = new Point(p2d.getX()+6, p2d.getY()+18);
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            screen.points.add(point);
            System.out.println(point);
            Main.createOMBB(screen.points);
            screen.repaint();
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            for(int i=0;i<screen.points.size();i++)
            {
                if(screen.points.get(i).distance(point) < 20)
                {
                    screen.points.remove(i);
                    if(screen.points.size() > 3)
                        Main.createOMBB(screen.points);
                    screen.repaint();
                    return;
                }
            }
        }
    }
}