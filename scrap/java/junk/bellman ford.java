// bellman ford

import java.util.ArrayDeque;
import java.util.Queue;

public class Main
{
    private static final Integer MAX = Integer.MAX_VALUE;
    private static Integer[][] ar = {
            {MAX, -1, 4, MAX, MAX},
            {MAX, MAX, 3, 2, 2},
            {MAX, MAX, MAX, MAX, MAX},
            {MAX, 1, 5, MAX, MAX},
            {MAX, MAX, MAX, -3, MAX}
    };

    public static void main(String[] er)
    {
        BellmanFord bf = new BellmanFord();
        for (Integer i : bf.find(ar, 0))
        {
            System.out.print(i + " ");
        }
    }
}

class BellmanFord
{
    private Integer[][] ar;
    private Integer dist[];
    public Integer[] find(Integer[][] er, Integer src)
    {
        this.ar = er;
        this.dist = new Integer[ar.length];
        for (Integer i=0; i<dist.length; ++i)
        {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        Queue<Integer> que = new ArrayDeque<>();
        que.add(src);
        while (que.size() > 0)
        {
            Integer thisNode = que.remove();

            for (Integer childNode = 0; childNode<ar.length; ++childNode)
            {
                if (ar[thisNode][childNode] != Integer.MAX_VALUE
                        && (dist[thisNode] + ar[thisNode][childNode]) < dist[childNode])
                {
                    dist[childNode] = dist[thisNode] + ar[thisNode][childNode];
                    que.add(childNode);
                }
            }
        }

        return dist;
    }
}