// from given set of numebers get max number divisible by 3 and 5
// r1, q1, set24

import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] array = new Integer[]{5,6,7,9};
        //Integer[] array = new Integer[]{5,6,7,9,0};
        //Integer[] array = new Integer[]{5,9,5};
        //Integer[] array = new Integer[]{5,9,0};
        Integer[] array = new Integer[]{5,5,5,0,0,5};
        MaxMultiple mm = new MaxMultiple();
        System.out.println(mm.getMax(array));
    }
}

class MaxMultiple
{
    private Queue<Integer> zeros = new ArrayDeque<>();
    private Queue<Integer> ones = new ArrayDeque<>();
    private Queue<Integer> twos = new ArrayDeque<>();
    private Boolean removed5FromNumbers = false;
    private Boolean contained5 = false;

    public Integer getMax(Integer[] array)
    {
        List<Integer> numbers = new ArrayList<> (Arrays.asList(array));
        Collections.sort(numbers);
        if (numbers.get(0) != 0 && !contains5(array))
        {
            return -1;
        }
        if (numbers.get(0) != 0 && contained5)
        {
            numbers.remove(new Integer(5));
            removed5FromNumbers = true;
        }
        setupQueues(numbers);


        Integer sum = (removed5FromNumbers) ? 5 : 0;
        for (Integer value : numbers)
        {
            sum += value;
        }

        Integer remainder = sum%3;
        if (remainder == 0)
        {
            return convertQuesToInteger();
        }
        else if (remainder == 1)
        {
            if (ones.size() >= 1)
            {
                ones.remove();
                return convertQuesToInteger();
            }
            else if (twos.size() >= 2)
            {
                twos.remove();
                twos.remove();
                return convertQuesToInteger();
            }
        }
        else if (remainder == 2)
        {
            if (twos.size() >= 1)
            {
                twos.remove();
                return convertQuesToInteger();
            }
            else if (ones.size() >= 2)
            {
                ones.remove();
                return convertQuesToInteger();
            }
        }

        return -1;
    }

    private boolean contains5(Integer[] array)
    {
        Integer start = 0;
        Integer end = array.length-1;

        while (start <= end)
        {
            Integer mid = (start + end) / 2;
            if (array[mid] < 5)
            {
                start = mid + 1;
            }
            else if (array[mid] > 5)
            {
                end = mid - 1;
            }
            else
            {
                contained5 = true;
                return contained5;
            }
        }

        return false;
    }

    private Integer convertQuesToInteger()
    {
        List<Integer> values = new ArrayList<>();
        while(!zeros.isEmpty())
        {
            values.add(zeros.remove());
        }

        while(!twos.isEmpty())
        {
            values.add(twos.remove());
        }

        while(!ones.isEmpty())
        {
            values.add(ones.remove());
        }

        if (contained5 && removed5FromNumbers)
        {
            values.add(5);
        }

        Collections.sort(values);
        Integer value = 0;
        for (Integer i=0; i<values.size(); ++i)
        {
            value += values.get(i) * (int)Math.pow(10, i);
        }

        return value;
    }

    private void setupQueues(List<Integer> array)
    {
        for (Integer value : array)
        {
            if (value % 3 == 0)
            {
                zeros.add(value);
            }
            else if (value % 3 == 1)
            {
                ones.add(value);
            }
            else if (value % 3 == 2)
            {
                twos.add(value);
            }
        }
    }
}