// Given an n x n square matrix - find sum of all sub-squares of size k x k
// O(n2) time code which works for all values of k - until proved otherwise ;-)

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5},
        };
        final Integer R = ar.length;
        final Integer C = ar[0].length;
        final Integer k = 4;
        int[][] res = new int[R][C];

        for (Integer c=0; c<C; ++c)
        {
            for (Integer r=0; r<=R-k; ++r)
            {
                res[0][c] += ar[r][c];
            }

            for (Integer r=1; r<k; ++r)
            {
                res[r][c] = (res[r-1][c] - ar[r-1][c]) + ar[R-k+r][c];
            }
        }

        for (Integer r=0; r<k; ++r)
        {
            Integer sum = 0;
            for(Integer c=0; c<=C-k; ++c)
            {
                sum += res[r][c];
            }
            System.out.print(sum + " ");

            for (Integer c=1; c<k; ++c)
            {
                sum = (sum - res[r][c-1]) + + res[r][C-k+r];
                System.out.print(sum + " ");
            }
            System.out.println();
        }
    }
}