// binary conversion of fraction
// r1, q3, set24

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String strNumber = "3.625";
        if (!testBinaryConversion(strNumber))
        {
            System.out.print("cant conver to binary");
        }


        double number = Double.parseDouble(strNumber);
        int whole = (int) number;
        List<Integer> wholeBinary = new ArrayList<>();
        computeWholeBinary(whole, wholeBinary);
        wholeBinary.stream().forEach(x -> System.out.print(x));
        System.out.println();


        double fraction = number - whole;
        List<Integer> fractionBinary = new ArrayList<>();
        computeFractionBinary(fraction, fractionBinary);
        fractionBinary.stream().forEach(x -> System.out.print(x));

    }

    private static boolean testBinaryConversion(String strNumber)
    {
        String strFraction = strNumber.split("\\.")[1];
        Integer counter = strFraction.length();
        Integer number = Integer.parseInt(strFraction);

        while(counter > 0)
        {
            if (number % 5 != 0)
            {
                return false;
            }
            --counter;
            number /= 5;
        }

        return  true;
    }

    private static void computeFractionBinary(double fraction, List<Integer> fractionBinary)
    {
        while(fraction > 0)
        {
            fraction *= 2;
            fractionBinary.add((int) fraction);
            fraction -= (int) fraction;
        }
    }

    private static void computeWholeBinary(int whole, List<Integer> wholeBinary)
    {
        while(whole > 0)
        {
            wholeBinary.add(whole % 2);
            whole /= 2;
        }
    }


}