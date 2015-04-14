// set 2, amazon, geek for geeks, round 3, q1
// http://www.geeksforgeeks.org/amazon-interview-set-2/

import java.util.*;

// Created by shanxS on 4/11/2015.
public class Runner {

    Set<Pair> m_pairs;

    public Runner(Set<Pair> pairs)
    {
        m_pairs = new LinkedHashSet<>(pairs);
    }

    public Integer run()
    {
        Integer count = 0;
        Integer cacheCount = 0;

        Pair previousPair = null;
        for (Pair pair : m_pairs)
        {
            if (previousPair != null)
            {
                if (previousPair.getSecond() < pair.getFirst())
                {
                    ++cacheCount;
                }
                else
                {
                    if (cacheCount > count)
                    {
                        count = cacheCount;
                    }
                    cacheCount = 0;
                }
            }
            previousPair = pair;
        }

        if (cacheCount > count)
        {
            count = cacheCount;
        }

        return count;
    }

    public void print()
    {
        for (Pair pair : m_pairs)
        {
            System.out.println(pair.getFirst() + " " + pair.getSecond());
        }
    }
}

