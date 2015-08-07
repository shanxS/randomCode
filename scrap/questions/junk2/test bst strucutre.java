// test bst strucutre

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        int[] a = {2, 1, 4, 3};
        List<Integer> l1 = new ArrayList<>();
        for (Integer i : a)
        {
            l1.add(i);
        }

        int[] b = {2, 4, 3, 1};
        List<Integer> l2 = new ArrayList<>();
        for(Integer i : b)
        {
            l2.add(i);
        }

        (new Tester()).test(l1, l2);
    }
}

class Tester
{
    public void test(List<Integer> l1, List<Integer> l2)
    {
        System.out.print(processFor(l1, l2));
    }

    private boolean processFor(List<Integer> l1, List<Integer> l2)
    {
        if ((l1.size() == 0 || l2.size() == 0)
                || l1.size() != l2.size())
        {
            return l1.size() == l2.size();
        }

        if (l1.get(0) != l2.get(0))
        {
            return false;
        }

        List<Integer> l1Left = new ArrayList<>();
        List<Integer> l1Right = new ArrayList<>();
        List<Integer> l2Left = new ArrayList<>();
        List<Integer> l2Right = new ArrayList<>();
        Integer node = l1.get(0);
        for (Integer i=1; i<l1.size(); ++i)
        {
            if (l1.get(i) < node)
            {
                l1Left.add(l1.get(i));
            }
            else if (l1.get(i) > node)
            {
                l1Right.add(l1.get(i));
            }

            if (l2.get(i) < node)
            {
                l2Left.add(l2.get(i));
            }
            else if (l2.get(i) > node)
            {
                l2Right.add(l2.get(i));
            }
        }

        return processFor(l1Left, l2Left) && processFor(l1Right, l2Right);
    }
}