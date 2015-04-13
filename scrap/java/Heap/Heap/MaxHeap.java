package Heap;// Created by shanxS on 4/13/2015.

import java.util.Collections;

public class MaxHeap extends HeapBase {

    public Integer getMaximum()
    {
        return m_list.get(rootIndex);
    }

    @Override
    protected void bubbleUp() {
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

    @Override
    protected void bubbleDown() {
        Integer currentIndex = rootIndex;
        boolean isBubbleDownComplete = false;

        while (!isBubbleDownComplete)
        {
            Integer swapIndex = invalidIndex;

            Integer rightChildIndex = getRightChildIndex(currentIndex);
            if (rightChildIndex != invalidIndex)
            {
                if (m_list.get(rightChildIndex) > m_list.get(currentIndex))
                {
                    swapIndex = rightChildIndex;
                }
            }

            Integer leftChildIndex = getLeftChildIndex(currentIndex);
            if (leftChildIndex != invalidIndex)
            {
                if (m_list.get(leftChildIndex) > m_list.get(currentIndex))
                {
                    if (rightChildIndex != invalidIndex && m_list.get(rightChildIndex) < m_list.get(leftChildIndex)) {
                        swapIndex = leftChildIndex;
                    }
                }
            }

            if (swapIndex != invalidIndex)
            {
                Collections.swap(m_list, swapIndex, currentIndex);
                currentIndex = swapIndex;
            }
            else
            {
                isBubbleDownComplete = true;
            }
        }
    }
}
