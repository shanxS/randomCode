// sub array with a given sum - all elements of array are positive

public class Main
{
    private static Integer[] array = {4,2,3,1,4,2,2,5};//{4, 2, -3, 1, 6};
    private final static Integer K = 9;

    public static void main(String[] er)
    {
        Integer count = 0;
        Integer startIndex = 0;
        Integer endIndex = 0;
        count += array[endIndex];
        while (startIndex <= endIndex || endIndex < array.length)
        {
            if (count == K)
            {
                System.out.println(startIndex + " " + endIndex);
                count -= array[startIndex];
                ++startIndex;
            }
            else if (count < K)
            {

                if (endIndex < array.length)
                {
                    ++endIndex;
                    count += array[endIndex];
                }
                else
                {
                    break;
                }
            }
            else if (count > K)
            {
                count -= array[startIndex];

                if (startIndex == endIndex)
                {
                    ++endIndex;
                    count += array[endIndex];
                }
                ++startIndex;
            }

        }

    }
}