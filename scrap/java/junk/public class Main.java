// rotating array by n positions reverse

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,2,3,4,5};

        Integer n=2;
        Integer[] cache = new Integer[n];
        for (Integer i=n;i>0;--i)
        {
            cache[n-i] = array[array.length-i];
        }

        Integer writeCursor = array.length - n;
        Integer readCursor = 0;
        while (readCursor < array.length - n)
        {
            array[writeCursor%array.length] = array[readCursor];
            ++readCursor;
            ++writeCursor;
        }

        for (Integer i=n;i>0;--i)
        {
            array[writeCursor%array.length] = cache[n-i];
            ++writeCursor;
        }

        for (Integer value : array)
        {
            System.out.print(value + " ");
        }
    }
}