// radix sort
// code question: 124

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {170, 45, 75, 90, 802, 24, 2, 66};//{101,20,2,200};
        RadixSorter rs = new RadixSorter();
        for (Integer v : rs.sort(array))
        {
            System.out.print(v + " ");
        }
    }
}

class RadixSorter
{
    private Integer[] array;

    public Integer[] sort(Integer[] array)
    {
        this.array = array;
        Integer max = fetchMax();

        Integer exp = 1;
        while (max / exp > 0)
        {
            sortFor(exp);
            exp *= 10;
        }

        return array;
    }

    private void sortFor(Integer exp)
    {
        int[] count = new int[10];
        for(Integer i=0; i<array.length; ++i)
        {
            count[(array[i]/exp) % 10] += 1;
        }
        for (Integer i=1; i<count.length; ++i)
        {
            count[i] += count[i-1];
        }

        int[] out = new int[array.length];
        for (Integer i=array.length-1; i>=0; --i)
        {
            out[ count[(array[i]/exp) % 10]  - 1 ] = array[i];
            --count[(array[i]/exp) % 10];
        }

        for (int i=0; i<array.length; ++i)
        {
            array[i] = out[i];
        }
    }

    private Integer fetchMax()
    {
        Integer max = Integer.MIN_VALUE;
        for (Integer v : array)
        {
            if (v > max)
            {
                max = v;
            }
        }

        return max;
    }
}