// find inflexion point and element in rotated sorted array
// r1, q1, set18

public class Main
{
    public static void main(String[] args)
    {
//        Integer[] array = new Integer[]{4,1,2};
//        Integer[] array = new Integer[]{4,1,2,3};
//        Integer[] array = new Integer[]{3,4,1,2};
//        Integer[] array = new Integer[]{2,1,6,5,4,3};

//        Integer[] array = new Integer[]{5,6,7,8,9,4,3};
//        System.out.print(finder.find(3));

        Integer[] array = new Integer[]{5,4,3,2,1};

        Finder finder = new Finder(array);
        System.out.print(finder.find(1));
    }
}

class Finder
{
    Integer[] array;

    public Finder(Integer[] array)
    {
        this.array = array;
    }

    public Boolean find(Integer target)
    {
        Integer inflexionIndex = findInflexionIndex();

        return binarySearch(0, inflexionIndex, target) ||
                binarySearch(inflexionIndex, array.length, target);
    }

    private boolean binarySearch(int start, int end, int target)
    {
        if (end == 0)
        {
            return false;
        }

        Integer mid = (start + end)/2;
        Boolean found = false;
        Boolean ruleIsIncreasing = array[start] < array[end-1];

        while(start < end)
        {
            if (array[mid] == target)
            {
                found = true;
                break;
            }
            if ((array[mid] < target))
            {
                if (ruleIsIncreasing)
                {
                    start = mid+1;
                }
                else
                {
                    end = mid;
                }
            }
            else
            {
                if (ruleIsIncreasing)
                {
                    end = mid;
                }
                else
                {
                    start = mid+1;
                }
            }

            mid = (start+end)/2;
        }

        return found;
    }

    private Integer findInflexionIndex()
    {
        Integer globalMax = (array[0] > array[array.length-1]) ?
                array[0] : array[array.length-1];
        Boolean directionLeftGlobalMax = (array[0] >
                array[array.length-1]) ? true : false;

        Integer start = 0;
        Integer end = array.length-1;
        Integer mid = (start + end)/2;
        Boolean found = false;

        while(start <= end && mid-1 >=0 && mid+1 <=array.length-1)
        {
            if (array[mid] > array[mid+1]
                    == array[mid] > array[mid-1])
            {
                found = true;
                break;
            }
            else
            {
                if (array[mid] < globalMax)
                {
                    if (directionLeftGlobalMax)
                    {
                        end = mid-1;
                    }
                    else
                    {
                        start = end+1;
                    }
                }
                else
                {
                    if (array[mid] < array[mid+1])
                    {
                        start = mid+1;
                    }
                    else
                    {
                        end = mid-1;
                    }
                }
            }

            mid = (start+end)/2;
        }

        return (found) ? mid : 0;
    }


}
