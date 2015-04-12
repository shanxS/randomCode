// http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
// time complexit: nlogn
// space: n

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanxS on 4/11/2015.
 */
public class Runner {
    List<Integer> m_list;
    List<Integer> m_lengthList;
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

        m_lengthList = new ArrayList<Integer>();
        for (int i=0; i<m_list.size(); ++i)
        {
            m_lengthList.add(new Integer(0));
        }

        m_length = 0;
    }

    public void run()
    {
        try
        {
            for (int i=0; i<m_list.size(); ++i)
            {
                if (m_length == 0)
                {
                    m_lengthList.set(m_length, m_list.get(i));
                    m_length++;
                }
                else if (m_lengthList.get(m_length-1) < m_list.get(i))
                {
                    m_lengthList.set(m_length, m_list.get(i));
                    m_length++;
                }
                else
                {
                    int replacementIndex = findReplacementIndex(m_list.get(i));
                    m_lengthList.set(replacementIndex, m_list.get(i));
                }
            }

            printList(m_lengthList);

            System.out.println(m_length);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

