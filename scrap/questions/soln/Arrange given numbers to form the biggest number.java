// Arrange given numbers to form the biggest number
// code question: 119

public class Main
{
    private static Integer[] array = {54, 546, 548, 60};
    public static void main(String[] er)
    {
        quickSort(0, array.length-1);
        for (Integer v : array)
        {
            System.out.print(v + " ");
        }
    }

    private static void quickSort(Integer startIndex, Integer endIndex)
    {
        if (startIndex == endIndex)
        {
            return;
        }

        Integer pivotIndex = endIndex;
        Integer cursor = startIndex;

        while (cursor < pivotIndex)
        {
            if (compare(array[cursor], array[pivotIndex]) > 0)
            {
                if (cursor+1 == pivotIndex)
                {
                    swap(cursor, pivotIndex);
                    --pivotIndex;
                    break;
                }
                else
                {
                    swap(pivotIndex, pivotIndex-1);
                    swap (cursor, pivotIndex);
                    --pivotIndex;
                }
            }
            else
            {
                ++cursor;
            }
        }
    }

    private static void swap(Integer fromIndex, Integer toIndex)
    {
        Integer tmp = array[fromIndex];
        array[fromIndex] = array[toIndex];
        array[toIndex] = tmp;
    }

    private static int compare(Integer value1, Integer value2)
    {
        String X = value1 + "" + value2;
        String Y = value2 + "" + value1;

        return X.compareTo(Y);
    }
}