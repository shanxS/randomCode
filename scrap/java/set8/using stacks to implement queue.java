// using stacks to implement queue
// r1, q2, set8

import java.util.Stack;

public class Main{
    public static void main(String[] args) {
        StackAsQueue saq = new StackAsQueue();
        saq.push(1);
        System.out.println("push complete " + saq.peek());
        saq.push(2);
        System.out.println("push complete " + saq.peek());
        saq.push(3);
        System.out.println("push complete " + saq.peek());
        saq.push(4);
        System.out.println("push complete " + saq.peek());

        saq.pop();
        System.out.println("pop complete " + saq.peek());
        saq.push(10);
        System.out.println("push complete " + saq.peek());

        saq.pop();
        System.out.println("pop complete " + saq.peek());
        saq.pop();
        System.out.println("pop complete " + saq.peek());
        saq.pop();
        System.out.println("pop complete " + saq.peek());
        saq.pop();
        System.out.println("pop complete " + saq.peek());
    }

}

class StackAsQueue
{
    private Stack<Integer> primaryStack, secondaryStack;

    public StackAsQueue() {
        primaryStack = new Stack<>();
        secondaryStack = new Stack<>();
    }

    public void push(Integer value){
        if (secondaryStack.size() != 0)
        {
            transferStack(secondaryStack, primaryStack);
        }

        primaryStack.push(value);
    }

    private void transferStack(Stack<Integer> source, Stack<Integer> target) {
        while(source.size() > 0){
            target.push(source.pop());
        }
    }


    public Integer pop() {
        if (primaryStack.size() == 0 && secondaryStack.size() == 0)
        {
            return null;
        }

        if (primaryStack.size() > 0)
        {
            transferStack(primaryStack, secondaryStack);
        }

        return secondaryStack.pop();
    }

    public Integer peek() {
        if (primaryStack.size() == 0 && secondaryStack.size() == 0)
        {
            return null;
        }

        if (primaryStack.size() > 0)
        {
            transferStack(primaryStack, secondaryStack);
        }


        return secondaryStack.peek();
    }
}