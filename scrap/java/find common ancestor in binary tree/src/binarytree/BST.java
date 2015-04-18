package binarytree;// Created by shanxS on 4/17/2015.

import java.util.ArrayList;

public class BST<T extends Comparable<T>> extends Tree<T>
{
    public BST()
    {
        super (new ArrayList<T>());
    }

    @Override
    public void insert(T value)
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
    public T getCommonAncestor(T first, T second)
    {
        boolean isCommonParentFound = false;
        Integer commonParentIndex = 0;
        Integer firstTrail = 0;
        Integer secondTrail = 0;
        while ( commonParentIndex<m_list.size()
                && getElementAt(firstTrail) != first
                && getElementAt(secondTrail) != second)
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

        return (isCommonParentFound) ? getElementAt(commonParentIndex) : null;
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

    private Integer getNextIndex(Integer parentIndex, T targetObject)
    {
        if (m_list.get(parentIndex).compareTo(targetObject) > 0)
        {
            return getRightIndex(parentIndex);
        }
        else
        {
            return getLefttIndex(parentIndex);
        }
    }
}
