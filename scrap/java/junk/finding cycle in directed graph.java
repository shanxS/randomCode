// finding cycle in directed graph

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {
                {0,1,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,1},
                {1,0,0,0,1},
                {0,1,0,0,0}
        };

//        Integer[][] ar = {
//                {0,1,1},
//                {0,0,1},
//                {0,0,0}
//        };

        LoopFinder lf = new LoopFinder();
        System.out.print(lf.hasLoop(ar));
    }
}

class LoopFinder
{
    private Integer[][] adjMatrix;
    private Integer nodeCount;
    private boolean[] grayNode, visitedNode;
    public boolean hasLoop(Integer[][] aMatrix)
    {
        this.adjMatrix = aMatrix;
        nodeCount = adjMatrix.length;
        grayNode = new boolean[nodeCount];
        visitedNode = new boolean[nodeCount];

        return dfs(0);
    }

    private boolean dfs(Integer parent)
    {
        grayNode[parent] = true;

        for (Integer childNode = 0; childNode<nodeCount; ++childNode)
        {
            if (adjMatrix[parent][childNode] == 1)
            {
                if (grayNode[childNode] || (!visitedNode[childNode] && dfs(childNode)))
                {
                    return true;
                }
            }
        }

        grayNode[parent] = false;
        visitedNode[parent] = true;

        return false;
    }
}