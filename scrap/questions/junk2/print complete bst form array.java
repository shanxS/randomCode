// print complete bst form array

import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {4, 2, 5, 1, 3};
        (new BTPrinter()).print(ar);
    }
}

class BTPrinter
{
    private int[] ar;
    private Stack<Integer> stack;
    public void print(int[] ar)
    {
        this.ar = ar;

        stack = new Stack<>();

        pushLeftChild(0);

        while (stack.size() > 0)
        {
            Integer thisIndex = stack.pop();
            System.out.print(ar[thisIndex] + " ");
            pushLeftChild(getRightChild(thisIndex));
        }
    }

    private int getRightChild(Integer index)
    {
        return getLeftChild(index) + 1;
    }

    private void pushLeftChild(int index)
    {
        while (isValid(index))
        {
            stack.push(index);
            index = getLeftChild(index);
        }
    }

    private boolean isValid(int index)
    {
        return index>=0 && index<ar.length;
    }

    private int getLeftChild(int i)
    {
        return (2*i)+1;
    }
}