#include <cstdio>
#include <cstring>
#include <iostream>
#include <set>
#include <string>

using namespace std;

#define MAX 1000
#define INVALID -1
#define ISVALIDCELL(r, c) ( (r>=0 && r<MAX && c>=0 && c<MAX) ? true : false )

char STR1[MAX];
char STR2[MAX];
set<int> INDEXHOLDER;
string SEQ = "";

int DP[MAX][MAX];

int solve(int r, int c)
{
    if (!ISVALIDCELL(r, c)) return 0;

    if (DP[r][c] != INVALID) return DP[r][c];

    if (STR1[r] == STR2[c])
    {
        if (INDEXHOLDER.find(r) == INDEXHOLDER.end())
        {
            SEQ += STR1[r];
            INDEXHOLDER.insert(r);
        }
        return (1 + solve(r-1, c-1));
    }
    else
    {
        return max(solve(r-1, c), solve(r, c-1));
    }
}

int main()
{
    while(1)
    {
        scanf("%s%s", STR1, STR2);
        memset (DP, INVALID, sizeof(DP));

        solve(strlen(STR1) - 1, strlen(STR2) - 1);
        cout << "seq is: " << SEQ << endl;
        SEQ.erase();
    }

    return 0;
}
