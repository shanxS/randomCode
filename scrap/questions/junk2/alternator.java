alternator

public class Main
{
    public static void main(String[] er)
    {

        (new Alternator()).print(5,5);

    }
}

class Alternator
{
    public void print(Integer R, Integer C)
    {
        Integer cStart = 0, cEnd = C - 1;
        Integer rStart = 0, rEnd = R - 1;
        Integer[][] ar = new Integer[R][C];
        Integer value = 1;
        boolean flag = false;

        while (rStart <= rEnd && cStart <= cEnd)
        {
            Integer r = rStart;
            Integer c = cStart;
            while (c <= cEnd)
            {
                ar[r][c] = value;
                ++c;
            }

            c = cStart;
            r = rStart;
            while (r <= rEnd)
            {
                ar[r][c] = value;
                ++r;
            }

            c = cEnd;
            r = rStart;
            while (r <= rEnd)
            {
                ar[r][c] = value;
                ++r;
            }

            r = rEnd;
            c = cStart;
            while (c <= cEnd)
            {
                ar[r][c] = value;
                ++c;
            }

            ++rStart;
            ++cStart;
            --rEnd;
            --cEnd;

            flag = !flag;
            if (flag)
            {
                value = 0;
            } else
            {
                value = 1;
            }
        }

        for (Integer r = 0; r < R; ++r)
        {
            for (Integer c=0; c<C; ++c)
            {
                System.out.print(ar[r][c] + " ");
            }
            System.out.println();
        }
    }
}