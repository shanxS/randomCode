// shunting yard practice

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        ShuntingYard sy = new ShuntingYard("3+4*2/(1-5)^2^3");
        sy.getPostfix().stream().forEach(x -> System.out.print(x + " "));

    }
}


class ShuntingYard
{
    private String expression;
    private Stack<Character> numberStack;
    private Stack<Character> opStack;
    private List<Character> opPrecedence;

    public ShuntingYard(String exp)
    {
        this.expression = exp;
        this.numberStack = new Stack<>();
        this.opStack = new Stack<>();
        this.opPrecedence = new ArrayList<>();
        opPrecedence.add('-');
        opPrecedence.add('+');
        opPrecedence.add('*');
        opPrecedence.add('/');
        opPrecedence.add('^');

    }

    public List<Character> getPostfix()
    {
        for (Character c : expression.toCharArray())
        {
            if (Character.isDigit(c))
            {
                numberStack.push(c);
            }
            else
            {
                if (c == ')')
                {
                    while (opStack.peek() != '(') {
                        numberStack.push(opStack.pop());
                    }
                    opStack.pop();
                    continue;
                }
                else if (opStack.size()>0 && opPrecedence.contains(c) && opStack.peek() != '(' && isLowerPrecedence(c, opStack.peek()))
                {
                    while(opStack.size()>0 && isLowerPrecedence(c, opStack.peek()))
                    {
                        numberStack.push(opStack.pop());
                    }
                }

                opStack.push(c);
            }
        }


        while(opStack.size() > 0)
        {
            numberStack.push(opStack.pop());
        }
        List<Character> post = new ArrayList<>();
        while(numberStack.size() > 0)
        {
            post.add(numberStack.pop());
        }


        return post;
    }

    private boolean isLowerPrecedence(Character incoming, Character old) {

        if ((incoming == '*' && old == '/') || (incoming == '/' && old == '*'))
        {
            return true;
        }

        Integer incomingIndex = null;
        Integer oldIndex = null;

        for(Integer i=0; i<opPrecedence.size(); ++i)
        {
            if (incoming == opPrecedence.get(i))
            {
                incomingIndex = i;
            }

            if (old == opPrecedence.get(i))
            {
                oldIndex = i;
            }
        }

        if (oldIndex > incomingIndex)
        {
            return true;
        }
        else {
            return false;
        }
    }

}