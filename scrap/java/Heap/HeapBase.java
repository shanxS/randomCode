package Heap;// Created by shanxS on 4/13/2015.

import java.util.ArrayList;
import java.util.List;

abstract class HeapBase {
    protected final Integer rootIndex;
    protected List<Integer> m_list;

    public HeapBase()
    {
        rootIndex = 0;
        m_list = new ArrayList<>();
    }

    public void insertElement(Integer element)
    {
        m_list.add(element);
        heapify();
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

    protected abstract void heapify();

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

    protected void log(String s)
    {
        System.out.println("Heap: " + s);
    }
}
