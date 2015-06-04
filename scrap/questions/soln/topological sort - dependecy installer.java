// topological sort - dependecy installer
// code question 39

import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
        String[] s = new String[]{"abcd", "bc", "db"};
        GraphCreator gc = new GraphCreator();
        Node head = gc.create(s);
        GraphCreator.print(head);

        TopologicalSort ts = new TopologicalSort();
        ts.topoPrint(head);
    }
}

class TopologicalSort
{
    public void topoPrint(Node head)
    {
        Set<Character> set = new LinkedHashSet<>();
        sort(head, set);

        for (Character c : set)
        {
            System.out.print(c + " ");
        }
    }

    private void sort(Node node, Set<Character> set)
    {
        if (node == null)
        {
            return;
        }

        for (Node d : node.getDependencies())
        {
            sort(d, set);
        }

        set.add(node.getValue());
    }
}

class GraphCreator
{
    private Map<Character, Node> nodeMap;
    private Node head;

    public Node create(String[] depends)
    {
        nodeMap = new HashMap<>();

        head = null;

        for (String s : depends)
        {
            Character c = s.charAt(0);
            Node thisModule = nodeMap.get(c);
            if (thisModule == null)
            {
                thisModule = new Node(c);
                nodeMap.put(c, thisModule);
            }

            if (head == null)
            {
                head = thisModule;
            }

            for (Integer i=1; i<s.length(); ++i)
            {
                Character depencyC = s.charAt(i);
                Node dependencyNode = nodeMap.get(depencyC);
                if (dependencyNode == null)
                {
                    dependencyNode  = new Node(depencyC);
                    nodeMap.put(depencyC, dependencyNode);
                }

                thisModule.addDependency(dependencyNode);
            }
        }

        return head;
    }

    public static void print(Node d)
    {
        if (d == null)
        {
            return;
        }

        System.out.print(d.getValue() + " - ");
        for (Node node : d.getDependencies())
        {
            System.out.print(node.getValue() + " ");
        }
        System.out.println();

        for (Node node : d.getDependencies())
        {
            print(node);
        }
    }
}

class Node
{
    private Character value;
    private List<Node> dependencies;

    public Node(Character value)
    {
        this.value = value;
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(Node d)
    {
        dependencies.add(d);
    }

    public List<Node> getDependencies()
    {
        return dependencies;
    }

    public Character getValue()
    {
        return value;
    }

    public void setValue(Character value)
    {
        this.value = value;
    }
}