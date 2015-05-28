// convert double to string
// watch out for fractions which cant be converted to binary
// r3, q7, set30

public class Main
{
    public static void main(String[] er)
    {
        double number = 10.625;
        int whole = (int) number;
        double fraction = number - whole;

        String str = getStringFromWhole(whole);
        str += "." + getStringFromFraction(fraction);

        System.out.print(str);
    }

    private static String getStringFromFraction(double fraction)
    {
        String result = "";
        while(fraction - (int)fraction != 0.0)
        {
            fraction *= 10;

            int value = (int)(fraction);
            result += value;

            fraction = fraction - value;
        }

        return result;
    }

    private static String getStringFromWhole(int whole)
    {
        String result = "";

        while(whole > 0)
        {
            result = whole%10 + result;
            whole /= 10;
        }

        return result;
    }
}