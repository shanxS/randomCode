// floyd warshal

public class Main
{
    public static void main(String[] er)
    {
        int INF = Integer.MAX_VALUE;
        int[][] ar = { {0,   5,  INF, 10},
                {INF,  0,  3,  INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0} };

        (new FloydWarshal()).find(ar);
    }
}

class FloydWarshal
{
    private boolean[] updated;
    private int[][] ar;
    public void find(int[][] ar)
    {
        this.ar = ar;
        updated = new boolean[ar.length];

        for (int i = 0; i < ar.length; ++i)
        {
            if (!updated[i])
            {
                updateFor(i);
            }
        }

        for (int i=0; i<ar.length;++i)
        {
            for (int j=0; j<ar[0].length; ++j)
            {
                System.out.print(ar[i][j] + " ");
            }
            System.out.println();
        }

    }

    private void updateFor(Integer i)
    {
        if (updated[i])
        {
            return;
        }

        int MAX = Integer.MAX_VALUE;
        updated[i] = true;
        for (int j=0; j<ar.length; ++j)
        {
            if (i!=j && ar[i][j] < MAX)
            {
                updateFor(j);
                for (int k=0;k<ar.length; ++k)
                {
                    if (j!=k && ar[j][k] < MAX)
                    {
                        ar[i][k] = Math.min(ar[i][k], ar[i][j] + ar[j][k]);
                    }
                }
            }
        }
    }
}