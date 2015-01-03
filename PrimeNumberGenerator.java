import java.util.*;

public class PrimeNumberGenerator{

    public static void main(String[] args) {
        int limit = 20;
        List<Integer> a = seive(limit);
        for(int i=0; i<limit; ++i)
            System.out.println(i + " is " + (a.get(i) == 0 ? "prime" : "not prime"));
    }

    // indexing starts from 0
    // indicies of values == 0 are prime
    public static List<Integer> seive(int limit){
        int i, j, MAX;
        MAX = limit;
        Integer[] array = new Integer[MAX];
        List<Integer> isPrime = Arrays.asList(array);
        Collections.fill(isPrime, 0);
        isPrime.set(0, 1);
        isPrime.set(1, 1);
        for(i=2;i*i<MAX;i++)
        {
            if(isPrime.get(i)==0)
            {
                j=i*i;
                for(;j<MAX;j+=i)
                    isPrime.set(j, 1);
            }
        }

        return isPrime;
    }
    
}