// find ceil and floor in sorted array

public class Main
{
    private static Integer[] array = {1, 2, 8, 10, 10, 12, 19};
    private static Integer ceil, floor;

    public static void main(String[] er)
    {
        ceil = Integer.MIN_VALUE;
        floor = Integer.MAX_VALUE;

        find(3);

        System.out.print(ceil + " " + floor);
    }

    private static void find(Integer k)
    {
        Integer start = 0;
        Integer end = array.length-1;

        while (start <= end)
        {
            Integer mid = start + (end-start)/2;

            if (array[mid] < k)
            {
                floor = array[mid];
                start = mid+1;
            }
            else if (array[mid] > k)
            {
                ceil = array[mid];
                end = mid-1;
            }
            else
            {
                if (mid != 0)
                {
                    floor = array[mid-1];
                }

                if (mid != array.length-1)
                {
                    ceil = array[mid+1];
                }

                break;
            }
        }
    }
}