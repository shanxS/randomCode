// counting sort

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1, 4, 1, 2, 7, 5, 2};
        CountSort cs = new CountSort();
        for (int i : cs.sort(ar, 8))
        {
            System.out.print(i + " ");
        }
    }
}

class CountSort
{
    public int[] sort(Integer[] ar, Integer range)
    {
        int[] counter = new int[range];
        int[] sorted = new int[ar.length];

        for (Integer i = 0; i < ar.length; ++i)
        {
            ++counter[ar[i]];
        }

        Integer sortedCursor = 0;
        for (Integer i = 0; i < counter.length; ++i)
        {
            while (counter[i] > 0)
            {
                sorted[sortedCursor] = i;
                --counter[i];
                ++sortedCursor;
            }
        }

        return sorted;
    }
}