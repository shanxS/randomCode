// recursive with path
// amazon set 71, 3rd round ques2: http://www.geeksforgeeks.org/amazon-interview-set-71-sde-2/

#include <iostream>
#include <vector>
#include <utility>
#include <cassert>
#include <limits>

using namespace std;

const int NUM_STATION = 4;
const int INVALID = numeric_limits<int>::max();
int a[][NUM_STATION] = {{4, 5, 3, 2},
                        {2, 10, 1, 4}};
int t[][NUM_STATION] = {{0, 7, 4, 5},
                        {0, 9, 2, 8}};
int e[] = {10, 12}, x[] = {18, 7};

int T1[NUM_STATION];
int T2[NUM_STATION];

int getOtherLine(int line)
{
    return (line == 0) ? 1 : 0;
}

// pair<line, cost>
pair<vector<int>, int> T(int line, int j)
{
    assert (j >= 0);

    if (j==0)
    {
        vector<int> v = {line};
        return make_pair( v, e[line] + a[line][j]);
    }
    
    auto currentRetVal = T(line, j-1);
    int currentLineCost =  currentRetVal.second + a[line][j];
    
    int otherLine = getOtherLine(line);
    auto otherRetVal = T(otherLine, j-1);
    int otherLineCost = otherRetVal.second + t[otherLine][j] + a[line][j];
    
    if (otherLineCost < currentLineCost)
    {
        otherRetVal.first.push_back(otherLine);
        return make_pair(otherRetVal.first, otherLineCost);
    }
    else
    {
        currentRetVal.first.push_back(line);
        return make_pair(currentRetVal.first, currentLineCost);
    }
}

pair<vector<int>, int> calculateCost(int n)
{
    auto retVal0 = T(0, n);
    int cost0 = retVal0.second + x[0];
    
    auto retVal1 = T(1, n);
    int cost1 = retVal1.second + x[1];
    
    if (cost1 < cost0)
    {
        retVal1.first.push_back(1);
        return make_pair(retVal1.first, cost1);
    }
    else
    {
        retVal0.first.push_back(0);
        return make_pair(retVal0.first, cost0);
    }
}

int main()
{   
    auto ans = calculateCost(3);
    cout << ans.second << endl;
    
    for (int i=0; i<4; ++i)
    {
        cout << "station " << i << " line " << ans.first.at(i) << endl;
    }
    
}


///////////////////////////////////////////////
// looping soln // http://www.geeksforgeeks.org/dynamic-programming-set-34-assembly-line-scheduling/
#include <iostream>
#include <cassert>
#include <limits>

using namespace std;

const int NUM_STATION = 4;
const int INVALID = numeric_limits<int>::max();
int a[][NUM_STATION] = {{4, 5, 3, 2},
                        {2, 10, 1, 4}};
int t[][NUM_STATION] = {{0, 7, 4, 5},
                        {0, 9, 2, 8}};
int e[] = {10, 12}, x[] = {18, 7};

int T1[NUM_STATION];
int T2[NUM_STATION];

int T()
{
    T1[0] = e[0] + a[0][0];
    T2[0] = e[1] + a[1][0];
    
    for (int i=1; i<NUM_STATION; ++i)
    {
        T1[i] = min(T1[i-1] + a[0][i], T2[i-1] + t[1][i] + a[0][i]);
        T2[i] = min(T2[i-1] + a[1][i], T1[i-1] + t[0][i] + a[1][i]);
    }
    
    return min(T1[NUM_STATION - 1] + x[0], T2[NUM_STATION - 1] + x[1]);
}

int main()
{
    cout << T();
}
