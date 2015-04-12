// http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
// time complexit: nlogn
// space: n

#include <iostream>
#include <string>
#include <cassert>
#include <vector>

namespace algorithm
{

using namespace std;

vector<int> array = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };//{0, 8, 4, 12, 2, 10, 6, 14, -1, 9, 5, 13, 3, 11, 7, 15};
vector<int> lenArray;
int len = 0;

void setupLenArray()
{
    for (int i=0; i<array.size(); ++i)
    {
        lenArray.push_back(0);
    }
}

int findReplacementIndex(int value)
{
    for (int i=len-1; i>=0; --i)
    {
        if (lenArray.at(i) < value)
        {
            return i+1;
        }
    }
    
    throw string("algorithm::findReplacementIndex cannot find replacement index");
    return -1;
}

int maxLen()
{
    setupLenArray();
    
    for (auto value : array)
    {
        if (len == 0)
        {
            lenArray.at(len) = value;
            ++len;
        }
        else if (lenArray.at(len-1) < value)
        {
            lenArray.at(len) = value;
            ++len;
        }
        else
        {
            int replacementIndex = findReplacementIndex(value);
            lenArray.at(replacementIndex) = value;
        }
    }
    
    return len;
}

}

int main ()
{
    std::cout << algorithm::maxLen() << std::endl;
}
