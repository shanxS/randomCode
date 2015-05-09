// find 1st occurance of 1 in sorted binary array
// r3, q3, set13

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{0,0,0,0,1};

        OneFinder of = new OneFinder(array);
        of.find();
    }
}

class OneFinder
{
    private Integer[] array;

    public OneFinder(Integer[] array)
    {
        this.array = array;
    }

    public void find()
    {
        Integer start = 0;
        Integer end = array.length;
        Integer mid = (start + end)/2;
        Integer lastFoundIndex = -1;

        while(start < end)
        {
            if (array[mid] == 1)
            {
                lastFoundIndex = mid;
                end = mid-1;
            }
            if (array[mid] == 0)
            {
                start = mid + 1;
            }

            mid = (start + end)/2;
        }

        System.out.print(lastFoundIndex);
    }
}