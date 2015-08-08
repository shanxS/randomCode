// max 1 matrix

public class Main
{
    public static void main(String[] er)
    {
        int[][] ar = {
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}};
        (new MaxMatrix()).compute(ar);
    }
}

class MaxMatrix
{
    private int[][] ar;
    public void compute(int[][] ar)
    {
        this.ar = ar;

        int max = 0;
        int maxR = -1, maxC = -1;

        for (int r=1; r<ar.length; ++r)
        {
            for (int c=1; c<ar[0].length; ++c)
            {
                if (ar[r][c] > 0
                        && ar[r][c-1] > 0
                        && ar[r-1][c] > 0
                        && ar[r-1][c-1] > 0)
                {
                    ar[r][c] += Math.min(ar[r][c-1], Math.min(ar[r-1][c], ar[r-1][c-1]));
                    if (max < ar[r][c])
                    {
                        max = ar[r][c];
                        maxC = c;
                        maxR = r;
                    }
                }
            }
        }

        System.out.print(maxC + " " + maxR + " " + max);
    }

}