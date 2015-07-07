import java.util.Stack;

public class Main
{
    private static final Integer[][] adjMatrix = {
            {0,1,0,0},
            {0,0,1,0},
            {0,0,0,0},
            {0,2,0,0},
    };

//    private static final Integer[][] adjMatrix = {
//            {0,1,1,0},
//            {0,0,1,0},
//            {1,0,0,1},
//            {0,0,0,1}
//    };
    private static final Integer nodeCount = adjMatrix.length;

    private static boolean[] visited = new boolean[nodeCount];
    private static boolean noLoopFound;

    public static void main(String[] er)
    {
        noLoopFound = true;

        for (Integer nodeName=0; nodeName<nodeCount; ++nodeName)
        {
            if (!noLoopFound)
            {
                break;
            }
            else if (!visited[nodeName])
            {
                DFS(nodeName);
            }
        }

        if (noLoopFound)
        {
            System.out.print("no loop found");
        }
        else
        {
            System.out.print("loop found");
        }

    }

    private static void DFS(Integer startNode)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        while (stack.size() > 0)
        {
            Integer parentNode = stack.pop();
            visited[parentNode] = true;
            for (Integer childNode = 0; childNode<nodeCount; ++childNode)
            {
                if (stack.contains(childNode))
                {
                    noLoopFound = false;
                    break;
                }
                else if (adjMatrix[parentNode][childNode] == 1 && !visited[childNode])
                {
                    stack.push(childNode);
                }
            }
        }
    }
}