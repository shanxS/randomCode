// max area of rectangle in histogram

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {6,2,5,4,5,2,6};
//        Integer[] ar = {6,2,5,4,5,1,6};

        Stack<Integer> stack = new Stack<>();

        Integer[] start = new Integer[ar.length];
        Integer[] end = new Integer[ar.length];

        for (Integer i=0; i<ar.length; ++i)
        {
            while (stack.size() > 0 && ar[stack.peek()] > ar[i])
            {
                Integer removedIndex = stack.pop();
                end[removedIndex] = i-1;
            }

            if (stack.size() == 0)
            {
                start[i] = 0;
            }
            else
            {
                start[i] = stack.peek() + 1;
            }
            stack.push(i);
        }

        while (stack.size() > 0)
        {
            Integer removedIndex = stack.pop();
            end[removedIndex] = ar.length-1;
        }

        Integer maxValue = Integer.MIN_VALUE;
        Integer value = -1;
        for (Integer i=0; i<ar.length; ++i)
        {
            System.out.println(ar[i] + " - " + start[i] + " " + end[i]);
            if ((maxValue < ar[i] * (end[i] - start[i] + 1)))
            {
                maxValue = ar[i] * (end[i] - start[i] + 1);
                value = ar[i];
            }
        }

        System.out.print(value + " " + maxValue);
    }
}