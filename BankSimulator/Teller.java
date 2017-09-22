// DO NOT ADD NEW METHODS OR DATA FIELDS!
// DO NOT MODIFY ANY METHODS OR DATA FIELDS!
package PJ3;

class Teller {

    // teller id and current customer which is served by this cashier
    private int tellerID = 0;
    private Customer customer;

    // start time and end time of current free/busy interval
    private int startFreeTime = 0;
    private int endFreeTime = 0;
    private int startBusyTime = 0;
    private int endBusyTime = 0;

    // for keeping statistical data
    private int totalFreeTime = 0;
    private int totalBusyTime = 0;
    private int totalCustomers = 0;

    // Constructor
    Teller() {
        this(-1);
    }

    // Constructor with teller id
    Teller(int tellerId) {
        this.tellerID = tellerId;
        // add statements
    }

    //--------------------------------
    // accessor methods
    //--------------------------------
    int getTellerID() {
        return tellerID;
    }

    Customer getCustomer() {
        return customer;
    }

    int getEndBusyTime() {
        return endBusyTime;
    }

    //--------------------------------
    // mutator methods
    //--------------------------------
    void setCustomer(Customer newCustomer) {
        customer = newCustomer;
    }

    void setStartFreeTime(int time) {
        startFreeTime = time;
    }

    void setStartBusyTime(int time) {
        startBusyTime = time;
    }

    void setEndFreeTime(int time) {
        endFreeTime = time;
    }

    void setEndBusyTime(int time) {
        endBusyTime = time;
    }

    void updateTotalFreeTime() {
        totalFreeTime += (endFreeTime - startFreeTime);

    }

    void updateTotalBusyTime() {
        totalBusyTime += (endBusyTime - startBusyTime);    
    }
    void updateTotalCustomers() {
        totalCustomers++;
    }

    //--------------------------------
    // Teller State Transition methods
    //--------------------------------
    // From free interval to busy interval
    void freeToBusy(Customer newCustomer, int currentTime) { 
        setEndFreeTime(currentTime);
        updateTotalFreeTime();
        setStartBusyTime(currentTime);
        setEndBusyTime(startBusyTime + newCustomer.getTransactionTime());
        setCustomer(newCustomer);
        updateTotalCustomers();
       
        // goal  : start serving newCustomer at curreTime
        // steps : set endFreeTime, update TotalFreeTime
        //         set startBusyTime, endBusyTime, customer
        //         update totalCustomers
        //add statements
    }

    // Transition from busy interval to free interval
    Customer busyToFree() {
        
        updateTotalBusyTime();
        endBusyTime = startFreeTime;

        // goal  : end serving customer at endBusyTime
        // steps : update TotalBusyTime, set startFreeTime
        //         return customer 
        //add statements
        return customer;
    }

    //--------------------------------
    // Print statistical data   
    //--------------------------------
    void printStatistics() {
        // print teller statistics, see project statement
        System.out.println("\t\tTeller ID                : " + tellerID);
        System.out.println("\t\tTotal free time          : " + totalFreeTime);
        System.out.println("\t\tTotal busy time          : " + totalBusyTime);
        System.out.println("\t\tTotal # of customers     : " + totalCustomers);
        if (totalCustomers > 0) {
            System.out.format("\t\tAverage transaction time : %.2f%n\n", (totalBusyTime * 1.0) / totalCustomers);
        }
    }

    public String toString() {
        return "tellerID=" + tellerID
                + ":startFreeTime=" + startFreeTime + ":endFreeTime=" + endFreeTime
                + ":startBusyTime=" + startBusyTime + ":endBusyTime=" + endBusyTime
                + ":totalFreeTime=" + totalFreeTime + ":totalBusyTime=" + totalBusyTime
                + ":totalCustomer=" + totalCustomers + ">>customer:" + customer;
    }

    public static void main(String[] args) {
        // quick check
        Customer mycustomer = new Customer(1, 5, 15);
        Teller myteller = new Teller(5);
        myteller.setStartFreeTime(0);
        System.out.println(myteller);
        myteller.freeToBusy(mycustomer, 20);
        System.out.println("\n" + myteller);
        myteller.busyToFree();
        System.out.println("\n" + myteller);
        System.out.println("\n\n");
        myteller.printStatistics();

    }

};
