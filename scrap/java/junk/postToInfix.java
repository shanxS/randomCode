import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        InfixToPostFix i2p = new InfixToPostFix();
        PostfixToInfix p2i = new PostfixToInfix();
        System.out.print(p2i.convert(i2p.convert("3+4*2/(1-5)^2^3")));
    }
}

class PostfixToInfix
{
    public String convert(String post)
    {
        Stack<String> stack = new Stack<>();

        for (Character c : post.toCharArray())
        {
            if (Character.isDigit(c))
            {
                stack.push(c + "");
            }
            else
            {
                String d1 = stack.pop();
                String d2 = stack.pop();

                String res = "( " + d2 + " " + c + " " + d1 + " )";
                stack.push(res);
            }
        }

        return stack.pop();
    }
}

class InfixToPostFix
{
    final private List<Character> operatorPrecedence;
    private Stack<Character> digits, operator;

    public InfixToPostFix()
    {
        operatorPrecedence = new ArrayList<>();
        operatorPrecedence.add('-');
        operatorPrecedence.add('+');
        operatorPrecedence.add('*');
        operatorPrecedence.add('/');
        operatorPrecedence.add('^');
    }

    public String convert(String in)
    {
        digits = new Stack<>();
        operator = new Stack<>();

        for (Character c : in.toCharArray())
        {
            if (Character.isDigit(c))
            {
                digits.push(c);
            }
            else
            {
                if (c == '(')
                {
                    operator.push(c);
                }
                else if (c == ')')
                {
                    while (operator.peek() != '(')
                    {
                        digits.push(operator.pop());
                    }
                    operator.pop();
                }
                else
                {
                    if (operator.size() > 1)
                    {
                        if (isLowerPrecedence(c, operator.peek()))
                        {
                            while (operator.size() > 0 && isLowerPrecedence(c, operator.peek()))
                            {
                                digits.push(operator.pop());
                            }
                        }
                    }

                    operator.push(c);
                }
            }
        }

        while(operator.size() > 0)
        {
            digits.push(operator.pop());
        }

        String post = "";
        while (digits.size() > 0)
        {
            post += digits.pop();
        }

        return (new StringBuilder(post).reverse()).toString();
    }

    private boolean isLowerPrecedence(Character incoming, Character previous)
    {
        if ((incoming == '*' && previous == '/')
            || (previous == '*' && incoming == '/'))
        {
            return true;
        }

        return operatorPrecedence.indexOf(incoming) < operatorPrecedence.indexOf(previous);
    }
}