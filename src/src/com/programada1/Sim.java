package com.programada1;

import java.util.Comparator;
import java.util.List;

public class Sim {

    private List<Client> clientList;
    private List<Event> eventList;
    private double clock;
    private int server[] = new int[2];
    private int queueLength;
    private int currentClient;

    public Sim()
    {
        server[0] = -1;
        server[1] = -1;
        currentClient = 0;
        queueLength = 0;
    }

    private void processArrive()
    {
        // A new client has arrived
        // Wee need to take information from generated event
        Event topArrive = this.eventList.get(0);

        // Then, this event is no necessary anymore
        this.eventList.remove(0 );

        // create the new client
        Client newClient = new Client( topArrive.client, topArrive.getClockTime(), -1, -1 );

        if ( -1 != server[ 0 ] && -1 != server [1] )
        {
            ++queueLength;
        }
        else
            {
                // verify which server is free
                if (  -1 != this.server [0] )
                {
                    this.server[0] = topArrive.client;
                    // no time in waiting queue
                    newClient.setQueueTimeLeave( topArrive.getClockTime() );

                }else if ( -1 != this.server [1] )
                {
                    this.server[1] = topArrive.client;
                    // no time in waiting queue
                    newClient.setQueueTimeLeave( topArrive.getClockTime() );
                }

                this.generateLeave( topArrive.client );
            }
            this.generateArrive( );
            // client  is appended
            this.clientList.add( this.clientList.size(), newClient );
  ;
    }

    private void processLeave()
    {

        // take the next leave event is
        // always on top
        Event topLeave = this.eventList.get( 0 );

        // this event is no necessary anymore
        this.eventList.remove(0 );

        if ( queueLength > 0 )
        {
            // select from queue the next client and put it in a server
            int queueClient = -1;

            if ( server[0]  == topLeave.client )
            {
                if ( topLeave.client < server [1] )
                    server [0] = server [1]  + 1;
                else
                    ++server [ 0 ];
                queueClient = server[0];
            }
            else
                {
                    if ( topLeave.client < server [0] )
                        server [1] = server [0]  + 1;
                    else
                        ++server [ 1 ];
                    queueClient = server[1];
                }

            // one client gets out from queue
            --queueLength;

            // and the client who left queue needs to leave system one day
            this.generateLeave( queueClient );

            // and finally, update the queueTimeLeave value
            for (int clientIndex = 0; clientIndex < this.clientList.size(); ++ clientIndex )
            {
                if ( clientList.get( clientIndex ).getId() == queueClient )
                {
                    clientList.get( clientIndex ).setQueueTimeLeave( topLeave.getClockTime() );
                    clientIndex = this.clientList.size();
                }
            }
        }
        else
            {
                // one of the servers is going to be free
                if ( server[0]  == topLeave.client )
                    server [0] = -1;

                if ( server [1] == topLeave.client )
                    server [1] = -1;

                // finally update when client left the system
                for (int clientIndex = 0; clientIndex < this.clientList.size(); ++ clientIndex )
                {
                    if ( clientList.get( clientIndex ).getId() == topLeave.client )
                    {
                        clientList.get( clientIndex ).setSystemTimeLeave( topLeave.getClockTime() );
                        clientIndex = this.clientList.size();
                    }
                }
            }
    }

    private void generateArrive( )
    {
        // a new client just arrived
        ++currentClient;

        // new arrived event is added
        this.eventList.add( new Event(1, this.clock, this.currentClient ) );

        // then, eventList is sorted by type and by time
        this.eventList.sort( Comparator.comparing( Event::getType ) ); // leaves < arrives always
        this.eventList.sort( Comparator.comparing( Event::getClockTime ) ); // and per times
    }

    private void generateLeave( int client )
    {
        // new arrived event is added
        this.eventList.add( new Event(0, this.clock , client ) );

        // then, eventList is sorted by type and by time
        this.eventList.sort( Comparator.comparing( Event::getType ) );
        this.eventList.sort( Comparator.comparing( Event::getClockTime ) );
    }

    public static void main(String[] args) {
	// write your code here
    }
}

