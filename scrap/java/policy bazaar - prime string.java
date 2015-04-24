// policy bazaar hiring
/*
Alice has just learnt about primeStrings. A string is a primeString if the number of distinct alphabets used in the string is a prime and also the number of occurrences of each alphabet in the string is also a prime.
Given a String you need to tell if it is a primeString or not.

Input:

First line contains T which is the number of test cases.
T lines follow each containing a string of characters 'a' to 'z'.

Output:

For each input, output "YES" if the number is a primeString or "NO" if not.

Constraints:

1 = T = 10
1 = Length of string = 105
Scoring:

1 = T = 10, 1 = Length of string = 10 (20 pts)
1 = T = 10, 1 = Length of string = 1000 (30 pts)
1 = T = 10, 1 = Length of string = 105 (50 pts)
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static BufferedReader br;
    static int T;
    static List<String> ANS;
    static boolean[] isComposite;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        runEratosthenesSieve(100000);

        for (int i=0; i<T; ++i){
            String str = br.readLine();
            Map<Character, Integer> charFreq = parse(str);
            if(isPrimeString(charFreq))
            {
                System.out.println("YES");
            }
            else
            {
                System.out.println("NO");
            }
        }

    }

    private static Map<Character, Integer> parse(String str) {
        Map<Character, Integer> charFreq = new HashMap<>();

        for (Character c : str.toCharArray())
        {
            Integer prevValue = charFreq.get(c);
            if (prevValue == null)
            {
                prevValue = 0;
            }
            ++prevValue;
            charFreq.put(c, prevValue);
        }

        for (Map.Entry<Character, Integer> entry : charFreq.entrySet())
        {
            //System.out.println(entry.getKey() + " " + entry.getValue());
        }

        return charFreq;
    }

    private static boolean isPrimeString(Map<Character, Integer> charFreq) {

        if (isComposite[charFreq.size()])
        {
            //System.out.println("size not prime");
            return false;
        }

        for (Map.Entry<Character, Integer> entry : charFreq.entrySet())
        {
            if (isComposite[entry.getValue()])
            {
                //System.out.println("count not prime" + entry.getKey() + " " + entry.getValue());
                return false;
            }
        }

        return true;
    }

    private static void runEratosthenesSieve(int upperBound) {

        int upperBoundSquareRoot = (int) Math.sqrt(upperBound);

        isComposite = new boolean[upperBound + 1];
        isComposite[0] = true;
        isComposite[1] = true;

        for (int m = 2; m <= upperBoundSquareRoot; m++) {

            if (!isComposite[m]) {

                //System.out.print(m + " ");

                for (int k = m * m; k <= upperBound; k += m)

                    isComposite[k] = true;

            }

        }

    }


}
