// randomise array
// r2, q1, set5, amazon

import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
        Integer[] array = {1,2,3,4,5,6,7,8,9};

        for(Integer counter = array.length-1; counter >= 0; --counter)
        {
            Integer swapIndex = randInt(0, counter);
            Integer tmp = array[counter];
            array[counter] = array[swapIndex];
            array[swapIndex] = tmp;
        }

        for (Integer value : array)
        {
            System.out.print(value + " ");
        }

    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
