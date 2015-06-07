// code for knight to visit all squares on chess board

import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
        KightTraveller kt = new KightTraveller();

        Set<Position> postions = kt.getPositions(new Position(0, 0));

        System.out.print(kt.getUniquePositions());
    }
}

class KightTraveller
{
    private Set<Position> travelledPosition;
    final private Integer ROW = 8, COL = 8;
    private Integer uniquePositions = Integer.MIN_VALUE;

    public Set<Position> getPositions (Position startPosition)
    {
        travelledPosition = new HashSet<>();

        travelledPosition.add(startPosition);
        if (travelledPosition.size() > uniquePositions)
        {
            uniquePositions = travelledPosition.size();
        }
        for (Position p : getNextPositions(startPosition))
        {
            if (trace(p))
            {
                return travelledPosition;
            }
        }

        return new HashSet<>();
    }

    private boolean trace(Position thisPosition)
    {
        travelledPosition.add(thisPosition);
        if (travelledPosition.size() > uniquePositions)
        {
            uniquePositions = travelledPosition.size();
        }

        for (Position p : getNextPositions(thisPosition))
        {
            if (trace(p))
            {
                return true;
            }
        }

        if (travelledPosition.size() == ROW*COL)
        {
            return true;
        }
        else
        {
            travelledPosition.remove(thisPosition);
            return false;
        }
    }

    public Integer getUniquePositions()
    {
        return uniquePositions;
    }

    private List<Position> getNextPositions(Position startPosition)
    {
        List<Position> positions = new ArrayList<>();

        positions.addAll(fetchUpPositions(startPosition));
        positions.addAll(fetchDownPositions(startPosition));
        positions.addAll(fetchLeftPositions(startPosition));
        positions.addAll(fetchRightPositions(startPosition));

        return positions;
    }

    private List<Position> fetchRightPositions(Position startPosition)
    {
        List<Position> rightPositions = new ArrayList<>();

        if (isValidPoint(startPosition.getR()+1, startPosition.getC()+2))
        {
            rightPositions.add(new Position(startPosition.getR()+1, startPosition.getC()+2));
        }
        if (isValidPoint(startPosition.getR()-1, startPosition.getC()+2))
        {
            rightPositions.add(new Position(startPosition.getR()-1, startPosition.getC()+2));
        }

        return rightPositions;
    }

    private List<Position> fetchLeftPositions(Position startPosition)
    {
        List<Position> leftPositions = new ArrayList<>();

        if (isValidPoint(startPosition.getR()+1, startPosition.getC()-2))
        {
            leftPositions.add(new Position(startPosition.getR()+1, startPosition.getC()-2));
        }
        if (isValidPoint(startPosition.getR()-1, startPosition.getC()-2))
        {
            leftPositions.add(new Position(startPosition.getR()-1, startPosition.getC()-2));
        }

        return leftPositions;
    }

    private List<Position> fetchDownPositions(Position startPosition)
    {
        List<Position> downPositions = new ArrayList<>();

        if (isValidPoint(startPosition.getR()+2, startPosition.getC()+1))
        {
            downPositions.add(new Position(startPosition.getR()+2, startPosition.getC()+1));
        }
        if (isValidPoint(startPosition.getR()+2, startPosition.getC()-1))
        {
            downPositions.add(new Position(startPosition.getR()+2, startPosition.getC()-1));
        }

        return downPositions;
    }

    private List<Position> fetchUpPositions(Position startPosition)
    {
        List<Position> upPositions = new ArrayList<>();

        if (isValidPoint(startPosition.getR()-2, startPosition.getC()+1))
        {
            upPositions.add(new Position(startPosition.getR()-2, startPosition.getC()+1));
        }
        if (isValidPoint(startPosition.getR()-2, startPosition.getC()-1))
        {
            upPositions.add(new Position(startPosition.getR()-2, startPosition.getC()-1));
        }

        return upPositions;
    }

    private boolean isValidPoint(Integer r, Integer c)
    {
        return (r >= 0 && r < ROW && c >= 0 && c < COL);
    }
}

class Position
{
    private Integer r, c;

    public Position(Integer r, Integer c)
    {
        this.r = r;
        this.c = c;
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

    public Integer getR()
    {
        return r;
    }

    public void setR(Integer r)
    {
        this.r = r;
    }

    public Integer getC()
    {
        return c;
    }

    public void setC(Integer c)
    {
        this.c = c;
    }
}