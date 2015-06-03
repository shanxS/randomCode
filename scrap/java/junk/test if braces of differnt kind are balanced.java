// test if braces of differnt kind are balanced

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Stack<Character> stack = new Stack<>();
        String s = "[]{{}(})";

        for (Character c : s.toCharArray())
        {
            if (isOpening(c))
            {
                stack.push(c);
            }
            else
            {
                if (getClosingFor(stack.peek()) == c)
                {
                    stack.pop();
                }
                else
                {
                    break;
                }
            }
        }

        if (stack.size() != 0)
        {
            System.out.println("not balanced");
        }
        else
        {
            System.out.println("balanced");
        }
    }

    private static Character getClosingFor(Character c)
    {
        if (c == '[')
        {
            return ']';
        }

        if (c == '{')
        {
            return '}';
        }

        if (c == '(')
        {
            return ')';
        }

        return null;
    }

    private static boolean isOpening(Character c)
    {
        if (c == '[' || c == '{' || c == '(')
        {
            return true;
        }

        return false;
    }
}