// http://www.geeksforgeeks.org/print-nodes-distance-k-leaf-node/
// O(n)

#include <iostream>
#include <limits>
#include <memory>
#include <vector>
#include <queue>

using namespace std;

class BinaryTree
{
    protected:
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
        
        const int m_invalidNum;
        NodePtr m_head;

    public:
        BinaryTree()
        : m_invalidNum(numeric_limits<int>::max()),
          m_head(new Node(m_invalidNum))
        {}
        
        virtual ~BinaryTree() = default;
        
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

};

class GettingNthNodeFromLeaf : public BinaryTree
{
    public:
        void go(int distance)
        {
            queue<NodePtr> que;
            que.push(m_head);
            goPrint(m_head, que, distance);
        }
        
    private:
        void goPrint(NodePtr &node, queue<NodePtr> que, const int distance)
        {
            if (distance == que.size() && (isLeafNode(node)))
            {       
                cout << que.front()->value << endl;
            }
            
            if (distance == que.size())
            {
                que.pop();
            }
            que.push(node);
            
            if (node->rightChildPtr)
            {
                goPrint (node->rightChildPtr, que, distance);
            }
            if (node->leftChildPtr)
            {
                goPrint (node->leftChildPtr, que, distance);
            }
        }
        
        bool isLeafNode(NodePtr node)
        {
            if (!node->leftChildPtr && !node->rightChildPtr)
            {
                return true;
            }
            
            return false;
        }
      
};

int main()
{
    GettingNthNodeFromLeaf bt;
    bt.insert(10);
    bt.insert(5);
    bt.insert(15);
    bt.insert(7);
    bt.insert(12);
    bt.insert(17);
    bt.insert(11);
    bt.insert(13);
    
    bt.printTree();
    
    cout << "========" << endl;
    
    bt.go (2);
    
    

}
