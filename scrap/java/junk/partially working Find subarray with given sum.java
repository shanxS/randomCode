// partially working Find subarray with given sum

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2, 1, 2, 3, 10, 100};
        final Integer sum = 0;
        Integer startIndex = 0;
        Integer endIndex = 0;
        Integer thisSum = array[startIndex];
        Boolean found = false;

        while(!found)
        {
            if (thisSum < sum)
            {
                ++endIndex;
                if (endIndex >= array.length)
                {
                    break;
                }

                thisSum += array[endIndex];
            }
            else if (thisSum > sum)
            {
                thisSum -= array[startIndex];

                if (startIndex == endIndex)
                {
                    ++endIndex;
                    thisSum = array[endIndex];
                }
                ++startIndex;
            }
            else
            {
                System.out.print(startIndex + " " + endIndex);
                found = true;
            }
        }

        if (!found)
        {
            System.out.print("not found");
        }

    }
}