// some dumb shit

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main
{
    private static Integer[][] ar = {
            {1, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 1},
            {1, 1, 1, 0, 0, 1},
            {0, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 0},
    };
    private static final Integer R=ar.length;
    private static final Integer C=ar[0].length;

    private static boolean[][] valid = new boolean[R][C];

    public static void main (String[] er)
    {
        for (Integer r=0; r<R; ++r)
        {
            for (Integer c=0; c<C; ++c)
            {
                if (ar[r][c] == 0 && !valid[r][c])
                {
                    bfs(r, c);
                }
            }
        }

        for (Integer r=0; r<R; ++r)
        {
            for (Integer c = 0; c < C; ++c)
            {
                System.out.print(ar[r][c] + " ");
            }

            System.out.println();
        }

    }

    private static void bfs(Integer r, Integer c)
    {
        Stack<Location> old = new Stack<>();
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(new Location(r,c));
        boolean isValid = false;

        while (queue.size() > 0)
        {
            Location thisLoc = queue.remove();

            if (isAtEdge(thisLoc) || valid[thisLoc.r][thisLoc.c])
            {
                isValid = true;
                break;
            }

            old.push(thisLoc);
            addNeighbors(thisLoc, queue, old);
        }

        while (old.size() > 0)
        {
            Location thisLoc = old.pop();
            Integer thisR = thisLoc.r;
            Integer thisC = thisLoc.c;
            if (isValid)
            {
                valid[thisR][thisC] = true;
            }
            else
            {
                ar[thisR][thisC] = 1;
            }
        }
    }

    private static void addNeighbors(Location thisLoc, Queue<Location> queue, Stack<Location> stack)
    {
        Integer r=thisLoc.r;
        Integer c=thisLoc.c;

        if (r+1 < R && ar[r+1][c] == 0  && !queue.contains(new Location(r+1, c))
                && !stack.contains(new Location(r+1, c)))
        {
            queue.add(new Location(r+1, c));
        }

        if (r-1 >= 0 && ar[r-1][c] == 0 && !queue.contains(new Location(r-1, c))
                && !stack.contains(new Location(r-1, c)))
        {
            queue.add(new Location(r-1, c));
        }

        if (c+1 < C && ar[r][c+1] == 0 && !queue.contains(new Location(r, c+1))
                && !stack.contains(new Location(r, c+1)))
        {
            queue.add(new Location(r, c+1));
        }

        if (c-1 >= 0 && ar[r][c-1] == 0 && !queue.contains(new Location(r, c-1))
                && !stack.contains(new Location(r, c-1)))
        {
            queue.add(new Location(r, c-1));
        }
    }

    private static boolean isAtEdge(Location thisLoc)
    {
        return thisLoc.r == 0 || thisLoc.r == R-1 || thisLoc.c == 0 || thisLoc.c == C-1;
    }

    private static class Location
    {
        Integer r, c;
        public Location(Integer r, Integer c)
        {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o)
        {
            Location otherLocation = (Location) o;
            return otherLocation.r == r && otherLocation.c==c;
        }

    }
}