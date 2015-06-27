// Given an array of of size n and a number k - find all elements that appear more than n/k times

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {3, 1, 2, 2, 1, 2, 3, 3};
        final Integer N = array.length;
        final Integer k = 4;

        for (Integer v : array)
        {
            array[v % N] +=  N;
        }

        for (Integer i=0; i<array.length; ++i)
        {
            if ((array[i]/N) > (N/k))
            {
                System.out.print(i + " ");
            }
        }
    }
}