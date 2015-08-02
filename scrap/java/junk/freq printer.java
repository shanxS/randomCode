// freq printer

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2,3,4,5,5,5,6,7,7};
        (new RepeatPrinter()).find(ar);
    }
}

class RepeatPrinter
{
    public void find(Integer[] ar)
    {
        Integer len = ar.length;

        for (Integer i=0; i<ar.length; ++i)
        {
            ar[ar[i]%len] += len;
        }

        for(Integer i=0; i<len; ++i)
        {
            Integer thisCount = ar[i] - (ar[i] % len);
            if (thisCount > 0)
            {
                System.out.println(i + " repeated " + (thisCount/len));
            }
        }
    }
}