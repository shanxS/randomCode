// Longest discontinous Bitonic Subsequence
// code question: 115

public class Main
{
    private static Integer[] array = {10,20,5,30,1,50};
    private static Integer[] endingAtLIS = new Integer[array.length];
    private static Integer[] startingAtLDS = new Integer[array.length];

    public static void main(String[] er)
    {
        computeLIS();
        computeLDS();

        for (Integer value : endingAtLIS)
        {
            System.out.print(value + " ");
        }
        System.out.println();

        for (Integer value : startingAtLDS)
        {
            System.out.print(value + " ");
        }
        System.out.println();

        Integer maxIncDec = 1;
        for (Integer i=0; i<array.length; ++i)
        {
            if (maxIncDec < startingAtLDS[i] + endingAtLIS[i] -1)
            {
                maxIncDec = startingAtLDS[i] + endingAtLIS[i] -1;
            }
        }

        System.out.print(maxIncDec);
    }

    private static void computeLDS()
    {
        Integer cursor = 0;
        while (cursor < array.length)
        {
            startingAtLDS[cursor] = 1;
            ++cursor;
        }

        cursor = array.length-2;
        while(cursor >= 0)
        {
            Integer thisValue = array[cursor];
            for (Integer i=cursor+1; i<array.length; ++i)
            {
                if (thisValue > array[i] && startingAtLDS[cursor] < 1 + startingAtLDS[i])
                {
                    startingAtLDS[cursor] = 1 + startingAtLDS[i];
                }
            }

            --cursor;
        }
    }

    private static void computeLIS()
    {
        Integer cursor = 0;
        while (cursor < array.length)
        {
            endingAtLIS[cursor] = 1;
            ++cursor;
        }

        cursor = 1;
        while(cursor < array.length)
        {
            Integer thisValue = array[cursor];
            for (Integer i=cursor-1; i>=0; --i)
            {
                if (thisValue > array[i] && endingAtLIS[cursor] < 1 + endingAtLIS[i])
                {
                    endingAtLIS[cursor] = 1 + endingAtLIS[i];
                }
            }

            ++cursor;
        }
    }
}