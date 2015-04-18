package binarytree;// Created by shanxS on 4/17/2015.

import java.util.List;

public interface ITree<T>
{
    List<T> getList();
    void print();
    void insert(T value);
    T getElementAt (Integer position);
    Integer size();
    T getCommonAncestor(T first, T second);
}

abstract class Tree<T> implements ITree<T>
{
    protected List<T> m_list;

    public Tree(List<T> list)
    {
        m_list = list;
    }

    @Override
    public List<T> getList()
    {
        return m_list;
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

    @Override
    public T getElementAt (Integer position)
    {
        if (m_list.size()-1 < position)
        {
            return null;
        }
        else
        {
            return m_list.get(position);
        }
    }

    @Override
    public Integer size()
    {
        return m_list.size();
    }

    protected Integer getRightIndex(Integer parentIndex)
    {
        return ((2*parentIndex) + 2);
    }

    protected Integer getLefttIndex(Integer parentIndex)
    {
        return ((2*parentIndex) + 1);
    }

    protected Integer getParentIndex(Integer childIndex)
    {
        return (childIndex - 1)/2;
    }

    protected boolean isValidIndex(Integer position)
    {
        if ((position > m_list.size()-1) || position < 0)
        {
            return false;
        }

        return true;
    }

    protected void insertAt(Integer position, T value)
    {
        while (position > m_list.size()-1)
        {
            m_list.add(null);
        }

        m_list.set(position, value);
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

}