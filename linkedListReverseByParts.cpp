#include <iostream>
#include <limits>
#include <memory>
#include <vector>
#include <queue>

using namespace std;

class LinkedList
{
    struct Node
    {
        Node(int intVal)
        : value(intVal),
          next()
        {}

        int value;
        shared_ptr<Node> next;
    };
    using NodePtr = shared_ptr<Node>;

    public:
        LinkedList()
        : m_head()
        {}

        void insert(int value)
        {
            if (!m_head)
            {
                m_head = shared_ptr<Node> (new Node(value));
            }
            else
            {
                NodePtr nodePtr = m_head;

                while(nodePtr->next)
                {
                    nodePtr = nodePtr->next;
                }

                nodePtr->next = shared_ptr<Node> (new Node(value));
            }
        }

        void printList()
        {
            NodePtr nodePtr = m_head;

            while(nodePtr->next)
            {
                cout << nodePtr->value << endl;
                nodePtr = nodePtr->next;
            }
            cout << nodePtr->value << endl;
        }

        void reverse()
        {
            NodePtr f = m_head;
            
            NodePtr s;
            if (m_head->next)
            {
                s = m_head->next;
            }
            else // there is only 1 node - no reverse
            {
                return;
            }
            
            NodePtr t;
            if (s->next)
            {
                t = s->next;
            }
            else // there are 2 nodes
            {
                f->next = shared_ptr<Node>();
                s->next = f;
                
                m_head = s;
                
                return;
            }
            
            f->next = shared_ptr<Node>();
            s->next = f;
            while(t->next)
            {
                f = s;
                s = t;
                t = t->next;
                
                s->next = f;
            }
            t->next = s;
            
            m_head = t;
            
        }
        
        void reverseRecursive()
        {
            auto tempHead = m_head;
            reverseByRecursion(m_head);
            tempHead->next = shared_ptr<Node>();
        }
        
        void reversInParts(int part)
        {}
        
    private:
        NodePtr m_head;
        
        void reverseByRecursion(NodePtr node)
        {
            if (node->next->next)
            {
                reverseByRecursion(node->next);
            }
            else
            {
                m_head = node->next;
            }
            
            node->next->next = node;
        }
        
};

int main()
{
    LinkedList ll;
    ll.insert(1);
    ll.insert(2);
    ll.insert(3);
    ll.insert(4);
    ll.insert(5);
    ll.insert(6);

    ll.printList();
    
    cout << "reverse by loop " << endl;
    ll.reverse();
    
    cout << "print " << endl;
    ll.printList();
    
    cout << "reverse by recursion " << endl;
    ll.reverseRecursive();
    
    cout << "print " << endl;
    ll.printList();

}
