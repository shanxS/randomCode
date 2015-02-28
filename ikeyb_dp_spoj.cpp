#include <iostream>
#include <stdio.h>
#include <string.h>


//using namespace std;

#define P(x) printf("%d\n", x)
#define PS(x) printf("%s\n", x)
#define S(x) scanf("%d", &x)
#define SS(x) scanf("%s", &x)

const int INF = 0x3f3f3f3f;
const int INVALID = -1;
const int MAX = 100;

int K, L;
char KEYS[MAX];
char LETTERS[MAX];
int COST[MAX];
int DP[MAX][MAX];
int PATH[MAX][MAX];

int solve(int k, int l)
{
    if (l == L) return 0;
    if (k == K) return INF;
    if (DP[k][l] != INVALID) return DP[k][l];

    int &ret = DP[k][l];
    ret = INF;

    int sum = 0;
    for (int i=l; i<L; ++i)
    {
        sum += COST[i] * (i - l + 1 );
        int tmp = sum + solve(k+1, i+1);
        if (ret > tmp)
        {
            ret = tmp;
            PATH[k][l] = i;
        }
    }

    return ret;
}

/*
void printDp()
{
    cout << "\n DP \n";
    for (int i=0; i<10; ++i)
    {
        for (int j=0; j<10; ++j)
        {
            cout << DP[i][j] << " ";
        }

        cout << endl;
    }
}

void printPath()
{
    cout << "\n Path \n";
    for (int i=0; i<10; ++i)
    {
        for (int j=0; j<10; ++j)
        {
            cout << PATH[i][j] << " ";
        }

        cout << endl;
    }
}*/

int main()
{
    int kp;
    scanf("%d", &kp);

    for (int i=0; i<kp; ++i)
    {
        scanf("%d %d %s %s", &K, &L, KEYS, LETTERS);
        for (int i=0; i<L; ++i) scanf("%d", &COST[i]);

        memset(DP, INVALID, sizeof(DP));
        solve(0, 0);
        //printDp();
        //printPath();

        printf("Keypad #%d\n", kp);
        int letter = 0;
        for (int k=0; k<K; ++k)
        {
            printf("%c: ", KEYS[k]);
            for (int i=letter; i<=PATH[k][letter]; ++i) putchar(LETTERS[i]);
            letter = PATH[k][letter] + 1;
            puts("");
        }
        puts("");
    }

    return 0;
}
