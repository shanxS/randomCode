// make array with the product of the  elements of the first array except for the current index element
// space complexity O(1)
// r2, q2, set8

import java.util.Arrays;

public class Main{

    public static void main(String[] args) {
        Holer holer = new Holer(new Integer[]{1,2,3,4,5});
        holer.hole();
    }
}

class Holer {
    private Integer[] array;

    public Holer(Integer[] array){
        this.array = Arrays.copyOf(array, array.length);
    }

    public void hole(){

        Integer[] ans = new Integer[array.length];
        for (Integer i=0; i<ans.length; ++i)
        {
            ans[i] = 1;
        }

        Integer temp = array[0];
        for(Integer i=1; i<array.length; ++i)
        {
            ans[i] *= temp;
            temp *= array[i];
        }

        temp = array[array.length-1];
        for(Integer i=array.length-2; i>=0; --i)
        {
            ans[i] *= temp;
            temp *= array[i];
        }


        Arrays.asList(ans).stream().forEach(x -> System.out.print(x + " "));
    }
}