// duplicate at k distance

import java.util.HashSet;
import java.util.Set;

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {1, 1,2, 3, 4, 1, 2, 3, 4};
        (new DuplicateElem()).find(ar, 2);
    }
}

class DuplicateElem
{
    public void find(int[] ar, int k)
    {
        Set<Integer> set = new HashSet<>();

        int lead = 1;
        while (lead<k)
        {
            set.add(ar[lead]);
            ++lead;
        }

        int cursor = 0;
        while(lead < ar.length)
        {
            set.add(ar[lead]);
            set.remove(ar[cursor]);
            if (set.contains(ar[cursor]) || set.size() < k)
            {
                System.out.print("repeated ");
                return;
            }

            ++lead;
            ++cursor;
        }
    }
}