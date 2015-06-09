// Given a sorted dictionary of alien language find order of characters
// set 181, r1, q2,1
// code question : 64

import java.util.*;

public class Main
{
    private static Graph graph;

    public static void main(String[] er)
    {
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        graph = new Graph();

        for (Integer i=0; i<words.length-1; ++i)
        {
            analyse(words[i], words[i+1]);
        }

        graph.printTopology();
    }

    private static void analyse(String word1, String word2)
    {
        Integer cursor1 = 0, cursor2 = 0;

        while (cursor1 < word1.length() && cursor2 < word2.length())
        {
            if (word1.charAt(cursor1) != word2.charAt(cursor2))
            {
                graph.connect(word1.charAt(cursor1), word2.charAt(cursor2));
            }

            ++cursor1;
            ++cursor2;
        }
    }
}

class Graph
{
    private Map<Character, Node> characterVertexMap;
    private Set<Character> charactersOrder;
    private Character firstCharacter;

    public Graph()
    {
        this.characterVertexMap = new LinkedHashMap<>();
        this.charactersOrder = new LinkedHashSet<>();
        this.firstCharacter = null;
    }

    public void printTopology()
    {
        Node head = characterVertexMap.get(firstCharacter);
        printTopoSort(head);

        for (Character c : charactersOrder)
        {
            System.out.print(c);
        }
    }

    private void printTopoSort(Node node)
    {
        if (node == null)
        {
            return;
        }

        node.setMark(true);

        for (Character c : node.getChildren())
        {
            Node childNode = characterVertexMap.get(c);
            if (!childNode.getMark())
            {
                printTopoSort(childNode);
            }
        }

        charactersOrder.add(node.getCharacter());
    }

    public void connect(Character parent, Character child)
    {
        if (firstCharacter == null)
        {
            firstCharacter = parent;
        }

        Node parentNode = characterVertexMap.get(parent);
        if (parentNode == null)
        {
            parentNode = new Node(parent);
            characterVertexMap.put(parent, parentNode);
        }

        Node childNode = characterVertexMap.get(child);
        if (childNode == null)
        {
            childNode = new Node(child);
            characterVertexMap.put(child, childNode);
        }

        if (!parentNode.hasInParentOrChild(child))
        {
            parentNode.addChild(child);
            childNode.addParent(parent);
        }
    }
}

class Node
{
    private List<Character> parents, children;
    private Character character;
    private Boolean mark;

    public Node(Character c)
    {
        this.character = c;
        parents = new ArrayList<>();
        children = new ArrayList<>();
        mark = false;
    }

    public Boolean getMark()
    {
        return mark;
    }

    public void setMark(Boolean mark)
    {
        this.mark = mark;
    }

    public void addParent(Character c)
    {
        parents.add(c);
    }

    public void addChild(Character c)
    {
        children.add(c);
    }

    public Boolean hasInParentOrChild(Character c)
    {
        return parents.contains(c) || children.contains(c);
    }

    public Boolean hasParent()
    {
        return parents.size() > 0;
    }

    public List<Character> getChildren()
    {
        return children;
    }

    public Character getCharacter()
    {
        return character;
    }
}