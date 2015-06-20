// Maximum difference between two elements such that larger element appears after the smaller number

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2, 3, 10, 6, 4, 8, 1};//{7, 9, 5, 6, 3, 2 };
        Integer minFromLeft = array[0];
        Integer maxSoFar = array[0];
        Integer maxDifference = maxSoFar - minFromLeft;

        for (Integer i=0; i<array.length; ++i)
        {
            if (array[i] < minFromLeft)
            {
                minFromLeft = array[i];
                maxSoFar = array[i];
            }
            else if (array[i] > maxSoFar)
            {
                maxSoFar = array[i];
            }

            if (maxSoFar - minFromLeft > maxDifference)
            {
                maxDifference = maxSoFar - minFromLeft;
            }
        }

        System.out.print(maxDifference);
    }
}