// postfix to infix with minimum braces
// r2, q1, set18 complete

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        //String str = "342*15-23^^/+";
        String in = "2*((4-2)+1)/3";
        InfixToPostfix itp = new InfixToPostfix(in);

        List<Character> postfix = itp.getPost();
        for (Character c : postfix)
        {
            System.out.print(c);
        }
        System.out.println();

        PostfixToExressionTree postfixToExressionTree = new PostfixToExressionTree(postfix);

        ExpressionTreeToInfix expressionTreeToInfix = new ExpressionTreeToInfix(postfixToExressionTree.getExpressionTree());
        System.out.print(expressionTreeToInfix.getInfix());
    }
}

class InfixToPostfix
{
    private List<Character> infix;
    private List<Character> postfix;
    private List<Character> symbols;
    private List<Character> precedenceList;

    public InfixToPostfix(String infixStr)
    {
        this.infix = new ArrayList<>();
        for(Character c : infixStr.toCharArray())
        {
            infix.add(c);
        }

        this.postfix = new ArrayList<>();
        this.symbols = new ArrayList<>();
        this.precedenceList = new ArrayList<>();
        precedenceList.add('-');
        precedenceList.add('+');
        precedenceList.add('*');
        precedenceList.add('/');
        precedenceList.add('^');
    }

    public List<Character> getPost()
    {
        for (Character c : infix)
        {
            if (Character.isDigit(c))
            {
                postfix.add(c);
            }
            else
            {
                if (c == '(')
                {
                    symbols.add(c);
                }
                else if (c == ')')
                {
                    while(symbols.get(symbols.size()-1) != '(')
                    {
                        postfix.add(symbols.get(symbols.size()-1));
                        symbols.remove(symbols.size()-1);
                    }
                    symbols.remove(symbols.size()-1);
                }
                else
                {
                    if (symbols.size() > 0 && !lowPrecedence(c, symbols.get(symbols.size()-1)))
                    {
                        while(symbols.size() > 0 && !lowPrecedence(c, symbols.get(symbols.size()-1)))
                        {
                            postfix.add(symbols.get(symbols.size()-1));
                            symbols.remove(symbols.size()-1);
                        }
                    }
                    symbols.add(c);
                }
            }
        }

        for (Integer cursor = symbols.size()-1; cursor >= 0; --cursor)
        {
            postfix.add(symbols.get(cursor));
        }

        return postfix;
    }

    private boolean lowPrecedence(Character newCharacter, Character oldOharacter)
    {
        if ((newCharacter == '/' && oldOharacter == '*')
                || (oldOharacter == '/' && newCharacter == '*'))
        {
            return false;
        }

        Integer newIndex = precedenceList.indexOf(newCharacter);
        Integer oldIndex = precedenceList.indexOf(oldOharacter);

        if (newIndex < oldIndex)
        {
            return false;
        }

        // for higher or equal precedence
        return true;
    }
}

class ExpressionTreeToInfix
{
    private Node head;
    private String infix;
    private List<Character> precedenceList;

    public ExpressionTreeToInfix(Node head)
    {
        this.head = head;
        this.infix = "";
        this.precedenceList = new ArrayList<>();
        precedenceList.add('-');
        precedenceList.add('+');
        precedenceList.add('*');
        precedenceList.add('/');
        precedenceList.add('^');
    }

    public String getInfix()
    {
        parse(head, null);

        return infix;
    }



    private void parse(Node node, Character previousOperator)
    {
        if (node == null)
        {
            return;
        }

        if (Character.isDigit(node.getCharacter()))
        {
            infix += node.getCharacter();
        }
        else
        {
            if (isPrecedenceHigher(previousOperator, node.getCharacter()))
            {
                infix += '(';
            }

            parse(node.getLeft(), node.getCharacter());
            infix += node.getCharacter();
            parse(node.getRight(),node.getCharacter());

            if (isPrecedenceHigher(previousOperator, node.getCharacter()))
            {
                infix += ')';
            }
        }
    }

    private boolean isPrecedenceHigher(Character previousOperator, Character thisOperattor)
    {
        if (previousOperator == null)
        {
            return false;
        }

        if ((previousOperator == '*' && thisOperattor == '/')
                || (previousOperator == '/' && thisOperattor == '*'))
        {
            return false;
        }

        Integer previousOperatorIndex = precedenceList.indexOf(previousOperator);
        Integer thisOperatorIndex = precedenceList.indexOf(thisOperattor);
        if (previousOperatorIndex > thisOperatorIndex)
        {
            return true;
        }

        return false;
    }
}

class PostfixToExressionTree
{
    private List<Character> postfix;
    private Stack<Node> stack;

    public PostfixToExressionTree(List<Character> postfix)
    {
        this.postfix = postfix;
        this.stack = new Stack<>();
    }

    public Node getExpressionTree()
    {
        for (Character c : postfix)
        {
            if (Character.isDigit(c))
            {
                stack.push(new Node(c));
            }
            else
            {
                Node right = stack.pop();
                Node left = stack.pop();

                Node thisNode = new Node(c);
                thisNode.setLeft(left);
                thisNode.setRight(right);
                stack.push(thisNode);
            }
        }

        return stack.peek();
    }
}

class Node
{
    private Character character;
    private Node left, right;

    public Node(Character character)
    {
        this.character = character;
        this.left = null;
        this.right = null;
    }

    public Character getCharacter()
    {
        return character;
    }

    public void setCharacter(Character character)
    {
        this.character = character;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }
}