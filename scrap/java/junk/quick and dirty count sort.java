// quick and dirty count sort

public class Main
{
    private static Integer[] array = {170, 45, 75, 90, 802, 24, 2, 66};
    private static final Integer RANGE = 803;

    public static void main(String[] er)
    {
        countSort();
        for (Integer v : array)
        {
            System.out.print(v + " ");
        }
    }

    private static void countSort()
    {
        int[] count = new int[RANGE];

        for (Integer v : array)
        {
            ++count[v];
        }

        Integer k=0;
        for (Integer i=0; i<count.length; ++i)
        {
            while (count[i] > 0)
            {
                array[k] = i;
                --count[i];
                ++k;
            }
        }
    }
}