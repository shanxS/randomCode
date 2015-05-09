// 2d matrix has 0s and 1s - given 2 cells find trail of 1s joining them
// r4, q3, set14

import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        Integer[][] array = new Integer[][]{
                {1,1,0,0},
                {1,1,1,1},
                {0,1,0,0},
                {0,1,1,1}
        };

        DFS dfs = new DFS(array);
        Point start = new Point(0,0);
        Point end = new Point(3,3);

        Stack<Point> path = dfs.getPath(start, end);
        if (path == null)
        {
            System.out.print("cant find path");
        }
        else
        {
            for (Point point : path)
            {
                System.out.println(point.getRow() + " " + point.getColumn());
            }
        }
    }
}

class DFS
{
    private Integer[][] map;
    private final Integer visited;
    private final Integer invalid;
    private final Integer valid;
    private Point end;
    private Stack<Point> globalStack;

    public DFS(Integer[][] map)
    {
        this.map = map;
        this.visited = -1;
        this.valid = 1;
        this.invalid = 0;
        this.end = null;
        this.globalStack = null;
    }

    public Stack<Point> getPath(Point start, Point end)
    {
        if (!isValid(start))
        {
            return null;
        }

        this.end = end;

        Stack<Point> stack = new Stack<>();
        stack.push(start);
        mark(start);
        if (search(stack))
        {
            return globalStack;
        }

        return null;
    }

    private void mark(Point point)
    {
        map[point.getRow()][point.getColumn()] = visited;
    }

    private boolean search(Stack<Point> stack)
    {
        Point neighbour = getNextValidNeighbour(stack.peek());

        while(isValid(neighbour))
        {
            Stack<Point> thisStack = (Stack<Point>)stack.clone();
            thisStack.push(neighbour);
            mark(neighbour);

            if (isEnd(neighbour))
            {
                globalStack = (Stack<Point>)thisStack.clone();
                return true;
            }

            if (search(thisStack))
            {
                return true;
            }

            neighbour = getNextValidNeighbour(stack.peek());
        }

        return false;
    }

    private boolean isEnd(Point point)
    {
        if (point != null && point.getColumn() == end.getColumn() && point.getRow() == end.getRow())
        {
            return true;
        }

        return false;
    }

    private Point getNextValidNeighbour(Point point)
    {
        Point up = new Point(point.getRow() - 1,
                             point.getColumn());
        if (isValid(up))
        {
            return up;
        }

        Point right = new Point(point.getRow(),
                point.getColumn() + 1);
        if (isValid(right))
        {
            return right;
        }

        Point down = new Point(point.getRow() + 1,
                point.getColumn());
        if (isValid(down))
        {
            return down;
        }

        Point left = new Point(point.getRow(),
                point.getColumn() - 1);
        if (isValid(down))
        {
            return left;
        }

        return null;
    }

    private boolean isValid(Point point)
    {
        if (point == null || point.getColumn() < 0 || point.getRow() < 0
                || point.getRow() > map.length-1 || point.getColumn() > map[0].length-1)
        {
            return false;
        }

        if (map[point.getRow()][point.getColumn()] == valid)
        {
            return true;
        }

        return false;
    }


}

class Point
{
    private Integer row, column;

    public Point(Integer row, Integer column)
    {
        this.row = row;
        this.column = column;
    }

    public Integer getRow()
    {
        return row;
    }

    public void setRow(Integer row)
    {
        this.row = row;
    }

    public Integer getColumn()
    {
        return column;
    }

    public void setColumn(Integer column)
    {
        this.column = column;
    }
}