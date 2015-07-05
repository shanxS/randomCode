// Dijkstra algo

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMatrix = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 0, 10, 0, 2, 0, 0},
                {0, 0, 0, 14, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

//        Integer[][] adjMatrix = {
//                {0,1,2,10},
//                {1,0,0,1},
//                {2,0,0,3},
//                {10,1,3,0}
//        };

        DijekstraMinPath dp = new DijekstraMinPath();
        Integer[] parent = dp.findMinPath(adjMatrix, 0);
        for (Integer i=0; i<parent.length; ++i)
        {
            System.out.println(parent[i] + " is parent to " + i);
        }
    }
}

class DijekstraMinPath
{
    private Integer[] parent, distance;
    private boolean[] used;

    public Integer[] findMinPath(Integer[][] adjMatrix, Integer source)
    {
        Integer nodeCount = adjMatrix.length;
        parent = new Integer[nodeCount];

        distance = new Integer[nodeCount];
        for (Integer i=0; i<nodeCount; ++i)
        {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        parent[source] = -1;

        used = new boolean[nodeCount];

        for (Integer count = 0; count<nodeCount-1; ++count)
        {
            Integer parentNode = findMinDistanceNode();

            used[parentNode] = true;

            for (Integer childNode=0; childNode<nodeCount; ++childNode)
            {
                if (adjMatrix[parentNode][childNode] > 0 && used[childNode] == false
                        && distance[childNode] > adjMatrix[parentNode][childNode] + distance[parentNode])
                {
                    distance[childNode] = adjMatrix[parentNode][childNode] + distance[parentNode];
                    parent[childNode] = parentNode;
                }
            }
        }

        return parent;
    }

    private Integer findMinDistanceNode()
    {
        Integer minIndex = null;
        Integer minValue = Integer.MAX_VALUE;

        for (Integer node = 0; node<distance.length; ++node)
        {
            if (!used[node] && minValue > distance[node])
            {
                minIndex = node;
                minValue = distance[node];
            }
        }

        return minIndex;
    }
}