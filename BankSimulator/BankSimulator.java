package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data in this class 
// You may modify any functions or data members here
// You must use Customer, Teller and ServiceArea classes
// to implement Bank simulator
class BankSimulator {

    // input parameters
    private int numTellers = 0, customerQLimit = 0;
    private int simulationTime = 0, dataSource = 0;
    private int chancesOfArrival = 0,
            maxTransactionTime = 0;

    // statistical data
    private int numGoaway = 0, numServed = 0, totalWaitingTime = 0;

    // internal data
    private int customerIDCounter = 0;   // customer ID counter
    private ServiceArea servicearea; // service area object
    private Scanner dataFile;	   // get customer data from file
    private Random dataRandom;

    private String data;
    private boolean getdata;
    private String read;
    private Teller teller;
    private Customer customer;
    private int user;
    private int Goaway=0;
    private ArrayList<Boolean> array1 = new ArrayList(simulationTime);
    private ArrayList<Integer> array2 = new ArrayList(simulationTime);

// get customer data using random function

    // most recent customer arrival info, see getCustomerData()
    private boolean anyNewArrival;
    private int transactionTime = 0;

    // initialize data fields
    private BankSimulator() {

        // add statements
    }

    private void setupParameters() {
        Scanner sc = new Scanner(System.in);
//1.
        System.out.print("Enter Simulation time: (must be > 0 and < 10000)  ");
        simulationTime = sc.nextInt();

        if (simulationTime > 0 || simulationTime < 10000) {
            while (simulationTime < 0 || simulationTime > 10000) {
                System.out.println("invalid input try again");
                simulationTime = sc.nextInt();

            }

        }
//2.       
        System.out.print("Enter the number of Tellers:  (1-10)  ");
        numTellers = sc.nextInt();
        if (numTellers > 0 || numTellers < 10) {

            while (numTellers < 0 || numTellers > 10) {
                System.out.println("invalid input try again");
                numTellers = sc.nextInt();
            }
        }
//3.
        System.out.print("Enter chances of new customer:  (0-100%)   ");
        chancesOfArrival = sc.nextInt();
        if (chancesOfArrival > 0 || chancesOfArrival < 100) {
            while (chancesOfArrival < 0 || chancesOfArrival > 100) {
                System.out.println("invalid input try again");
                chancesOfArrival = sc.nextInt();
                anyNewArrival = false;
            }
            anyNewArrival = true;
        }
//4.
        System.out.print("Enter maximum transaction time of customers: (0-500)  ");
        maxTransactionTime = sc.nextInt();
        if (maxTransactionTime > 0 || maxTransactionTime < 500) {
            while (maxTransactionTime < 0 || maxTransactionTime > 500) {
                System.out.println("invalid input try again");
                maxTransactionTime = sc.nextInt();
            }
        }
//5.
        System.out.print("Enter customer queue limit: (0-50)  ");
        customerQLimit = sc.nextInt();
        if (customerQLimit < 0 || customerQLimit > 50) {
            while (customerQLimit > 0 || customerQLimit < 50) {
                System.out.println("invalid input try again");
                customerQLimit = sc.nextInt();
            }
        }
//6.
        System.out.print("Enter 0/1 to get data from Random/file:   ");
        System.out.println("0 for Random numbers and 1 for numbers from file");
        user = sc.nextInt();

        if (user == 0) {
            getCustomerData();

            //no dont get data from randomfile
        } else if (user == 1) {
            System.out.print("Please Enter the file ");
            data = sc.next();
            getCustomerData();

        } else {

            while (dataSource < 0 || dataSource > 1) {
                System.out.println("invalid input try again");
                dataSource = sc.nextInt();
            }
        }

        //System.out.println("Enter filename:  " + dataFile);
        // read input parameters
        // setup dataFile or dataRandom
        // add statements
    }

    // Refer to step 1 in doSimulation()
    private void getCustomerData() {
        String datas;
        Scanner loop= new Scanner(System.in);
        if (user == 1) {

            try {
                Scanner reader = new Scanner(new File(data ));
                for(int i =0; i<simulationTime;i++){
                if (reader.hasNextInt()) {
                    int data1 = reader.nextInt();
                    int data2 = reader.nextInt();
                    anyNewArrival = (((data1 % 100) + 1) <= chancesOfArrival);
                    transactionTime = (data2 % maxTransactionTime) + 1;
                    array1.add(i, anyNewArrival);
                    array2.add(i, transactionTime);
                }}
            } catch (FileNotFoundException ex) {
                System.out.print("Please restart program and try again with valid input. ");
       }

        } else if (user == 0) {
            dataRandom = new Random();
            for(int i =0; i<simulationTime;i++){
            anyNewArrival = ((dataRandom.nextInt(100) + 1) <= chancesOfArrival);
            transactionTime = dataRandom.nextInt(maxTransactionTime) + 1;
            array1.add(i, anyNewArrival);
            array2.add(i, transactionTime);
        }
            }

        //yes get data from random file
        //Customer(customerIDCounter);1
        // get next customer data : from file or random number generator
        // set anyNewArrival and transactionTime
        // see Readme file for more info
        // add statements
    }

    private void doSimulation() {
        System.out.println("Start Simulation");
        servicearea = new ServiceArea(numTellers, customerQLimit);

// Initialize ServiceArea
        // --------------------------------------------------------------------------
        // Step 1: any new customer enters the bank?
        // Step 1.1: setup customer data
        // Step 1.2: check customer waiting queue is it too long?
        // if it is too long, update numTurnedAway
        // else enter customer queue
        // Step 2: free busy tellers that are done at currentTime, add to
        // freeTellerQ
        // Step 3: get free tellers to serve waiting customers at
        // currentTime		
        // --------------------------------------------------------------------------
        /*
        1.customer #6 arrives with transaction time 5 units
        2.customer #6 wait in the customer queue
        3.customer #4 is done
        4.customer # line is long they leave
        5.customer #6 gets a teller
        6.teller  #2 is free
        7.teller #2 starts serving customer #6 for 5 units        
        
         */
        servicearea = new ServiceArea(numTellers, customerQLimit);

        for (int i = 0; i < numTellers; i++) {
            servicearea.insertFreeTellerQ(teller = new Teller(i + 1));
        }
        getCustomerData();
        // add statements
        //ServiceArea();
        // Initialize ServiceArea
        // Time driver simulation loop
        for (int currentTime = 0; currentTime < simulationTime; currentTime++) {
            System.out.println("            ");
            System.out.println("            ");
            System.out.println("---------------------------------------------\nTime: " + currentTime);

          

            //1          //if anyone arrives
            if (array1.get(currentTime)) {
                
                customerIDCounter++;
                customer = new Customer(customerIDCounter,array2.get(currentTime), currentTime);
                System.out.println("\n Customer # " + customerIDCounter + " arrives at transaction time " + customer.getTransactionTime());

                //1.2 // if customerQ is too long       
                if (servicearea.isCustomerQTooLong()) {
                    /*1.2*/ 
                    System.out.println("\n Customer " + customer.getCustomerID() + " customer goes away due to long line ");
                    
                    Goaway++;
                } else {
                    //if theirs space in customerQ add the customer
                    totalWaitingTime++;
                    System.out.print("\n Customer " + customer.getCustomerID() + " gets into the line ");

                    servicearea.insertCustomerQ(customer);
                }
            }// if no one arrives found
            else {
                System.out.println("\n We have no new Customers ");
                // if so, do nothing
            }
            if (servicearea.emptyBusyTellerQ()) {
                System.out.println("\n We have no busy Tellers ");
            } else if (servicearea.getFrontBusyTellerQ().getEndBusyTime() <= currentTime) {

                servicearea.getFrontBusyTellerQ().busyToFree();
                System.out.println("\n Teller " + servicearea.getFrontBusyTellerQ().getTellerID());
                servicearea.insertFreeTellerQ(servicearea.removeBusyTellerQ());

                numServed++;
            }
            if (servicearea.emptyCustomerQ()) {
                System.out.println("\n there are no customers in line ");
            } else if (servicearea.emptyFreeTellerQ()) {
                System.out.println("\n there are no tellers available ");
            } else {
                Teller nTeller = servicearea.removeFreeTellerQ();
                Customer nCustomer = servicearea.removeCustomerQ();
                System.out.println("\n Customer " + nCustomer.getCustomerID() + " gets a teller " + nCustomer.getCustomerID() + " for " + nCustomer.getTransactionTime());
                System.out.println("\n Teller " + nTeller.getTellerID() + " starts helping customer ");
                nTeller.freeToBusy(nCustomer, currentTime);
                servicearea.insertBusyTellerQ(nTeller);
            }

            System.out.println("\nWe have" + servicearea.numWaitingCustomers() + "customers in line ");
            System.out.println("\nWe have" + servicearea.numFreeTellers() + " free tellers ");
            System.out.println("\nWe have" + servicearea.numBusyTellers() + " busy tellers ");

        }// end of for loop time 

        System.out.println("\nEnd Simulation\n---------------------------------------------\n");
    }

    private void printStatistics() {
        System.out.println("\nEnd of simulation report");
        
        System.out.println("\n# total arrival customers  			: " + customerIDCounter);
       
        System.out.println("\n# customers turned-away        		: " + Goaway);
        
        System.out.println("\n# customers served         			: " + numServed + "\n");
        
        System.out.println("\n   *** Current Tellers Info ***   \n");
        servicearea.printStatistics();
        System.out.println("\n\n\nTotal waiting time         		: " +totalWaitingTime );
        System.out.println("\nAverage waiting time       			: " + ((double) customerIDCounter)/ ((numGoaway+numServed) - numGoaway));
        System.out.println("\n\n\tBusy Tellers (" + servicearea.numBusyTellers() + ") Info\n\n");
        for (int i = 0; i < servicearea.numBusyTellers();) {
            Teller printBusyTeller = servicearea.removeBusyTellerQ();
            printBusyTeller.printStatistics();
        }
        System.out.println("\n\nFree Tellers " + servicearea.numFreeTellers() + " Info \n");
        for (int i = 0; i < servicearea.numFreeTellers();) {
            Teller printFreeTeller = servicearea.removeFreeTellerQ();
            printFreeTeller.printStatistics();
            System.out.println("\n");
        }
    }

    // *** main method to run simulation ****
    public static void main(String[] args) {
        BankSimulator runBankSimulator = new BankSimulator();
        runBankSimulator.setupParameters();
        runBankSimulator.doSimulation();
        runBankSimulator.printStatistics();
    }

}
