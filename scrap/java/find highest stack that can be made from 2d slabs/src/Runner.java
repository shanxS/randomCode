// amazon set 2, geek for geeks
// round 2, question 1
// You are given many slabs each with a length and a breadth. A slab i can be put on slab j if both dimensions of i are less than that of j. In this similar manner, you can keep on putting slabs on each other. Find the maximum stack possible which you can create out of the given slabs.
// http://www.geeksforgeeks.org/amazon-interview-set-2/

import java.util.*;

public class Runner {
    TreeMap<Integer, Integer> m_keyValue;
    List<Integer> m_list;
    TreeMap<Integer, Integer> m_valueKey;
    List<Pair> m_stack;

    public Runner(Map<Integer, Integer> keyValue)
    {
        m_keyValue = new TreeMap<Integer, Integer>(keyValue); // natural ordering will do
        m_list = new ArrayList<Integer>(m_keyValue.values());

        m_valueKey = new TreeMap<Integer, Integer>();
        for(Map.Entry<Integer, Integer> entry : m_keyValue.entrySet())
        {
            m_valueKey.put(entry.getValue(), entry.getKey());
        }

        m_stack = new ArrayList<Pair>();
    }

    public List<Pair> run()
    {
        LongestIncreasingSubSequence lis = new LongestIncreasingSubSequence(m_list);
        List<Integer> subSeq = lis.getLongestSubSequence();

        for (Integer i : subSeq)
        {
            Integer value = i;
            Integer key  = m_valueKey.get(value);
            m_stack.add(new Pair(key, value));
        }

        return m_stack;
    }

    private void printList(List<Integer> m_lengthList)
    {
        for(Integer i : m_lengthList)
        {
            System.out.print(i + " ");
        }

        System.out.println("");
    }

}

class LongestIncreasingSubSequence
{
    private List<Integer> m_seq;
    private List<Integer> m_lengthList;
    private List<Integer> m_subSeq;
    private Integer m_length;
    private Map<Integer, ArrayList<Integer>> m_valueListMap;

    public LongestIncreasingSubSequence(List<Integer> list)
    {
        m_seq = new ArrayList<Integer>(list);

        m_lengthList = new ArrayList<Integer>();
        for (int i=0; i<m_seq.size(); ++i)
        {
            m_lengthList.add(0);
        }

        m_subSeq = null;
        m_length = 0;
        m_valueListMap = new HashMap<Integer, ArrayList<Integer>>();

        calculateSubSequence();
    }

    List <Integer> getLongestSubSequence()
    {
        return m_subSeq;
    }

    private void calculateSubSequence()
    {
        try {
            for (int i=0; i<m_seq.size(); ++i)
            {
                if (m_length == 0)
                {
                    m_lengthList.set(m_length, m_seq.get(i));
                    makeNewList(m_seq.get(i));
                    m_length++;
                }
                else if (m_lengthList.get(m_length-1) < m_seq.get(i))
                {
                    m_lengthList.set(m_length, m_seq.get(i));
                    appendToList(m_seq.get(i));
                    m_length++;
                }
                else
                {
                    int replacementIndex = findReplacementIndex(m_seq.get(i));

                    if (m_lengthList.get(replacementIndex) > m_seq.get(i))
                    {
                        replaceInList(m_lengthList.get(replacementIndex), m_seq.get(i));
                        m_lengthList.set(replacementIndex, m_seq.get(i));
                    }

                }
            }

            int maxValueLength = m_valueListMap.entrySet().iterator().next().getKey();
            for (Integer i : m_valueListMap.keySet())
            {
                if (m_valueListMap.get(i).size() > m_valueListMap.get(maxValueLength).size())
                {
                    maxValueLength = i;
                }
            }

            m_subSeq = m_valueListMap.get(maxValueLength);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void replaceInList(Integer previousLastValue, Integer newLastValue)
    {
        ArrayList<Integer> list = new ArrayList<Integer>(m_valueListMap.get(previousLastValue));
        list.remove(previousLastValue);
        list.add(newLastValue);

        m_valueListMap.remove(previousLastValue);
        m_valueListMap.put(newLastValue, list);
    }

    private void appendToList(Integer newLastValue) {
        int previousLastValue = m_lengthList.get(m_length - 1);
        ArrayList<Integer> list = new ArrayList<Integer>(m_valueListMap.get(previousLastValue));
        list.add(newLastValue);

        m_valueListMap.put(newLastValue, list);
    }

    private void makeNewList(Integer value)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(value);
        m_valueListMap.put(value, list);
    }

    private int findReplacementIndex(Integer integer) throws Exception
    {
        for (int i=0; i<m_lengthList.size(); ++i)
        {
            if (m_lengthList.get(i) > integer)
            {
                return i;
            }
        }

        throw new Exception("cannot find replacement index");
    }
}
