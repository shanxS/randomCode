// swap elem in array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2, 0, 1, 4, 5, 3};
        Swapper sp = new Swapper();
        sp.perform(ar);
    }
}


class Swapper
{
    public void perform(Integer[] ar)
    {
        Integer len = ar.length;

        for (Integer i=0; i<ar.length; ++i)
        {
            Integer index = ar[i]%len;
            Integer value = i*len;

            ar[index] += value;
        }

        for (Integer i=0; i<ar.length; ++i)
        {
            ar[i] = (ar[i] - (ar[i]%len))/len;
            System.out.print(ar[i] + " ");
        }
    }
}