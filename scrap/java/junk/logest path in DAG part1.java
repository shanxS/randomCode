// logest path in DAG part1

import java.util.Stack;

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
    private static Integer maxDepth;

    public static void main(String[] er)
    {
        maxDepth = Integer.MIN_VALUE;

        for (Integer node = 0; node<nodeCount; ++node)
        {
            if (!visited[node])
            {
                dfs(node);
            }
        }

        System.out.print(maxDepth);
    }

    private static void dfs(Integer node)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);

        boolean[] localVisited = new boolean[nodeCount];
        Integer depth = 1;
        while (stack.size() > 0)
        {
            Integer parentNode = stack.pop();
            visited[parentNode] = true;
            localVisited[parentNode] = true;

            for (Integer childNode = 0; childNode<nodeCount; ++childNode)
            {
                if (!localVisited[childNode] && adjMatrix[parentNode][childNode] > 0)
                {
                    stack.push(childNode);
                    ++depth;
                }
            }
        }

        if (depth > maxDepth)
        {
            maxDepth = depth;
        }
    }
}