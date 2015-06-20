// Largest Sum Contiguous Subarray - with indices

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {-2,-3,4,-1,-2,1,5,-3};
        Integer maxSoFar = Integer.MIN_VALUE;
        Integer cursor = 0;
        Integer startIndex = cursor;
        Integer endIndex = cursor;
        Integer currentMax = array[cursor];

        while (cursor < array.length)
        {
            if (currentMax < 0)
            {
                currentMax = array[cursor];
                startIndex = cursor;
                endIndex = cursor;
            }
            else
            {
                currentMax += array[cursor];
            }

            if (maxSoFar < currentMax)
            {
                maxSoFar = currentMax;
                endIndex = cursor;
            }

            ++cursor;
        }

        System.out.print(maxSoFar + " " + startIndex + " " + endIndex);
    }
}