// binary tree using vector
// https://en.wikipedia.org/wiki/Binary_tree#Arrays
// assuming the root has index zero - if a node has an index i, its children are found at indices 2i + 1 (for the left child) 
// and 2i +2 (for the right), while 
// its parent (if any) is found at index floor ((i-1)/2)

#include <iostream>
#include <limits>
#include <vector>

using namespace std;

class BST
{
    public:
        BST()
        : invalidNum(numeric_limits<int>::max()),
          tree()
        {}
       
        void insert(const int value)
        {
            insertIntoTree(0, value);
        }
    
        void printTree()
        {
            for (int i=0; i<tree.size(); ++i)
            {
                if (getValue(i) != invalidNum)
                {
                    cout << getValue(i) << " - " << getValue(getLeftChildPosition(i)) 
                         << " " << getValue(getRightChildPosition(i)) << endl;
                }
            }
        }
    
    private:
        void insertIntoTree(const int parent, const int value)
        {
            if (parent == 0 && tree.size() == 0)
            {
                addValue(parent, value);
            }
            else if (getValue(parent) == invalidNum)
            {
                getValue(parent) = value;
            }
            else if (getValue(parent) > value)
            {
                insertIntoTree(getRightChildPosition(parent), value);
            }
            else if (getValue(parent) < value)
            {
                insertIntoTree(getLeftChildPosition(parent), value);
            }
        }
    
        void makeRoomFor(int position)
        {
            int count = position - tree.size() + 1;
            for (int i=0; i<=count; ++i)
            {
                tree.push_back(invalidNum);
            }
        }
    
        int getLeftChildPosition(const int parent)
        {
            return ((2*parent) + 1);
        }
        
        int getRightChildPosition(const int parent)
        {
            return ((2*parent) + 2);
        }
    
        int& getValue(const int position)
        {
            if (position >= tree.size())
            {
                makeRoomFor(position);
            }
            
            return tree.at(position);
        }
    
        void addValue(const int position, const int value)
        {
            if (position < tree.size())
            {
                tree.push_back(value);
            }
            else if (position >= tree.size())
            {
                makeRoomFor(position);
                tree.at(position) = value;
            }
            else // position < tree.size()
            {
                tree.at(position) = value;
            }
        }
    
        const int invalidNum;
        vector<int> tree;
};

int main()
{
    BST myBinary;
    
    myBinary.insert(1);
    myBinary.insert(3);
    myBinary.insert(6);
    myBinary.insert(2);
    myBinary.insert(0);
    
    myBinary.printTree();
}
