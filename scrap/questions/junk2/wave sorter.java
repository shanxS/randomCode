// wave sorter

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {10, 5, 6, 3, 2, 20, 100, 80};
        (new WaveSorter()).sort(ar);
    }
}

class WaveSorter
{
    private int[] ar;
    public void sort(int[] xx)
    {
        this.ar = xx;
        int cursor = 0;
        while (cursor < ar.length)
        {
            if (cursor == 0)
            {
                if (ar[0] < ar[1])
                {
                    swap(0,1);
                }
            }
            else if (cursor == ar.length-2 && cursor%2 == 0)
            {
                if (ar[cursor] < ar[ar.length-1])
                {
                    swap(cursor, ar.length-1);
                }
            }
            else
            {
                if (ar[cursor] < ar[cursor-1] || ar[cursor] < ar[cursor+1])
                {
                    if (ar[cursor-1] > ar[cursor+1])
                    {
                        swap(cursor, cursor-1);
                    }
                    else
                    {
                        swap(cursor, cursor+1);
                    }
                }
            }

            cursor += 2;
        }

        for (int i=0; i<ar.length; ++i)
        {
            System.out.print(ar[i] + " ");
        }
    }

    private void swap(int from, int to)
    {
        int tmp = ar[from];
        ar[from] = ar[to];
        ar[to] = tmp;
    }
}