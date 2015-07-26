// stock span problem

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {100, 80, 60, 70, 60, 75, 85};
        Stack<Integer> stack = new Stack<>();

        Integer[] shadow = new Integer[ar.length];
        for (Integer i=0; i<ar.length; ++i)
        {
            while (stack.size() > 0 && ar[stack.peek()] < ar[i])
            {
                stack.pop();
            }

            if (stack.size() == 0)
            {
                shadow[i] = 0;
            }
            else
            {
                shadow[i] = stack.peek() + 1;
            }
            stack.push(i);
        }

        Integer maxShadow = Integer.MIN_VALUE;
        Integer value = null;
        for (Integer i=0; i<ar.length; ++i)
        {
            if (maxShadow < (i - shadow[i] + 1))
            {
                maxShadow = (i - shadow[i] + 1);
                value = ar[i];
            }
        }

        System.out.print(maxShadow + " " + value);
    }
}