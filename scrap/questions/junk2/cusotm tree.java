cusotm tree

import java.util.HashSet;
import java.util.Set;

public class Main
{
    public static void main(String[] er)
    {
        int[][] ar = {
                {0, 1},
                {0,10},
                {1, 2},
                {2, 3},
                {3, 4},
                {2, 5},
                {6, 7},
                {7, 8},
                {8, 9}
        };

        (new CustomTree()).print(ar, 11);
    }
}

class CustomTree
{
    private int[][] adj;
    private Set<Integer> heads;
    private String printer;

    public void print(int[][] relations, int nodeCount)
    {
        populateAdj(relations, nodeCount);

        heads = new HashSet<>();
        fetchHeads();

        printer = "";
        for (Integer i : heads)
        {
            printFor(i, 0);
        }

        System.out.print(printer);
    }

    private void printFor(Integer node, int depth)
    {
        printer += "\n";
        for (int i=0; i<depth; ++i)
        {
            printer += " ";
        }

        printer += node;
        for (int c=0; c<adj.length; ++c)
        {
            if (adj[node][c] == 1)
            {
                printFor(c, depth+1);
            }
        }
    }

    private void fetchHeads()
    {
        for (int c=0; c<adj.length; ++c)
        {
            if (isHead(c))
            {
                heads.add(c);
            }
        }
    }

    private boolean isHead(int c)
    {
        for (int r=0; r<adj.length; ++r)
        {
            if (adj[r][c] == 1)
            {
                return false;
            }
        }

        return true;
    }

    private void populateAdj(int[][] relations, int nodeCount)
    {
        adj = new int[nodeCount][nodeCount];

        for (int p=0; p<relations.length; ++p)
        {
//            if (relations[p][1] == 1)
            {
                System.out.println("connecting " + relations[p][0] + " " + relations[p][1]);
                adj[relations[p][0]][relations[p][1]]=1;
            }
        }
    }
}