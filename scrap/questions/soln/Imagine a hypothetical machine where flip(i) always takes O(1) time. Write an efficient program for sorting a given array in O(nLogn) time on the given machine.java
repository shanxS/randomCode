// Imagine a hypothetical machine where flip(i) always takes O(1) time. Write an efficient program for sorting a given array in O(nLogn) time on the given machine
// code question: 121

public class Main
{
    private static Integer[] array = {23, 10, 20, 11, 12, 6, 7};

    public static void main(String[] er)
    {

        Integer startIndex = 1;
        while (startIndex < array.length)
        {
            Integer ceilIndex = getCeilIndex(startIndex-1, array[startIndex]);

            flip(ceilIndex+1, startIndex);
            flip(ceilIndex+2, startIndex);

            ++startIndex;
        }

        for (Integer v : array)
        {
            System.out.print(v + " ");
        }
    }

    private static Integer getCeilIndex(Integer endIndex, Integer value)
    {
        Integer endIndexCache = endIndex;
        Integer startIndex = 0;
        while (startIndex <= endIndex)
        {
            Integer midIndex = startIndex + (endIndex-startIndex)/2;

            if (array[midIndex] > value)
            {
                if (midIndex == endIndex || (array[midIndex+1] < value))
                {
                    return midIndex;
                }
                else
                {
                    startIndex = midIndex+1;
                }
            }
            else
            {
                endIndex = midIndex-1;
            }
        }

        if (startIndex == 0)
        {
            return -1;
        }
        else
        {
            return endIndexCache;
        }
    }

    private static void flip(Integer fwdCursor, Integer revCursor)
    {
        while (fwdCursor < revCursor)
        {
            Integer tmp = array[fwdCursor];
            array[fwdCursor] = array[revCursor];
            array[revCursor] = tmp;

            --revCursor;
            ++fwdCursor;
        }
    }

}