public final class RotatingCaliper
{
    public static Rectangle minimumBox(Polygon polygon)
    {
        Rectangle[] rectangle = new Rectangle[polygon.edges()];


        for(int i=0;i<polygon.edges();i++)
        {
            Point edge = polygon.getEdge(i);

            double theta = Math.acos(edge.normalize().y);
            polygon.rotate(theta);
            //Calculate a bounding box
            rectangle[i] = boundingBox(polygon);
            polygon.rotate(-theta);
            rectangle[i].rotate(-theta, polygon.getCenter());
        }

        double minArea = Double.MAX_VALUE;
        Rectangle minBox = rectangle[0];

        //Find the bounding box with the smallest area, this is the minimum bounding box
        for(int i=0;i<rectangle.length;i++)
        {
            double area = rectangle[i].area();
            if(area < minArea)
            {
                minArea = area;
                minBox = rectangle[i];
            }
        }
        return minBox;
    }

    public static Rectangle boundingBox(Polygon polygon)
    {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for(int i=0;i<polygon.pointCount();i++)
        {
            Point p = polygon.getPoint(i);
            if(minX > p.x)
                minX = p.x;
            if(maxX < p.x)
                maxX = p.x;
            if(minY > p.y)
                minY = p.y;
            if(maxY < p.y)
                maxY = p.y;
        }
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }
}