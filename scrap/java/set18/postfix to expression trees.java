// postfix to expression trees
// r2, q1, set18, partial

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        String str = "342*15-/+";
        List<Character> postfix = new ArrayList<>();
        for (Character c : str.toCharArray())
        {
            postfix.add(c);
        }

        PostfixToExressionTree postfixToExressionTree = new PostfixToExressionTree(postfix);
        postfixToExressionTree.getExpressionTree();
        
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