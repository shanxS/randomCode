// given preorder traversal of BST - test if every node has 1 child
// code question: 77

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] preorder = {20, 10, 11, 13, 12};
//        Integer[] preorder = {20, 10, 11, 13, 12, 21};
        Integer[] preorder = {10,40,13,20,19,18};

        SingleChildTester tester = new SingleChildTester();
        System.out.print(tester.test(preorder));
    }
}

class SingleChildTester
{
    public boolean test(Integer[] preorder)
    {
        boolean result = true;
        Integer end = preorder.length - 1;

        Integer min = preorder[end] > preorder[end - 1] ? preorder[end - 1] : preorder[end];
        Integer max = preorder[end] < preorder[end - 1] ? preorder[end - 1] : preorder[end];

        for (Integer i = end - 1; i >= 0; --i)
        {
            Integer value = preorder[i];
            if (value < max && value > min)
            {
                result = false;
                break;
            }

            if (value > max)
            {
                max = value;
            }
            else if (value < min)
            {
                min = value;
            }
        }

        return result;
    }
}