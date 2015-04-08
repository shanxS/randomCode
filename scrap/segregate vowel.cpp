// Given a linked list containing character in each node, segregate its nodes in such a way that all nodes containing a vowel are moved to the end of the linked list. We will have to maintain the order.
// amazon set 2 geek for geeks
// http://www.geeksforgeeks.org/amazon-interview-set-2/

#include <iostream>
#include <memory>

using namespace std;

class VowelSegregator
{
    struct Node 
    {
        Node(char c)
        :   value(c),
            next()
        {}

        char value;
        shared_ptr<Node> next;
    };
    
    public:
        VowelSegregator()
        : m_head()
        {}
    
        void insertChar(char c)
        {
            if (!m_head)
            {
                m_head = shared_ptr<Node>(new Node(c));
                m_last = m_head;
            }
            else
            {
                shared_ptr<Node> node = m_head;
                while(node->next)
                {
                    node = node->next;
                }
                node->next = shared_ptr<Node> (new Node(c));
                m_last = node;
            }
            
        }
        
        void printList()
        {
            shared_ptr<Node> node = m_head;
            cout << endl;
            while (node->next)
            {
                cout << node->value << " ";
                node = node->next;
            }
            cout << node->value << " ";
        }
        
        void segregate()
        {
            shared_ptr<Node> origLast = m_last;
            
            shared_ptr<Node> prevNode;
            shared_ptr<Node> node = m_head;
            while (node != origLast)
            {
                shared_ptr<Node> nextNode = node->next;
                if (isVowel(node->value))
                {
                    sendToEnd(prevNode, node);
                }
                
                prevNode = node;
                node = nextNode;
            }
        }
    
    private:
        shared_ptr<Node> m_head;
        shared_ptr<Node> m_last;
        
        void sendToEnd(shared_ptr<Node> prevNode, shared_ptr<Node> node)
        {
            if (!node->next)
            {
                // this is already at last
                return;
            }
            
            if (prevNode)
            {
                prevNode->next = node->next;
            }
            
            node->next = m_last->next;
            m_last->next = node;
            m_last = node;
            
        }
        
        bool isVowel(char c)
        {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    
};



int main()
{
    VowelSegregator vs;
    vs.insertChar ('x');
    vs.insertChar ('a');
    vs.insertChar ('b');
    vs.insertChar ('i');
    vs.insertChar ('q');
    vs.insertChar ('e');
    
    vs.printList();

    vs.segregate();
    
    vs.printList();
}
