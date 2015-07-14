// floyd warshall

public class Main
{
    public static void main(String[] er)
    {
        final Integer INF = Integer.MAX_VALUE;
        Integer[][] a = { {0,   5,  INF, 10},
                {INF, 0,   3, INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
        };

        final Integer nodeCount = a.length;

        for (Integer i=0; i<nodeCount; ++i)
        {
            Integer j=(i+1)%nodeCount;
            while (j!= i)
            {
                if (a[i][j] != INF)
                {
                    Integer k=(j+1)%nodeCount;
                    while (k!= j)
                    {
                        if (a[j][k] != INF)
                        {
                            a[i][k] = Math.min(a[i][k], (a[j][k] + a[i][j]));
                        }

                        k=(k+1)%nodeCount;
                    }
                }
                j=(j+1)%nodeCount;
            }
        }

        for (Integer i=0; i<nodeCount; ++i)
        {
            for (Integer j=0; j<nodeCount; ++j)
            {
                System.out.print(a[i][j] + " ");
            }

            System.out.println();
        }
    }
}