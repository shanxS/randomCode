// binary tree is stored as map<int, pair<int, int>> (BST)
// and child and correspoding parent is storedin map<int, int> (childParent)
// find number of nodes: BST.size(), constant time complexity
// find leaves: iterate BST and all keys who's right and left values are invlidNum are leaf, O(N)
// find innner nodes: BST.size() - leaves, O(N)
// find nodes with no sibling: iterate BST, O(N)
// find common parent of 2 nodes: use childParent, O(N)
// find depth of node: use childParent, O(N)



#include <iostream>
#include <utility>
#include <map>
#include <limits>

using namespace std;

using BST = map<int, pair<int, int>>;
const int invalidNum = numeric_limits<int>::max();

int getValue(BST::iterator it) {return it->first;}
int& getSecondChild(BST::iterator it) {return it->second.second;}
int& getFirstChild(BST::iterator it) {return it->second.first;}

void printTree(BST tree)
{
    for (const auto &value : tree)
    {
        cout << value.first << " " 
             << value.second.first << " "
             << value.second.second << endl;
    }
}

void insertIntoTree(BST::iterator it, BST &tree, const int &value)
{
    if (it == tree.end() && it == tree.begin())
    {
        tree.insert (make_pair(value, make_pair(invalidNum, invalidNum)));
    }
    else if (getValue(it) < value)
    {
        if (getSecondChild(it) == invalidNum)
        {
            getSecondChild(it) = value;
            tree.insert (make_pair(value, make_pair(invalidNum, invalidNum)));
        }
        else
        {
            auto rightIt = tree.find(getSecondChild(it));
            insertIntoTree(rightIt, tree, value);
        }
    }
    else if (getValue(it) > value)
    {
        if (getFirstChild(it) == invalidNum)
        {
            getFirstChild(it) = value;
            tree.insert (make_pair(value, make_pair(invalidNum, invalidNum)));
        }
        else
        {
            auto leftIt = tree.find(getFirstChild(it));
            insertIntoTree(leftIt, tree, value);
        }
    }
}

void printChildParent(const map<int, int> &childParent)
{
    for (const auto &entry : childParent)
    {
        cout << entry.first << " " << entry.second << endl;
    }
}

map<int, int> setupChildParent(const BST &tree)
{
    map<int, int> childParent;
    
    for(const auto &entry : tree)
    {
        if (entry.second.first != invalidNum)
        {
            childParent.insert(make_pair(entry.second.first, entry.first));
        }
        
        if (entry.second.second != invalidNum)
        {
            childParent.insert(make_pair(entry.second.second, entry.first));
        }
    }
    
    return childParent;
}

int main()
{
    BST tree;
    insertIntoTree(tree.begin(), tree, 1);
    insertIntoTree(tree.begin(), tree, 3);
    insertIntoTree(tree.begin(), tree, 6);
    insertIntoTree(tree.begin(), tree, 2);
    insertIntoTree(tree.begin(), tree, 0);
    
    map<int, int> childParent = setupChildParent(tree);
    
    printTree(tree);
    
    printChildParent(childParent);
}
