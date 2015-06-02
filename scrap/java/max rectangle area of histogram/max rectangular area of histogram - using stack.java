// max rectangular area of histogram - using stack

import java.util.Stack;

public class Main
{
    static Integer[] bars = new Integer[] {6, 1, 5, 4, 5, 2, 6};
    public static void main(String[] er)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(bars[0]);

        Integer maxSoFer = Integer.MIN_VALUE;

        for (Integer cursor = 1; cursor<bars.length; ++cursor)
        {
            if (stack.peek() > bars[cursor])
            {
                stack.push(bars[cursor]);
            }
            else
            {
                Integer thisSize = (stack.size() + 1) * stack.peek();
                if (thisSize > maxSoFer)
                {
                    maxSoFer = thisSize;
                }

                stack.clear();
                stack.push(bars[cursor]);
            }
        }

        System.out.print(maxSoFer);

    }
}