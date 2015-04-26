// test if tree is BST
// written test, q1, set7, amazon

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(6);
        tree.insert(3);
        tree.insert(11);
        tree.insert(1);
        tree.insert(4);
        tree.insert(null);
        tree.insert(14);
        tree.insert(null);
        tree.insert(null);
        tree.insert(null);
        tree.insert(null);
        tree.insert(null);
        tree.insert(null);
        tree.insert(13);

        /*tree.insert(6);
        tree.insert(3);
        tree.insert(8);
        tree.insert(11);
        tree.insert(null);
        tree.insert(9);
        tree.insert(10);
        tree.insert(null);
        tree.insert(null);
        tree.insert(null);
        tree.insert(null);
        tree.insert(1);
        tree.insert(null);
        tree.insert(2);*/

        tree.print();

        BSTClaim bstClaim = new BSTClaim(tree);
        System.out.println(bstClaim.isBST());

    }
}

class BSTClaim
{
    private Tree tree;
    private Boolean isLeftChildLeaaThan;

    public BSTClaim(Tree tree)
    {
        this.tree = tree;
        isLeftChildLeaaThan = false;
    }

    public Boolean isBST()
    {
        setRule();
        if (isLeftChildLeaaThan == null)
        {
            return false;
        }

        return isSubTreeBST(tree.getHeadIndex());
    }

    private Boolean isSubTreeBST(Integer index) {
        Integer parent = tree.getValueAt(index);
        if (parent == null)
        {
            return true;
        }

        Integer rightChild = tree.getValueAt(tree.getRightChildIndex(index));
        Integer leftChild = tree.getValueAt(tree.getLeftChildIndex(index));

        if ((rightChild != null) && (rightChild > parent) != isLeftChildLeaaThan)
        {
            return false;
        }
        if ((leftChild != null) && (leftChild < parent) != isLeftChildLeaaThan)
        {
            return false;
        }

        if (!isSubTreeBST(tree.getRightChildIndex(index)))
        {
            return false;
        }
        if (!isSubTreeBST(tree.getLeftChildIndex(index)))
        {
            return false;
        }

        return true;
    }

    private void setRule() {
        Integer head = tree.getValueAt(tree.getHeadIndex());
        if (head == null)
        {
            isLeftChildLeaaThan = null;
            return;
        }

        Integer rightChild = tree.getValueAt(tree.getRightChildIndex(tree.getHeadIndex()));
        if (rightChild == null)
        {
            isLeftChildLeaaThan = null;
            return;
        }

        Integer leftChild = tree.getValueAt(tree.getLeftChildIndex(tree.getHeadIndex()));
        if (leftChild == null)
        {
            isLeftChildLeaaThan = null;
            return;
        }

        if (leftChild < head && rightChild > head)
        {
            isLeftChildLeaaThan = true;
            return;
        }
        else if (leftChild > head && rightChild < head)
        {
            isLeftChildLeaaThan = false;
            return;
        }
        else
        {
            isLeftChildLeaaThan = null;
            return;
        }
    }
}

class Tree
{
    List<Integer> list;

    public Tree()
    {
        list = new ArrayList<>();
    }

    public void insert(Integer value)
    {
        list.add(value);
    }

    private Boolean isValidIndex(Integer index)
    {
        return (index >= list.size() || index<0) ? false : true;
    }

    public Integer getValueAt(Integer index)
    {
        if (isValidIndex(index))
        {
            return list.get(index);
        }

        return null;
    }

    public Integer getHeadIndex()
    {
        return 0;
    }

    public Integer getRightChildIndex(Integer parentIndex)
    {
        return (parentIndex*2) + 2;
    }

    public Integer getLeftChildIndex(Integer parentIndex)
    {
        return (parentIndex*2) + 1;
    }

    public void print() {
        print(0);
    }

    private void print(int index) {
        if (!isValidIndex(index)|| list.get(index) == null)
        {
            return;
        }

        System.out.print(list.get(index) + " - ");
        if (isValidIndex(getLeftChildIndex(index)))
        {
            System.out.print(list.get(getLeftChildIndex(index)));
        }
        System.out.print(", ");
        if (isValidIndex(getRightChildIndex(index)))
        {
            System.out.print(list.get(getRightChildIndex(index)));
        }
        System.out.println();

        print(getRightChildIndex(index));
        print(getLeftChildIndex(index));

    }
}
