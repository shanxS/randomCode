// find path which has given sum - path cna start and end anywhere

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10,20,30,60,70,50,40};
        BST tree = new BST(ar);
        tree.print(tree.getHead());

        SumFinder sf = new SumFinder();
        sf.find(tree.getHead(), 80);
    }
}

class SumFinder
{
    private Integer sum;
    public void find(Node head, Integer sum)
    {
        this.sum = sum;
        findFor(head);
    }

    private SubTreeElements findFor(Node node)
    {
        if (node == null)
        {
            return null;
        }

        SubTreeElements leftElements = findFor(node.getLeft());
        SubTreeElements rightElements = findFor(node.getRight());

        testFor(leftElements, rightElements, node.getValue());

        return consolidateTrees(leftElements, rightElements, node.getValue());
    }

    private SubTreeElements consolidateTrees(SubTreeElements leftElements, SubTreeElements rightElements, Integer value)
    {
        if (leftElements == null && rightElements == null)
        {
            List<Integer> list = new ArrayList<>();
            list.add(value);
            return new SubTreeElements(value, list);
        }
        else
        {
            return new SubTreeElements(leftElements.sum + rightElements.sum + value,
                    consolidate(leftElements.tree, rightElements.tree, value));
        }
    }

    private void testFor(SubTreeElements leftElements, SubTreeElements rightElements, Integer value)
    {
        if (leftElements == null || rightElements == null)
        {
            return;
        }

        testForCrossSum(leftElements, rightElements, value);
        testForHalfSum(leftElements, value);
        testForHalfSum(rightElements, value);
    }

    private void testForHalfSum(SubTreeElements halfElements, Integer value)
    {
        Integer subSum = halfElements.sum;
        List<Integer> half = new ArrayList<>(halfElements.tree);

        half.add(0, value);
        subSum += value;

        if (subSum > sum)
        {
           while (subSum > sum
                   && half.size() > 0)
           {
               Integer lastIndex = half.size()-1;

               while (half.get(lastIndex) == null
                       && half.size() > 0)
               {
                   half.remove((int) lastIndex);
                   --lastIndex;
               }

               if (half.size() == 0)
               {
                   break;
               }

               if (subSum-half.get(lastIndex) == sum)
               {
                   subSum -= half.remove((int) lastIndex);
               }
               else if (getLastLastIndex(half, lastIndex) != null
                       && subSum-half.get(getLastLastIndex(half, lastIndex)) == sum)
               {
                   Integer lastLastIndex = getLastLastIndex(half, lastIndex);
                   subSum -= half.get(lastLastIndex);
                   half.set(lastLastIndex, null);
               }
               else
               {
                   Integer lastLastIndex = getLastLastIndex(half, lastIndex);

                   if (lastLastIndex != null)
                   {
                       if (half.get(lastIndex) < half.get(lastLastIndex))
                       {
                           subSum -= half.remove((int) lastIndex);
                       }
                       else
                       {
                           subSum -= half.get(lastLastIndex);
                           half.set(lastLastIndex, null);
                       }
                   }
                   else
                   {
                       subSum -= half.remove((int) lastIndex);
                   }
               }
           }
        }

        if (subSum == sum)
        {
            for (Integer i : half)
            {
                System.out.print(i + " ");
            }
            System.out.println(".......");
        }
    }

    private void testForCrossSum(SubTreeElements leftElements, SubTreeElements rightElements, Integer value)
    {

        Integer subSum = leftElements.sum + rightElements.sum;
        List<Integer> left = new ArrayList<>(leftElements.tree);
        List<Integer> right = new ArrayList<>(rightElements.tree);

        List<Integer> consolidatedTree = consolidate(left, right, value);
        subSum += value;

        if (subSum> sum && left.size() > 0 && right.size()>0)
        {
            while (subSum > sum
                    && consolidatedTree.size() > 0)
            {
                Integer lastIndex = consolidatedTree.size()-1;
                while (consolidatedTree.get(lastIndex) == null
                        && consolidatedTree.size() > 0)
                {
                    consolidatedTree.remove((int) lastIndex);
                    --lastIndex;
                }

                if (consolidatedTree.size() == 0)
                {
                    break;
                }

                if (subSum-consolidatedTree.get(lastIndex) == sum)
                {
                    subSum -= consolidatedTree.remove((int) lastIndex);
                }
                else if (getLastLastIndex(consolidatedTree, lastIndex) != null
                        && subSum-consolidatedTree.get(getLastLastIndex(consolidatedTree, lastIndex)) == sum)
                {
                    Integer lastLastIndex = getLastLastIndex(consolidatedTree, lastIndex);
                    subSum -= consolidatedTree.get(lastLastIndex);
                    consolidatedTree = consolidatedTree.subList(0, lastLastIndex);
                }
                else
                {
                    Integer lastLastIndex = getLastLastIndex(consolidatedTree, lastIndex);

                    if (lastLastIndex != null)
                    {
                        if (consolidatedTree.get(lastIndex) < consolidatedTree.get(lastLastIndex))
                        {
                            subSum -= consolidatedTree.remove((int) lastIndex);
                        }
                        else
                        {
                            subSum -= consolidatedTree.get(lastLastIndex);
                            consolidatedTree = consolidatedTree.subList(0, lastLastIndex);
                        }
                    }
                    else
                    {
                        subSum -= consolidatedTree.remove((int) lastIndex);
                    }
                }
                --lastIndex;
            }
        }


        if (subSum == sum)
        {
            for (Integer i : consolidatedTree)
            {
                System.out.print(i + " ");
            }
            System.out.println(".......");
        }
    }

    private static List<Integer> consolidate(List<Integer> left, List<Integer> right, Integer value)
    {
        Integer len = (2 * Math.max(left.size(), right.size())) + 1;
        Integer[] tree = new Integer[len];
        tree[0] = value;
        tree[1] = left.get(0);
        tree[2] = right.get(0);

        Integer leftCounter = 1, rightCounter = 1;
        Integer counter = 3;
        Integer copyCount = 2;
        while (counter < len)
        {
            for (Integer i=0; i<copyCount && counter<len; ++i)
            {
                if (leftCounter < left.size())
                {
                    tree[counter] = left.get(leftCounter);
                    ++leftCounter;
                }

                ++counter;
            }

            for (Integer i=0; i<copyCount && counter<len; ++i)
            {
                if (rightCounter < right.size())
                {
                    tree[counter] = right.get(rightCounter);
                    ++rightCounter;
                }

                ++counter;
            }

            copyCount *= 2;
        }

        return new ArrayList<>(Arrays.asList(tree));
    }

    private Integer getLastLastIndex(List<Integer>list ,Integer index)
    {
        --index;
        while (index >= 0 && list.get(index) == null)
        {
            --index;
        }

        return index < 0 ? null : index;
    }

    private class SubTreeElements
    {
        public Integer sum;
        public List<Integer> tree;

        public SubTreeElements(Integer sum)
        {
            this.sum = sum;
        }

        public SubTreeElements(Integer sum, List<Integer> tree)
        {
            this.sum = sum;
            this.tree = tree;
        }
    }
}

class BST
{
    private Node head;

    public BST(Integer [] ar)
    {
        Node[] nodes = new Node[ar.length];
        nodes[0] = new Node(ar[0]);

        for (Integer i=1; i<ar.length; ++i)
        {
            if (ar[i] == null)
            {
                continue;
            }

            Node parent = nodes[getParentIndex(i)];
            if (i%2 != 0)
            {
                parent.setLeft(new Node(ar[i]));
                nodes[i] = parent.getLeft();
            }
            else
            {
                parent.setRight(new Node(ar[i]));
                nodes[i] = parent.getRight();
            }
        }

        head = nodes[0];
    }

    private Integer getParentIndex(Integer index)
    {
        return (index-1)/2;
    }

    public void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer v)
    {
        value = v;
    }

    public Integer getValue()
    {
        return value;
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