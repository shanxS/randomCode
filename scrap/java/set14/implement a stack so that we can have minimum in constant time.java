// implement a stack so that we can have minimum in constant time
// r2, q2, set14

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{2,4,1,3,0};
        MinStack minStack = new MinStack();
        for (Integer i : array)
        {
            minStack.push(i);
            System.out.println(minStack.getMin());
        }

        System.out.println("---------------------------");

        while (minStack.size() > 0)
        {
            minStack.pop();
            System.out.println(minStack.getMin());
        }
    }
}

class MinStack
{
    private Stack<Integer> stack;
    private List<Integer> list;

    public MinStack()
    {
        this.stack = new Stack<>();
        this.list = new ArrayList<>();
    }

    public Integer size()
    {
        return stack.size();
    }

    public void push(Integer value)
    {
        stack.push(value);
        if(list.size() == 0)
        {
            list.add(value);
        }
        else
        {
            if (value < getMin())
            {
                list.add(value);
            }
        }
    }

    public Integer getMin()
    {
        if (list.size() > 0)
        {
            return list.get(list.size() - 1);
        }
        return null;
    }

    public Integer pop()
    {
        Integer value = stack.pop();

        if (value == getMin())
        {
            list.remove(value);
        }

        return value;
    }

}