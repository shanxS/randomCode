// sudoku solver
// code question: 59

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] grid = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        SudokuSolver ss = new SudokuSolver();
        Integer[][] solved  = ss.solve(grid);

        Integer[][] test = {
                {3, 1, 6, 5, 7, 8, 4, 9, 2},
                {5, 2, 9, 1, 3, 4, 7, 6, 8},
                {4, 8, 7, 6, 2, 9, 5, 3, 1},
                {2, 6, 3, 4, 1, 5, 9, 8, 7},
                {9, 7, 4, 8, 6, 3, 1, 2, 5},
                {8, 5, 1, 7, 9, 2, 6, 4, 3},
                {1, 3, 8, 9, 4, 7, 2, 5, 6},
                {6, 9, 2, 3, 5, 1, 8, 7, 4},
                {7, 4, 5, 2, 8, 6, 3, 1, 9}};

        boolean found = true;
        for(Integer r=0; r<solved.length; ++r)
        {
            for(Integer c=0; c<solved[0].length; ++c)
            {
                if (test[r][c] != solved[r][c])
                {
                    found = false;
                    break;
                }
            }

        }

        System.out.print(found);
    }
}

class SudokuSolver
{
    private Integer[][] grid;
    final private Integer INVALID = 0;

    public Integer[][] solve(Integer[][] grid)
    {
        this.grid = grid;

        Map<Integer, Integer> numberMap = initialiseNumberMap();
        List<Position> unfilledPositions = initialiseUnfilledPosition();

        Position thisPosition = unfilledPositions.get(0);
        List<Position> newUnfillledPositions = new ArrayList<>(unfilledPositions);
        newUnfillledPositions.remove(thisPosition);

        for (Map.Entry<Integer, Integer> entry : numberMap.entrySet())
        {
            if (entry.getValue() > 0)
            {
                if (test(thisPosition, entry.getKey()))
                {
                    grid[thisPosition.getR()][thisPosition.getC()] = entry.getKey();

                    Map<Integer, Integer> newNumberMap = new HashMap<>(numberMap);
                    reduce(newNumberMap, entry.getKey());

                    if (contemplate(newUnfillledPositions, newNumberMap))
                    {
                        return grid;
                    }

                    grid[thisPosition.getR()][thisPosition.getC()] = INVALID;
                }
            }
        }

        return null;
    }

    private boolean contemplate(List<Position> unfillledPositions, Map<Integer, Integer> numberMap)
    {
        if (unfillledPositions.size() == 0)
        {
            return true;
        }

        Position thisPosition = unfillledPositions.get(0);
        List<Position> newUnfilledPosition = new ArrayList<>(unfillledPositions);
        newUnfilledPosition.remove(thisPosition);

        for (Map.Entry<Integer, Integer> entry : numberMap.entrySet())
        {
//            System.out.print("ananlyse " + thisPosition.getR() + " " + thisPosition.getC() + " " + entry.getKey());

            if (entry.getValue() > 0)
            {
                if (test(thisPosition, entry.getKey()))
                {
                    grid[thisPosition.getR()][thisPosition.getC()] = entry.getKey();

                    Map<Integer, Integer> newNumberMap = new HashMap<>(numberMap);
                    reduce(newNumberMap, entry.getKey());

                    if (contemplate(newUnfilledPosition, newNumberMap))
                    {
                        return true;
                    }

                    grid[thisPosition.getR()][thisPosition.getC()] = INVALID;
                }
            }
        }

        return false;
    }

    private boolean test(Position position, Integer key)
    {
        if (testRow(position, key)
                && testCol(position, key)
                && testBlock(position, key))
        {
            return true;
        }
        else
        {

            return false;
        }

                //;
    }

    private boolean testCol(Position position, Integer key)
    {
        for (Integer r=0; r<9; ++r)
        {
            if (grid[r][position.getC()] == key)
            {
                return false;
            }
        }

        return true;
    }

    private boolean testRow(Position position, Integer key)
    {
        for (Integer c=0; c<9; ++c)
        {
            if (grid[position.getR()][c] == key)
            {
                return false;
            }
        }

        return true;
    }

    private boolean testBlock(Position position, Integer key)
    {
        Integer startR, endR;
        if (position.getR() <= 2)
        {
            startR = 0;
            endR = 2;
        }
        else if (position.getR() >=3 && position.getR()<=5)
        {
            startR = 3;
            endR = 5;
        }
        else
        {
            startR = 6;
            endR = 8;
        }

        Integer startC, endC;
        if (position.getC() <=2)
        {
            startC = 0;
            endC = 2;
        }
        else if (position.getC() >= 3 && position.getC() <= 5)
        {
            startC = 3;
            endC = 5;
        }
        else
        {
            startC = 6;
            endC = 8;
        }

        for (Integer r=startR; r<=endR; ++r)
        {
            for (Integer c=startC; c<=endC; ++c)
            {
                if (grid[r][c] == key)
                {
                    return false;
                }
            }
        }

        return true;
    }

    private List<Position> initialiseUnfilledPosition()
    {
        List<Position> unfilledPosition = new ArrayList<>();

        for (Integer r=0; r<grid.length; ++r)
        {
            for (Integer c = 0; c < grid[0].length; ++c)
            {
                if (grid[r][c] == INVALID)
                {
                    unfilledPosition.add(new Position(r, c));
                }
            }
        }

        return unfilledPosition;
    }

    private Map<Integer, Integer> initialiseNumberMap()
    {
        Map<Integer, Integer> numberMap = new HashMap<>();
        for (Integer i=1; i<=9; ++i)
        {
            numberMap.put(i, 9);
        }

        for (Integer r=0; r<grid.length; ++r)
        {
            for (Integer c=0; c<grid[0].length; ++c)
            {
                if (grid[r][c] != INVALID)
                {
                    reduce(numberMap, grid[r][c]);
                }
            }
        }

        return numberMap;
    }

    private void reduce(Map<Integer, Integer> numberMap, Integer value)
    {
        Integer previousValue = numberMap.get(value);
        if (previousValue != 0)
        {
            numberMap.put(value, previousValue-1);
        }
    }

    public void printGrid()
    {
        for (Integer r=0; r<grid.length; ++r)
        {
            for (Integer c=0; c<grid[0].length; ++c)
            {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

}

class Position
{
    private Integer r,c;

    public Position(Integer r, Integer c)
    {
        this.r = r;
        this.c = c;
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position otherPosition = (Position) o;

        return (r == otherPosition.getR() && c == otherPosition.getC());
    }

    @Override
    public int hashCode()
    {
        int result = r != null ? r.hashCode() : 0;
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}