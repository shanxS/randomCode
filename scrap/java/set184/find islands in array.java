// find islands in array
// set 184, r2, q2

import java.util.ArrayDeque;
import java.util.Queue;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] sea = new Integer[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}};

        IslandFinder isf = new IslandFinder();
        System.out.print(isf.countIsland(sea));
    }
}

class IslandFinder
{
    private Integer[][] sea;
    final private Integer INVALID = 0;
    final private Integer VISITED = -1;
    final private Integer VALID = 1;

    public Integer countIsland(Integer[][] sea)
    {
        this.sea = sea;

        Integer count = 0;
        for (Integer r=0; r < sea.length; ++r)
        {
            for (Integer c=0; c<sea[0].length; ++c)
            {
                if (sea[r][c] == VALID)
                {
                    invalidateIsland(r, c);
                    ++count;
                }
            }
        }

        return count;
    }

    private void invalidateIsland(Integer r, Integer c)
    {
        Queue<Point> que = new ArrayDeque<>();

        que.add(new Point(r, c));
        while (que.size() > 0)
        {
            Point top = que.remove();
            markVisited(top);

            if (getLeft(top) != null)
            {
                que.add(getLeft(top));
            }
            if (getRight(top) != null)
            {
                que.add(getRight(top));
            }
            if (getUp(top) != null)
            {
                que.add(getUp(top));
            }
            if (getDown(top) != null)
            {
                que.add(getDown(top));
            }
        }

    }

    private Point getDown(Point point)
    {
        if (isValidPoint(point.r+1, point.c))
        {
            return new Point(point.r+1, point.c);
        }
        return null;
    }

    private Point getUp(Point point)
    {
        if (isValidPoint(point.r-1, point.c))
        {
            return new Point(point.r-1, point.c);
        }
        return null;
    }

    private Point getRight(Point point)
    {
        if (isValidPoint(point.r, point.c+1))
        {
            return new Point(point.r, point.c+1);
        }

        return null;
    }

    private Point getLeft(Point top)
    {
        if (isValidPoint(top.r, top.c-1))
        {
            return new Point(top.r, top.c-1);
        }

        return null;
    }

    private boolean isValidPoint(Integer r, Integer c)
    {
        return (r >=0 && r<sea.length)
                && (c >=0 && c<sea[0].length) && (sea[r][c] == VALID);
    }

    private void markVisited(Point top)
    {
        sea[top.r][top.c] = VISITED;
    }

    private class Point
    {
        Integer r, c;
        public Point(Integer r, Integer c)
        {
            this.r = r;
            this.c = c;
        }
    }
}