// Check if array elements are consecutive

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {7, 5, 5, 3, 4};//{34, 23, 52, 12, 3};//{83, 78, 80, 81, 79, 82};//{5, 2, 3, 1, 4};
        Integer min = array[0];
        Integer max = array[0];
        Integer arrayXor = array[0];
        for (Integer i=1; i<array.length; ++i)
        {
            arrayXor ^= array[i];

            if(min > array[i])
            {
                min = array[i];
            }

            if (max < array[i])
            {
                max = array[i];
            }
        }

        if (max-min+1 != array.length)
        {
            System.out.print("not");
            return;
        }

        Integer properXor = 0;
        for (Integer i=min; i<=max; ++i)
        {
            properXor ^= i;
        }

        if ((properXor^arrayXor) != 0)
        {
            System.out.print("not");
        }
        else
        {
            System.out.print("yes");
        }
    }
}