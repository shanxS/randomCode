// count island in 2d array

import java.util.Stack;

public class Main
{
    private static Integer[][] sea = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1},
    };

    private static final Integer INVALID = -1;

    public static void main(String[] er)
    {

        Integer count = 0;
        for (Integer r = 0; r<sea.length; ++r)
        {
            for (Integer c=0; c<sea[r].length; ++c)
            {
                if (sea[r][c] != INVALID && sea[r][c] != 0)
                {
                    process (r, c);
                    ++count;
                }
            }
        }

        System.out.print(count);

    }

    private static void process(Integer r, Integer c)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(c);
        stack.push(r);

        while (stack.size() > 0)
        {
            Integer thisR = stack.pop();
            Integer thisC = stack.pop();
            sea[thisR][thisC] = INVALID;

            pushUp(stack, thisR, thisC);
            pushDown(stack, thisR, thisC);

            pushLeft(stack, thisR, thisC);
            pushRight(stack, thisR, thisC);

            pushLeftUp(stack, thisR, thisC);
            pushRightUp(stack, thisR, thisC);

            pushLeftDown(stack, thisR, thisC);
            pushRightDown(stack, thisR, thisC);


        }
    }

    private static void pushRightDown(Stack<Integer> stack, Integer r, Integer c)
    {
        if (c+1 < sea[0].length && r+1 < sea.length
                && sea[r+1][c+1] != 0 && sea[r+1][c+1] != INVALID)
        {
            stack.push(c+1);
            stack.push(r+1);
        }
    }

    private static void pushLeftDown(Stack<Integer> stack, Integer r, Integer c)
    {
        if (c-1 >= 0 && r+1 < sea.length
            && sea[r+1][c-1] != 0 && sea[r+1][c-1] != INVALID)
        {
            stack.push(c-1);
            stack.push(r+1);
        }
    }

    private static void pushRightUp(Stack<Integer> stack, Integer r, Integer c)
    {
        if (c+1 < sea[0].length && r-1 >= 0
                && sea[r-1][c+1] != 0 && sea[r-1][c+1] != INVALID)
        {
            stack.push(c+1);
            stack.push(r-1);
        }
    }

    private static void pushLeftUp(Stack<Integer> stack, Integer r, Integer c)
    {
        if (c-1 >= 0 && r-1 >=0
            && sea[r-1][c-1] != 0 && sea[r-1][c-1] != INVALID)
        {
            stack.push(c-1);
            stack.push(r-1);
        }
    }

    private static void pushRight(Stack<Integer> stack, Integer r, Integer c)
    {
        if (c+1 < sea[0].length && sea[r][c+1] != 0 && sea[r][c+1] != INVALID)
        {
            stack.push(c+1);
            stack.push(r);
        }
    }

    private static void pushLeft(Stack<Integer> stack, Integer r, Integer c)
    {
        if (c-1 >= 0 && sea[r][c-1] != 0 && sea[r][c-1] != INVALID)
        {
            stack.push(c-1);
            stack.push(r);
        }
    }

    private static void pushDown(Stack<Integer> stack, Integer r, Integer c)
    {
        if (r+1 < sea.length && sea[r+1][c] != 0 && sea[r+1][c] != INVALID)
        {
            stack.push(c);
            stack.push(r+1);
        }
    }

    private static void pushUp(Stack<Integer> stack, Integer r, Integer c)
    {
        if (r-1 >= 0 && sea[r-1][c] != 0 && sea[r-1][c] != INVALID)
        {
            stack.push(c);
            stack.push(r-1);
        }
    }
}