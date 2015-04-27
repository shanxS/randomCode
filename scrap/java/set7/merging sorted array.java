// merging sorted array
// written test q3, set7, amazon

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] array1 = new Integer[] {1,3,5,7};
        Integer[] array2 = new Integer[8];
        array2[0] = 2;
        array2[1] = 4;
        array2[2] = 6;
        array2[3] = 8;

        merge(array1, array2);
        for (Integer i : array2)
        {
            System.out.print(i + " ");
        }

    }

    private static void merge(Integer[] source, Integer[] target) {
        Integer targetElementCount = getElementCount(target);
        Integer[] copyOfTarget = Arrays.copyOfRange(target, 0, targetElementCount);

        Integer sourceCounter = 0;
        Integer targetCounter = 0;
        Integer mergedCounter = 0;
        while(sourceCounter < source.length && targetCounter < copyOfTarget.length)
        {
            if (source[sourceCounter] < copyOfTarget[targetCounter])
            {
                target[mergedCounter] = source[sourceCounter];
                ++sourceCounter;
            }
            else
            {
                target[mergedCounter] = copyOfTarget[targetCounter];
                ++targetCounter;
            }

            ++mergedCounter;
        }

        while(sourceCounter < source.length)
        {
            target[mergedCounter] = source[sourceCounter];
            ++sourceCounter;
            ++mergedCounter;
        }

        while(targetCounter < copyOfTarget.length)
        {
            target[mergedCounter] = copyOfTarget[targetCounter];
            ++targetCounter;
            ++mergedCounter;
        }

    }

    private static Integer getElementCount(Integer[] target) {
        Integer count = 0;

        for(Integer element : target)
        {
            if (element != null)
            {
                ++count;
            }
        }

        return count;
    }
}
