// Reverse a stack using recursion

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Stack<Integer> stack = new Stack<>();
        Integer[] array = {1,2,3,4};
        for (Integer v : array)
        {
            stack.push(v);
        }

        reverse(stack);

        while (stack.size() > 0)
        {
            System.out.print(stack.pop());
        }
    }

    private static void reverse(Stack<Integer> stack)
    {
        if (stack.size() == 1)
        {
            return;
        }

        Integer top = stack.pop();
        reverse(stack);
        insertToBottom(stack, top);
    }

    private static void insertToBottom(Stack<Integer> stack, Integer bottom)
    {
        if (stack.size() == 0)
        {
            stack.push(bottom);
            return;
        }

        Integer thisTop = stack.pop();
        insertToBottom(stack, bottom);
        stack.push(thisTop);
    }
}