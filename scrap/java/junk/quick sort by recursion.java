// quick sort by recursion

import java.util.Stack;

public class Main
{
    private static Integer[] array = {4, 3, 5, 2, 1, 3, 2, 3};
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] er)
    {
        stack.push(0);
        stack.push(array.length - 1);

        while (stack.size() > 0)
        {
            Integer rightMax = stack.pop();
            Integer leftMax = stack.pop();

            Integer dividingIndex = partition(leftMax, rightMax);

            if (dividingIndex > leftMax)
            {
                stack.push(leftMax);
                stack.push(dividingIndex - 1);
            }

            if (dividingIndex < rightMax)
            {
                stack.push(dividingIndex + 1);
                stack.push(rightMax);
            }
        }

        for (Integer v : array)
        {
            System.out.print(v + " ");
        }
    }

    private static Integer partition(Integer startIndex, Integer endIndex)
    {
        if (endIndex == startIndex)
        {
            return startIndex;
        }

        Integer pivotIndex = endIndex;
        Integer cursor = startIndex;
        while (cursor < pivotIndex)
        {
            if (array[cursor] > array[pivotIndex])
            {
                if (cursor + 1 == pivotIndex)
                {
                    swap(pivotIndex, cursor);
                    --pivotIndex;
                    break;
                }
                else
                {
                    swap(pivotIndex-1, pivotIndex);
                    swap(pivotIndex, cursor);
                    --pivotIndex;
                }
            }
            else
            {
                ++cursor;
            }
        }

        return pivotIndex;
    }

    private static void swap(Integer fromIndex, Integer toIndex)
    {

        Integer tmp = array[fromIndex];
        array[fromIndex] = array[toIndex];
        array[toIndex] = tmp;
    }
}