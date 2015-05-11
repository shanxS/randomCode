// LIS
// written q3 set17

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[] {1,2,10,5,4,7,3,12,10};
        LIS lis = new LIS(array);
        System.out.print(lis.findLength());
    }
}

class LIS
{
    private Integer[] array;

    public LIS(Integer[] array)
    {
        this.array = array;
    }

    public Integer findLength()
    {
        Integer length = 0;
        List<Integer> list = new ArrayList<>();

        for (Integer i=0; i<array.length; ++i)
        {
            if (i==0)
            {
                list.add(array[i]);
                ++length;
            }
            else if (list.get(length-1) < array[i])
            {
                list.add(array[i]);
                ++length;
            }
            else if (list.get(length-1) > array[i])
            {
                Integer replacementIndex = getReplacementIndex(list, array[i]);
                list.set(replacementIndex, array[i]);
            }

            for (Integer value : list)
            {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        return length;
    }

    private Integer getReplacementIndex(List<Integer> list, Integer value)
    {
        Integer cursor = list.size() - 1;
        while(cursor >= 0)
        {
            if (list.get(cursor) < value)
            {
                return cursor + 1;
            }
            --cursor;
        }

        return 0;
    }
}