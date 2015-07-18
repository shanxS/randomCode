// Minimum Cost Polygon Triangulation
// code question: 132

public class Main
{
    public static void main(String[] er)
    {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 1),
                new Point(1, 2),
                new Point(0, 2)};

        MinTriangulationFinder mtf = new MinTriangulationFinder();
        System.out.print(mtf.findMinCost(points));
    }
}

class MinTriangulationFinder
{
    private Point[] points;

    public Double findMinCost(Point[] points)
    {
        this.points = points;

        return minCost(0, points.length-1);
    }

    private Double minCost(Integer start, Integer end)
    {
        if (end-start < 2)
        {
            return Double.MAX_VALUE;
        }
        else if (end-start == 2)
        {
            return cost(start, end);
        }

        Double thisMinCost = Double.MAX_VALUE;
        for (Integer k=start+1; k<=end-1; ++k)
        {
            thisMinCost = Math.min(thisMinCost,
                    minCost(start, k) + minCost(k, end) + cost(start, k, end));
        }

        return thisMinCost;
    }

    private Double cost(Integer index1, Integer index2, Integer index3)
    {
        return distance(points[index1], points[index2])
                + distance(points[index2], points[index3])
                + distance(points[index3], points[index1]);
    }

    private Double cost(Integer start, Integer end)
    {
        return distance(points[start], points[start+1])
                + distance(points[start+1], points[end])
                + distance(points[end], points[start]);
    }

    private Double distance(Point p1, Point p2)
    {
        return Math.pow((((p1.x - p2.x)*(p1.x - p2.x))
                + ((p1.y-p2.y)*(p1.y-p2.y)))
                , 0.5);
    }
}

class Point
{
    final Integer x, y;
    public Point(Integer x, Integer y)
    {
        this.x = x;
        this.y = y;
    }
}