// binary search for 1 in binary sorted array

public class Main
{
    private static Integer[][] ar = {
            {0,1,1,1},
            {0,0,1,1},
            {1,1,1,1},
            {0,0,0,0}
    };
    final private static Integer nodeCount = ar.length;
    public static void main(String[] er)
    {
        Integer minIndex = nodeCount-1;
        for (Integer r=0; r<nodeCount; ++r)
        {
            Integer index = findIndex(ar[r]);
            if (index < minIndex)
            {
                minIndex = index;
            }
        }

        System.out.print((nodeCount-minIndex));
    }

    private static Integer findIndex(Integer[] array)
    {
        Integer start = 0;
        Integer end = array.length-1;

        while (start <= end)
        {
            Integer mid = start + (end-start)/2;

            if (array[mid] == 1)
            {
                if (mid == 0 || array[mid-1] == 0)
                {
                    return mid;
                }
                else if (array[mid-1] == 1)
                {
                    end = mid - 1;
                }
            }
            else
            {
                start = mid + 1;
            }
        }

        return array.length-1;
    }
}