// r2, q1, set 4, amazon, geek for geeks
// Given a Binary tree and two nodes. Need to find the minimum ancestor, no parent nodes given.
// http://www.geeksforgeeks.org/amazon-interview-set-4-2/

import binarytree.BST;
import binarytree.BT;
import binarytree.ITree;

public class Main {

    public static void main(String[] args)
    {
        /*BST<Integer> BSTree = new BST<>();
        BSTree.insert(6);
        BSTree.insert(4);
        BSTree.insert(7);
        BSTree.insert(1);
        BSTree.insert(5);
        BSTree.insert(3);

        ITree<Integer> tree = BSTree;*/

        BT<Integer> BTree = new BT<>();
        BTree.insert(1);
        BTree.insert(2);
        BTree.insert(3);
        BTree.insert(4);
        BTree.insert(5);
        BTree.insert(6);

        ITree<Integer> tree = BTree;

        if (tree instanceof BST)
        {
            System.out.println("is BST");
            System.out.println("common ancestor is: " + ((BST) tree).getCommonAncestor(3, 7));
        }
        else
        {
            System.out.println("is not BST");
            System.out.println("common ancestor is: " + ((BT) tree).getCommonAncestor(4, 5));
        }

        tree.print();



        //CommonAncestorFinder<Integer> finder = new CommonAncestorFinder<>(tree.getList());
        //System.out.println("common ancestor is: " + finder.getCommonAncestor(1, 5));
    }
}
