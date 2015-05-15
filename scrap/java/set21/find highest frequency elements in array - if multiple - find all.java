// find highest frequency elements in array - if multiple - find all
// online q1, set21

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] wer)
    {
        Integer range = 7;
        Integer[] array = new Integer[] {1,2,3,4,3,2,5};

        for (Integer i=0; i<array.length; ++i)
        {
            array[array[i]%range] += range;
        }

        List<Integer> list = new ArrayList<>();
        Integer max = Integer.MIN_VALUE;
        for (Integer i=0; i<array.length; ++i)
        {
            if (max < array[i] - (array[i]%range))
            {
                max = array[i] - (array[i]%range);
                list.clear();
                list.add(i);
            }
            else if (max == array[i] - (array[i]%range))
            {
                list.add(i);
            }
        }

        list.stream().forEach(x -> System.out.print(x + " "));
    }
}