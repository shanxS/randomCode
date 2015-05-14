// stack with O(1) time to get max
// r3, q1, set19

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        ModifiedStack ms = new ModifiedStack();
        ms.push(0);
        System.out.println(ms.getMax() + " " + ms.peek());
        ms.push(1);
        System.out.println(ms.getMax() + " " + ms.peek());
        ms.push(5);
        System.out.println(ms.getMax() + " " + ms.peek());
        ms.push(3);
        System.out.println(ms.getMax() + " " + ms.peek());
        ms.push(5);
        System.out.println(ms.getMax() + " " + ms.peek());
        ms.push(2);
        System.out.println(ms.getMax() + " " + ms.peek());

        ms.pop();
        System.out.println(ms.getMax() + " " + ms.peek());
        ms.pop();
        System.out.println(ms.getMax() + " " + ms.peek());
        ms.pop();
        System.out.println(ms.getMax() + " " + ms.peek());
        ms.pop();
        System.out.println(ms.getMax() + " " + ms.peek());


    }
}


class ModifiedStack
{
    private List<Integer> stack;
    private List<Integer> max;

    public ModifiedStack()
    {
        this.stack = new ArrayList<>();
        this.max = new ArrayList<>();
    }

    public Integer getMax()
    {
        return (max.size() > 0) ? max.get(max.size()-1) : null;
    }

    public Integer peek()
    {
        return (stack.size() > 0) ? stack.get(stack.size()-1) : null;
    }

    public void push(Integer value)
    {
        stack.add(value);
        if (max.size() == 0)
        {
            max.add(value);
        }
        else if (max.size() > 0 && max.get(max.size()-1) <= value)
        {
            max.add(value);
        }

    }

    public Integer pop()
    {
        Integer value = stack.get(stack.size()-1);
        stack.remove(stack.size() - 1);

        if (max.get(max.size()-1) == value)
        {
            max.remove(max.size() - 1);
        }

        return value;
    }

    public Integer size()
    {
        return stack.size();
    }
}