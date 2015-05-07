// given 3 sorted arrays merge them in least time complexity
// r4, q2

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[][] array = new Integer[][] {
                {1,4,7,10},
                {2,5,8},
                {12,13,14,15}
        };

        Sorter sorter = new Sorter(array);
        for (Integer i : sorter.sort())
        {
            System.out.print(i  + " ");
        }
    }
}

class Sorter
{
    private Integer[][] array;

    public Sorter(Integer[][] array)
    {
        this.array = array;
    }

    public Integer[] sort()
    {
        List<Integer> list = new ArrayList<>();

        Integer[] counters = new Integer[array.length];
        for (Integer i=0; i<counters.length; ++i)
        {
            counters[i] = 0;
        }

        Integer min = Integer.MAX_VALUE;
        Integer minCounter = null;


        while (!triggerBreak(counters))
        {
            for (Integer r=0; r<array.length; ++r)
            {
                if (counters[r] < array[r].length)
                {
                    if (min > array[r][counters[r]])
                    {
                        min  = array[r][counters[r]];
                        minCounter = r;
                    }
                }
            }
            list.add(min);
            counters[minCounter] += 1;
            min = Integer.MAX_VALUE;
            minCounter = null;
        }

        return list.toArray(new Integer[list.size()]);
    }

    private boolean triggerBreak(Integer[] counters)
    {
        Boolean allEnd = true;

        for (Integer i=0; i<counters.length && allEnd; ++i)
        {
            if (counters[i] < array[i].length)
            {
                allEnd = false;
            }
        }

        return allEnd;
    }
}