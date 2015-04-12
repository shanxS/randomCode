import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shanxS on 4/11/2015.
 */
public class Runner {
    List<Integer> m_list;
    List<Integer> m_lengthList;
    Map<Integer, ArrayList<Integer>> m_valueListMap;
    Integer m_length;

    public Runner()
    {
        m_list = new ArrayList<Integer>();
        m_list.add(2);
        m_list.add(5);
        m_list.add(3);
        m_list.add(7);
        m_list.add(11);
        m_list.add(8);
        m_list.add(10);
        m_list.add(13);
        m_list.add(6);
        /*
        m_list.add(0);
        m_list.add(8);
        m_list.add(4);
        m_list.add(12);
        m_list.add(2);
        m_list.add(10);
        m_list.add(6);
        m_list.add(14);*/

        m_lengthList = new ArrayList<Integer>();
        for (int i=0; i<m_list.size(); ++i)
        {
            m_lengthList.add(new Integer(0));
        }

        m_valueListMap = new HashMap<Integer, ArrayList<Integer>>();

        m_length = 0;
    }

    public void run()
    {
        try {
            for (int i=0; i<m_list.size(); ++i)
            {
                if (m_length == 0)
                {
                    m_lengthList.set(m_length, m_list.get(i));
                    makeNewList(m_list.get(i));
                    m_length++;
                }
                else if (m_lengthList.get(m_length-1) < m_list.get(i))
                {
                    m_lengthList.set(m_length, m_list.get(i));
                    appendToList(m_list.get(i));
                    m_length++;
                }
                else
                {
                    int replacementIndex = findReplacementIndex(m_list.get(i));

                    if (m_lengthList.get(replacementIndex) > m_list.get(i))
                    {
                        replaceInList(m_lengthList.get(replacementIndex), m_list.get(i));
                        m_lengthList.set(replacementIndex, m_list.get(i));
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

            printList(m_valueListMap.get(maxValueLength));
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

    private void printList(List<Integer> m_lengthList)
    {
        for(Integer i : m_lengthList)
        {
            System.out.print(i + " ");
        }

        System.out.println("");
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

