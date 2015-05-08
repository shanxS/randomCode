// getting next greater number with same set of digits
// r1, q1, set13

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        NextLargestGetter nlg = new NextLargestGetter(218765);
        System.out.print(nlg.getNextLargest());
    }
}

class NextLargestGetter
{
    private List<Integer> list;
    private Integer value;

    public NextLargestGetter(Integer value)
    {
        this.value = value;
        this.list = new ArrayList<>();
    }

    public Integer getNextLargest()
    {
        parse();

        Integer cursor = 1;
        Integer minIndex = 0;

        while(cursor < list.size())
        {
            if (list.get(cursor) < list.get(cursor - 1))
            {
                Collections.swap(list, cursor, minIndex);
                Collections.sort(list.subList(0, cursor));
                Collections.reverse(list.subList(0, cursor));
                break;
            }

            if (list.get(cursor) < list.get(minIndex))
            {
                minIndex = cursor;
            }

            ++cursor;
        }

        if (cursor == list.size())
        {
            return null;
        }

        return convertListToInteger();
    }

    private Integer convertListToInteger()
    {
        Integer result = 0;

        for (Integer i=0; i<list.size(); ++i)
        {
            result += list.get(i) * (int)Math.pow(10, i);
        }

        return result;
    }

    private void parse()
    {
        Integer result = value;

        while(result > 0)
        {
            list.add(result % 10);
            result = result/10;
        }
    }
}