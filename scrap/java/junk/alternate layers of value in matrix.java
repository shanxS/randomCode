// alternate layers of value in matrix

public class Main
{
    public static void main(String[] er)
    {
        final Integer R = 8, C = 7;
        Integer[][] ar = new Integer[R][C];

        Integer rStart = 0;
        Integer rEnd = R-1;
        Integer cStart = 0;
        Integer cEnd = C-1;
        boolean type = false;
        Integer value = (type) ? 1 : 0;
        while (rStart<=rEnd || cStart<=cEnd)
        {
            for (Integer c=cStart; c<=cEnd; ++c)
            {
                if (c == cStart || c==cEnd)
                {
                    for (Integer r = rStart; r <= rEnd; ++r)
                    {
                        ar[r][c] = value;
                    }
                }
                else
                {
                    ar[rStart][c] = value;
                    ar[rEnd][c] = value;
                }
            }

            ++rStart; --rEnd;
            ++cStart; --cEnd;

            type = !type;
            value = (type) ? 1 : 0;
        }

        for (Integer r=0; r<R; ++r)
        {
            for (Integer c = 0; c < C; ++c)
            {
                System.out.print(ar[r][c] + " ");
            }
            System.out.println();
        }
    }
}