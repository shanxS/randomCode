// stack with O(1) max
// r1, q2, set28

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        ModifiedStack ms = new ModifiedStack();
        Integer[] array = new Integer[]{1,1,2,3,4,4,3,2,1};
        for (Integer value : array)
        {
            ms.push(value);
            System.out.println("max " + ms.getMax());
        }

        while(ms.size() > 0)
        {
            System.out.println("pop " + ms.pop() + " max " + ms.getMax());
        }

    }
}

class ModifiedStack
{
    private Stack<Integer> max;
    private Stack<Integer> stack;

    public ModifiedStack()
    {
        this.max = new Stack<>();
        this.stack = new Stack<>();
    }

    public void push(Integer value)
    {
        stack.push(value);
        if (max.size() > 0)
        {
            if (max.peek() <= value)
            {
                max.push(value);
            }
        }
        else
        {
            max.push(value);
        }
    }

    public Integer size()
    {
        return stack.size();
    }

    public Integer pop()
    {
        if (stack.size() == 0)
        {
            return null;
        }

        Integer poppedValue = stack.pop();
        if (max.peek() == poppedValue)
        {
            max.pop();
        }

        return poppedValue;
    }

    public Integer getMax()
    {
        return (max.size() > 0) ? max.peek() : null;
    }
}