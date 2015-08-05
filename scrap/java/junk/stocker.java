// stocker

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] ar = {10, 22, 5, 75, 65, 80};
        Integer[] ar = {90, 80, 70, 60, 50};//{100, 30, 15, 10, 8, 25, 80};//{2, 30, 15, 10, 8, 25, 80};
        (new Stocker()).trade(ar);
    }
}

class Stocker
{
    private Integer[] ar;

    public void trade(Integer[] ar)
    {
        this.ar = ar;
        Integer buyIndex = buyAt(0);
        Integer sellIndex = sellAt(buyIndex + 1);
        int profit = 0;
        while (buyIndex < ar.length && sellIndex < ar.length)
        {
            System.out.println(buyIndex + " " + sellIndex + " " + (ar[sellIndex]-ar[buyIndex]));
            profit += (ar[sellIndex]-ar[buyIndex]);
            buyIndex = buyAt(sellIndex+1);
            sellIndex = sellAt(buyIndex + 1);
        }

        System.out.println(profit);
    }

    private Integer sellAt(int cursor)
    {
        while (cursor < ar.length-1 && ar[cursor] < ar[cursor+1])
        {
            ++cursor;
        }

        return cursor;
    }

    private Integer buyAt(int cursor)
    {
        while (cursor < ar.length-1 && ar[cursor] > ar[cursor+1])
        {
            ++cursor;
        }

        return cursor;
    }
}