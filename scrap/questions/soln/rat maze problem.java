// rat maze problem

import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}};

        RatHelper rh = new RatHelper();
        rh.RatHelper(maze, new Position(0,0), new Position(3,3)).stream().forEach(x -> System.out.println(x.getR() + " " + x.getC()));
    }
}

class RatHelper
{
    private Integer[][] maze;
    private Set<Position> path;
    private Integer ROW, COL;
    private Position destination;
    private Integer VALID = 1;

    public Set<Position> RatHelper(Integer[][] maze, Position source, Position destination)
    {
        this.maze = maze;
        this.destination = destination;
        ROW = maze.length;
        COL = maze[0].length;
        this.path = new LinkedHashSet<>();
        find (source);

        return path;
    }

    private Boolean find(Position startPosition)
    {
        path.add(startPosition);
        if (startPosition.equals(destination))
        {
            return true;
        }

        for (Position p : getNextPositions(startPosition))
        {
            if (find(p))
            {
                return true;
            }
        }

        return false;
    }

    private List<Position> getNextPositions(Position startPosition)
    {
        List<Position> positions = new ArrayList<>();

        if (isValidPosition(startPosition.getR(), startPosition.getC()+1))
        {
            positions.add(new Position(startPosition.getR(), startPosition.getC()+1));
        }
        if (isValidPosition(startPosition.getR()+1, startPosition.getC()))
        {
            positions.add(new Position(startPosition.getR()+1, startPosition.getC()));
        }

        return positions;
    }

    private boolean isValidPosition(Integer r, Integer c)
    {
        return (r >= 0 && r < ROW
                && c >= 0 && c < COL
                && maze[r][c] == VALID);
    }
}

class Position
{
    private Integer r, c;

    public Position(Integer r, Integer c)
    {
        this.c = c;
        this.r = r;
    }

    public Integer getR()
    {
        return r;
    }

    public Integer getC()
    {
        return c;
    }

    @Override
    public int hashCode()
    {
        int result = 17;
        result = 31 * result + r;
        result = 31 * result + c;
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        Position otherPosition = (Position) o;

        return (r == otherPosition.getR() && c == otherPosition.getC());
    }
}