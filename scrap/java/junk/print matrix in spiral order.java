// print matrix in spiral order

public class Main
{
//    static Integer[][] array = {
//            {1, 2, 3, 4, 5, 6},
//            {7, 8, 9, 10, 11, 12},
//            {13, 14, 15, 16, 17, 18}
//    };
    static Integer[][] array = {
        {1,    2,   3,   4},
        {5,    6,   7,   8},
        {9,   10,  11,  12},
        {13,  14,  15,  16}
    };
    static Integer rStart = 0, rEnd = array.length-1;
    static Integer cStart = 0, cEnd = array[0].length-1;

    public static void main(String[] er)
    {
        Integer r=0, c=0;
        while (validRange())
        {
            r=rStart;
            c=cStart;
            if (validRange())
            {
                while (c<=cEnd)
                {
                    System.out.print(array[r][c] + " ");
                    ++c;
                }
            }
            ++rStart;

            r=rStart;
            c=cEnd;
            if (validRange())
            {
                while (r<=rEnd)
                {
                    System.out.print(array[r][c] + " ");
                    ++r;
                }
            }
            --cEnd;

            c=cEnd;
            r=rEnd;
            if (validRange())
            {
                while(c>=cStart)
                {
                    System.out.print(array[r][c] + " ");
                    --c;
                }
            }
            --rEnd;

            c=cStart;
            r=rEnd;
            if (validRange())
            {
                while (r>=rStart)
                {
                    System.out.print(array[r][c] + " ");
                    --r;
                }
            }
            ++cStart;
        }
    }

    private static boolean validRange()
    {
        return (rEnd>=rStart) && (cEnd>=cStart);
    }
}