// The Stock Span Problem

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {100, 80, 60, 70, 60, 75, 85};
        Integer[] result = new Integer[array.length];
        Stack<Integer> stack = new Stack<>();

        Integer cursor = array.length-1;
        stack.push(cursor);
        while (cursor >= 0)
        {
            while (stack.size() > 0 && array[stack.peek()] < array[cursor])
            {
                Integer previousIndex = stack.pop();
                result[previousIndex] = previousIndex - cursor;
            }

            stack.push(cursor);
            --cursor;
        }
        
        for (Integer v : result)
        {
            System.out.print(v + " ");
        }
    }
}