// Biconnected graph

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMatrix = {
                {0,1,1,0,0,0,0},
                {1,0,1,1,1,0,1},
                {1,1,0,0,0,0,1},
                {0,1,0,0,0,1,0},
                {0,1,0,0,0,1,0},
                {0,0,0,1,1,0,0},
                {0,1,1,0,0,0,0}
        };

        BiconnectedTester bt = new BiconnectedTester();
        bt.test(adjMatrix);
    }
}

class BiconnectedTester
{
    private Integer[][] adjMatrix;
    private Integer[] parent;
    private boolean[] visited;
    private int[] low, disc;
    private int time;
    private boolean terminate;

    public void test(Integer[][] adjMatrix)
    {
        this.adjMatrix = adjMatrix;
        parent = new Integer[adjMatrix.length];
        visited = new boolean[adjMatrix.length];
        low = new int[adjMatrix.length];
        disc = new int[adjMatrix.length];
        terminate = false;

        for (Integer node = 0; node<adjMatrix.length; ++node)
        {
            if (!visited[node])
            {
                contemplate(node);
            }
        }

        if (terminate || Arrays.asList(visited).contains(false))
        {
            System.out.print("not binconnected");
        }
        else
        {
            System.out.println("binconnected");
        }
    }

    private void contemplate(Integer thisNode)
    {
        if (terminate)
        {
            return;
        }

        visited[thisNode] = true;

        low[thisNode] = time;
        disc[thisNode] = time;
        ++time;

        Integer[] children = adjMatrix[thisNode];
        Integer childrenCount = 0;
        for (Integer nextNode = 0; nextNode<children.length; ++nextNode)
        {
            if (children[nextNode] == 1)
            {
                if (!visited[nextNode])
                {
                    ++childrenCount;
                    parent[nextNode] = thisNode;
                    contemplate(nextNode);

                    low[thisNode] = Math.min(low[thisNode], low[nextNode]);

                    if (parent[thisNode] == null && childrenCount > 1)
                    {
                        terminate = true;
                    }

                    if (parent[thisNode] != null && low[nextNode] > disc[thisNode])
                    {
                        terminate = true;
                    }
                }
                else
                {
                    if (nextNode != parent[thisNode])
                    {
                        low[thisNode] = Math.min(low[thisNode], disc[nextNode]);
                    }
                }
            }
        }
    }
}