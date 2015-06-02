// max rectangular area of histogram - using stack

import java.util.Stack;

public class Main
{
    static Integer[] bars = new Integer[] {6, 2, 5, 4, 5, 2, 6};
    public static void main(String[] er)
    {
        Stack<Integer> stack = new Stack<>();
        Integer maxSoFer = Integer.MIN_VALUE;

        Integer cursor = 0;
        while (cursor<bars.length)
        {
            Integer value = bars[cursor];

            if (stack.size() == 0 || (bars[stack.peek()] <= value))
            {
                stack.push(cursor);
                ++cursor;
            }
            else
            {
                Integer index = stack.pop();

                Integer thisSize = bars[index] * ((stack.size() == 0) ? cursor : (cursor - stack.peek() - 1));
                if (thisSize > maxSoFer)
                {
                    maxSoFer = thisSize;
                }
            }
        }

        System.out.print(maxSoFer);

    }
}