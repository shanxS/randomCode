// binary tree using nodes and pointers

#include <iostream>
#include <limits>
#include <memory>

using namespace std;

class BinaryTree
{
    struct Node
    {
        Node (int value)
        : value(value),
          leftChildPtr(),
          rightChildPtr()
        {}

        int value;
        shared_ptr<Node> leftChildPtr;
        shared_ptr<Node> rightChildPtr;
    };
    using NodePtr = shared_ptr<Node>;

    public:
        BinaryTree()
        : m_invalidNum(numeric_limits<int>::max()),
          m_head(new Node(m_invalidNum))
        {}
        
        void insert(const int value)
        {
            if (m_head->value == m_invalidNum)
            {
                m_head->value = value;
            }
            else
            {
                insertIntoTree(m_head, value);
            }
        }
        
        void printTree()
        {
            print(m_head);
        }
        
    private:
        void print(NodePtr node)
        {
            if (node)
            {
                cout << node->value << " ";
                if (node->leftChildPtr)
                {
                    cout << node->leftChildPtr->value << " ";
                }
                if (node->rightChildPtr)
                {
                    cout << node->rightChildPtr->value << " ";
                }
                
                cout << endl;
                
                print(node->leftChildPtr);
                print(node->rightChildPtr);
            }
        }
    
        void insertIntoTree(NodePtr &node, const int value)
        {
            if (!node)
            {
                node = shared_ptr<Node>(new Node(value));
                return;
            }
            
            if (node->value > value)
            {
                insertIntoTree(node->rightChildPtr, value);
            }
            else if (node->value < value)
            {
                insertIntoTree(node->leftChildPtr, value);
            }
        }
    
        const int m_invalidNum;
        NodePtr m_head;
    

};

int main()
{
    BinaryTree bt;
    bt.insert(1);
    bt.insert(3);
    bt.insert(6);
    bt.insert(2);
    bt.insert(0);
    
    bt.printTree();

}
