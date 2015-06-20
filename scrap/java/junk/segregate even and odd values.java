// segregate even and odd values

public class Main
{
    private static Integer[] array = {12, 34, 45, 9, 8, 90, 3};
    public static void main(String[] er)
    {
        Integer evenCursor = array.length-1;
        Integer oddCursor = 0;

        while (evenCursor > oddCursor)
        {
            while (array[evenCursor] % 2 == 0)
            {
                --evenCursor;
            }

            while (array[oddCursor] % 2 != 0)
            {
                ++oddCursor;
            }

            if (array[evenCursor] % 2 != 0
                    && array[oddCursor] % 2 == 0)
            {
                Integer tmp = array[evenCursor];
                array[evenCursor] = array[oddCursor];
                array[oddCursor] = tmp;

                --evenCursor;
                ++oddCursor;
            }
            else  if (evenCursor - oddCursor == 1)
            {
                break;
            }
        }

        for (Integer v : array)
        {
            System.out.print(v + " ");
        }
    }
}