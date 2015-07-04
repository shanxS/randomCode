// Strongly Connected components

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[][] adjMatrix = {
//                {0,1,1,1,0},
//                {1,0,1,0,0},
//                {1,1,0,0,0},
//                {1,0,0,0,1},
//                {0,0,0,1,0}
//        };

        Integer[][] adjMatrix = {
                {0,1,1,0,0,0,0},
                {1,0,1,1,1,0,1},
                {1,1,0,0,0,0,0},
                {0,1,0,0,0,1,0},
                {0,1,0,0,0,1,0},
                {0,0,0,1,1,0,0},
                {0,1,0,0,0,0,0},
        };

        SCC ssc = new SCC();
        ssc.printSCC(adjMatrix);
    }
}

class SCC
{
    private Integer[][] adjMatrix;
    private Integer[] disc, low, parent;
    private Stack<Integer> stack;
    private Integer time;

    public void printSCC(Integer[][] adjMat)
    {
        this.adjMatrix = adjMat;
        disc = new Integer[adjMat.length];
        low = new Integer[adjMat.length];
        parent = new Integer[adjMat.length];
        stack = new Stack<>();
        time = 0;

        for (Integer node = 0; node<adjMat.length; ++node)
        {
            if (disc[node] == null)
            {
                contemplate(node);
            }
        }

    }

    private void contemplate(Integer thisNode)
    {
        stack.push(thisNode);
        disc[thisNode] = time;
        low[thisNode] = time;
        ++time;

        Integer[] children = adjMatrix[thisNode];
        for (Integer nextChild = 0; nextChild<children.length; ++ nextChild)
        {
            if (children[nextChild] != 1)
            {
                continue;
            }

            parent[nextChild] = thisNode;

            if (disc[nextChild] == null)
            {
                contemplate(nextChild);
                low[thisNode] = Math.min(low[thisNode], low[nextChild]);
            }
            else
            {
                if (stack.contains(nextChild) && parent[thisNode] != nextChild)
                {
                    low[thisNode] = Math.min(low[thisNode], disc[nextChild]);
                }
            }
        }

        if (low[thisNode] == disc[thisNode])
        {
            System.out.println("popping for " + thisNode);
            printDisc();
            printLow();


            while (stack.peek() != thisNode)
            {
                System.out.print(stack.pop() + " ");
            }
            System.out.println(stack.pop() + " ");
            System.out.println("-------------------");
        }
    }

    private void printLow()
    {
        System.out.print("low:  ");
        for (Integer i=0; i<low.length; ++i)
        {
            System.out.print(low[i] + " ");
        }
        System.out.println();
    }

    private void printDisc()
    {
        System.out.print("disc: ");
        for (Integer i=0; i<disc.length; ++i)
        {
            System.out.print(disc[i] + " ");
        }
        System.out.println();
    }
}































