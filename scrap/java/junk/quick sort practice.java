// quick sort practice

public class Main
{
    private static Integer[] array = {2,1,9,2,8,3,3,7};
    public static void main(String[] er)
    {
        quickSort(0, array.length-1);
        for (Integer value : array)
        {
            System.out.print(value + " ");
        }
    }

    private static void quickSort(Integer start, Integer end)
    {
        if (start >= end)
        {
            return;
        }

        Integer pivotIndex = end;
        Integer cursor = start;

        while (cursor < pivotIndex)
        {
            if (array[cursor] > array[pivotIndex])
            {
                if (cursor + 1 == pivotIndex)
                {
                    Integer tmp = array[pivotIndex];
                    array[pivotIndex] = array[cursor];
                    array[cursor] = tmp;

                    --pivotIndex;
                    ++cursor;
                }
                else
                {
                    Integer tmp  = array[pivotIndex-1];
                    array[pivotIndex-1] = array[pivotIndex];
                    array[pivotIndex] = array[cursor];
                    array[cursor] = tmp;

                    --pivotIndex;
                }
            }
            else
            {
                ++cursor;
            }
        }

        quickSort(start, pivotIndex-1);
        quickSort(pivotIndex+1, end);
    }

}
