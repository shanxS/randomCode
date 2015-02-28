// idea is to mimic the internals of printf
// this is a very very watered down version of what printf does
// this one only evaluates %s and %d args
//
// NOTE: in real world instead of using cout to print in stdout, FILE* is used
//
#include <stdarg.h>
#include <string.h>
#include <iostream>

using namespace std;

int countDigit(int num)
{
    int count = 0;
    while (num != 0)
    {
        count++;
        num /= 10;
    }

    return count;
}

int evaluateAndPrint(const char *format, int pos, va_list *arg)
{
    int count = 0;

    switch(*(format + pos + 1))
    {
    case 'd':
    {
        int num = va_arg(*arg, int);
        cout << num;
        return countDigit(num);
    }

    case 's':
    {
        const char *str = va_arg(*arg, char*);
        cout << str;
        return strlen(str);
    }
    }

    cout << "\n evaluateAndPrint cant find " << *(format + pos + 1) << endl;
}

int vppf(const char* format, va_list arg)
{
    int len = strlen(format);
    int count = 0;
    for (int i=0; i<len; ++i)
    {
        if (*(format + i) != '%')
        {
            cout << *(format + i);
            ++count;
        }
        else
        {
            count += evaluateAndPrint(format, i, &arg);

            // skip char after %
            ++i;
        }
    }

    return count;
}

int ppf(const char* format, ...)
{
    va_list arg;
    va_start(arg, format);
    int count = vppf(format, arg);
    va_end(arg);

    return count;
}

int main()
{
    int count = ppf("thsi is a %s %d %s", "test", 10, "teset2");
    cout << "\n" << count;
    return 0;
}

