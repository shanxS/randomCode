// find contious sub array with MIN sum Kadane algo
// easier to undestand than wikipedia's soln

public class Main{
    public static void main(String[] args) {
        Integer[] array = new Integer[]{-2, 1, -3, 4, -1, 2, 1, 4};

        printMinSubArraySum(array);
    }

    private static void printMinSubArraySum(Integer[] array) {
        Integer currentMin = 0;
        Integer previousCurrentMin = currentMin;
        Integer minSoFar = 0;
        Integer previousMinSoFar = minSoFar;
        Integer start = 0;
        Integer end = 0;


        for (Integer i=0; i<array.length; ++i)
        {
            currentMin = Math.min(0, currentMin + array[i]);
            minSoFar = Math.min(minSoFar, currentMin);

            if (minSoFar != previousMinSoFar)
            {
                if (previousCurrentMin == 0)
                {
                    start = i;
                    end = i;
                }
                else
                {
                    end = i;
                }
            }

            previousCurrentMin = currentMin;
            previousMinSoFar = minSoFar;
        }

        System.out.println("min " + minSoFar);
        while (start <= end)
        {
            System.out.print(array[start] + " ");
            ++start;
        }
    }

}