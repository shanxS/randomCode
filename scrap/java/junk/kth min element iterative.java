// kth min element iterative

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {7, 10, 4, 3, 20, 15};
        (new KthMin()).find(ar, 3);
    }
}

class KthMin
{
    private int targetPivot;
    private int[] ar;
    public void find(int[] ar, int k)
    {
        this.ar = ar;
        targetPivot = k-1;
        partialSort(0, ar.length-1);
        System.out.print(ar[targetPivot]);
    }

    private void partialSort(int start, int end)
    {
        if (start >= end)
        {
            return;
        }

        int pivotIndex = end;
        int cursor = start;
        while (pivotIndex > cursor)
        {
            if (ar[pivotIndex] < ar[cursor])
            {
                if (pivotIndex - 1 == cursor)
                {
                    swap(pivotIndex, cursor);
                    --pivotIndex;
                    ++cursor;
                } else
                {
                    swap(pivotIndex - 1, cursor);
                    swap(pivotIndex - 1, pivotIndex);
                    --pivotIndex;
                }
            }
            else
            {
                ++cursor;
            }
        }

        if (pivotIndex == targetPivot)
        {
            return;
        }
        else if (pivotIndex < targetPivot)
        {
            partialSort(pivotIndex+1, end);
        }
        else
        {
            partialSort(start, pivotIndex-1);
        }
    }

    private void swap(int from, int to)
    {
        int tmp = ar[from];
        ar[from] = ar[to];
        ar[to] = tmp;
    }
}