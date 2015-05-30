// lis in NlogN practice

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        LIS lis = new LIS();
        System.out.print(lis.getLIS(array));
    }
}

class LIS
{
    public Integer getLIS(Integer[] array)
    {
        List<Integer> numbers = new ArrayList<>();
        Integer length = 0;

        for (Integer cursor = 0; cursor<array.length; ++cursor)
        {
            if (cursor == 0)
            {
                numbers.add(array[cursor]);
                ++length;
            }
            else if(array[cursor] >= numbers.get(length-1))
            {
                numbers.add(array[cursor]);
                ++length;
            }
            else
            {
                Integer replcementIndex = findReplcementIndex(array[cursor], numbers);
                numbers.set(replcementIndex, array[cursor]);
            }
        }

        return length;
    }

    private Integer findReplcementIndex(Integer number, List<Integer> numbers)
    {
        Integer index = numbers.size()-1;
        while(index > 0 && number < numbers.get(index))
        {
            --index;
        }

        return index+1;
    }
}