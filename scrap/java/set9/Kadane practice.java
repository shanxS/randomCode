// kadane for min
// telephonic2, q2, set9, amazon

public class Main{
    public  static void main(String[] args) {
        Integer array[] = new Integer[]{-2, 1, -3, 4, -1, 2, 1, 4};

        Integer minSoFar = 0;
        Integer previousMinSoFar = minSoFar;
        Integer currentMin = 0;
        Integer previousCurrentMin = currentMin;
        Integer start = 0;
        Integer end = 0;

        for (Integer i=0; i<array.length; ++i) {
            currentMin = Math.min(0, currentMin + array[i]);
            minSoFar = Math.min(minSoFar, currentMin);

            if (previousMinSoFar != minSoFar) {
                if (previousCurrentMin != 0) {
                    end = i;
                } else {
                    start = i;
                    end = i;
                }
            }

            previousCurrentMin = currentMin;
            previousMinSoFar = minSoFar;
        }

        System.out.println(minSoFar);
        while(start <= end) {
            System.out.print(array[start]);
            ++start;
        }
    }
}



-----------------------------------

// kadane for max

public class Main{
    public  static void main(String[] args) {
        Integer array[] = new Integer[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        Integer maxSoFar = 0;
        Integer previousMaxSoFar = maxSoFar;
        Integer currentMax = 0;
        Integer previousCurrentMax = currentMax;
        Integer start = 0;
        Integer end = 0;

        for (Integer i=0; i<array.length; ++i) {
            currentMax = Math.max(0, currentMax + array[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);

            if (previousMaxSoFar != maxSoFar) {
                if (previousCurrentMax != 0) {
                    end = i;
                }
                else {
                    start = i;
                    end = i;
                }
            }

            previousCurrentMax = currentMax;
            previousMaxSoFar = maxSoFar;
        }

        System.out.println(maxSoFar);
        while(start <= end) {
            System.out.print(" " + array[start]);
            ++start;
        }
    }
}

