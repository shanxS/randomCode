// counting sort

public class Main
{
    static Integer[] array = new Integer[]{1, 4, 1, 2, 7, 5, 2};
    static Integer[] count = new Integer[7+1];
    static Integer[] output = new Integer[array.length];

    public static void main(String[] args)
    {
        for (Integer i = 0; i < count.length; ++i)
        {
            count[i] = 0;
        }

        prepCount();
        sort();

        for (Integer i : output)
        {
            System.out.print(i + " ");
        }
    }

    private static void sort()
    {
        for (Integer endI = array.length-1; endI >= 0; --endI)
        {
            count[array[endI]] -= 1;
            Integer destinationIndex = count[array[endI]];
            output[destinationIndex] = array[endI];
        }
    }

    private static void prepCount()
    {
        for (Integer index : array)
        {
            ++count[index];
        }

        for (Integer i=1; i<count.length; ++i)
        {
            count[i] += count[i-1];
        }
    }
}