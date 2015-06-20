// finding peak elemmnt in array in logn

public class Main
{
    private static Integer[] array = {100, 80, 60, 50, 20};//{10, 20, 30, 40, 50};//{10, 20, 15, 2, 23, 90, 67};

    public static void main(String[] er)
    {
        checkByBinarySearch(0, array.length-1);
    }

    private static void checkByBinarySearch(Integer start, Integer end)
    {
        if (start > end)
        {
            return;
        }

        Integer mid = start + ((end-start)/2);

        if (testPeak(mid))
        {
            System.out.println(array[mid]);

            checkByBinarySearch(start, mid-2);
            checkByBinarySearch(mid+2, end);
        }
        else
        {
            if (mid != 0 && mid != array.length - 1)
            {
                if (array[mid] < array[mid + 1])
                {
                    checkByBinarySearch(mid + 1, end);
                } else if (array[mid] > array[mid + 1])
                {
                    checkByBinarySearch(mid + 2, end);
                }

                if (array[mid] < array[mid - 1])
                {
                    checkByBinarySearch(start, mid - 1);
                } else if (array[mid] > array[mid - 1])
                {
                    checkByBinarySearch(start, mid - 2);
                }
            }
            else if (mid == 0)
            {
                checkByBinarySearch(mid + 1, end);
            }
            else if (mid == array.length-1)
            {
                checkByBinarySearch(start, mid-1);
            }
        }
    }

    private static boolean testPeak(Integer mid)
    {
        if (mid == 0)
        {
            return array[mid] > array[mid+1];
        }
        else if (mid == array.length - 1)
        {
            return array[mid] > array[mid-1];
        }
        else
        {
            return (array[mid] > array[mid+1]) && (array[mid] > array[mid-1]);
        }
    }
}