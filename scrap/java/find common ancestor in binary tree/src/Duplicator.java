import java.util.*;

class CommonAncestorFinder<T extends Comparable<T>>
{
    private List<T> m_list;

    public CommonAncestorFinder(List<T> list)
    {
        m_list = list;
    }

    public T getCommonAncestor(T first, T second)
    {
        boolean isCommonParentFound = false;
        Integer commonParentIndex = 0;
        Integer firstTrail = 0;
        Integer secondTrail = 0;
        while ( commonParentIndex<m_list.size()
             && m_list.get(firstTrail) != first
             && m_list.get(secondTrail) != second)
        {
            firstTrail = getNextIndex(commonParentIndex, first);
            secondTrail = getNextIndex(commonParentIndex, second);

            if (firstTrail != secondTrail)
            {
                isCommonParentFound = true;
                break;
            }
            else
            {
                commonParentIndex = firstTrail;
            }
        }

        return (isCommonParentFound) ? m_list.get(commonParentIndex) : null;
    }

    private Integer getNextIndex(Integer parentIndex, T second) {

        if (m_list.get(parentIndex).compareTo(second) > 0)
        {
            return parentIndex*2 + 2;
        }
        else
        {
            return parentIndex*2 + 1;
        }
    }

}

class Tree<T extends Comparable<T>> implements BST<T>, BT<T>
{
    private List<T> m_list;

    public Tree(Comparator<T> comparator)
    {
        m_list = new ArrayList<>();
    }

    public Tree()
    {
        m_list = new ArrayList<>();
    }

    private Integer getRightIndex(Integer parentIndex)
    {
        return ((2*parentIndex) + 2);
    }

    private Integer getLefttIndex(Integer parentIndex)
    {
        return ((2*parentIndex) + 1);
    }

    private Integer getParentIndex(Integer childIndex)
    {
        return (childIndex - 1)/2;
    }

    @Override
    public void insertNext(T value)
    {
        Integer i = 0;
        for (; i<m_list.size(); ++i)
        {
            if (m_list.get(i) == null)
            {
                break;
            }
        }

        insertAt(i, value);
    }


    @Override
    public void insertSequence(T value)
    {
        if (m_list.size() == 0)
        {
            insertAt(0, value);
        }
        else
        {
            Integer parentIndex = 0;
            insertByComparision(parentIndex, value);
        }
    }

    @Override
    public List<T> getList() {
        return m_list;
    }

    private void insertByComparision(Integer parentIndex, T value) {
        if (!isValidIndex(parentIndex) || m_list.get(parentIndex) == null)
        {
            insertAt(parentIndex, value);
        }

        if (m_list.get(parentIndex).compareTo(value) > 0)
        {
            insertByComparision(getRightIndex(parentIndex), value);
        }
        if (m_list.get(parentIndex).compareTo(value) < 0)
        {
            insertByComparision(getLefttIndex(parentIndex), value);
        }
    }

    private boolean isLeftChildIndex(Integer lastValidIndex) {
        return !isRightChildIndex(lastValidIndex);
    }

    private boolean isRightChildIndex(Integer lastValidIndex) {
        return (lastValidIndex/2 == 0) ? true : false;
    }

    private Integer getLastValidIndex()
    {
        Integer lastValidIndex = 0;
        for (; lastValidIndex < m_list.size(); ++ lastValidIndex)
        {
            if (m_list.get(lastValidIndex) == null)
            {
                --lastValidIndex;
                break;
            }
        }

        return lastValidIndex;
    }

    private void insertAt(Integer position, T value)
    {
        while (position > m_list.size()-1)
        {
            m_list.add(null);
        }

        m_list.set(position, value);
    }

    private boolean isValidIndex(Integer position)
    {
        if ((position > m_list.size()-1) || position < 0)
        {
            return false;
        }

        return true;
    }

    @Override
    public void print()
    {
        for (Integer i=0; i<m_list.size(); ++i)
        {
            System.out.print(m_list.get(i) + " - ");
            if (isValidIndex(getLefttIndex(i)))
            {
                System.out.print(" " + m_list.get(getLefttIndex(i)));
            }

            if (isValidIndex(getRightIndex(i)))
            {
                System.out.print(", " + m_list.get(getRightIndex(i)));
            }
            System.out.println();
        }
    }

}

interface BST<T>
{
    void insertSequence(T value);
    List<T> getList();
    void print();
}

interface BT<T>
{
    void insertNext(T value);
    List<T> getList();
    void print();
}

class A
{}