// inversion counter - buggy

public class Main
{
    private static Integer[] array = {3,5,2,4,1};//{2, 4, 1, 3, 5};

    public static void main(String[] er)
    {
        divide(0, array.length - 1);
    }

    private static void divide(Integer start, Integer end)
    {
        if (end == start)
        {
            return;
        }

        Integer mid = start + (end-start)/2;
        divide(start, mid);
        divide(mid+1, end);

        merge(start, mid, mid + 1, end);
    }

    private static void merge(Integer start1, Integer end1, Integer start2, Integer end2)
    {
        Integer cursor1 = start1;
        Integer cursor2 = start2;

        while (cursor1 <= end1 && cursor2 <=end2)
        {
            if (array[cursor1] > array[cursor2])
            {
                Integer cursor1Cache = cursor1;
                while (array[cursor1] > array[cursor2])
                {
                    System.out.println(array[cursor1] + " " + array[cursor2]);
                    ++cursor1;
                }

                cursor1 = cursor1Cache;
                ++cursor2;
            }
            else if (array[cursor1] < array[cursor2])
            {
                ++cursor1;
            }
            else
            {
                ++cursor1;
                ++cursor2;
            }
        }
    }
}