// Find the maximum repeating number in O(n) time and O(1) extra space

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2, 3, 2, 3};

        Integer range = array.length;
        for (Integer v :  array)
        {
            array[v % range] += range;
        }

        Integer maxIndex = 0;
        for (Integer i=0; i<array.length; ++i)
        {
            if (array[i]/range > array[maxIndex]/range)
            {
                maxIndex = i;
            }
        }

        System.out.print(maxIndex);
    }
}