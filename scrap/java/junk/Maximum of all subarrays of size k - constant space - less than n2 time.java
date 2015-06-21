// Maximum of all subarrays of size k - constant space - less than n2 time

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        final Integer k = 4;

        Integer startIndex = 0;
        Integer endIndex = k-1;
        Integer max = array[startIndex];
        Integer maxIndex = startIndex;
        for (Integer i=0; i<=endIndex; ++i)
        {
            if (array[i] > max)
            {
                max = array[i];
                maxIndex = i;
            }
        }
        System.out.println(max);

        ++startIndex;
        ++endIndex;
        while (endIndex < array.length)
        {
            if (array[endIndex] > max)
            {
                max = array[endIndex];
                maxIndex = endIndex;
            }
            else
            {
                if (startIndex > maxIndex)
                {
                    max = startIndex;
                    maxIndex = startIndex;
                    for(Integer i=startIndex; i<=endIndex; ++i)
                    {
                        if (array[i] > max)
                        {
                            max = array[i];
                            maxIndex = i;
                        }
                    }
                }
            }

            System.out.println(max);

            ++endIndex;
            ++startIndex;
        }
    }
}