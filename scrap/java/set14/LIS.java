// LIS
// r6, q3, set14

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        LIS lis = new LIS(new Integer[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
        System.out.print(lis.find());
    }
}

class LIS
{
    private Integer[] array;

    public LIS(Integer[] array)
    {
        this.array = array;
    }

    public Integer find()
    {
        List<Integer> LIS = new ArrayList<>();
        Integer length = 0;

        for (Integer i=0; i<array.length; ++i)
        {
            if (i == 0)
            {
                length = 1;
                LIS.add(array[i]);
            }
            else if (LIS.get(length - 1) < array[i])
            {
                LIS.add(array[i]);
                ++length;
            }
            else
            {
                Integer replacementIndex = findReplacementIndex(LIS, array[i]);
                LIS.set(replacementIndex, array[i]);
            }
        }

        return length;
    }

    private Integer findReplacementIndex(List<Integer> LIS, Integer target)
    {
        for (Integer i=LIS.size()-1; i>=0; --i)
        {
            if (LIS.get(i) < target)
            {
                return i+1;
            }
        }

        return null;
    }
}