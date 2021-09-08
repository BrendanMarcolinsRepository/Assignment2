package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Record
{
    float arrival;
    float departure;

    public void setDeparture(float departure)
    {
        this.departure = departure;
    }

    public float getDeparture()
    {
        return departure;
    }

    public void setArrival(float arrival)
    {
        this.arrival = arrival;
    }

    public float getArrival()
    {
        return arrival;
    }


    public String display()
    {
        return "Record{" +
                "arrival=" + arrival +
                ", departure=" + departure +
                '}';
    }
}


class Queue
{
    Record array[] = new Record[500];
    int size  = 0;
    int front = 0;
    int rear = 0;

    public void in(Record data)
    {
        array[rear] = data;
        rear++;
        size++;
    }

    public boolean isEmpty()
    {

        if(array[0] == null)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public float dequeue()
    {
        Record data = array[front];
        if(array[0]==null)
        {
            return 0;
        }
        float d  = array[0].departure;
        for(int i = 0; i < rear - 1; i++)
        {
            array[i] = array[i+1];
        }

        array[rear-1] = null;
        rear--;
        return d;

    }


    public void out()
    {
        array[rear] = null;
        rear--;
        size--;
    }

    public void display()
    {
        int count = 1;
        for(Record i : array)
        {
                if(i != null)
                System.out.println("Customer: " + count + "\n"  + i.display());
                count++;
        }
    }

}



public class Main
{
    private static Queue queue;
    private static boolean busy;
    private static float serviceEnd = 0;

    static class Servers
    {
        
        public static void process(float next_service,float next_arrival, float time,Record record)
        {

            if(busy == true)
            {
                getFUckedUowWanqingKING();
                if(serviceEnd < next_service)
                {
                    service();
                    System.out.println("1");
                }
                else
                {

                    arrival(record,next_service,next_arrival);
                    System.out.println("2");

                }
            }
            else
            {
                arrival(record,next_service,next_arrival);
                System.out.println("3");

            }
        }

        public static void arrival(Record record, float nextArrival, float nextServices)
        {
            float time = nextArrival;
            if(busy)
            {
                queue.in(record);
                System.out.println("4");

            }
            else
            {
                busy = true;
                serviceEnd += time + nextServices;
                System.out.println("here "+serviceEnd);
                System.out.println("5");


            }
        }

        public static void service()
        {
            float time = serviceEnd;
            if(queue.isEmpty())
            {
                busy = false;
                System.out.println("6");
            }
            else
            {
                serviceEnd += time + queue.dequeue();
                System.out.println("7");
            }
        }

        public static void getFUckedUowWanqingKING()
        {
            if(!queue.isEmpty())
            {
                System.out.println("8");
                float i = queue.dequeue();
                System.out.println(i);
                serviceEnd += i;
                System.out.println(serviceEnd);

            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File fileObject = new File("a2-sample.txt");
        Scanner scanner = new Scanner(fileObject);
        Record record = new Record();
        queue = new Queue();
        busy = false;

        Servers servers1 = new Servers();
        Servers servers2 = new Servers();
        Servers servers4 = new Servers();
        Servers Bank[] = {servers1,servers2,servers4};

        boolean empty = queue.isEmpty();
        float time = 0;




        int count = 1;
        while(scanner.hasNextFloat())
        {
            float data = scanner.nextFloat();
            if(count <= 2)
            {
                if(count == 1)
                {
                    record.arrival = data;
                    count++;

                }
                else
                {
                    record.departure = data;
                    count = 1;
                    System.out.println(record.getArrival() + "  " + record.getDeparture());
                    Servers.process(record.getArrival(), record.getDeparture(), time,record);
                    record = new Record();
                    System.out.println("Service : " + serviceEnd);


                }
            }
        }

        queue.display();

        System.out.println("Service : " + serviceEnd);


    }


}
