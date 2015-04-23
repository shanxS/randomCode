// Given two strings, write a function to remove all characters in one string which are present in other string
// r1, q2, set6, amazon

public class Main {

    public static void main(String[] args)
    {
        String first = "random sentence where I did some spelling mistakes";
        String second = "another total randomly spe sent";
        for (Character a : second.toCharArray()) {
            first = first.replaceFirst("" + a, "");
            System.out.println(first);
        }

    }

}
