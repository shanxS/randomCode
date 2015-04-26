// find mean of sorted arrya in log n
// r2, q1. set 6, amazon

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] array1 = new Integer[] {1,3,5,7};
        Integer[] array2 = new Integer[] {2,4,6};

        Integer median = getMedianIndex(array1, array2);
        System.out.print(median);
    }

    private static Integer getMedianIndex(Integer[] array1, Integer[] array2) {
        Integer medianIndex1 = array1.length/2;
        Integer medianIndex2 = array2.length/2;

        if (array1[medianIndex1] == array2[medianIndex2])
        {
            return array1[medianIndex1];
        }
        else if(array1.length == array2.length && array1.length == 2)
        {
            return Math.min(Math.max(array1[0], array2[0])
                            , Math.min(array1[1], array2[1]));
        }
        else if (array1[medianIndex1] > array2[medianIndex2])
        {
            Integer[] arrayCopy1 = array1.length == 2 ? array1 : Arrays.copyOfRange(array1, 0, medianIndex1+1);
            Integer[] arrayCopy2 = array2.length == 2 ? array2 : Arrays.copyOfRange(array2, medianIndex2, array2.length);
            return getMedianIndex(arrayCopy1, arrayCopy2);
        }
        else
        {
            Integer[] arrayCopy1 = array1.length == 2 ? array1 : Arrays.copyOfRange(array1, medianIndex1, array1.length);
            Integer[] arrayCopy2 = array2.length == 2 ? array2 : Arrays.copyOfRange(array2, 0, medianIndex2+1);
            return getMedianIndex(arrayCopy1, arrayCopy2);
        }
    }
}