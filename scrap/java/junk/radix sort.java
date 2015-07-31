// radix sort

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {170, 45, 75, 90, 802, 24, 2, 66};
//        int[] ar = {2,45,170,75};
        RadixSort rs = new RadixSort();

        for (int a : rs.sort(ar))
        {
            System.out.print(a + " ");
        }
    }
}

class RadixSort
{
    private Integer max;
    private int[] ar;

    public int[] sort(int[] ar)
    {
        this.max = getMax(ar);
        this.ar  = ar;

        Integer exp = 1;
        while (max/exp > 0)
        {
            sortFor(exp);
            exp *= 10;
        }

        return this.ar;
    }

    private Integer getMax(int[] ar)
    {
        int max = Integer.MIN_VALUE;

        for (Integer i=0; i<ar.length; ++i)
        {
            max = Math.max(max, ar[i]);
        }

        return max;
    }

    private void sortFor(Integer exp)
    {
        int[] counter = new int[10];
        for (Integer i=0; i<ar.length; ++i)
        {
            ++counter[(ar[i]/exp)%10];
            System.out.println(ar[i] + " mapped to " + ((ar[i] / exp) % 10) + " count of " + counter[(ar[i]/exp)%10]);
        }

        for (Integer i=1; i<10; ++i)
        {
            counter[i] += counter[i-1];
        }

        int[] out = new int[ar.length];
        for (Integer i=ar.length-1; i>=0; --i)
        {
            out[counter[(ar[i] / exp) % 10] - 1] = ar[i];
            --counter[(ar[i]/exp)%10];
        }

        ar = out;
    }
}