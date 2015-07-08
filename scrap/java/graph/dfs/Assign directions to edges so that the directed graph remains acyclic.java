// Assign directions to edges so that the directed graph remains acyclic

import java.util.Arrays;

public class Main
{
    private static Integer[][] adjMatrix = {
            {0,1,-1,-1,0,1},
            {0,0,1,1,1,0},
            {-1,0,0,1,1,0},
            {-1,0,0,0,1,0},
            {0,0,0,0,0,-1},
            {0,1,1,0,-1,0}
    };
    private final static Integer nodeCount = adjMatrix.length;
    private static boolean[] visited, inProcess;

    public static void main(String[] er)
    {
        visited = new boolean[nodeCount];
        inProcess = new boolean[nodeCount];

        for (Integer node=0; node<nodeCount; ++node)
        {
            if (!visited[node])
            {
                dfs(node);
            }
        }

        for (Integer r=0; r<nodeCount; ++r)
        {
            for (Integer c = 0; c < nodeCount; ++c)
            {
                System.out.print(adjMatrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static void dfs(Integer parent)
    {
        inProcess[parent] = true;

        for (Integer child=0; child<nodeCount; ++child)
        {
            if (adjMatrix[parent][child] == 1 && !visited[child])
            {
                dfs(child);
            }
            else if (adjMatrix[parent][child] == -1)
            {
                adjMatrix[parent][child] = 1;
                adjMatrix[child][parent] = 0;
                boolean[] tempInProcess = Arrays.copyOf(inProcess, nodeCount);
                boolean[] tempVisited = Arrays.copyOf(visited, nodeCount);
                if (!testDfs(child, tempInProcess, tempVisited))
                {
                    adjMatrix[parent][child] = 0;
                    adjMatrix[child][parent] = 1;

                    dfs(child);
                }
                else
                {
                    inProcess = tempInProcess;
                    visited = tempVisited;
                }
            }
        }

        inProcess[parent] = false;
        visited[parent] = true;
    }

    private static boolean testDfs(Integer parent, boolean[] tempInProcess, boolean[] tempVisited)
    {
        tempInProcess[parent] = true;

        for (Integer child=0; child<nodeCount; ++child)
        {
            if (adjMatrix[parent][child] == 1 && !tempVisited[child])
            {
                if (tempInProcess[child] || (!testDfs(child, tempInProcess, tempVisited)))
                {
                    return false;
                }
            }
            else if (adjMatrix[parent][child] == -1)
            {
                if (tempInProcess[child])
                {
                    adjMatrix[parent][child] = 0;
                    adjMatrix[child][parent] = 1;
                    if ((!testDfs(child, tempInProcess, tempVisited)))
                    {
                        return false;
                    }
                }
                else
                {
                    adjMatrix[parent][child] = 1;
                    adjMatrix[child][parent] = 0;
                    boolean[] temp2InProcess = Arrays.copyOf(tempInProcess, nodeCount);
                    boolean[] temp2Visited = Arrays.copyOf(tempVisited, nodeCount);
                    if (!testDfs(child, temp2InProcess, temp2Visited))
                    {
                        adjMatrix[parent][child] = 0;
                        adjMatrix[child][parent] = 1;

                        if (!testDfs(child, temp2InProcess, temp2Visited))
                        {
                            return false;
                        }
                    }
                    else
                    {
                        tempInProcess = temp2InProcess;
                        tempVisited = temp2Visited;
                    }
                }
            }
        }

        tempInProcess[parent] = false;
        tempVisited[parent] = true;
        return true;
    }
}