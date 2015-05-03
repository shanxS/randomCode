// find index of array where left half and right half have same values
// telephonic1, q2, amazon

public class Main
{
    static Integer[] array = new Integer[] {119,20,30,50,10,9};
    //static Integer[] array = new Integer[] {20,30,50,10,9,119};
    //static Integer[] array = new Integer[] {20,30,50,119,10,9};
    static Boolean currentDirection = null;
    static Boolean hasDirectionChanged = false;

    public static void main(String[] args)
    {
        Integer mid = (array.length)/2;
        Boolean found = false;

        Integer sumFirstHalf = getSum(0, mid);
        Integer sumLastHalf = getSum(mid, array.length);

        while(mid >= 0 && mid < array.length && !found && !hasDirectionChanged)
        {
            if (sumFirstHalf == sumLastHalf)
            {
                found = true;
            }
            else
            {
                if (sumFirstHalf > sumLastHalf)
                {
                    sumFirstHalf -= array[mid-1];
                    sumLastHalf += array[mid-1];
                    --mid;
                    setDirection(false);
                }
                else
                {
                    sumFirstHalf += array[mid];
                    sumLastHalf -= array[mid];
                    ++mid;
                    setDirection(true);
                }
            }
        }

        if (!found)
        {
            System.out.println("cant find");
        }
        else
        {
            System.out.println("found at " + array[mid]);
        }
    }

    private static void setDirection(Boolean dir)
    {
        if (currentDirection == null)
        {
            currentDirection = dir;
        }
        else if (currentDirection != dir)
        {
            hasDirectionChanged = true;
        }
    }

    private static Integer getSum(Integer start, Integer end)
    {
        Integer sum = 0;
        for (Integer i=start; i<end; ++i)
        {
            sum += array[i];
        }

        return sum;
    }
}