// Find the maximum element in an array which is first increasing and then decreasing

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {120, 100, 80, 20, 0};//{10, 20, 30, 40, 50};//{8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
        BinarySearch bs = new BinarySearch();
        System.out.print(bs.findMax(array));
    }
}

class BinarySearch
{
    public Integer findMax(Integer[] array)
    {
        Integer startIndex = 0;
        Integer endIndex = array.length-1;
        Integer lastMaxKnown = Integer.MIN_VALUE;

        while (startIndex <= endIndex)
        {
            Integer mid = startIndex + (endIndex-startIndex)/2;

            if (mid == array.length-1)
            {
                lastMaxKnown = array[mid];
                break;
            }
            else if (array[mid] < array[mid+1])
            {
                if (lastMaxKnown < array[mid+1])
                {
                    lastMaxKnown = array[mid+1];
                }

                startIndex = mid+1;
            }
            else
            {
                if (lastMaxKnown < array[mid])
                {
                    lastMaxKnown = array[mid];
                }
                endIndex = mid-1;
            }
        }

        return lastMaxKnown;
    }
}