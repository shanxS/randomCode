// find number of inversions in an array
// r5, q3, set27

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = new Integer[]{1,3,5,7,2,4,6,8};
//        Integer[] array = new Integer[]{1,9,8,3,7,4};
//        Integer[] array = new Integer[]{6,7,8,9,3,2,1};
//        Integer[] array = new Integer[]{2, 4, 1, 3, 5};
        Integer[] array = new Integer[]{2, 5, 3, 1,10};
        InPlaceMergeSort sor = new InPlaceMergeSort();
        for (ValuePair pair : sor.sort(array))
        {
            System.out.println(pair.getValue1() + " " + pair.getValue2()    );
        }
    }
}

class InPlaceMergeSort
{
    private Integer[] array;
    private List<ValuePair> inversionValues;

    public List<ValuePair> sort(Integer[] array)
    {
        this.array = array;
        this.inversionValues = new ArrayList<>();

        Integer mid = (0 + array.length-1) / 2;
        sort(0, mid);
        sort(mid+1, array.length-1);
        merge(0, mid, mid+1, array.length-1);

        return inversionValues;
    }

    private void sort(Integer start, Integer end)
    {
        if (end-start <= 1)
        {
            return;
        }

        Integer mid = (start + end) / 2;

        sort(start, mid);
        sort(mid + 1, end);
        merge(start, end / 2, (end/2) + 1, end);
    }

    private void merge(Integer start1, Integer end1,
                       Integer start2, Integer end2)
    {
        Integer cursor1 = end1;
        Integer cursor2 = start2;

        while(cursor2 <= end2)
        {
            if (array[cursor2] < array[cursor1])
            {
                Integer tempValue = array[cursor2];
                Integer writeCursor = cursor2;
                Integer readCursor = cursor1;

                while(readCursor >= 0 && tempValue < array[readCursor])
                {
                    inversionValues.add(new ValuePair(tempValue, array[readCursor]));
                    array[writeCursor] = array[readCursor];
                    --writeCursor;
                    --readCursor;
                }
                array[readCursor + 1] = tempValue;
            }
            ++cursor1;
            ++cursor2;
        }
    }
}

class ValuePair
{
    final private Integer value1, value2;
    public ValuePair(Integer value1, Integer value2)
    {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Integer getValue1()
    {
        return value1;
    }

    public Integer getValue2()
    {
        return value2;
    }
}