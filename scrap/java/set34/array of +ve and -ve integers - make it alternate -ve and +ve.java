// array of +ve and -ve integers - make it alternate -ve and +ve
// online2, q2, set34

public class Main
{
    static Integer[] array = new Integer[]{1,2,3,-1,-2,-3,4,5,-6};

    public static void main(String[] er)
    {
        Integer negativeCursor = 0;
        while (negativeCursor < array.length && array[negativeCursor] > 0)
        {
            ++negativeCursor;
        }

        Integer positiveCursor = 0;
        while (positiveCursor < array.length && array[positiveCursor] < 0)
        {
            ++positiveCursor;
        }

        while (positiveCursor < array.length
                && negativeCursor < array.length)
        {
            if (isAlternate(positiveCursor, negativeCursor))
            {
                if (positiveCursor < negativeCursor)
                {
                    positiveCursor = getNextPositiveIndex(positiveCursor);
                }
                else
                {
                    negativeCursor = getNextNegativeIndex(negativeCursor);
                }
            }
            else
            {
                if (positiveCursor < negativeCursor)
                {
                    swap(positiveCursor+1, negativeCursor);
                    positiveCursor += 1;
                    positiveCursor = getNextPositiveIndex(positiveCursor);

                    negativeCursor = getNextNegativeIndex(negativeCursor);
                }
                else
                {
                    swap (negativeCursor+1, positiveCursor);
                    negativeCursor += 1;
                    negativeCursor = getNextNegativeIndex(negativeCursor);

                    positiveCursor = getNextPositiveIndex(positiveCursor);
                }
            }
        }

        for (Integer value : array)
        {
            System.out.print(value + " ");
        }
    }

    private static void swap(Integer toIndex, Integer fromIndex)
    {
        Integer tmp = array[toIndex];
        array[toIndex] = array[fromIndex];
        array[fromIndex] = tmp;
    }

    private static Integer getNextNegativeIndex(Integer previousNegativeCursor)
    {
        Integer newIndex = previousNegativeCursor + 1;
        while (newIndex < array.length && array[newIndex] > 0)
        {
            ++newIndex;
        }

        return newIndex;
    }

    private static Integer getNextPositiveIndex(Integer previousPositiveCursor)
    {
        Integer newIndex = previousPositiveCursor + 1;
        while(newIndex < array.length && array[newIndex] < 0)
        {
            ++newIndex;
        }

        return newIndex;
    }

    private static boolean isAlternate(Integer positiveCursor, Integer negativeCursor)
    {
        return (Math.abs(positiveCursor - negativeCursor) == 1);
    }
}