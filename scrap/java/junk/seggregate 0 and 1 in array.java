// seggregate 0 and 1 in array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {0,0,0,0};
        Integer zeroCursro = array.length-1;
        Integer oneCursor = 0;

        while (oneCursor < zeroCursro)
        {
            while (zeroCursro > oneCursor && array[zeroCursro] == 0)
            {
                --zeroCursro;
            }

            while (oneCursor < zeroCursro && array[oneCursor] == 1)
            {
                ++oneCursor;
            }

            if (array[oneCursor] == 0 && array[zeroCursro] == 1)
            {
                array[oneCursor] = 1;
                array[zeroCursro] = 0;

                ++oneCursor;
                --zeroCursro;
            }
            else if (zeroCursro - oneCursor == 1)
            {
                break;
            }
        }

        for (Integer value : array)
        {
            System.out.print(value + " ");
        }
    }
}