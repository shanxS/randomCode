// bracket checker with rules
// [] can enclosed [], {} and ()
// {} can enclosed {}, ()
// () can enclosed only ()
// r4, q1.1 set12

import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        String exp = "[{()()}()]()";//"[{()()[]}]";//"[]{}()";//"[[{()}]]";
        Tester tester = new Tester(exp);
        System.out.print("valid " + tester.isValid());
    }
}

class Tester
{
    private String expression;
    private Stack<Character> stack;

    public Tester(String exp)
    {
        this.expression = exp;
        this.stack = new Stack<>();
    }

    public Boolean isValid()
    {
        for (Character c : expression.toCharArray())
        {
            if (!isExpected(c))
            {
                return false;
            }

            if (isOpening(c))
            {
                stack.push(c);
            }
            else
            {
                if (c != getClosingFor(stack.peek()))
                {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.size() == 0 ? true : false;
    }

    private Character getClosingFor(Character c)
    {
        if (c == '[')
        {
            return ']';
        }
        else if (c == '{')
        {
            return '}';
        }
        else if (c == '(')
        {
            return ')';
        }

        return null;
    }

    private boolean isOpening(Character c)
    {
        if (c == '['
                || c == '{'
                || c == '(')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isExpected(Character c)
    {
        if (stack.size() == 0)
        {
            return true;
        }

        Character top  = stack.peek();
        if (top == '[')
        {
            if (c == '[' || c == ']'
                    || c == '{' || c == '}'
                    || c == '(' || c == ')')
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (top == '{')
        {
            if (c == '{' || c == '}'
                    || c == '(' || c == ')')
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (top == '(')
        {
            if (c == '(' || c == ')')
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }
}