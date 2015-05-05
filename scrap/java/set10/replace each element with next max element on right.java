// replace each element with next max element on right
// http://www.geeksforgeeks.org/next-greater-element/
// r4, q3, set10

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]/*{13,7,6,12};*/{4,5,2,25};

        NextMaxCopier nmc = new NextMaxCopier(array);
        nmc.copyNextMax();
    }
}

class NextMaxCopier
{
    private List<Integer> list;
    private Stack<Integer> stack;

    public NextMaxCopier(Integer[] array)
    {
        this.list = Arrays.asList(array);
        this.stack = new Stack<>();
    }

    public void copyNextMax()
    {
        stack.push(0);

        for (Integer i  = 1; i<list.size(); ++i)
        {
            while (stack.size() > 0 && list.get(i) > list.get(stack.peek()))
            {
                list.set(stack.peek(), list.get(i));
                stack.pop();
            }

            stack.push(i);
        }

        while (stack.size() > 0)
        {
            list.set(stack.peek(), -1);
            stack.pop();
        }

        list.stream().forEach(x -> System.out.print(x + " "));
    }
}