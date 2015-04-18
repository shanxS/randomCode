// diametr of tree as: http://stackoverflow.com/questions/20267062/diameter-of-a-tree
// r1, q1, amazon set 5, geek for geeks

class TreeDiameter
{
    private BST m_tree;
    Integer m_rightDepth;
    Integer m_leftDepth;

    public TreeDiameter(BST tree)
    {
        m_tree = tree;
        m_rightDepth = 0;
        m_leftDepth = 0;
    }

    public Integer getDiameter()
    {
        Integer lastDepth = 0;
        m_rightDepth = getDepth(m_tree.getHead().getRight(), lastDepth);
        m_leftDepth = getDepth(m_tree.getHead().getLeft(), lastDepth);

        // add 1 for root
        return m_rightDepth + m_leftDepth + 1;
    }

    private Integer getDepth(Node node, Integer lastDepth)
    {
        // set to 1, counting this node
        Integer rightDepth = 1;
        Integer leftDepth = 1;
        if (node.getRight() != null)
        {
            rightDepth += getDepth(node.getRight(), lastDepth);
        }
        if (node.getLeft() != null)
        {
            leftDepth += getDepth(node.getLeft(), lastDepth);
        }

        if (rightDepth > leftDepth)
        {
            return rightDepth;
        }
        else
        {
            return leftDepth;
        }
    }

}

class BST
{
    private Node m_head;

    public BST()
    {
        m_head = null;
    }

    public Node getHead()
    {
        return m_head;
    }

    public void insert(Integer value)
    {
        if (m_head == null)
        {
            m_head = new Node(value);
        }
        else
        {
            insert(m_head, value);
        }
    }

    private void insert(Node node, Integer value) {
        if (node.getValue() > value)
        {
            if (node.getRight() == null)
            {
                node.setRight(new Node(value));
            }
            else
            {
                insert(node.getRight(), value);
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getLeft() == null)
            {
                node.setLeft(new Node(value));
            }
            else
            {
                insert(node.getLeft(), value);
            }
        }
        else
        {
            System.out.println("duplicate vlaue cannot insert into BST");
        }
    }

    public void print()
    {
        print(m_head);
    }

    private void print(Node node) {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.print(", ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        System.out.println();

        print(node.getRight());
        print(node.getLeft());
    }
}