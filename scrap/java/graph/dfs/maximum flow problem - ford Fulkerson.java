// maximum flow problem - ford Fulkerson

import java.util.ArrayDeque;
import java.util.Queue;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMat = new Integer[][]{ {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        FordFulkerson ff = new FordFulkerson();
        System.out.print(ff.findFlow(adjMat, 0, 5));
    }
}

class FordFulkerson
{
    private int[][] rGraph;
    private Integer nodeCount, maxFlow, source, target;
    private Integer[] path;

    public Integer findFlow(Integer[][] adjMat, Integer src, Integer tar)
    {
        source = src;
        target = tar;
        nodeCount = adjMat.length;
        path = new Integer[nodeCount];
        maxFlow = 0;

        rGraph = new int[adjMat.length][adjMat.length];
        for (Integer r=0; r<adjMat.length; ++r)
        {
            for (Integer c = 0; c < adjMat.length; ++c)
            {
                rGraph[r][c] = adjMat[r][c];
            }
        }

        while (bfs())
        {
            Integer thisFlow = Integer.MAX_VALUE;

            // ////////////////////////////////////////////
            // ////////////////////////////////////////////
            // CATCH IS IN HOW LOOP IS EXECUTED
            // ////////////////////////////////////////////
            for (Integer node=target; node != source; node = path[node])
            {
                Integer parent = path[node];
                thisFlow = Math.min(thisFlow, rGraph[parent][node]);
            }

            // ////////////////////////////////////////////
            // ////////////////////////////////////////////
            // CATCH IS IN HOW LOOP IS EXECUTED
            // ////////////////////////////////////////////
            for (Integer childNode=target; childNode != source; childNode = path[childNode])
            {
                Integer parentNode = path[childNode];
                rGraph[parentNode][childNode] -= thisFlow;
                rGraph[childNode][parentNode] += thisFlow;
            }

            maxFlow += thisFlow;
        }

        return maxFlow;

    }

    private boolean bfs()
    {
        boolean[] visited = new boolean[nodeCount];

        Queue<Integer> que = new ArrayDeque<>();
        que.add(source);
        while (que.size() > 0)
        {
            Integer parent = que.remove();

            visited[parent] = true;

            int[] children = rGraph[parent];
            for (Integer child=0; child<children.length; ++child)
            {
                if (!visited[child] && children[child] > 0 )
                {
                    path[child] = parent;
                    que.add(child);
                }
            }
        }

        return visited[target];
    }
}



















