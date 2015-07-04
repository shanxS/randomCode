// Bridges in a graph

public class Main
{
    public static void main(String[] er)
    {
//        Integer[][] adjMatrix = {
//                {0,1,1,0,0,0,0},
//                {1,0,1,1,1,0,1},
//                {1,1,0,0,0,0,0},
//                {0,1,0,0,0,1,0},
//                {0,1,0,0,0,1,0},
//                {0,0,0,1,1,0,0},
//                {0,1,0,0,0,0,0}
//        };

//        Integer[][] adjMatrix = {
//                {0,1,0,0},
//                {0,0,1,0},
//                {0,0,0,1},
//                {0,0,0,0}
//        };

        Integer[][] adjMatrix = {
                {0,1,1,1,0},
                {1,0,1,0,0},
                {1,1,0,0,0},
                {1,0,0,0,1},
                {0,0,0,0,1}
        };

        BridgeFinder bf = new BridgeFinder();
        bf.printBridges(adjMatrix);
    }
}

class BridgeFinder
{
    private Integer[][] adjMatrix;
    private Integer[] parent;
    private boolean[] visited;
    private int[] low, disc;
    private int time;

    public void printBridges(Integer[][] adjMatrix)
    {
        this.adjMatrix = adjMatrix;
        parent = new Integer[adjMatrix.length];
        visited = new boolean[adjMatrix.length];
        low = new int[adjMatrix.length];
        disc = new int[adjMatrix.length];
        time = 0;

        for (Integer node = 0; node<adjMatrix.length; ++node)
        {
            if (!visited[node])
            {
                contemplate(node);
            }
        }
    }

    private void contemplate(Integer thisNode)
    {
        visited[thisNode] = true;

        low[thisNode] = time;
        disc[thisNode] = time;
        ++time;

        Integer[] children = adjMatrix[thisNode];
        for (Integer nextNode = 0; nextNode < children.length; ++nextNode)
        {
            if (children[nextNode] == 1)
            {
                if (!visited[nextNode])
                {
                    parent[nextNode] = thisNode;
                    contemplate(nextNode);

                    low[thisNode] = Math.min(low[thisNode], low[nextNode]);

                    if (low[nextNode] > disc[thisNode])
                    {
                        System.out.println(thisNode + " " + nextNode);
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