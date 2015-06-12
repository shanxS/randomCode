// Sorted order printing of a given array that represents a BST - using recusion
// code question: 83

public class Main
{
    private static Integer[] BSTArray = {4, 2, 5, 1, 3};

    public static void main(String[] er)
    {
        printInoder(0);
    }

    private static void printInoder(Integer index)
    {
        if (!isValidIndex(index))
        {
            return;
        }

        printInoder(getLeftIndex(index));
        System.out.print(BSTArray[index] + " ");
        printInoder(getRightIndex(index));

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
}