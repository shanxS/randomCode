// repalce each element in array with max of all the elements on right
// http://www.geeksforgeeks.org/replace-every-element-with-the-greatest-on-right-side/

import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{16,17,4,3,5,2};
        MaxReplacer mr = new MaxReplacer(array);
        mr.replace();
    }
}

class MaxReplacer
{

    private List<Integer> list;

    public MaxReplacer(Integer[] array)
    {
        this.list = Arrays.asList(array);
    }

    public void replace()
    {
        Integer maxSoFar = list.get(list.size()-1);
        list.set(list.size()-1, -1);

        for (Integer i=list.size()-2; i>=0; --i)
        {
            Integer number = list.get(i);
            list.set(i, maxSoFar);

            if (maxSoFar < number)
            {
                maxSoFar = number;
            }
        }

        list.stream().forEach(x -> System.out.print(" " + x));
    }
}