// Product Array Puzzle

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {10,3,5,6,2};
        Integer[] result = new Integer[array.length];
        for (Integer i=0; i<result.length; ++i)
        {
            result[i] = 1;
        }

        Integer fCache = result[0];
        Integer rCache = result[result.length-1];
        for (Integer f=1, r=array.length-2;
                f < array.length; ++f, --r)
        {
            fCache *= array[f-1];
            rCache *= array[r+1];

            result[f] *= fCache;
            result[r] *= rCache;
        }

        for (Integer value : result)
        {
            System.out.print(value + " ");
        }
    }
}