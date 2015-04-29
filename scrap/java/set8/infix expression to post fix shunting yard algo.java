// infix expression to post fix shunting yard algo

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String operatiorn = "3+4*2/(1-5)^2^3";
        PostFix pf = new PostFix(operatiorn);
        Stack<Character> post = pf.getPostfix();
        while (post.size() >0)
        {
            System.out.print(post.pop() + " ");
        }

    }
}

class PostFix
{
    private String infix;
    private Stack<Character> numberStack;
    private Stack<Character> symbolStack;
    private List<Character> symbolTable;

    public PostFix(String infix)
    {
        this.infix = infix;
        this.numberStack = new Stack<>();
        this.symbolStack = new Stack<>();
        this.symbolTable = new ArrayList<>();
        symbolTable.add('-');
        symbolTable.add('+');
        symbolTable.add('*');
        symbolTable.add('/');
        symbolTable.add('^');
    }

    public Stack<Character> getPostfix()
    {
        for (Character c : infix.toCharArray())
        {
            if (isOperand(c))
            {
                performOperandOperation(c);
            }
            else
            {
                numberStack.add(c);
            }
        }

        while (symbolStack.size() > 0)
        {
            numberStack.push(symbolStack.pop());
        }

        return numberStack;
    }

    private void performOperandOperation(Character c) {
        if (c == ')')
        {
            while(symbolStack.peek() != '(')
            {
                numberStack.push(symbolStack.pop());
            }
            symbolStack.pop();
        }
        else if (c == '(' || (symbolStack.size()>0 && symbolTable.contains(c) && symbolStack.peek() == '('))
        {
            symbolStack.push(c);
        }
        else
        {
           while(symbolStack.size()>0 && lessPrecedence(c, symbolStack.peek()))
           {
               numberStack.push(symbolStack.pop());
           }

            symbolStack.push(c);
        }
    }

    private boolean lessPrecedence(Character op1, Character op2) {

        if ((op1 == '*' && op2 == '/') || (op1 == '/' && op2 == '*'))
        {
            return true;
        }

        Integer op1Index = null;
        Integer op2Index = null;

        for (Integer i=0; (i<symbolTable.size()) && (op1Index == null || op2Index == null); ++i)
        {
            if (symbolTable.get(i) == op1)
            {
                op1Index = i;
            }

            if (symbolTable.get(i) == op2)
            {
                op2Index = i;
            }
        }

        if (op2Index > op1Index) {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isOperand(Character c) {
        if (symbolTable.contains(c) || c == '(' || c == ')')
        {
            return true;
        }
        else {
            return false;
        }
    }


}