// printing matrix diagonally

public class Main
{
    public static void main(String[] er)
    {
        int[][] ar = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19, 20},
        };

        Printer p = new Printer();
        p.print(ar);
    }
}

class Printer
{
    public void print(int[][] chars)
    {
        Integer CStart = 0;
        Integer RStart = 0;

        while (CStart < chars[0].length && RStart < chars.length)
        {
            for (Integer r=RStart, c=CStart; r>=0 && c<chars[0].length; ++c, --r)
            {
                System.out.print(chars[r][c] + " ");
            }
            System.out.println();

            ++RStart;
            if (RStart == chars.length)
            {
                ++CStart;
                --RStart;
            }
        }
    }
}