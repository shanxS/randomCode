// http://www.geeksforgeeks.org/print-nodes-distance-k-leaf-node/

#include <iostream>
#include <limits>
#include <memory>
#include <vector>

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
        void go(const int distance)
        {
            goPrint(m_head, distance);
        }
        
    private:
        void goPrint(NodePtr node, const int distance)
        {
            vector<NodePtr> children = getNodesAtDepth(node, distance);
            
            for (const auto &value : children)
            {
                if (isLeafNode(value))
                {
                    cout << node->value << endl;
                    break;
                }
            }
            
            if (node->rightChildPtr)
            {
                goPrint (node->rightChildPtr, distance);
            }
            if (node->leftChildPtr)
            {
                goPrint (node->leftChildPtr, distance);
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
        
        vector<NodePtr> getNodesAtDepth(NodePtr node, int depth)
        {
            vector<NodePtr> nodes;
            --depth;
            
            if (depth == 0)
            {
                nodes.push_back(node);
            }
            else
            {
                vector<NodePtr> rightNodes, leftNodes;
                
                if (node->rightChildPtr)
                {
                    rightNodes = getNodesAtDepth (node->rightChildPtr, depth);
                }
                if (node->leftChildPtr)
                {
                    leftNodes = getNodesAtDepth (node->leftChildPtr, depth);
                }
                
                nodes.insert(nodes.end(), rightNodes.begin(), rightNodes.end());
                nodes.insert(nodes.end(), leftNodes.begin(), leftNodes.end());
            }
            
            return nodes;
        }
    
};

int main()
{
    GettingNthNodeFromLeaf bt;
    bt.insert(1);
    bt.insert(3);
    bt.insert(6);
    bt.insert(2);
    bt.insert(0);
    
    bt.printTree();
    
    bt.go (2);
    
    

}
