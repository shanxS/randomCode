// prim minimum spannig tree

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        PrimMinimumSpaningTree pmst = new PrimMinimumSpaningTree();
        Integer[] parent = pmst.minimumSpanningTree(ar);
        for (Integer i=0; i<ar.length; ++i)
        {
            System.out.println(parent[i] + " is parent of " + i);
        }
    }
}

class PrimMinimumSpaningTree
{
    private Integer nodeCount;
    private Integer[] weight, parent;
    private boolean[] visited;
    private Integer[][] adjMatrix;
    private Integer nextMinNode;

    public Integer[] minimumSpanningTree(Integer[][] aMatrix)
    {
        adjMatrix = aMatrix;
        nodeCount = adjMatrix.length;
        parent = new Integer[nodeCount];
        visited = new boolean[nodeCount];
        weight = new Integer[nodeCount];

        for (Integer i=0; i<nodeCount; ++i)
        {
            weight[i] = Integer.MAX_VALUE;
        }

        weight[0] = 0;
        
        Integer parentNode = getNextMinNode();
        while (parentNode != null)
        {
            visited[parentNode] = true;

            for (Integer childNode = 0; childNode<nodeCount; ++childNode)
            {
                if (!visited[childNode] && adjMatrix[parentNode][childNode] > 0 && weight[childNode] > adjMatrix[parentNode][childNode])
                {
                    weight[childNode] = adjMatrix[parentNode][childNode];
                    parent[childNode] = parentNode;
                }
            }

            parentNode = getNextMinNode();
        }

        return parent;
    }

    private Integer getNextMinNode()
    {
        Integer node = null;

        for (Integer i=0; i<nodeCount; ++i)
        {
            if (!visited[i])
            {
                if (node == null)
                {
                    node = i;
                }
                else if (weight[i] < weight[node])
                {
                    node = i;
                }
            }
        }

        return node;
    }
}