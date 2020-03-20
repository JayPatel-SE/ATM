/*
 * Jay Patel
 * ATM MACHINE - Account class
 */

import java.util.ArrayList;

public class Account {

    /**
     *  The name of the account.
     */
    private String name;

    /**
     *  the account ID number.
     */
    private String uuid;

    /**
     *  The User object that owns this account.
     */
    private User holder;

    /**
     *  The list of transactions for this account.
     */
    private ArrayList<Transaction> transactions;

    /**
     *  Create a new Account
     * @param name          the name of the account
     * @param holder         the User object that holds this accont
     * @param theBank      the bank that issues the account
     */
    public Account(String name, User holder, Bank theBank) {

        // set the account name and holder
        this.name = name;
        this.holder = holder;

        // get new accoutn UUID
        this.uuid = theBank.getNewAccountUUID();

        // init trancactions
        this.transactions = new ArrayList<Transaction>();

    }//end constructor()

    /**
     * Get the account ID
     * @return  the uuid
     */
    public String getUUID() { return this.uuid;}

    /**
     * Get summary line for the account
     * @return  the string summary
     */
    public String getSummaryLine() {

        //get the balance
        double balance = this.getBalance();

        // format the summary line, depending on whether the balance
        // is negative
        if(balance >= 0){
            return String.format("%s : $%.2f : %s", this.uuid, balance, this.name);
        } else {
            return  String.format("%s : $%.2f : %s", this.uuid, balance, this.name);
        }//end if

    }//end getSummaryLine()

    /**
     * Get the balance of this account by adding the amounts of the transactions
     * @return   the balance value
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions){
            balance += t.getAmount();
        }//end forloop

        return balance;

    }//end getBalance()

    /**
     *  Print the transaction history of the account
     */
    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for(int t = this.transactions.size() - 1;  t >= 0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }//end forloop
        System.out.println();

    }//end printTransHistory()

    /**
     *  Add a new transaction in this accont
     * @param amount    the amount transacted
     * @param memo      the transaction memo
     */
    public void addTransaction(double amount, String memo) {

        //create a new Transaction object and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);

    }//end addTransaction()

}//end class
