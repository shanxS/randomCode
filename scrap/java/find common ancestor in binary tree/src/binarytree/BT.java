package binarytree;// Created by shanxS on 4/18/2015.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BT<T> extends Tree<T>
{
    Map<T, Integer> m_valueIndex;

    public BT()
    {
        super(new ArrayList<T>());
        m_valueIndex = new HashMap<>();
    }

    @Override
    public void insert(T value)
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
        m_valueIndex.put(value, i);
    }

    @Override
    public T getCommonAncestor(T first, T second)
    {
        Integer firstIndex = m_valueIndex.get(first);
        Integer secondIndex = m_valueIndex.get(second);

        if (firstIndex == null || secondIndex == null)
        {
            return null;
        }

        Integer firstParentIndexTrail = getParentIndex(firstIndex);
        Integer secondParentIndexTrail = getParentIndex(secondIndex);
        while(firstParentIndexTrail >= 0
                && secondParentIndexTrail >=0)
        {
            if (firstParentIndexTrail == secondParentIndexTrail)
            {
                return m_list.get(firstParentIndexTrail);
            }

            firstParentIndexTrail = getParentIndex(firstParentIndexTrail);
            secondParentIndexTrail = getParentIndex(secondParentIndexTrail);
        }


        return null;
    }

}
