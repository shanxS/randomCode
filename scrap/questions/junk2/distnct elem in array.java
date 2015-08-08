// distnct elem in array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1, 2, 3, 4, 5,4,5,1,3,3};
        (new DistinctFinder()).find(ar);
    }
}

class DistinctFinder
{
    public void find(Integer[] ar)
    {
        Integer len = ar.length;
        for (int i=0; i<ar.length; ++i)
        {
            ar[ar[i]%len] += len;
        }

        for (int i=0; i<ar.length; ++i)
        {
            Integer value = (ar[i] - ar[i]%len)/len;
            if (value != 0)
            {
                System.out.print(i + " ");
            }
        }
    }
}