// sum of elements and changing array
// written, q3, set31

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{4, 6, 8, 3, 6};
        Integer validLength = array.length;
        while(validLength > 1)
        {
            Integer sum = 0;
            for (Integer i=0; i<validLength; ++i)
            {
                sum += array[i];
            }
            System.out.println(sum);

            for (Integer i=0; i<validLength-1; ++i)
            {
                array[i] = array[i] - array[i+1];
            }

            --validLength;
        }

    }
}