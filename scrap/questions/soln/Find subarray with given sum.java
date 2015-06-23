// Find subarray with given sum
// code question: 113

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 4};
        final Integer sum = 0;
        Integer startIndex = 0;
        Integer endIndex = 1;
        Integer thisSum = array[startIndex];

        while (endIndex < array.length)
        {
            if (thisSum == sum)
            {
                System.out.print(startIndex + " " + (endIndex-1));
                if (startIndex + 1 == endIndex)
                {
                    ++endIndex;
                    ++startIndex;
                    thisSum = array[startIndex];
                }
                else
                {
                    thisSum -= array[startIndex];
                    ++startIndex;
                }

            }
            else if (thisSum < sum)
            {
                thisSum += array[endIndex];
                ++endIndex;
            }
            else
            {
                if (startIndex + 1 == endIndex)
                {
                    ++endIndex;
                    ++startIndex;
                    thisSum = array[startIndex];
                }
                else
                {
                    thisSum -= array[startIndex];
                    ++startIndex;
                }
            }
        }
        if (thisSum == sum)
        {
            System.out.print(startIndex + " " + (endIndex-1));
        }
    }
}