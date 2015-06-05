// line sweep - given set of points - find closest distance
// code question 46

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2,3));
        points.add(new Point(12,30));
        points.add(new Point(40,50));
        points.add(new Point(5,1));
        points.add(new Point(12,10));
        points.add(new Point(3,4));

        MinDistFinder mf = new MinDistFinder();
        System.out.print(mf.findMin(points));
    }
}

class MinDistFinder
{
    List<Point> points;

    public double findMin(List<Point> points)
    {
        this.points = points;
        Collections.sort(this.points);

        return minInRange(0, points.size()-1);
    }

    private double minInRange(Integer start, Integer end)
    {
        if (end-start < 3)
        {
            return bruteForceDistance(start, end);
        }

        Integer mid = (start + end)/2;
        double dl = minInRange(start, mid);
        double dr = minInRange(mid+1, end);

        double d = Math.min(dl, dr);

        Point midPoint = points.get(mid);
        List<Point> strip = new ArrayList<>();
        for (Integer i=start; i<=end; ++i)
        {
            if (Math.abs(midPoint.getX() - points.get(i).getX()) <= d)
            {
                strip.add(points.get(i));
            }
        }

        return Math.min(d, minInStrip(strip, d));
    }

    private double bruteForceDistance(Integer start, Integer end)
    {
        double d = Double.MAX_VALUE;

        for (int i=start; i<=end; ++i)
        {
            for (int j=i+1; j<=end; ++j)
            {
                double thisD = distance(points.get(i), points.get(j));
                if (thisD < d)
                {
                    d = thisD;
                }
            }
        }

        return d;
    }

    private double minInStrip(List<Point> strip, double d)
    {
        for (Point p : strip)
        {
            p.setSortByX(false);
        }

        Collections.sort(strip);
        double thisD = d;
        for(Integer i=0; i<strip.size(); ++i)
        {
            for (Integer j = i + 1; j < strip.size() && Math.abs(strip.get(j).getY() - strip.get(i).getY()) <= thisD; ++j)
            {
                if (distance (strip.get(j), strip.get(i)) < thisD)
                {
                    thisD = distance (strip.get(j), strip.get(i));
                }
            }
        }

        return thisD;
    }

    private double distance(Point point1, Point point2)
    {
        double xDiff = Math.pow((point1.getX() - point2.getX()), 2);
        double yDiff = Math.pow((point1.getY() - point2.getY()), 2);

        return Math.sqrt(xDiff + yDiff);
    }
}

class Point implements Comparable<Point>
{
    private Integer x, y;
    private Boolean sortByX;

    public Point(Integer x, Integer y)
    {
        this.x = x;
        this.y = y;
        sortByX = true;
    }

    @Override
    public int compareTo(Point otherPoint)
    {
        if (sortByX)
        {
            if (this.getX() > otherPoint.getX())
            {
                return 1;
            }
            else if (this.getX() < otherPoint.getX())
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            if (this.getY() > otherPoint.getY())
            {
                return 1;
            }
            else if (this.getY() < otherPoint.getY())
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }

    public Boolean getSortByX()
    {
        return sortByX;
    }

    public void setSortByX(Boolean sortByX)
    {
        this.sortByX = sortByX;
    }

    public Integer getX()
    {
        return x;
    }

    public Integer getY()
    {
        return y;
    }
}