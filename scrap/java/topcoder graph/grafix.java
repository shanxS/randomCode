import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        GrafixFill gf = new GrafixFill();
        for (Integer size : gf.getSizes(new String[]{"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"}))
        {
            System.out.print(size + " ");
        }
    }
}

class GrafixFill
{
    private Integer[][] matrix;
    private final Integer ROWCOUNT, COLCOUNT, FILLED, BLOCKED, AVAILABLE;

    public GrafixFill()
    {
        ROWCOUNT = 400;
        COLCOUNT = 600;
        matrix = new Integer[ROWCOUNT][COLCOUNT];
        FILLED = 1;
        BLOCKED = -1;
        AVAILABLE = 0;
    }

    public List<Integer> getSizes(String[] cord)
    {
        reset();
        for (String s : cord)
        {
            String[] theseCord = s.split(" ");
            Integer topR = Integer.parseInt(theseCord[0]);
            Integer topC = Integer.parseInt(theseCord[1]);
            Integer bottomR = Integer.parseInt(theseCord[2]);
            Integer bottomC = Integer.parseInt(theseCord[3]);

            blockRectangle(topR, topC, bottomR, bottomC);
        }

        List<Integer> sizes = new ArrayList<>();
        for (Integer r=0; r<ROWCOUNT; ++r)
        {
            for (Integer c=0; c<COLCOUNT; ++c)
            {
                if (matrix[r][c] == AVAILABLE)
                {
                    sizes.add(computeSize(r, c));
                }
            }
        }

        return sizes;
    }

    private Integer computeSize(Integer row, Integer col)
    {
        Stack<Location> stack = new Stack<>();
        matrix[row][col] = FILLED;
        stack.push(new Location(row, col));
        Integer size = 1;

        while ( stack.size() > 0 )
        {
            Location thisLocation = stack.pop();
            Integer r = thisLocation.r;
            Integer c = thisLocation.c;

            Location rightLocation = getRight(r,c);
            if (rightLocation != null)
            {
                matrix[rightLocation.r][rightLocation.c] = FILLED;
                stack.push(rightLocation);
                ++size;
            }

            Location leftLocation = getLeft(r, c);
            if (leftLocation != null)
            {
                matrix[leftLocation.r][leftLocation.c] = FILLED;
                stack.push(leftLocation);
                ++size;
            }

            Location topLocation = getTop(r, c);
            if (topLocation != null)
            {
                matrix[topLocation.r][topLocation.c] = FILLED;
                stack.push(topLocation);
                ++size;
            }

            Location bottomLocation = getBottom(r, c);
            if (bottomLocation != null)
            {
                matrix[bottomLocation.r][bottomLocation.c] = FILLED;
                stack.push(bottomLocation);
                ++size;
            }
        }

        return size;
    }

    private Location getBottom(Integer r, Integer c)
    {
        if (isValidLocation(r+1, c) && matrix[r+1][c] == AVAILABLE)
        {
            return new Location(r+1, c);
        }

        return null;
    }

    private Boolean isValidLocation(Integer r, Integer c)
    {
        return (r >=0 && r < ROWCOUNT) && (c >=0 && c < COLCOUNT);
    }

    private Location getTop(Integer r, Integer c)
    {
        if (isValidLocation(r-1, c) && matrix[r-1][c] == AVAILABLE)
        {
            return new Location(r-1, c);
        }

        return null;
    }

    private Location getLeft(Integer r, Integer c)
    {
        if (isValidLocation(r, c-1) && matrix[r][c-1] == AVAILABLE)
        {
            return new Location(r, c-1);
        }

        return null;
    }

    private Location getRight(Integer r, Integer c)
    {
        if (isValidLocation(r, c+1) && matrix[r][c+1] == AVAILABLE)
        {
            return new Location(r, c+1);
        }

        return null;
    }

    private class Location
    {
        public Integer r;
        public Integer c;

        public Location(Integer r, Integer c)
        {
            this.r = r;
            this.c = c;
        }
    }

    private void blockRectangle(Integer topR, Integer topC, Integer bottomR, Integer bottomC)
    {
        for (Integer r=topR; r<bottomR; ++r)
        {
            for (Integer c=topC; c<bottomC; ++c)
            {
                matrix[r][c] = BLOCKED;
            }
        }
    }

    private void reset()
    {
        for (Integer r=0; r<matrix.length; ++r)
        {
            for (Integer c=0; c<matrix[0].length; ++c)
            {
                matrix[r][c] = AVAILABLE;
            }
        }
    }
}