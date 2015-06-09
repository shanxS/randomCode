// schedule meeting
// set 181, r4, q2

import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
        Calendar calendar = new Calendar();

        List<MeetingNode> meetings = generateMeetings();
        for (MeetingNode meetingnode : meetings)
        {
            calendar.insert(meetingnode);
        }

        Collection<Person> persons = calendar.getPersons();
        for (Person person : persons)
        {
            System.out.println(person.getName());
            person.printSchedule();

            System.out.println();
            System.out.println();
        }
    }

    private static List<MeetingNode> generateMeetings()
    {
        List<MeetingNode> meetingNodesList = new ArrayList<>();

        MeetingNode mn = new MeetingNode(1600, 1, new Character[]{'B', 'C', 'D'}, 'A');
        meetingNodesList.add(mn);

        mn = new MeetingNode(1700, 4, new Character[]{'A', 'D'}, 'B');
        meetingNodesList.add(mn);

        mn = new MeetingNode(1400, 2, new Character[]{'C', 'D'}, 'A');
        meetingNodesList.add(mn);

        mn = new MeetingNode(1330, 3, new Character[]{'C'}, 'B');
        meetingNodesList.add(mn);

        return meetingNodesList;
    }
}

class Person
{
    private Character name;
    private CalanderReference meetingsHead;

    public Person(Character name)
    {
        this.name = name;
    }

    public void scheduleMeeting(MeetingNode meetingNode)
    {
        if (meetingsHead == null)
        {
            meetingsHead = new CalanderReference(meetingNode);
        }
        else
        {
            insertMeetingNode(meetingsHead, meetingNode);
        }
    }

    public Character getName()
    {
        return name;
    }

    private void insertMeetingNode(CalanderReference node, MeetingNode meetingNode)
    {
        if (node.getTime() > meetingNode.getTime())
        {
            if (node.getLeft() != null)
            {
                insertMeetingNode(node.getLeft(), meetingNode);
            }
            else
            {
                node.setLeft(new CalanderReference(meetingNode));
            }
        }
        else if (node.getTime() < meetingNode.getTime())
        {
            if (node.getRight() != null)
            {
                insertMeetingNode(node.getRight(), meetingNode);
            }
            else
            {
                node.setRight(new CalanderReference(meetingNode));
            }
        }
    }

    public void printSchedule()
    {
        print(meetingsHead);
    }

    private void print(CalanderReference node)
    {
        if (node == null)
        {
            return;
        }

        print(node.getLeft());

        System.out.println(" time: " + node.getMeeting().getTime());
        System.out.println(" id: " + node.getMeeting().getId());
        System.out.println(" invitor: " + node.getMeeting().getInvitor());
        System.out.print(" attendees: ");
        for (Character c : node.getMeeting().getAttendees())
        {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println();

        print(node.getRight());
    }

    private class CalanderReference
    {
        private Integer time;
        private MeetingNode meeting;
        private CalanderReference left, right;

        public CalanderReference(MeetingNode meeting)
        {
            this.time = meeting.getTime();
            this.meeting = meeting;
            this.left = left;
            this.right = right;
        }

        public Integer getTime()
        {
            return time;
        }

        public void setTime(Integer time)
        {
            this.time = time;
        }

        public MeetingNode getMeeting()
        {
            return meeting;
        }

        public void setMeeting(MeetingNode meeting)
        {
            this.meeting = meeting;
        }

        public CalanderReference getLeft()
        {
            return left;
        }

        public void setLeft(CalanderReference left)
        {
            this.left = left;
        }

        public CalanderReference getRight()
        {
            return right;
        }

        public void setRight(CalanderReference right)
        {
            this.right = right;
        }
    }
}

class Calendar
{
    private MeetingNode meetingHead;
    private Map<Character, Person> namePersonMap;

    public Calendar()
    {
        this.meetingHead = null;
        this.namePersonMap = new HashMap<>();
    }

    public void insert(MeetingNode meetingNode)
    {
        addToPersonSchedule(meetingNode);

        if (meetingHead == null)
        {
            meetingHead = meetingNode;
        }
        else
        {
            insert(meetingHead, meetingNode);
        }
    }

    private void addToPersonSchedule(MeetingNode meetingNode)
    {
        for (Character attendee : meetingNode.getAttendees())
        {
            Person person = namePersonMap.get(attendee);
            if (person == null)
            {
                person = new Person(attendee);
                namePersonMap.put(attendee, person);
            }
            person.scheduleMeeting(meetingNode);
        }

        Person person = namePersonMap.get(meetingNode.getInvitor());
        if (person == null)
        {
            person = new Person(meetingNode.getInvitor());
        }
        person.scheduleMeeting(meetingNode);

    }

    public Collection<Person> getPersons()
    {
        return namePersonMap.values();
    }

    private void insert(MeetingNode node, MeetingNode newNode)
    {
        if (node.getTime() > newNode.getTime())
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), newNode);
            }
            else
            {
                node.setLeft(newNode);
            }
        }
        else if (node.getTime() < newNode.getTime())
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), newNode);
            }
            else
            {
                node.setRight(newNode);
            }
        }
    }

    public void printMeetings()
    {
        print(meetingHead);
        System.out.println("________________________");
    }

    private void print(MeetingNode node)
    {
        if (node == null)
        {
            return;
        }

        print(node.getLeft());

        System.out.println(" time: " + node.getTime());
        System.out.println(" id: " + node.getId());
        System.out.println(" invitor: " + node.getInvitor());
        System.out.print(" attendees: ");
        for (Character c : node.getAttendees())
        {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println();

        print(node.getRight());
    }
}

class MeetingNode
{
    private Character[] attendees;
    private Integer id, time;
    private Character invitor;
    private MeetingNode left, right;

    public MeetingNode(Integer time, Integer id, Character[] attendees, Character invitor)
    {
        this.time = time;
        this.id = id;
        this.attendees = attendees;
        this.invitor = invitor;
        this.left = null;
        this.right = null;
    }

    public MeetingNode getLeft()
    {
        return left;
    }

    public void setLeft(MeetingNode left)
    {
        this.left = left;
    }

    public MeetingNode getRight()
    {
        return right;
    }

    public void setRight(MeetingNode right)
    {
        this.right = right;
    }

    public Character[] getAttendees()
    {
        return attendees;
    }

    public Integer getId()
    {
        return id;
    }

    public Integer getTime()
    {
        return time;
    }

    public Character getInvitor()
    {
        return invitor;
    }
}