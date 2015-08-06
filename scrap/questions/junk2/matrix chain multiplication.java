// matrix chain multiplication

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10, 20, 30};
        List<Integer> list = new ArrayList<>();
        for (Integer i : ar)
        {
            list.add(i);
        }

        System.out.print((new ChainMultiplication()).findMin(list));
    }
}

class ChainMultiplication
{
    public Integer findMin(List<Integer> list)
    {
        if (list.size() == 3)
        {
            return list.get(0) * list.get(1) * list.get(2);
        }

        Integer thisMin = null;
        for (Integer i=0; i<list.size()-2; ++i)
        {
            List<Integer> bag = new ArrayList<>(list);
            bag.remove((int)(i+1));
            Integer value = (list.get(i)*list.get(i+1)*list.get(i+2))
                    + findMin(bag);

            if (thisMin == null)
            {
                thisMin = value;
            }
            else
            {
                thisMin = Math.min(thisMin, value);
            }
        }

        return thisMin;
    }


}