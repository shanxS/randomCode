// Maximum Sum Increasing Subsequence

public class Main
{
    public static void main(String[] er)
    {
        MaxSumLIS mslis = new MaxSumLIS();
//        System.out.print(mslis.find(new Integer[]{1, 101, 2, 3, 100, 4, 5}));
//        System.out.print(mslis.find(new Integer[]{3, 4, 5, 10}));
        System.out.print(mslis.find(new Integer[]{10, 5, 4, 3}));
    }
}

class MaxSumLIS
{
    private Integer[] ar;
    public Integer find(Integer[] ar)
    {
        this.ar = ar;

        return (ar[0] < ar[1]) ? (ar[0] + contemplate(1))
                               : Math.max(ar[0], contemplate(1));
    }

    private Integer contemplate(Integer index)
    {
        if (index == ar.length-1)
        {
            return ar[index];
        }
        if (index >= ar.length)
        {
            return 0;
        }

        return (ar[index] < ar[index+1]) ? (ar[index] + contemplate(index+1))
                : Math.max(ar[index], contemplate(index+1));
    }
}