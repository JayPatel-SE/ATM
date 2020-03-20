/*
 * Jay Patel
 * ATM MACHINE - Transaction class
 */
import java.util.Date;

public class Transaction {

    /**
     *  The amount of this transaction.
     */
    private double amount;

    /**
     *  The time and date of this transaction.
     */
    private Date timeStamp;

    /**
     *  A memo for this transaction.
     */
    private String memo;

    /**
     *  The account in which the transaction was performed.
     */
    private Account inAccount;

    /**
     * Create new transaction
     * @param amount        The amount transacted
     * @param inAccount     The account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount){

        this.amount = amount;
        this.inAccount = inAccount;
        this.timeStamp = new Date();
        this.memo = "";

    }//end constructor(amount, account)

    /**
     *  Create new transaction
     * @param amount    The amount transacted
     * @param memo       The memo of the transaction
     * @param inAccount The account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount){

        //call the two-arg constructor first
        this(amount, inAccount);

        //set the memo
        this.memo = memo;

    }//end constructor(amount, memo, account)

    /**
     * Get the amount of the transaction
     * @return  the amount
     */
    public double getAmount() {
        return this.amount;
    }//end getAmount()

    /**
     *     Get a string summarizing the transaction
     * @return  the summarty string
     */
    public String getSummaryLine() {

        if(this.amount >= 0){
            return String.format("%s : $%.02f : %s", this.timeStamp.toString(),
                    this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f) : %s", this.timeStamp.toString(),
                    -this.amount, this.memo);
        }//end if

    }//end getSummaryLine()

}//end class

