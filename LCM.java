// needs GCD and is recursive
private static long lcm(long a, long b)
{
    return a * (b / gcd(a, b));
}

// loop
private static long lcm(long[] input)
{
    long result = input[0];
    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
    return result;
}