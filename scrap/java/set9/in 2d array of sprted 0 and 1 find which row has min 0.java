// in 2d array of sprted 0 and 1 find which row has min 0
// telephonic1, q1, set9 

public class Main{
    public static void main(String[] args) {
        Integer[][] array = new Integer[][] {
                {0,0,0,0,1},
                {0,0,1,1,1},
                {0,1,1,1,1}
        };

        Integer minrow = null;
        Integer minIndex = Integer.MAX_VALUE;
        for (Integer row = 0; row < array.length; ++row) {
            Integer tempMinIndex = binarySearch(array[row], 1);
            if (tempMinIndex < minIndex)
            {
                minIndex = tempMinIndex;
                minrow = row;
            }
        }

        System.out.print("row " + minrow);

    }

    private static Integer binarySearch (Integer[] array, Integer key) {
        Integer index = null;

        Integer start = 0;
        Integer end = array.length;
        Integer mid = (start + end)/2;

        while (start <= end) {
            if (array[mid] == key) {
                if (mid - 1 < 0 || array[mid-1] != key) {
                    index = mid;
                    break;
                } else {
                    end = mid-1;
                    mid = (start + end)/2;
                }
            }
            else if (array[mid] > key) {
                end = mid-1;
                mid = (start + end)/2;
            }
            else {
                start = mid+1;
                mid = (start + end)/2;
            }

        }

        return index;
    }
}