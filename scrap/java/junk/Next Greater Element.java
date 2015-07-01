// Next Greater Element

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {4, 5, 2, 25};//{5, 7, 2, 6, 4, 3, 25};
        Integer[] result = new Integer[array.length];
        Stack<Integer> stack = new Stack<>();

        Integer cursor = array.length - 1;
        result[cursor] = -1;
        stack.push(array[cursor]);
        --cursor;

        while (cursor >= 0)
        {
            if (stack.size() > 0)
            {
                while (stack.size() > 0 && array[cursor] > stack.peek() )
                {
                    stack.pop();
                }

                if (stack.size() > 0)
                {
                    result[cursor] = stack.peek();
                }
                else
                {
                    result[cursor] = -1;
                }

                stack.push(array[cursor]);
            }

            --cursor;
        }

        for (Integer i : result)
        {
            System.out.print(i + " ");
        }
    }
}