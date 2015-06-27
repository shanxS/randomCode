// count sort optimised for given range

public class Main
{
    private static Integer[] array = {20,10,19,15,11,16,13};
    final static private Integer M = 10, N = 20;

    public static void main(String[] er)
    {
        int[] count = new int[N-M+1];
        for (Integer v : array)
        {
            ++count[v-M];
        }

        Integer k =0;
        for (Integer i=0; i<count.length; ++i)
        {
            while (count[i] > 0)
            {
                array[k] = i+M;
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