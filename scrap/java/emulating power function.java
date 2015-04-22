// emulation Math.pow

public class Main {

    public static void main(String[] args)
    {
        /*
        for fractional power:
        exp(ln(a)) = a
        ln(a^b) = b*ln(a)
        ln(1+x) = x - (x^2)/2 + (x^3)/3 - ... + ...
        exp(x) = 1 + x + (x^2)/2 + ...

        Note that in this code the ln (x) is not working properly
        so I ahve used ln from the lib

        source: http://stackoverflow.com/a/3606851
         */

        System.out.print(power(4, -2.5));
    }

    private static double ln(double value)
    {
        return Math.log(value);

        // the following series is some how screwing up
        /*
        double result = 0;
        value -= 1;
        for (Integer i=1; i<2000; ++i)
        {
            double temp = power(value, i) / i;
            temp *= (i%2 == 0) ? -1 : 1;
            result += temp;
        }

        System.out.println("ln " + value + " " + result);

        return result;*/
    }

    private static double ePower(double value) {
        double result = 1;

        for (int i = 1; i < 4; ++i)
        {
            result += power(value, i)/i;
        }

        return result;
    }

    private static double power(double base, double exp)
    {
        Integer integralExp = (int)exp;
        double fractionalExp = exp - integralExp;

        double integralResult = power(base, integralExp);

        double fractionalResult = 1;
        if (Math.abs(fractionalExp) < 1)
        {
            fractionalResult = ePower(fractionalExp * ln(base));
        }


        return fractionalResult * integralResult;
    }

    private static double power(double base, int exp)
    {
        if (exp == 0.0)
        {
            return 1;
        }




        if (exp < 0)
        {
            base = 1/base;
            exp *= -1.0;
        }

        if (exp%2 == 0)
        {
            return power(base, exp/2) * power(base, exp/2);
        }
        else
        {
            return base * power(base, exp/2) * power(base, exp/2);
        }
    }
}
