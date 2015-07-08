// Assign directions to edges so that the directed graph remains acyclic using topo sort

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMatrix = {
                {0,1,-1,-1,0,1},
                {0,0,1,1,1,0},
                {-1,0,0,1,1,0},
                {-1,0,0,0,1,0},
                {0,0,0,0,0,-1},
                {0,1,1,0,-1,0}
        };

        TopoSort ts = new TopoSort();
        List<Integer> sorted = ts.toposort(adjMatrix);

        Integer nodeCount = adjMatrix.length;
        for(Integer parent=0; parent<nodeCount; ++parent)
        {
            for (Integer child=0; child<nodeCount; ++child)
            {
                if (adjMatrix[parent][child] == -1)
                {
                    if (sorted.indexOf(parent) > sorted.indexOf(child))
                    {
                        adjMatrix[parent][child] = 0;
                        adjMatrix[child][parent] = 1;
                    }
                    else
                    {
                        adjMatrix[parent][child] = 1;
                        adjMatrix[child][parent] = 0;
                    }
                }
            }
        }

        for(Integer parent=0; parent<nodeCount; ++parent)
        {
            for (Integer child = 0; child < nodeCount; ++child)
            {
                System.out.print(adjMatrix[parent][child] + " ");
            }
            System.out.println();
        }
    }
}

class TopoSort
{
    private boolean[] visited;
    private Integer nodeCount;
    private List<Integer> sorted;
    private Integer[][] adjMatrix;

    public List<Integer> toposort(Integer[][] aMatrix)
    {
        adjMatrix = aMatrix;
        nodeCount = adjMatrix.length;
        visited = new boolean[nodeCount];
        sorted = new ArrayList<>();

        for (Integer node=0; node<nodeCount; ++node)
        {
            if (!visited[node])
            {
                dfs(node);
            }
        }

        return sorted;
    }

    private void dfs(Integer parent)
    {
        visited[parent] = true;

        for (Integer child = 0; child<nodeCount; ++child)
        {
            if (!visited[parent] && adjMatrix[parent][child] == 1)
            {
                dfs(child);
            }
        }

        sorted.add(parent);
    }
}