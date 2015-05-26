// get min, max, pop, push in O(1)
// r2, q2, set29

import java.util.LinkedList;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{10,11,9,12,8,13,7};
        ModifiedStack ms = new ModifiedStack();
        for (Integer value : array)
        {
            ms.push(value);
            System.out.println(ms.peek() + " " + ms.getMin() + " " + ms.getMax());
        }
        System.out.println("------------------");
        while(ms.size() > 0)
        {
            System.out.println(ms.pop() + " " + ms.getMin() + " " + ms.getMax());
        }
    }
}

class ModifiedStack
{
    private LinkedList<Integer> min, max, values;

    public ModifiedStack()
    {
        this.min = new LinkedList<>();
        this.max = new LinkedList<>();
        this.values = new LinkedList<>();
    }

    public Integer size()
    {
        return values.size();
    }

    public Integer pop()
    {
        if (values == null)
        {
            return null;
        }

        Integer top = values.get(getLastIndex(values));
        values.remove(getLastIndex(values));
        if (top == getMax())
        {
            max.remove(getLastIndex(max));
        }
        if (top == getMin())
        {
            min.remove(getLastIndex(min));
        }

        return top;
    }

    public void push(Integer value)
    {
        if (values.size() == 0)
        {
            min.add(value);
            max.add(value);
            values.add(value);
        }
        else
        {
            values.add(value);

            if (getMin() >= value)
            {
                min.add(value);
            }

            if (getMax() <= value)
            {
                max.add(value);
            }
        }
    }

    public Integer peek()
    {
        if (values.size() == 0)
        {
            return null;
        }

        return values.get(getLastIndex(values));
    }

    public Integer getMin()
    {
        if (min.size() == 0)
        {
            return null;
        }

        return min.get(getLastIndex(min));
    }

    private int getLastIndex(LinkedList<Integer> linkedList)
    {
        return linkedList.size() - 1;
    }

    public Integer getMax()
    {
        if (max.size() == 0)
        {
            return null;
        }

        return max.get(getLastIndex(max));
    }
}