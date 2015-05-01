// find contious sub array with max sum Kadane algo
// r1, q1, set 8 amazon

public class Main{
    public static void main(String[] args) {
        Integer[] array = new Integer[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        Integer maxSoFar = 0;
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

            previousCurrentMax = currentMax;
            previousMaxSoFar = maxSoFar;
        }

        System.out.println("max sum " + maxSoFar + " sub array is:");
        while (start <= end)
        {
            System.out.print(array[start] + " ");
            ++start;
        }
    }
}