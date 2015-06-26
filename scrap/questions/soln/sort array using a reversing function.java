// sort array using a reversing function
// code question: 120

public class Main
{
    private static Integer[] array = {23, 10, 20, 11, 12, 6, 7};

    public static void main(String[] er)
    {
        Integer revCursor = array.length-1;

        while (revCursor > 0)
        {
            Integer maxIndex = findMaxIndex(revCursor);
            flip(maxIndex);
            flip(revCursor);

            --revCursor;
        }

        for (Integer v : array)
        {
            System.out.print(v + " ");
        }
    }

    private static void flip(Integer revCursor)
    {
        Integer fwdCursor = 0;

        while (fwdCursor < revCursor)
        {
            Integer tmp = array[fwdCursor];
            array[fwdCursor] = array[revCursor];
            array[revCursor] = tmp;

            --revCursor;
            ++fwdCursor;
        }
    }

    private static Integer findMaxIndex(Integer endIndex)
    {
        Integer maxIndex = 0;
        Integer cursor = 0;
        while (cursor <= endIndex)
        {
            if (array[maxIndex] < array[cursor])
            {
                maxIndex = cursor;
            }

            ++cursor;
        }

        return maxIndex;
    }
}