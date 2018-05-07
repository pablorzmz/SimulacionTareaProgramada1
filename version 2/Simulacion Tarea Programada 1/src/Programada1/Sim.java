package Programada1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Sim {

    private List<Client> clientList = new ArrayList<>();
    private List<Event> eventList = new ArrayList<>();
    private double clock;
    private int server[] = new int[2];
    private int queueLength;
    private int currentClient;
    private double randomNumber;
    private int numClientsLeave;

    public Sim()
    {
        server[0] = -1;
        server[1] = -1;
        currentClient = 0;
        queueLength = 0;
        randomNumber = -1;
        numClientsLeave = 0;
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
            if (  -1 == this.server [0] )
            {
                this.server[0] = topArrive.client;
                // no time in waiting queue
                newClient.setQueueTimeLeave( topArrive.getClockTime() );

            }else if ( -1 == this.server [1] )
            {
                this.server[1] = topArrive.client;
                // no time in waiting queue
                newClient.setQueueTimeLeave( topArrive.getClockTime() );
            }

            this.generateLeave( topArrive.client );
        }
        this.generateArrive( false );
        // client  is appended
        this.clientList.add( this.clientList.size(), newClient );
        ;
    }

    private void processLeave()
    {
        this.numClientsLeave += 1;

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
        }
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

    private void generateArrive( boolean firstClient )
    {
        // a new client just arrived
        ++currentClient;

        // new arrived event is added
        this.eventList.add( new Event(1,firstClient? 0 :this.clock + timeValue(1), this.currentClient ) );

        // then, eventList is sorted by type and by time
        this.eventList.sort( Comparator.comparing( Event::getType ) ); // leaves < arrives always
        this.eventList.sort( Comparator.comparing( Event::getClockTime ) ); // and per times
    }

    private void generateLeave( int client )
    {

        this.eventList.add( new Event(0, this.clock + timeValue(0) , client ) );

        // then, eventList is sorted by type and by time
        this.eventList.sort( Comparator.comparing( Event::getType ) );
        this.eventList.sort( Comparator.comparing( Event::getClockTime ) );

    }

    private void nextRandom(  )
    {
        this.randomNumber = Math.random();
    }

    private double timeValue(int type )
    {
        double returnValue = 0;
        nextRandom();

        if ( type == 0 )
        {
            // leave event
            if ( randomNumber >= 0.0 && randomNumber <= (0.10) )
            {
                returnValue = 2.0;
            }
            else if ( randomNumber > 0.10 && randomNumber <= 0.35)
            {
                returnValue = 3.0;

            }else if ( randomNumber > 0.35 && randomNumber <= 0.75)
            {
                returnValue = 4.0;
            }
            else if ( randomNumber > 0.75 && randomNumber <= 0.95)
            {
                returnValue = 7.0;
            }
            else if ( randomNumber > 0.95 && randomNumber <= 1.0 )
            {
                returnValue = 10.0;
            }
        }else
        {
            //arrive event
            if (randomNumber >= 0.0 && randomNumber <= 0.40)
            {
                returnValue = 1;
            }
            else if (randomNumber > 0.40 && randomNumber <= 0.75)
            {
                returnValue = 2;
            }
            else if (randomNumber > 0.75 && randomNumber <= 1.0)
            {
                returnValue = 3;
            }
        }
        return  returnValue;
    }


    public static void main(String[] args) {
        //@Walter y  @Luis esta es la prueba general y me parece que sale bien
        // falta:
        // copiar las tablas de las hoja de llegadas y salidas y hacer que seleccione de  acuerdo al intervalo
        // condición de parada cuando cliente 15 salga (si hay que modificar processLeave() se hace)
        // procesar estadisticas
        // documentar mejor con java doc (se genera automático)
        // actualizar diseño uml
        int iterations = 0;

        for (int index = 1; index < args.length; ++index) {
            if (args[index].equals("--help")) {

            }else{
                iterations = Integer.parseInt(args[index]);
            }
        }

        if(0 == iterations){
            Scanner reader = new Scanner(System.in);
            System.out.println("Write the number of clients to attend in this simulation: ");
            iterations = reader.nextInt();
        }

        Sim mySimulation = new Sim();

        // first client arrives
        mySimulation.generateArrive( true );

        while ( iterations > mySimulation.numClientsLeave )
        {
            // get next event
            Event closestEvent = mySimulation.eventList.get(0);

            // move clock
            mySimulation.clock =  closestEvent.getClockTime();

            // process next event
            if ( closestEvent.getType() == 1)
            {
                // process arrived
                mySimulation.processArrive();
            }else
            {
                // process leave
                mySimulation.processLeave();
            }
        }

        // print stats
        System.out.println("Number of clients queued: " + mySimulation.queueLength);
        double timeInQueue = 0;
        double averageTimeInQueue = 0;
        for (int index = 0; index < mySimulation.clientList.size(); ++index )
        {
            if(0.0 <= mySimulation.clientList.get(index).getQueueTimeLeave()){
                timeInQueue = mySimulation.clientList.get(index).getQueueTimeLeave();
                timeInQueue -= mySimulation.clientList.get(index).getSystemTimeArrived();
                averageTimeInQueue += timeInQueue;
            }
        }
        DecimalFormat form = new DecimalFormat("#.000");
        averageTimeInQueue = averageTimeInQueue / (mySimulation.clientList.size() - mySimulation.queueLength);
        System.out.println("Average time in queue: " + form.format(averageTimeInQueue));
    }
}
