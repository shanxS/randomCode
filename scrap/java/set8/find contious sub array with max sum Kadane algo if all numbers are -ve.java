// find contious sub array with max sum Kadane algo if all numbers are -ve
// easier to undestand than wikipedia's soln

public class Main{
    public static void main(String[] args) {
        Integer[] array = new Integer[]{-2,-3,-1};//{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        Integer maxSoFar = 0;
        Integer maxNumber = Integer.MIN_VALUE;
        Integer maxNumberIndex = null;
        Integer previousMaxSoFar = maxSoFar;
        Integer currentMax = 0;
        Integer previousCurrentMax = currentMax;
        Integer start = 0;
        Integer end = 0;

        for (Integer i=0; i<array.length; ++i){
            currentMax = Math.max(0, currentMax + array[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);

            if (maxSoFar != previousMaxSoFar)
            {
                if (previousCurrentMax == 0)
                {
                    start = i;
                    end  = i;
                }
                else
                {
                    end = i;
                }
            }

            if (array[i] > maxNumber)
            {
                maxNumber = array[i];
                maxNumberIndex = i;
            }

            previousCurrentMax = currentMax;
            previousMaxSoFar = maxSoFar;
        }

        if (maxSoFar != 0)
        {
            System.out.println("max sum " + maxSoFar + " sub array is:");
            while (start <= end)
            {
                System.out.print(array[start] + " ");
                ++start;
            }
        }
        else
        {
            System.out.println("max sum is " + maxNumber + " at " + maxNumberIndex);
        }

    }
}