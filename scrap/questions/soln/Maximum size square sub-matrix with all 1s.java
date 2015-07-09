// Maximum size square sub-matrix with all 1s
// code question: 127

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] M = {
                {0,  1,  1,  0,  1},
                {1,  1,  0,  1,  0},
                {0,  1,  1,  1,  0},
                {1,  1,  1,  1,  0},
                {1,  1,  1,  1,  1},
                {0,  0,  0,  0,  0}
        };
        final Integer R = M.length;
        final Integer C = M[0].length;

        int[][] S = new int[R][C];
        for (Integer i=0; i<C; ++i)
        {
            S[0][i] = M[0][i];
        }
        for (Integer i=0; i<R; ++i)
        {
            S[i][0] = M[i][0];
        }

        Integer max = 0;
        Integer maxR = null, maxC=null;
        for (Integer r=1; r<R; ++r)
        {
            for (Integer c=1; c<C; ++c)
            {
                if (M[r][c] == 1)
                {
                    S[r][c] = Math.min(S[r][c - 1], Math.min(S[r - 1][c], S[r - 1][c - 1])) + 1;
                    if (max < S[r][c])
                    {
                        max = S[r][c];
                        maxR = r;
                        maxC = c;
                    }
                }
            }
        }

        System.out.print("max size is " + max + " bottom right indx is " + maxR + ", " + maxC);
    }
}