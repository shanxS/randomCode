// find duplicate chracters is string
// r2, q1, set8 amazon

import java.util.BitSet;

public class Main{
    private static BitSet bs = new BitSet(26+26);

    public static void main(String[] args) {
        String text = "aabcddffghijAA 123";

        printDuplicate(text);
    }

    private static void printDuplicate(String text) {
        for(Character c : text.toCharArray())
        {
            if (!Character.isAlphabetic(c))
            {
                System.out.println("not an alphabet " + c);
                continue;
            }

            if (Character.isUpperCase(c))
            {
                if (bs.get(c - 'A' + 'z' + 1))
                {
                    System.out.println("repeated " + c);
                }
                else
                {
                    bs.set(c - 'A' + 'z' + 1);
                }
            }
            else
            {
                if (bs.get(c - 'a'))
                {
                    System.out.println("repeated " + c);
                }
                else
                {
                    bs.set(c - 'a');
                }
            }
        }
    }
}