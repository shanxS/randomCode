// Given a matrix of O and X - replace O with X if surrounded by X
// http://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main
{
    private static Integer R, C;
    
    private static Integer[][] matrix= {
            {1,1,1,1,1},
            {1,0,1,0,1},
            {0,0,1,0,1},
            {1,1,1,1,1},
    };
//    private static Integer[][] matrix= {
//            {1,1,1,1,1},
//            {1,0,1,0,1},
//            {0,0,1,0,1},
//            {1,1,1,0,1},
//    };

    private static boolean[][] known;

    public static void main(String[] er)
    {
        R = matrix.length;
        C = matrix[0].length;

        print();
        scan();
        System.out.println("-----------------------");
        print();
    }

    private static void scan()
    {
        known = new boolean[R][C];

        for (Integer r = 1; r <R-1; ++r)
        {
            for (Integer c=1; c<C-1; ++c)
            {
                if (matrix[r][c] == 0 && !known[r][c])
                {
                    BFS(r,c);
                }
            }
        }
    }

    private static void BFS(Integer r, Integer c)
    {
        Queue<Location> bfsQue = new ArrayDeque<>();
        bfsQue.add(new Location(r,c));
        boolean[][] visited = new boolean[R][C];

        Stack<Location> candidateLocations = new Stack<>();

        while (bfsQue.size() > 0)
        {
            Location thisLocation = bfsQue.remove();
            visited[thisLocation.r][thisLocation.c] = true;
            if (known[thisLocation.r][thisLocation.c] || isEdge(thisLocation))
            {
                markKnown(candidateLocations);
                break;
            }
            else
            {
                candidateLocations.push(thisLocation);
            }

            addUp(bfsQue, thisLocation, visited);
            addDown(bfsQue, thisLocation, visited);
            addLeft(bfsQue, thisLocation, visited);
            addRight(bfsQue, thisLocation, visited);
        }

        if (candidateLocations.size() > 0)
        {
            markOne(candidateLocations);
        }
    }

    private static void markOne(Stack<Location> candidateLocations)
    {
        while (candidateLocations.size() > 0)
        {
            Location location = candidateLocations.pop();
            Integer r = location.r;
            Integer c = location.c;

            matrix[r][c] = 1;
        }
    }

    private static void markKnown(Stack<Location> candidateLocations)
    {
        while (candidateLocations.size() > 0)
        {
            Location location = candidateLocations.pop();
            Integer r = location.r;
            Integer c = location.c;

            known[r][c] = true;
        }
    }

    private static boolean isEdge(Location location)
    {
        Integer r = location.r;
        Integer c = location.c;
        return (r==0 || c==0 || r==(R-1) || c==(C-1));
    }

    private static void addRight(Queue<Location> bfsQue, Location thisLocation, boolean[][] visited)
    {
        Integer r = thisLocation.r;
        Integer c = thisLocation.c;

        if (c < C-1 && matrix[r][c+1] == 0 && !visited[r][c+1])
        {
            bfsQue.add(new Location(r, c+1));
        }
    }

    private static void addLeft(Queue<Location> bfsQue, Location thisLocation, boolean[][] visited)
    {
        Integer r = thisLocation.r;
        Integer c = thisLocation.c;

        if (c > 0 && matrix[r][c-1] == 0 && !visited[r][c-1])
        {
            bfsQue.add(new Location(r, c-1));
        }
    }

    private static void addDown(Queue<Location> bfsQue, Location thisLocation, boolean[][] visited)
    {
        Integer r = thisLocation.r;
        Integer c = thisLocation.c;

        if (r < R-1 && matrix[r+1][c] == 0 && !visited[r+1][c])
        {
            bfsQue.add(new Location(r+1, c));
        }
    }

    private static void addUp(Queue<Location> bfsQue, Location thisLocation, boolean[][] visited)
    {
        Integer r = thisLocation.r;
        Integer c = thisLocation.c;

        if (r > 0 && matrix[r-1][c] == 0 && !visited[r-1][c])
        {
            bfsQue.add(new Location(r-1, c));
        }
    }

    private static void print()
    {
        for (Integer r=0; r<R; ++r)
        {
            for (Integer c=0; c<C; ++c)
            {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static class Location
    {
        Integer r, c;

        public Location(Integer r, Integer c)
        {
            this.r = r;
            this.c = c;
        }
    }
}