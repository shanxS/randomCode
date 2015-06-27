// given array place negative and positive alternativly using seggreagtion

public class Main
{
    private static Integer[] array = {-1,-2,-3,4,5};//{-1, 2, -3, 4, 5, 6, -7, 8, 9};
    final private static Boolean positive = true;
    final private static Boolean negative = false;

    public static void main(String[] er)
    {
        doAlternate(seggreate());

        for (Integer v : array)
        {
            System.out.print(v + " ");
        }
    }

    private static void doAlternate(Integer negCursor)
    {
        Integer startCursor = 0;
        Boolean type = positive;

        while (startCursor < negCursor)
        {
            if (array[startCursor] > 0 != type)
            {
                if (negCursor >= array.length)
                {
                    break;
                }

                swap(startCursor, negCursor);
                ++negCursor;
            }

            ++startCursor;
            type = !type;
        }
    }

    private static Integer seggreate()
    {
        Integer endCursor = array.length-1;
        Integer startCursor = 0;

        while (startCursor < endCursor)
        {
            if (array[startCursor] < 0)
            {
                while (endCursor > startCursor && array[endCursor] < 0)
                {
                    --endCursor;
                }
                if (endCursor == startCursor)
                {
                    break;
                }

                swap(startCursor, endCursor);
            }
            else
            {
                ++startCursor;
            }
        }

        while (endCursor >= 1 && array[endCursor-1] < 0)
        {
            --endCursor;
        }

        return endCursor;
    }

    private static void swap(Integer fromIndex, Integer toIndex)
    {
        Integer tmp = array[fromIndex];
        array[fromIndex] = array[toIndex];
        array[toIndex] = tmp;
    }
}