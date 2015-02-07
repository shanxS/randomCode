#include <bits/stdc++.h>
#include <iostream>
#include <math.h>

using namespace std;

#define P(x) printf("%d\n", x)
#define PS(x) printf("%s\n", x)
#define S(x) scanf("%d", &x)
#define SS(x) scanf("%s", &x)
#define CI(x) (x - '0')
#define BI(x) (x + '0')
#define ODD(x) (x%2 != 0)
#define copyRToL() (copy(true))
#define copyLToR() (copy(false))

#define EQUALBIG 0
#define RIGHTBIG 1
#define LEFTBIG  2

#define MAXN 1000000
char NUM[MAXN];
int LEN;

void increment (int index)
{
    assert (index >= 0);

    if (CI(NUM[index]) == 9)
    {
        NUM[index] = BI(0);

        if (index == 0)
        {
            memmove(NUM+1, NUM, LEN);

            NUM[0] = BI(0);

            LEN++;
            index++;
        }

        increment (index - 1);
    }
    else
    {
        NUM[index]++;
    }
}

bool isPalin()
{
    bool bPalin = true;

    for (int i=0, j=LEN-1; i<LEN/2; ++i, --j)
    {
        if (NUM[i] != NUM[j])
        {
            bPalin = false;
            break;
        }
    }

    return bPalin;
}

void setRangeZero(int ss, int se)
{
    while(ss <= se)
    {
        NUM[ss] = '0';
    }
}

int biggerBunch()
{
    int i=0;
    int j;
    if (ODD(LEN)) j = (LEN/2) + 1;
    else          j = LEN/2;

    int limit = LEN/2;
    for (; i<limit; ++i, ++j)
    {
        if (NUM[i] > NUM[j])        return LEFTBIG;
        else if (NUM[i] < NUM[j])   return RIGHTBIG;
    }

    return EQUALBIG;
}

void copy(bool RToL)
{
    int i=0;
    int j;
    if (ODD(LEN))
    {
        j = (LEN/2) + 1;
        i = j - 2;
    }
    else
    {
        j = LEN/2;
        i = j - 1;
    }

    for (; i>=0; --i, ++j)
    {
        if (RToL) NUM[i] = NUM[j];
        else      NUM[j] = NUM[i];
    }
}

bool imageLGreaterThanR()
{
    int i=0;
    int j;
    if (ODD(LEN))
    {
        j = (LEN/2) + 1;
        i = j - 2;
    }
    else
    {
        j = LEN/2;
        i = j - 1;
    }

    for (; i<LEN/2; --i, ++j)
    {
        if (NUM[i] > NUM[j])        return true;
        else if (NUM[i] < NUM[j])   return false;
    }
}

void solve()
{
    if (ODD(LEN))
    {
        if (!isPalin())
        {
            if(!imageLGreaterThanR()) increment(LEN/2);
            copyLToR();
        }
        else
        {
            increment(LEN/2);
            if (CI(NUM[LEN/2]) == 0) solve();
        }

    }
    else
    {
        if (!isPalin())
        {
            if(imageLGreaterThanR()) copyLToR();
            else
            {
                increment((LEN/2) - 1);
                copyLToR();
            }
        }
        else
        {
            if (NUM[LEN/2] == '9')
            {
                increment(LEN/2);
                solve();
            }
            else
            {
                increment(LEN/2);
                increment((LEN/2) - 1);
            }
        }

    }
}


int main()
{
    int t;
    S(t);

    while(t > 0)
    {
        SS(NUM);
        LEN = strlen(NUM);
        solve();
        PS(NUM);

        memset(NUM, 0, LEN);
        t--;
    }

    return 0;
}

/*
int main()
{
    for(int i = 0;  i <= 30000; i++)
    {
        itoa (i,NUM,10);

        cout << "For " << NUM;
        LEN = strlen(NUM);
        solve();
        cout << " " << NUM << endl;

        assert(isPalin() == true);
        assert(atoi(NUM) > i);

        memset(NUM, 0, MAXN);
        LEN = 0;
    }

    return 0;
}*/

/*
int main()
{

    itoa(1002, NUM, 10);
    LEN = strlen(NUM);
    //cout << biggerBunch();
    solve();
    PS(NUM);

    return 0;
}*/
