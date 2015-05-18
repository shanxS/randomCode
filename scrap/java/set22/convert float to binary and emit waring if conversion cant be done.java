// convert float to binary and emit waring if conversion cant be done
// online, q1

// sources:
// http://stackoverflow.com/a/1089026
// http://www.exploringbinary.com/binary-converter/
// http://cs.furman.edu/digitaldomain/more/ch6/dec_frac_to_bin.htm
// http://stackoverflow.com/a/2755949

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] wer)
    {

        //String strNumber = "3.036";
        //String strNumber =  "3.03125";
        String strNumber =  "3.1";
        if (!fractionBinaryPossible(strNumber))
        {
            System.out.print("cant convert to binary");
            return;
        }

        double number = Double.parseDouble(strNumber);
        int whole = (int) number;

        double fraction = number - Math.floor(number);
        List<Integer> fractionBinary = new ArrayList<>();
        convertFractionToBinary(fraction, fractionBinary);

        List<Integer> wholeBinary = new ArrayList<>();
        convertWholeToBinary(whole, wholeBinary);

        wholeBinary.stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        fractionBinary.stream().forEach(x -> System.out.print(x + " "));
    }

    private static boolean fractionBinaryPossible(String strnumber) {
        String fraction = strnumber.split("\\.")[1];
        Integer integer = Integer.parseInt(fraction);

        for (Integer i=0; i<fraction.length(); ++i)
        {
            if (integer % 5 != 0)
            {
                return false;
            }
            integer /= 5;
        }

        return true;
    }

    private static void convertWholeToBinary(int whole, List<Integer> wholeBinary) {
        while(whole > 0)
        {
            wholeBinary.add(whole%2);
            whole /= 2;
        }
    }

    private static void convertFractionToBinary(double fraction, List<Integer> fractionBinary) {
        while (fraction > 0f)
        {
            fraction *= 2;

            fractionBinary.add((int) fraction);

            fraction = fraction - (int) fraction;
        }
    }
}