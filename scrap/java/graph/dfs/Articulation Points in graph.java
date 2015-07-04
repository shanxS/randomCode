// Articulation Points in graph

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMatrix = {
                {0,1,1,0,0,0,0},
                {1,0,1,1,1,0,1},
                {1,1,0,0,0,0,0},
                {0,1,0,0,0,1,0},
                {0,1,0,0,0,1,0},
                {0,0,0,1,1,0,0},
                {0,1,0,0,0,0,0}
        };

//        Integer[][] adjMatrix = {
//                {0,1,0,0},
//                {0,0,1,0},
//                {0,0,0,1},
//                {0,0,0,0}
//        };

//        Integer[][] adjMatrix = {
//                {0,1,1,1,0},
//                {1,0,1,0,0},
//                {1,1,0,0,0},
//                {1,0,0,0,1},
//                {0,0,0,0,1}
//        };

        ArticulationPointFinder apf = new ArticulationPointFinder();
        apf.printAP(adjMatrix);
    }
}

class ArticulationPointFinder
{
    private Integer[] parent;
    private int[] disc, low;
    private Integer[][] adjList;
    private boolean[] visited, AP;
    private Integer time;

    public void printAP(Integer[][] adjList)
    {
        this.adjList = adjList;
        parent = new Integer[adjList.length];
        disc = new int[adjList.length];
        low = new int[adjList.length];
        visited = new boolean[adjList.length];
        AP = new boolean[adjList.length];
        time = 0;

        for (Integer i = 0; i < adjList.length; ++i)
        {
            if (!visited[i])
            {
                contemplate(i);
            }
        }

        for (Integer i=0; i<AP.length; ++i)
        {
            if (AP[i])
            {
                System.out.println(i);
            }
        }
    }

    private void contemplate(Integer thisNode)
    {
        visited[thisNode] = true;

        disc[thisNode] = time;
        low[thisNode] = time;
        ++time;

        Integer childrenCount = 0;

        Integer[] children = adjList[thisNode];
        for (Integer nextNode=0; nextNode<children.length; ++nextNode)
        {
            if (children[nextNode] == 1)
            {
                if (!visited[nextNode])
                {
                    ++childrenCount;

                    parent[nextNode] = thisNode;
                    contemplate(nextNode);

                    low[thisNode] = Math.min(low[thisNode], low[nextNode]);

                    if ((parent[thisNode] == null && childrenCount > 1))
                    {
                        AP[thisNode] = true;
                    }

                    if ((parent[thisNode] != null && low[nextNode] >= disc[thisNode]))
                    {
                        AP[thisNode] = true;
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
