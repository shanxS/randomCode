// two coloring graph - bipartite

import java.util.ArrayDeque;
import java.util.Queue;

public class Main
{
    private static Integer[][] adjMatrix = {
            {0,1,0,1,0},
            {1,0,1,0,0},
            {0,1,0,0,1},
            {1,0,0,0,1},
            {0,0,1,1,0}
    };

//    private static Integer[][] adjMatrix = {
//            {0,1,0,1,0,0},
//            {1,0,1,0,0,0},
//            {0,1,0,0,0,1},
//            {1,0,0,0,1,0},
//            {0,0,0,1,0,1},
//            {0,0,1,0,1,0}
//    };
    private static final Integer nodeCount = adjMatrix.length;
    private static final Integer ONE = 1;
    private static final Integer TWO = 2;

    private static Integer[] color = new Integer[nodeCount];

    public static void main(String[] er)
    {
        boolean possible = true;
        for (Integer i=0; i<nodeCount && possible; ++i)
        {
            if (color[i] == null)
            {
                if (!bfs(i))
                {
                    possible = false;
                }
            }
        }

        if (possible)
        {
            System.out.print("possible");
        }
        else
        {
            System.out.print("not possible");
        }
    }

    private static boolean bfs(Integer node)
    {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(node);
        color[node] = ONE;

        while (que.size() > 0)
        {
            Integer parentNode = que.remove();
            Integer nextColor = (color[parentNode] == ONE) ? TWO : ONE;

            for (Integer childNode = 0; childNode<nodeCount; ++childNode)
            {
                if (adjMatrix[parentNode][childNode] > 0)
                {
                    if (color[childNode] != null && color[childNode] != nextColor)
                    {
                        return false;
                    }
                    else if (color[childNode] == null)
                    {
                        color[childNode] = nextColor;
                        que.add(childNode);
                    }
                }
            }
        }

        return true;
    }
}
