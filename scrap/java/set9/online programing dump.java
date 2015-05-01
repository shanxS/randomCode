public class Main{
    public static void main(String args[]) {
        String text = "this is a    randpom string";
        System.out.print(getFirstLetterWord(text));
    }

    static String getFirstLetterWord(String text) {
        String s = "";
        Boolean hasWordEnded = true;

        for(Character c : text.toCharArray()) {
            if (hasWordEnded && Character.isLetter(c)) {
                s += c;
                hasWordEnded = false;
            }

            if (c == ' '){
                hasWordEnded = true;
            }

        }

        return s;


    }
}

--------------------------------------

import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(6);

        System.out.print(iterateSequence(list, 2));
    }

    static int iterateSequence(List<Integer> list, int N) {
        while(list.size() > 1 && --N>=0){
            List<Integer> tempList = new ArrayList<>();

            for (Integer i=1; i<list.size(); ++i){
                tempList.add(list.get(i) - list.get(i-1));
            }

            list = tempList;
        }

        Integer sum = 0;
        for (Integer val : list){
            sum += val;
        }
        return sum;
    }
}


-------------------------------------------------------------------------

public class Main{
    public static void main(String args[]) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(4);
        bst.insert(2);
        bst.insert(6);
        bst.insert(1);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.insert(20);
        bst.insert(15);
        bst.insert(30);

        System.out.print(nLargeBST(bst.getHead(), 2));
    }

    static Integer nLargeBST(Node root, int N) {
        Boolean isLeftLess = getRuls(root);

        Node cursor = root;
        while(cursor != null && --N >= 0) {
            if (isLeftLess)
            {
                cursor = cursor.getRight();
            }
            else
            {
                cursor = cursor.getLeft();
            }
        }

        return cursor.getValue();
    }

    private static Boolean getRuls(Node root) {
        if (root.getLeft() != null) {
            return root.getValue() > root.getLeft().getValue();
        }
        else if (root.getRight() != null) {
            return root.getValue() < root.getRight().getValue();
        }
        else {
            return null;
        }
    }
}


class BinarySearchTree
{
    private Node head;

    public BinarySearchTree()
    {
        head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }
    }

    private void insert(Node node, Integer value) {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
        else
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new Node(value));
            }
        }
    }

    public Node getHead() {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}


-----------------------------------------------------------------------


public class Main{
    public static void main(String args[]) {

        LL l = new LL();
        l.insert(1);
        l.insert(2);
        l.insert(3);
        l.insert(4);

        l.print();



        System.out.println("========================");

        Node cursor = swapAdjacentNodes(l.getHead());

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }

    static Node swapAdjacentNodes(Node node) {
        if (node == null || node.getNext() == null) {
            return node;
        }

        Node current = node;
        Node next = current.getNext();
        if (next.getNext() == null)
        {
            next.setNext(current);
            current.setNext(null);
            return next;
        }

        Node nextNext = next.getNext();
        next.setNext(current);
        current.setNext(swapAdjacentNodes(nextNext));

        return next;

    }
}

class LL{
    private Node head;

    public LL() {
        head = null;
    }

    public void insert(Integer val){
        if (head == null){
            head = new Node(val);
        }
        else {
            Node cursor = head;
            while(cursor.getNext()  != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(val));
        }
    }

    public void print(){
        Node cursor = head;

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }

    public  Node getHead() {
        return head;
    }
}


class Node {
    private Integer value;
    private Node next;

    public Node(Integer value){
        this.value = value;
        this.next = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}


--------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(9);
        list.add(4);
        list.add(5);
        list.add(-1);
        list.add(10);
        list.add(8);

        System.out.print(lengthLIS(list));
    }

    static int lengthLIS(List<Integer> seq) {

        List<Integer> LIS = new ArrayList<>();
        seq.stream().forEach(x -> LIS.add(0));

        Integer length = 0;
        for (Integer i=0; i<seq.size(); ++i) {
            if (i==0) {
                LIS.set(length, seq.get(i));
                ++length;
            }
            else if (LIS.get(length-1) < seq.get(i)) {
                LIS.set(length, seq.get(i));
                ++length;
            }
            else {
                Integer replacementIndex = getReplcementIndex(LIS, length, seq.get(i));
                LIS.set(replacementIndex, seq.get(i));
            }
        }

        return length;
    }

    private static Integer getReplcementIndex(List<Integer> lis, Integer length, Integer value) {
        --length;
        while(length >= 0 && lis.get(length) < value){
            --length;
        }

        return length;
    }
}