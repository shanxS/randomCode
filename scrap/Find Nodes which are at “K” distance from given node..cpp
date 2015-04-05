// http://www.geeksforgeeks.org/amazon-interview-set-71-sde-2/
// round 1
// question 2

#include<iostream>
#include<cassert>
#include<memory>
#include<list>
#include<map>

using namespace std;

class BinaryTree
{
    struct Node
    {
        Node(int val)
        : value(val),
          leftChild(),
          rightChild()
        {}
        
        int value;
        shared_ptr<Node> leftChild;
        shared_ptr<Node> rightChild;
    };
    using NodePtr = shared_ptr<Node>;
    
    public:
        BinaryTree()
        : m_head()
        {}
        
        void addElement(int element)
        {
            insert(m_head, element);
        }
        
        void printTree()
        {
            print(m_head);
        }
        
        void printChildrenAtDepth (int startFrom, int depth)
        {
            NodePtr nodePtr = findNode(m_head, startFrom);
            assert(nodePtr);
            printDepth(nodePtr, depth);
        }
    
    private:
        NodePtr m_head;
        
        NodePtr findNode(NodePtr nodePtr, int value)
        {
            if (!nodePtr)
            {
                return nodePtr;
            }
            
            if (value == nodePtr->value)
            {
                return nodePtr;
            }
            
            if (nodePtr->value > value)
            {
                return findNode(nodePtr->rightChild, value);
            }
            else
            {
                return findNode(nodePtr->leftChild, value);
            }
        }
        
        void printDepth(NodePtr nodePtr, int depth)
        {
            if (!nodePtr)
            {
                return;
            }
            
            if (depth == 0)
            {
                cout << nodePtr->value << endl;
                return;
            }
            
            printDepth(nodePtr->rightChild, depth-1);
            printDepth(nodePtr->leftChild, depth-1);
            
        }
        
        void print(NodePtr nodePtr)
        {
            if (!nodePtr)
            {
                return;
            }
            
            cout << nodePtr->value << " - ";
            if (nodePtr->leftChild)
            {
                cout << nodePtr->leftChild->value;
            }
            cout << ", ";
            if (nodePtr->rightChild)
            {
                cout << nodePtr->rightChild->value;
            }
            cout << endl;
            
            print(nodePtr->rightChild);
            print(nodePtr->leftChild);
        }
        
        void insert(NodePtr &nodePtr, int element)
        {
            if (!nodePtr)
            {
                nodePtr = shared_ptr<Node> (new Node(element));
                return;
            }
        
            if (nodePtr->value > element)
            {
                insert(nodePtr->rightChild, element);
            }
            else
            {
                insert(nodePtr->leftChild, element);
            }
        }
        
};

int main()
{
    BinaryTree bt;
    bt.addElement(6);
    bt.addElement(3);
    bt.addElement(9);
    bt.addElement(4);
    bt.addElement(13);
    bt.addElement(7);
    bt.addElement(15);
    bt.addElement(12);
    bt.addElement(8);
    
    cout << "printing tree " << endl;
    bt.printTree();
    
    cout << "nodes at depth 2 from 6" << endl;
    bt.printChildrenAtDepth(6, 2);
    
    cout << "nodes at depth 2 from 9" << endl;
    bt.printChildrenAtDepth(9, 2);
    
    cout << "nodes at depth 1 from 13" << endl;
    bt.printChildrenAtDepth(13, 1);
}