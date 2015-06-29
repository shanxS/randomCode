// Find the closest pair from two sorted arrays

public class Main
{
    private static Integer[] array1 = {1, 4, 5, 7};//{1,2,3,4,5};//{5,15,19,25};
    private static Integer[] array2 = {10, 20, 30, 40};//{100,200,300};//{1,4,10,12,16};


    private static Integer start1 = 0, start2 = 0;
    private static Integer end1 = array1.length-1, end2 = array2.length-1;
    private static Integer target = 50;//500;//27;
    private static Integer big, small;
    private static Integer minDiff = Integer.MAX_VALUE, value1, value2;

    public static void main(String[] er)
    {


        while (start1 <= end1 || start2 <= end2)
        {
            setBigValue();
            setSmallValue();
            Integer thisSum = big + small;

            if (Math.abs(thisSum - target) < minDiff)
            {
                minDiff = Math.abs(thisSum - target);
                value1 = big;
                value2 = small;
            }

            if (thisSum < target)
            {
                increamentSmall();
            }
            else if (thisSum > target)
            {
                decrementBig();
            }
            else
            {
                break;
            }
        }

        System.out.print(value1 + " " + value2);

    }

    private static void decrementBig()
    {
        if (start1 <= end1 && start2 <= end2)
        {
            if (array1[end1] > array2[end2])
            {
                --end1;
            }
            else
            {
                --end2;
            }
        }
        else if (start1 <= end1)
        {
            --end1;
        }
        else if (start2 <= end2)
        {
            --end2;
        }
    }

    private static void increamentSmall()
    {
        if (start1 <= end1 && start2 <= end2)
        {
            if (array1[start1] > array2[start2])
            {
                ++start2;
            }
            else
            {
                ++start1;
            }
        }
        else if (start1 <= end1)
        {
            ++start1;
        }
        else if (start2 <= end2)
        {
            ++start2;
        }
    }

    private static void setSmallValue()
    {
        if (start1 <= end1 && start2 <= end2)
        {
            if (array1[start1] > array2[start2])
            {
                small = array2[start2];
            }
            else
            {
                small = array1[start1];
            }
        }
        else if (start1 <= end1)
        {
            small = array1[start1];
        }
        else if (start2 <= end2)
        {
            small = array2[start2];
        }
    }


    private static void setBigValue()
    {
        if (start1 <= end1 && start2 <= end2)
        {
            if (array1[end1] > array2[end2])
            {
                big = array1[end1];
            }
            else
            {
                big = array2[end2];
            }
        }
        else if (start1 <= end1)
        {
            big = array1[end1];
        }
        else if (start2 <= end2)
        {
            big = array2[end2];
        }
    }
}