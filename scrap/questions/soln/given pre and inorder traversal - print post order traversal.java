// given pre and inorder traversal - print post order traversal
// code question: 102

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] in = {4,2,5,1,6,3,7};
//        Integer[] in = {4,2,5,1,3,6};
//        Integer[] in = {3,2,5,4,1,6,7};
        List<Integer> inList = new ArrayList<>();
        for (Integer value : in)
        {
            inList.add(value);
        }

        Integer[] pre = {1,2,4,5,3,6,7};
//        Integer[] pre = {1,2,4,5,3,6};
//        Integer[] pre = {1,2,3,4,5,6,7};
        List<Integer> preList = new ArrayList<>();
        for (Integer value : pre)
        {
            preList.add(value);
        }

        PostFormInAndPreOrder po = new PostFormInAndPreOrder();
        po.print(inList, preList);
    }
}

class PostFormInAndPreOrder
{
    private List<Integer> post;
    private List<Integer> in, pre;

    public void print(List<Integer> in , List<Integer> pre)
    {
        post = new ArrayList<>();
        this.in = in;
        this.pre = pre;

        compute(0, in.size()-1);

        post.stream().forEach(x -> System.out.print(x + " "));
    }

    private void compute(Integer inStart, Integer inEnd)
    {
        if (inStart == inEnd)
        {
            post.add(in.get(inStart));
            return;
        }

        Integer thisPreIndex = findPreIndexForInRange(inStart, inEnd);
        if (thisPreIndex >= pre.size())
        {
            return;
        }

        Integer preValue = pre.get(thisPreIndex);
        Integer inIndex = in.indexOf(preValue);

        if (inIndex-1 >= inStart)
        {
            compute(inStart, inIndex - 1);
        }

        if (inIndex+1 <= inEnd)
        {
            compute(inIndex + 1, inEnd);
        }

        post.add(in.get(inIndex));
    }

    private Integer findPreIndexForInRange(Integer inStart, Integer inEnd)
    {
        Integer preIndex = Integer.MAX_VALUE;
        for (Integer inCursor = inStart; inCursor <= inEnd; ++inCursor)
        {
            if (preIndex > pre.indexOf(in.get(inCursor)))
            {
                preIndex = pre.indexOf(in.get(inCursor));
            }
        }

        return preIndex;
    }
}