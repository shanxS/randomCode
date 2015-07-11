public class Main
{
    private static Integer[][] ar = {{1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1},
            {0, 0, 0, 0, 1, 1},
    };
    private static final Integer INVALID = -1;
    private static final Integer R = ar.length;
    private static final Integer C = ar[0].length;


    public static void main(String[] er)
    {
        Integer count = 0;

        for (Integer r=0; r<R; ++r)
        {
            for (Integer c=0; c<C; ++c)
            {
                if (ar[r][c] == 1)
                {
                    dfs(r, c);
                    ++count;
                }
            }
        }

        System.out.print(count);
    }

    private static void dfs(Integer r, Integer c)
    {
        ar[r][c] = -1;

        if (isValid(r - 1, c))
        {
            dfs(r-1, c);
        }

        if (isValid(r+1, c))
        {
            dfs(r+1, c);
        }

        if (isValid(r, c+1))
        {
            dfs(r, c+1);
        }

        if (isValid(r, c-1))
        {
            dfs(r, c-1);
        }
    }

    private static boolean isValid(Integer r, int c)
    {
        return r>=0 && c>=0 && r<R && c<C && ar[r][c]==1;
    }
}