import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
        MinDistanceFinder mdf = new MinDistanceFinder();
        System.out.print(mdf.find(generatePoints()));
    }

    private static List<Point> generatePoints()
    {
        List<Point> points = new ArrayList<>();

        points.add(new Point(2,3));
        points.add(new Point(12,30));
        points.add(new Point(40,50));
        points.add(new Point(5,1));
        points.add(new Point(12,10));
        points.add(new Point(3,4));

        return points;
    }
}

class MinDistanceFinder
{
    private List<Point> closestPoints;
    public double find(List<Point> points)
    {
        Collections.sort(points);

        Set<Point> set = new HashSet<>();
        double minDistance = poplateClosestXPairs(points, set);
        if (set.size() < 2)
        {
            closestPoints = new ArrayList<>();
            closestPoints.addAll(set);
            return minDistance;
        }

        List<Point> xSortedPoints = new ArrayList<>();
        xSortedPoints.addAll(set);
        for (Point p : xSortedPoints)
        {
            p.sortByX = false;
        }

        Collections.sort(xSortedPoints);
        for (Integer i=1; i<xSortedPoints.size(); ++i)
        {
            double minSoFar = distanceY(xSortedPoints.get(i - 1), xSortedPoints.get(i));
            if (minSoFar < minDistance)
            {
                minDistance = minSoFar;
                closestPoints = new ArrayList<>();
                closestPoints.add(xSortedPoints.get(i-1));
                closestPoints.add(xSortedPoints.get(i));
            }
            else if (minSoFar == minDistance)
            {
                closestPoints.add(xSortedPoints.get(i));
            }
        }

        return minDistance;
    }

    private double distanceY(Point p1, Point p2)
    {
        return distance(p1,p2);
    }

    private double distance(Point p1, Point p2)
    {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x)
                    + (p1.y - p2.y) * (p1.y - p2.y));
    }

    private double poplateClosestXPairs(List<Point> points, Set<Point> set)
    {
        if (points.size() == 1)
        {
            return Integer.MAX_VALUE;
        }

        if (points.size() == 2)
        {
            double thisDistance = distance(points.get(0), points.get(1));
            set.addAll(points);
            return thisDistance;
        }

        Set<Point> firstPoints = new HashSet<>();
        double minFirst = poplateClosestXPairs(points.subList(0, points.size() / 2), firstPoints);

        Set<Point> lastPoints = new HashSet<>();
        double minLast = poplateClosestXPairs(points.subList((points.size() / 2) + 1, points.size()), lastPoints);

        Integer midIndex = points.size()/2;
        Point midPoint = points.get(midIndex);

        if (minFirst < minLast)
        {
            final double distance = minFirst;

            for (Integer i=midIndex+1; i<points.size(); ++i)
            {
                if (distance(midPoint, points.get(i)) > distance)
                {
                    break;
                }
                set.add(midPoint);
                set.add(points.get(i));
            }

            for (Integer i=midIndex-1; i>=0; --i)
            {
                if (distance(midPoint, points.get(i)) > distance)
                {
                    break;
                }
                set.add(midPoint);
                set.add(points.get(i));
            }

            set.addAll(firstPoints);
            return minFirst;
        }
        else if (minFirst > minLast)
        {
            final double distance = minLast;

            for (Integer i=midIndex+1; i<points.size(); ++i)
            {
                if (distance(midPoint, points.get(i)) > distance)
                {
                    break;
                }
                set.add(midPoint);
                set.add(points.get(i));
            }

            for (Integer i=midIndex-1; i>=0; --i)
            {
                if (distance(midPoint, points.get(i)) > distance)
                {
                    break;
                }
                set.add(midPoint);
                set.add(points.get(i));
            }

            set.addAll(lastPoints);

            return minLast;
        }
        else
        {
            final double distance = minLast;

            for (Integer i=midIndex+1; i<points.size(); ++i)
            {
                if (distance(midPoint, points.get(i)) > distance)
                {
                    break;
                }
                set.add(midPoint);
                set.add(points.get(i));
            }

            for (Integer i=midIndex-1; i>=0; --i)
            {
                if (distance(midPoint, points.get(i)) > distance)
                {
                    break;
                }
                set.add(midPoint);
                set.add(points.get(i));
            }

            set.addAll(lastPoints);
            set.addAll(firstPoints);

            return minFirst;
        }
    }
}

class Point implements Comparable
{
    public final Integer x, y;
    public boolean sortByX;

    public Point(Integer x, Integer y)
    {
        this.x = x;
        this.y = y;
        sortByX = true;
    }

    @Override
    public boolean equals(Object o)
    {
        Point otherPoint = (Point) o;
        return x == otherPoint.x && y == otherPoint.y;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public int compareTo(Object o)
    {
        Point otherPoint = (Point) o;
        if (sortByX)
        {
            if (otherPoint.x > x)
            {
                return -1;
            }
            else if (otherPoint.x < x)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            if (otherPoint.y > y)
            {
                return -1;
            }
            else if (otherPoint.y < y)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
}