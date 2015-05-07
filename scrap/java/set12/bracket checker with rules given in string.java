// bracket checker with rules given in string
// this is follow up question to r4 q1.1, set 12

// Two arrays are given.
// One array contains symbols and second one contains expressions. Symbol array contains opening symbol at even index and closing symbols at odd index just after opening symbol. Index is starting from 0. Opening symbol at index i can only contain symbols from i to 2n-1, If there n pairs of symbols. Now check that expression in the expression array is valid or not.

import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        String rule = "[]{}()";
        String exp = "[{()()}()]()";
//        String exp = "[{()()[]}]";
//        String exp = "[]{}()";
        //String exp = "[[{()}]]";
        Tester tester = new Tester(rule, exp);
        System.out.print("valid " + tester.isValid());
    }
}

class Tester
{
    private String expression;
    private String rule;
    private Stack<Character> stack;

    public Tester(String rule, String exp)
    {
        this.rule = rule;
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
        for (Integer i=0; i<rule.length(); ++i)
        {
            if (c == rule.charAt(i))
            {
                return rule.charAt(i+1);
            }
        }

        return null;
    }

    private Boolean isOpening(Character c)
    {
        for (Integer i=0; i<rule.length(); ++i)
        {
            if (c == rule.charAt(i))
            {
                return (i%2 == 0) ? true : false;
            }
        }

        return null;
    }

    private boolean isExpected(Character c)
    {
        if (stack.size() == 0)
        {
            return true;
        }

        Character top  = stack.peek();
        Integer topIndex = -1;
        Integer cIndex = -1;

        for (Integer i=0; i<rule.length(); ++i)
        {
            if (top == rule.charAt(i))
            {
                topIndex = i;
            }
            if (c == rule.charAt(i))
            {
                cIndex = i;
            }
        }

        return (topIndex <= cIndex) ? true : false;
    }
}