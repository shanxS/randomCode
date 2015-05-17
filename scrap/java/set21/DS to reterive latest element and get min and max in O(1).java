// DS to reterive latest element and get min and max in O(1)
// r3, q1, set21


import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,4,3,2,1};
        ModifiedStack ms = new ModifiedStack();
        for (Integer value : array)
        {
            ms.push(value);
            System.out.println(ms.peek() + " "
                    + ms.getMax() + " "
                    + ms.getMin());
        }

        System.out.println("--------------------");

        while (ms.size() > 0)
        {
            System.out.println(ms.pop() + " "
                    + ms.getMax() + " "
                    + ms.getMin());
        }
    }
}

class ModifiedStack
{
    private List<Integer> list;
    private List<Integer> max;
    private List<Integer> min;

    public ModifiedStack()
    {
        this.list = new ArrayList<>();
        this.max = new ArrayList<>();
        this.min = new ArrayList<>();
    }

    public Integer size()
    {
        return list.size();
    }

    public void push(Integer value)
    {
        Integer minValue = getMin();
        if (minValue != null)
        {
            if (minValue >= value)
            {
                min.add(value);
            }
        }
        else
        {
            min.add(value);
        }

        Integer maxValue = getMax();
        if (maxValue != null)
        {
            if (maxValue <= value)
            {
                max.add(value);
            }
        }
        else
        {
            max.add(value);
        }

        list.add(value);
    }

    public Integer peek()
    {
        if (list.size() > 0)
        {
            return list.get(list.size()-1);
        }

        return null;
    }

    public Integer pop()
    {
        if (list.size() == 0)
        {
            return null;
        }

        Integer value = peek();
        list.remove((int)list.size()-1);
        if (value == getMin())
        {
            min.remove((int)min.size()-1);
        }
        if (value == getMax())
        {
            max.remove((int)max.size()-1);
        }

        return value;
    }

    public Integer getMax()
    {
        if (max.size() > 0)
        {
            return max.get(max.size()-1);
        }

        return null;
    }

    public Integer getMin()
    {
        if (min.size() > 0)
        {
            return min.get(min.size()-1);
        }

        return null;
    }
}