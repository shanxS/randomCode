// Longest Even Length Substring such that Sum of First and Second Half is same
// code question: 53

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,2,3,1,2,3};
//        Integer[] array = {1,5,3,8,0,2,3};
        Finder f = new Finder();
        System.out.print(f.find(array));
    }
}

class Finder
{
    private Integer maxLen = Integer.MIN_VALUE;
    private Integer[] array;

    public Integer find(Integer[] array)
    {
        this.array = array;

        Integer end = array.length-1;
        if (end%2 == 0)
        {
            find(0, end-1);
            find(1,end);
        }
        else
        {
            find(0, end);
        }

        return maxLen;
    }

    private void find(Integer start, Integer end)
    {
        if (start >= end)
        {
            return;
        }

        Integer mid = start + (end-start)/2;
        Integer startCursor = start;
        Integer endCursor = mid+1;

        Integer leftSum = 0, rightSum = 0;
        while(startCursor <= mid)
        {
            leftSum += array[startCursor];
            rightSum += array[endCursor];

            ++startCursor;
            ++endCursor;
        }

        if (leftSum == rightSum && (end-start+1) > maxLen)
        {
            maxLen = end-start+1;
        }

        find(start + 2, end);
        find(start, end - 2);
    }
}