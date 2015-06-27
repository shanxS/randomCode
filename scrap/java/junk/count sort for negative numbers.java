// count sort for negative numbers

public class Main
{
    private static Integer[] array = {-10,6,-2,-1,-3,4};
    final static private Integer M = 10;

    public static void main(String[] er)
    {
        int[] count = new int[17];
        for (Integer v : array)
        {
            ++count[v+M];
        }

        Integer k = 0;
        for (Integer i=0; i<count.length; ++i)
        {
            while (count[i] > 0)
            {
                array[k] = i-M;
                --count[i];
                ++k;
            }
        }

        for (Integer v : array)
        {
            System.out.print(v + " ");
        }

    }
}