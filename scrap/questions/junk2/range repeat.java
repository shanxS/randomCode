range repeat

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {1, 2, 3, 4, 1, 2, 3, 4};
        (new DuplicateElem()).find(ar, 4);
    }
}

class DuplicateElem
{
    private Map<Integer, Integer> valueCount;

    public void find(int[] ar, int k)
    {
        valueCount = new HashMap<>();

        int lead = 0;
        while(lead < k)
        {
            add(ar[lead]);
            ++lead;
        }

        int cusror = 0;
        while (lead < ar.length)
        {
            add(ar[lead]);
            remove(ar[cusror]);

            if (valueCount.keySet().contains(ar[cusror]))
            {
                System.out.print("repeat " + ar[cusror]);
            }

            ++cusror;
            ++lead;
        }
    }

    private void remove(int v)
    {
        Integer prevCount = valueCount.get(v);
        if (prevCount == null)
        {
            return;
        }

        if (prevCount == 1)
        {
            valueCount.remove(v);
        }
        else
        {
            valueCount.put(v, prevCount-1);
        }
    }

    private void add(int v)
    {
        Integer prevCount = valueCount.get(v);
        if (prevCount == null)
        {
            prevCount = 0;
        }
        valueCount.put(v, prevCount+1);
    }
}