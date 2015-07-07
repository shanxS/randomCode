// longest path in DAG

public class Main
{
    private static Integer[][] adjMatrix = {
            {0,1,0,0,0,0},
            {0,0,1,0,0,0},
            {0,0,0,0,0,0},
            {0,1,0,0,0,0},
            {0,0,0,1,0,0},
            {0,0,0,0,1,0}
    };

//    private static Integer[][] adjMatrix = {
//            {0,1,0,0},
//            {0,0,1,0},
//            {0,0,0,0},
//            {0,1,0,0},
//    };
    private final static Integer nodeCount = adjMatrix.length;
    private static boolean[] visited = new boolean[nodeCount];
    private static Integer[] nodeDepth = new Integer[nodeCount];
    private static Integer maxDepth = Integer.MIN_VALUE;

    public static void main(String[] er)
    {
        for (Integer node = 0; node<nodeCount; ++node)
        {
            if (!visited[node])
            {
                dfs(node);
            }

            if (maxDepth < nodeDepth[node])
            {
                maxDepth = nodeDepth[node];
            }
        }

        System.out.print(maxDepth);
    }

    private static void dfs(Integer node)
    {

        Integer thisNodeDepth = 1;
        for (Integer childNode = 0; childNode<nodeCount; ++childNode)
        {
            if (adjMatrix[node][childNode] > 0)
            {
                if (!visited[childNode])
                {
                    dfs(childNode);
                }

                if (nodeDepth[childNode]+1 > thisNodeDepth)
                {
                    thisNodeDepth = nodeDepth[childNode]+1;
                }
            }
        }

        nodeDepth[node] = thisNodeDepth;
        visited[node] = true;
    }
}