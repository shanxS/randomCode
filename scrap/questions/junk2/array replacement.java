// array replacement

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2, 3, 4, 5, 6};

        Integer cache = null;
        for (Integer i=0; i<ar.length; ++i)
        {
            if (i == 0 && ar.length > 1)
            {
                cache = ar[0];
                ar[0] *= ar[1];
            }
            else if (i==ar.length-1)
            {
                ar[i] *= cache;
            }
            else
            {
                Integer thisCache = ar[i];
                ar[i] = cache * ar[i+1];
                cache = thisCache;
            }
        }

        for (Integer i : ar)
        {
            System.out.print(i + " ");
        }
    }
}