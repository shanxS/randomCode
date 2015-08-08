// partiioner

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10,6};
        List<Integer> list = new ArrayList<>();
        for (Integer i : ar)
        {
            list.add(i);
        }

        (new Partion()).partition(list);
    }
}

class Partion
{
    private Integer halfSun;

    public void partition(List<Integer> list)
    {
        Integer sum = 0;
        for (Integer i : list)
        {
            sum += i;
        }

        if (sum%2 !=0 )
        {
            System.out.print("no partition for odd sum");
            return;
        }

        halfSun = sum/2;

        List<Integer> evenList = new ArrayList<>(list);
        evenList.remove((int) 0);

        List<Integer> oddList = new ArrayList<>(list);
        oddList.remove((int) 1);

        boolean result = contemplate(sum-list.get(0), evenList) || contemplate(sum-list.get(1), oddList);
        System.out.print(result);
    }

    private boolean contemplate(int sum, List<Integer> list)
    {
        if (sum == halfSun)
        {
            return true;
        }
        if (sum < halfSun || list.size() <= 1)
        {
            return false;
        }

        List<Integer> evenList = new ArrayList<>(list);
        evenList.remove((int) 0);

        List<Integer> oddList = new ArrayList<>(list);
        oddList.remove((int) 1);

        return contemplate(sum-list.get(0), evenList) || contemplate(sum-list.get(1), oddList);
    }
}