// optimal BST

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        List<Integer> keys = new ArrayList<>();
        keys.add(20);
        keys.add(10);
        keys.add(12);


        Map<Integer, Integer> keyFreqMap = new HashMap<>();
        keyFreqMap.put(20,50);
        keyFreqMap.put(10,34);
//        keyFreqMap.put(12,8);
        keyFreqMap.put(12,30);

        Permuter p = new Permuter();

        Integer minCost = Integer.MAX_VALUE;
        List<Integer> seq = null;
        for (List<Integer> list : p.permute(keys))
        {
            BST tree = new BST();
            for (Integer v : list)
            {
                tree.insert(v);
            }

            Integer thisCost = tree.computeCost(keyFreqMap);
            if (minCost > thisCost)
            {
                minCost = thisCost;
                seq = list;
            }
        }

        System.out.println(minCost);
        for (Integer i : seq)
        {
            System.out.print(i + " ");
        }
    }
}

class Permuter
{


    public List<List<Integer>> permute(List<Integer> keys)
    {
        List<List<Integer>> permutations = new ArrayList<>();
        if (keys.size() == 0)
        {
            permutations.add(new ArrayList<>());
            return permutations;
        }

        for (Integer i=0; i<keys.size(); ++i)
        {

            List<Integer> bag = new ArrayList<>(keys);
            bag.remove((int) i);
            List<List<Integer>> restOfList = permute(bag);

            for (List<Integer> list : restOfList)
            {
                list.add(keys.get(i));
            }

            permutations.addAll(restOfList);
        }

        return permutations;
    }
}

class BST
{
    private Map<Integer, Integer> keyLevelMap;
    private Node head;

    public BST()
    {
        keyLevelMap = new HashMap<>();
        head = null;
    }

    public Integer computeCost(Map<Integer, Integer> keyFreqMap)
    {
        Integer cost = 0;
        for (Map.Entry<Integer, Integer> entry : keyFreqMap.entrySet())
        {
            Integer key = entry.getKey();
            Integer freq = entry.getValue();
            cost += keyLevelMap.get(key) * freq;
        }

        return cost;
    }

    public void insert(Integer v)
    {
        Integer level = 1;
        if (head == null)
        {
            head = new Node(v);
            keyLevelMap.put(v, level);
        }
        else
        {
            insert(head, v, level+1);
        }
    }

    private void insert(Node node, Integer v, Integer level)
    {
        if (node.getValue() > v)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), v, level+1);
            }
            else
            {
                node.setLeft(new Node(v));
                keyLevelMap.put(v, level);
            }
        }
        else if (node.getValue() < v)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), v, level+1);
            }
            else
            {
                node.setRight(new Node(v));
                keyLevelMap.put(v, level);
            }
        }
    }
}

class Node
{
    Integer value;
    Node left, right;

    public Node(Integer v)
    {
        this.value = v;
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