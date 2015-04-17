public class Main {

    public static void main(String[] args)
    {
        BST<Integer> tree = new Tree<>();
        tree.insertSequence(6);
        tree.insertSequence(4);
        tree.insertSequence(7);
        tree.insertSequence(1);
        tree.insertSequence(5);
        tree.insertSequence(3);


        /*tree.insertNext(1);
        tree.insertNext(2);
        tree.insertNext(3);
        tree.insertNext(4);
        tree.insertNext(5);
        tree.insertNext(6);*/

        tree.print();

        CommonAncestorFinder<Integer> finder = new CommonAncestorFinder<>(tree.getList());
        System.out.println("common ancestor is: " + finder.getCommonAncestor(1, 5));
    }
}
