// print leaders in array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {16, 17, 4, 3, 5, 2};
        Integer cursor = array.length-1;
        Integer maxSoFar = Integer.MIN_VALUE;

        while (cursor >= 0)
        {
            if (array[cursor] > maxSoFar)
            {
                System.out.print(array[cursor] + " ");
                maxSoFar = array[cursor];
            }

            --cursor;
        }
    }
}