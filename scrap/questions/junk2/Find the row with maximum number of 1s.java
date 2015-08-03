// Find the row with maximum number of 1s

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = { {0, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };

        BS b = new BS();

        Integer minIndex = ar.length;
        Integer row = -1;
        for (Integer i=0; i<ar.length; ++i)
        {
            Integer thisMinIndex = b.find(ar[i]);
            if (thisMinIndex < minIndex)
            {
                minIndex = thisMinIndex;
                row = i;
            }
        }

        System.out.print(row + " " + minIndex);
    }
}

class BS
{
    public Integer find(Integer[] ar)
    {
        Integer start = 0;
        Integer end = ar.length - 1;

        if (ar[end] == 0)
        {
            return ar.length;
        }

        while(start <= end)
        {
            Integer mid = start + ((end-start)/2);

            if (ar[mid] == 1)
            {
                if ((mid > 0 && ar[mid-1] == 0)
                        || (mid == 0))
                {
                    return mid;
                }
                else
                {
                    end = mid-1;
                }
            }
            else if(ar[mid] == 0)
            {
                start = mid+1;
            }
        }

        return null;
    }
}