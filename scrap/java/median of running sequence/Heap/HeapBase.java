package Heap;// Created by shanxS on 4/13/2015.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class HeapBase {
    protected final Integer rootIndex;
    protected final Integer invalidIndex;
    protected List<Integer> m_list;

    public HeapBase()
    {
        rootIndex = 0;
        invalidIndex = -1;
        m_list = new ArrayList<>();
    }

    public void insertElement(Integer element)
    {
        m_list.add(element);
        bubbleUp();
    }

    public  void printHeap()
    {
        System.out.print("heap: ");
        for (Integer i : m_list)
        {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public Integer elementCount()
    {
        return m_list.size();
    }

    public int removeRootElement()
    {
        Integer rootElement = m_list.get(rootIndex);
        Collections.swap(m_list, rootIndex, elementCount() - 1);
        m_list.remove(elementCount() - 1);
        bubbleDown();
        return rootElement;
    }

    protected abstract void bubbleUp();
    protected abstract void bubbleDown();

    protected Integer getParentIndex(Integer childElementIndex)
    {
        if (childElementIndex == 0)
        {
            log ("getParentIndex: childElementIndex == 0");
            return rootIndex;
        }
        else if (childElementIndex < 0)
        {
            log ("getParentIndex: childElementIndex < 0");
        }

        return ((childElementIndex-1)/2);
    }

    protected Integer getRightChildIndex(Integer parentElementIndex)
    {
        Integer index = parentElementIndex*2 + 1;
        if (index >= m_list.size())
        {
            log ("getParentIndex: right child out of range");
            return invalidIndex;
        }

        return index;
    }

    protected Integer getLeftChildIndex(Integer parentElementIndex)
    {
        Integer index = parentElementIndex*2 + 2;
        if (index >= m_list.size())
        {
            log ("getParentIndex: left child out of range");
            return invalidIndex;
        }

        return index;
    }

    protected void log(String s)
    {
        System.out.println("Heap: " + s);
    }
}
