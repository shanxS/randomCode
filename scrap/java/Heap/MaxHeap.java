package Heap;// Created by shanxS on 4/13/2015.

import java.util.Collections;

public class MaxHeap extends HeapBase {

    public Integer getMaximum()
    {
        return m_list.get(rootIndex);
    }

    @Override
    protected void heapify() {
        Integer newElementIndex = m_list.size() - 1;
        Integer immediateParentIndex = getParentIndex(newElementIndex);

        boolean isHeapifyComplete = false;
        while (immediateParentIndex != rootIndex)
        {
            if (m_list.get(immediateParentIndex) < m_list.get(newElementIndex))
            {
                Collections.swap(m_list, immediateParentIndex, newElementIndex);
                newElementIndex = immediateParentIndex;
                immediateParentIndex = getParentIndex(newElementIndex);
            }
            else
            {
                isHeapifyComplete = true;
                break;
            }
        }

        if (!isHeapifyComplete)
        {
            if (m_list.get(immediateParentIndex) < m_list.get(newElementIndex))
            {
                Collections.swap(m_list, immediateParentIndex, newElementIndex);
            }
        }
    }
}
