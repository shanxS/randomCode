// make array with the product of the  elements of the first array except for the current index element
// space complexity O(n)
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
        Integer[] sub1 = new Integer[array.length];
        sub1[0] = 1;
        Integer[] sub2 = new Integer[array.length];
        sub2[array.length-1] = 1;


        for(Integer i=1, j=array.length-2; i<array.length; ++i, --j){
            sub1[i] = sub1[i-1] * array[i-1];
            sub2[j] = sub2[j+1] * array[j+1];
        }

        Arrays.asList(sub1).stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        Arrays.asList(sub2).stream().forEach(x -> System.out.print(x + " "));
    }
}