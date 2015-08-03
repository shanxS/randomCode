// Maximum size square sub-matrix with all 1s

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {{0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}};

        (new Sub()).find(ar);
    }
}

class Sub
{
    private  Integer max, maxR, maxC;
    private Integer[][] mat;

    public void find(Integer[][] ar)
    {
        mat = ar;
        max = Integer.MIN_VALUE;
        for (Integer r=1; r<ar.length; ++r)
        {
            for (Integer c=1; c<ar[0].length; ++c)
            {
                if (shouldIncrese(r, c))
                {
                    ar[r][c] += Math.min(mat[r-1][c],
                            Math.min(mat[r][c-1], mat[r-1][c-1]));

                    if (ar[r][c] > max)
                    {
                        max = ar[r][c];
                        maxC = c;
                        maxR = r;
                    }
                }
            }
        }

        System.out.print(maxR + " " + maxC + " " + max);
    }

    private boolean shouldIncrese(Integer r, Integer c)
    {
        return (mat[r][c] > 0
        && mat[r-1][c] > 0
        && mat[r][c-1] > 0
        && mat[r-1][c-1] > 0);
    }
}