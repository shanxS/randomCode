// find next greatest number using same digits
// shortlis, q1, set16

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer number = 218765;
        NextMaxFinder nmf = new NextMaxFinder(number);
        System.out.print(nmf.find());
    }
}

class NextMaxFinder
{
    Integer number;
    List<Integer> list;

    public NextMaxFinder(Integer number)
    {
        this.number = number;
        this.list = new ArrayList<>();
    }

    public Integer find()
    {
        parse();
        return (getNextLargest()) ? convertToNumber() : null;
    }

    private boolean getNextLargest()
    {
        Integer smallestLargeIndex = 0;
        Integer smallestLarge = list.get(smallestLargeIndex);

        for (Integer i=1; i<list.size()-1; ++i)
        {
            if (list.get(i) < smallestLarge)
            {
                smallestLarge = list.get(i);
                smallestLargeIndex = i;
            }

            if (smallestLarge > list.get(i+1))
            {
                Collections.swap(list, i+1, smallestLargeIndex);
                Collections.sort(list.subList(0, i+1));
                Collections.reverse(list.subList(0, i+1));

                return true;
            }
        }

        return false;
    }

    private Integer convertToNumber()
    {
        Integer value = 0;

        for (Integer i=list.size()-1; i>=0; --i)
        {
            value += (int)Math.pow(10, i) * list.get(i);
        }

        return  value;
    }

    private void parse()
    {
        Integer cache = number;
        while(cache > 0)
        {
            list.add(cache % 10);
            cache /= 10;
        }
    }


}