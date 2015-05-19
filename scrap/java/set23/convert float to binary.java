// convert float to binary
// online, q1, set23

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String strNumber = "3.1";

        if (!isBinaryPossible(strNumber))
        {
            System.out.print("cant convert ot binary");
            return;
        }

        double number = Double.parseDouble(strNumber);
        int whole = (int) number;
        double fraction = number - whole;

        List<Integer> wholeBinary = new ArrayList<>();
        while(whole > 0)
        {
            wholeBinary.add(whole % 2);
            whole /= 2;
        }

        List<Integer> fractionBinary = new ArrayList<>();
        while (fraction > 0)
        {
            fraction *= 2;
            fractionBinary.add((int) fraction);
            fraction = fraction - (int)fraction;
        }

        wholeBinary.stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        fractionBinary.stream().forEach(x -> System.out.print(x + " "));
    }

    private static boolean isBinaryPossible(String strNumber)
    {
        String strFraction = strNumber.split("\\.")[1];
        Integer lenght = strFraction.length();
        Integer fraction = Integer.parseInt(strFraction);

        while(lenght > 0)
        {
            if (fraction % 5 != 0)
            {
                return false;
            }

            fraction /= 5;
            --lenght;
        }

        return true;
    }
}