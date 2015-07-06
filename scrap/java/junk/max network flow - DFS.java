import java.util.Stack;

public class Main
{
    private static Integer[][] adjMatrix = {
            {0, 16, 13, 0, 0, 0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
    };

    static private final Integer source = 0;
    static private final Integer target = 5;
    static private final Integer nodeCount = adjMatrix.length;
    static private int[] parent = new int[nodeCount];

    public static void main(String[] er)
    {
        Integer totalFlow = 0;
        while (bfs())
        {
            Integer minFlow = findMinFlow();
            updateFlow(minFlow);
            totalFlow += minFlow;
        }

        System.out.print(totalFlow);
    }

    private static void updateFlow(Integer minFlow)
    {
        Integer index = target;

        while (index != source)
        {
            adjMatrix[parent[index]][index] -= minFlow;
            index = parent[index];
        }
    }

    private static Integer findMinFlow()
    {
        Integer min = Integer.MAX_VALUE;
        Integer index = target;

        while (index != source)
        {
            if (min > adjMatrix[parent[index]][index])
            {
                min = adjMatrix[parent[index]][index];
            }

            index = parent[index];
        }

        return min;
    }

    private static boolean bfs()
    {
        boolean[] visited = new boolean[nodeCount];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        while (stack.size() > 0 && !visited[target])
        {
            Integer thisNode = stack.pop();

            for (Integer i=0; i<nodeCount; ++i)
            {
                if (adjMatrix[thisNode][i] > 0 && !visited[i])
                {
                    stack.push(i);
                    visited[i] = true;
                    parent[i] = thisNode;
                }
            }

        }

        return visited[target];
    }
}