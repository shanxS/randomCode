// http://www.geeksforgeeks.org/amazon-interview-set-71-sde-2/
// 3rd round
// ques 1

#include<iostream>
#include<cassert>
#include<vector>
#include<limits>

using namespace std;

class Tree
{
    public:
        Tree()
        : m_tree(),
          m_nextInsertionPoint(0),
          m_invalid(numeric_limits<int>::max()),
          m_rightGreaterThanHead(true)
        {}
        
        void addElement(int element)
        {
            if (m_tree.size() == 0)
            {
                m_tree.push_back(element);
            }
            else
            {
                assert (m_tree.at(m_nextInsertionPoint) == m_invalid);
                m_tree.at(m_nextInsertionPoint) = element;
            }
            
            makeRoom(m_nextInsertionPoint);
            updateNextInsertionPoint();
        }

        bool isBST()
        {
            if (setRule())
            {
                return testRule();
            }
            else
            {
                return false;
            }
        }
        
        void print()
        {

            for (int i=0; i<m_tree.size(); ++i)
            {
                if (m_tree.at(i) != m_invalid)
                {
                    cout << m_tree.at(i) << " - " 
                         << m_tree.at(getLeftChildPosition(i)) << ", "
                         << m_tree.at(getRightChildPosition(i)) << endl;
                }
            }
        }
    
    private:
        vector<int> m_tree;
        int m_nextInsertionPoint;
        int m_invalid;
        bool m_rightGreaterThanHead;
        
        bool testRule()
        {
            int position = 1;
            bool validBST = true;
            while(position < m_tree.size())
            {
                int parent = m_tree.at(position);
                if (parent != m_invalid)
                {   
                    int rightChild = m_tree.at(getRightChildPosition(position));
                    int leftChild = m_tree.at(getLeftChildPosition(position));
                    if (rightChild != m_invalid && leftChild != m_invalid)
                    {
                        if (m_rightGreaterThanHead)
                        {
                            if (rightChild < parent)
                            {
                                validBST = false;
                                break;
                            }
                            if (leftChild > parent)
                            {
                                validBST = false;
                                break;
                            }
                        }
                        else
                        {
                            if (rightChild > parent)
                            {
                                validBST = false;
                                break;
                            }
                            if (leftChild < parent)
                            {
                                validBST = false;
                                break;
                            }
                        }
                    }
                }
                
                ++position;
            }
            
            return validBST;
        }
        
        bool setRule()
        {
            int head = 0;
            assert(m_tree.size() >= 3);
            
            int parent = m_tree.at(head);
            assert(parent != m_invalid);
            
            int rightChild = m_tree.at(getRightChildPosition(head));
            assert(rightChild != m_invalid);
            
            int leftChild = m_tree.at(getLeftChildPosition(head));
            assert(leftChild != m_invalid);
            
            if (rightChild > parent
                && leftChild < parent)
            {
                m_rightGreaterThanHead = true;
            }
            else if (rightChild < parent
                && leftChild > parent)
            {
                m_rightGreaterThanHead = false;
            }
            else
            {
                return false;
            }
            
            return true;
        }
        
        void updateNextInsertionPoint()
        {
            while(m_tree.at(m_nextInsertionPoint) != m_invalid)
            {
                ++m_nextInsertionPoint;
            }
        }
        
        void makeRoom(int currentInsertionAt)
        {
            while(m_tree.size() <= getRightChildPosition(currentInsertionAt))
            {
                m_tree.push_back(m_invalid);
            }
        }
        
        int getRightChildPosition(int parent)
        { return (2*parent + 2); }
        
        int getLeftChildPosition(int parent)
        { return (2*parent + 1); }
};

int main()
{
    Tree tree;
    /*
    // is BST
    tree.addElement(6);
    tree.addElement(3);
    tree.addElement(10);
    tree.addElement(2);
    tree.addElement(4);*/
    
    /*
    // not BST
    tree.addElement(1);
    tree.addElement(2);
    tree.addElement(3);
    tree.addElement(4);
    tree.addElement(5);
    tree.addElement(6);
    */
    
    
    // cannot set rule
    tree.addElement(1);
    tree.addElement(4);
    tree.addElement(5);
    tree.addElement(3);
    tree.addElement(2);
    
    tree.print();
    if (tree.isBST())
    {
        cout << "is BST" << endl;
    }
    else
    {
        cout << "not BST" << endl;
    }
}
