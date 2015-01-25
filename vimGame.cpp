#include <bits/stdc++.h>
using namespace std;
#define MOD                     1000000007
#define pb(x)                   push_back(x)
#define mp(x,y)                 make_pair(x,y)
#define FF                      first
#define SS                      second
#define s(n)                    scanf("%d",&n)
#define sl(n)                   scanf("%lld",&n)
#define sf(n)                   scanf("%lf",&n)
#define ss(n)                   scanf("%s",n)
#define sc(n)                   {char temp[4]; ss(temp); n=temp[0];}
#define INF                     (int)1e9
#define LINF                    (long long)1e18
#define EPS                     1e-9
#define maX(a,b)                ((a)>(b)?(a):(b))
#define miN(a,b)                ((a)<(b)?(a):(b))
#define abS(x)                  ((x)<0?-(x):(x))
typedef long long ll;
typedef unsigned long long LL;
typedef pair<int,int> PII;
typedef pair<LL,LL> PLL;
typedef pair<int,PII> TRI;
typedef vector<int> VI;
typedef vector<LL> VL;
typedef vector<ll> vl;
typedef vector<PII> VII;
typedef vector<TRI> VT;
int n;
int TEST_NO;
int n1, n2;
const int MAXN = 1000006;
int loser[MAXN];
void precompute() {
	int d = 0;
	for (int i = 0; i < MAXN; ++i) {
		if(loser[i]) continue;		
		loser[i] = i + d;
                if(i + d < MAXN) loser[i + d] = i;
		d++;
	}
}
void read() {
	s(n1), s(n2);
}
void preprocess() {
	if(n1 > n2) swap(n1, n2);
}
void solve() {
	if(n2 == loser[n1]) puts("Don't Play");
	else puts("Play");
}
int main() {
	precompute();
	int t;
	s(t);
	for(TEST_NO = 1; TEST_NO <= t; TEST_NO ++) {
		read();
		preprocess();
		solve();
	}
	return 0;
}