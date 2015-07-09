// print matrix diagonally

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] array = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
                {17,18,19,20}
        };

        final Integer R = array.length;
        final Integer C = array[0].length;

        for (Integer r=0; r<R; ++r)
        {
            Integer c=0;
            Integer tempR = r;
            while (c<C && tempR>=0)
            {
                System.out.print(array[tempR][c] + " ");
                --tempR;
                ++c;
            }
            System.out.println();
        }

        for (Integer c=1; c<C; ++c)
        {
            Integer r=R-1;
            Integer tempC = c;
            while (tempC<C)
            {
                System.out.print(array[r][tempC] + " ");
                ++tempC;
                --r;
            }
            System.out.println();
        }
    }
}