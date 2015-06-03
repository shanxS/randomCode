// kth largest element in array using quick sort
// code question 37

public class Main
{
    static Integer[] array = new Integer[]{1,10,2,9,3,8,4,7,5};
    static Integer k = 3, foundElement = null;
    public static void main(String[] er)
    {
        sort(0, array.length - 1);

        System.out.println(foundElement);
        for (Integer value : array)
        {
            System.out.print(value + " ");
        }
    }

    private static void sort(Integer start, Integer end)
    {
        if (end <= start || foundElement != null)
        {
            return;
        }

        Integer pivot = end;

        for (Integer cursor=start; cursor<pivot;)
        {
            if (array[cursor] < array[pivot])
            {
                if (pivot-cursor == 1)
                {
                    Integer tmp = array[cursor];
                    array[cursor] = array[pivot];
                    array[pivot] = tmp;
                }
                else
                {
                    Integer tmp = array[pivot-1];
                    array[pivot-1] = array[pivot];
                    array[pivot] = array[cursor];
                    array[cursor] = tmp;
                }

                --pivot;
            }
            else
            {
                ++cursor;
            }
        }

        if (pivot == k)
        {
            foundElement = array[pivot];
        }

        sort(start, pivot-1);
        sort(pivot+1, end);
    }
}