// Sorted order printing of a given array that represents a BST - using stack and loop
// code question: 83

import java.util.Stack;

public class Main
{
    private static Integer[] BSTArray = {4, 2, 5, 1, 3};
    private static final boolean leftDirection = true;
    private static final boolean rightDirection = false;
    private static Stack<IndexDirection> stack = new Stack<>();

    public static void main(String[] er)
    {
        stack.push(new IndexDirection(0, leftDirection));
        pushAllChildren(0, leftDirection);


        while (stack.size() > 0)
        {
            IndexDirection thisIndexDirection = stack.pop();

            System.out.print(BSTArray[thisIndexDirection.getIndex()] + " ");
            if (thisIndexDirection.getDirection() == leftDirection)
            {
                pushRightFollowedByLeft(thisIndexDirection.getIndex());
            }
        }

    }

    private static void pushRightFollowedByLeft(Integer index)
    {
        Integer rightChildIndex = getRightIndex(index);

        if (isValidIndex(rightChildIndex))
        {
            stack.push(new IndexDirection(rightChildIndex, rightDirection));
            pushAllChildren(rightChildIndex, leftDirection);
        }
    }

    private static void pushAllChildren(Integer index, boolean direction)
    {
        Integer childIndex = (direction == leftDirection) ? getLeftIndex(index) : getRightIndex(index);
        while (isValidIndex(childIndex))
        {
            stack.push(new IndexDirection(childIndex, direction));
            childIndex = (direction == leftDirection) ? getLeftIndex(childIndex) : getRightIndex(childIndex);
        }
    }

    private static Integer getRightIndex(Integer index)
    {
        return getLeftIndex(index) + 1;
    }

    private static Integer getLeftIndex(Integer index)
    {
        return (2*index) + 1;
    }

    private static boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < BSTArray.length);
    }

    private static class IndexDirection
    {
        Integer index;
        boolean direction;

        public IndexDirection(Integer index, boolean direction)
        {
            this.index = index;
            this.direction = direction;
        }

        public Integer getIndex()
        {
            return index;
        }

        public void setIndex(Integer index)
        {
            this.index = index;
        }

        public boolean getDirection()
        {
            return direction;
        }

        public void setDirection(boolean direction)
        {
            this.direction = direction;
        }
    }
}