#include <iostream>
#include <limits>
#include <cstring>

using namespace std;

#define K 3
#define N 4
#define NF -1

int M[N+1][K+1];
int S[N+1];
int I[K];

void printM()
{
    for (int i=0; i<=N; ++i)
    {
        for (int j=0; j<=K; ++j)
        {
            cout << M[i][j] << " ";
        }
        cout << endl;
    }

}

void printI()
{
    for(int i=1; i<=K; ++i)
        cout << I[i] << " ";

    cout << endl;
}


int sum(int i, int j)
{
    int val = 0;
    for (int k=i; k<=j; ++k)
        val += S[k];

    return val;

}

int calculateCost(int n, int k)
{
    cout << n << " " << k << endl;

    if (M[n][k] != NF)
    {
        return M[n][k];
    }

    if (k == 1)
    {
        M[n][k] = sum(1, n);
        return M[n][k];
    }

    if (n == 1)
    {
        M[n][k] = S[n];
        return M[n][k];
    }

    int minVal = numeric_limits<int>::max();
    int minI = -1;
    for (int i=1; i<=n; ++i)
    {
        int prevVal = calculateCost(i, k-1);
        int thisVal = sum(i+1, n);

        int maxVal = max(prevVal, thisVal);

        if (minVal > maxVal)
        {
            minVal = maxVal;
            minI = i;
        }
    }

    M[n][k] = minVal;
    I[k] = minI;

    return minVal;
}

int calculateCostLoop()
{
    // init M
    for(int n=1; n<=N; ++n) M[n][1] = sum(1,n);
    for(int k=1; k<=K; ++k) M[1][k] = S[1];

    // calculate
    for(int k=2; k<=K; ++k)
    {
        for(int n=2; n<=N; ++n)
        {
            int minVal = numeric_limits<int>::max();
            int minI = -1;
            for (int i=1; i<=n; ++i)
            {
                // get all M[1..n][k]
                int prevVal = M[i][k-1];
                
                // get all sum(i+1, n)
                int curVal = sum(i+1, n);
                
                // get max for each of above pairs
                int maxVal = max(prevVal, curVal);
                
                // get min of all these max
                if (minVal > maxVal)
                {
                    minVal = maxVal;
                    minI = i;
                }
            }
            
            // set M[n][k]
            M[n][k] = minVal;
            I[k] = minI;
        }
    }

    return M[N][K];
}

void partition(int s[], int n, int k)
{
    int MAXN = N;
    int MAXK = K;
    int m[MAXN+1][MAXK+1]; /* DP table for values */
    int d[MAXN+1][MAXK+1]; /* DP table for dividers */
    int p[MAXN+1]; /* prefix sums array */
    int cost; /* test split cost */
    int i,j,x; /* counters */
    int MAXINT = numeric_limits<int>::max();
    
    memset(m, -1, sizeof(m));

    p[0] = 0; /* construct prefix sums */
    for (i=1; i<=n; i++) p[i]=p[i-1]+s[i];
    for (i=1; i<=n; i++) m[i][1] = p[i]; /* initialize boundaries */
    for (j=1; j<=k; j++) m[1][j] = s[1];

    for (i=2; i<=n; i++) /* evaluate main recurrence */
        for (j=2; j<=k; j++) {
            m[i][j] = MAXINT;
            for (x=1; x<=(i-1); x++) {
                cost = max(m[x][j-1], p[i]-p[x]);
                if (m[i][j] > cost) {
                    m[i][j] = cost;
                    d[i][j] = x;
                }
            }
    }
    
    cout << "\nskiena cost " << m[MAXN][MAXK] << endl << "m\n";
    for (int i=0; i<=MAXN; ++i)
    {
        for (int j=0; j<=MAXK; ++j)
        {
            cout << m[i][j] << " ";
        }
        cout << endl;
    }
}

int main()
{
    S[1] = 100;
    S[2] = 100;
    S[3] = 1000;
    S[4] = 1000;

    memset(M, -1, sizeof(M));
    cout << "recursion.." << endl;
    cout << "cost " << calculateCost(N, K) << endl;\
    cout << "I" << endl;
    printI();
    cout << "M" << endl;
    printM();

    cout << endl;

    memset(M, -1, sizeof(M));
    cout << "loop.." << endl;
    cout << "cost " << calculateCostLoop() << endl;
    cout << "I" << endl;
    printI();
    cout << "M" << endl;
    printM();
    
    partition(S, N, K);

    string s;
    cin >> s;

    return 0;
}
