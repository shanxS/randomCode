// finding equilibrium position in index

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {-7,1,5,2,-4,3,0};
        Integer cursor = array.length-1;
        Integer rightSum = 0;
        Integer leftSum = 0;
        for (Integer i=0; i<cursor; ++i)
        {
            leftSum += array[i];
        }

        while (cursor >= 0)
        {
            if (leftSum == rightSum)
            {
                System.out.println(cursor);
            }

            rightSum += array[cursor];
            --cursor;
            if (cursor >= 0)
            {
                leftSum -= array[cursor];
            }
        }
    }
}