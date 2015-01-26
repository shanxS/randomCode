#include <bits/stdc++.h>
#include <math.h>

using namespace std ;

#define P(x) printf("%d\n", x)
#define PN() printf("\n")
#define S1(x) scanf("%d", &x)
#define S2(x,y) scanf("%d%d", &x, &y)

#define MAXN 500000

int T;
int N;
int DIVSUM;
int DIV[MAXN];

void solve()
{
    for (int i = 1; i <= MAXN; i++)
    {
        for (int j = i+i; j <= MAXN; j += i)
        {
            DIV[j] += i;
        }
    }

}

int main()
{
    solve();

    S1(T);
    while(T--)
    {
        S1(N);
        if (N == 1) P(0);
        else        P(DIV[N]);
    }


    return 0;
}
