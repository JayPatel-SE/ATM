/*
 * Jay Patel
 * September 07 2019
 * ATM MACHINE - User class
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    /**
     *  The first name of the user.
     */
    private String firstName;

    /**
     *  The last name of the user.
     */
    private String lastName;

    /**
     *  The ID number of the user.
     */
    private String uuid;

    /**
     *  The MD5 hash of the users pin number.
     */
    private byte pinHash[];

    /**
     *  The list of accounts for this user.
     */
    private ArrayList<Account> accounts;

    /**
     * Create new user
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin             the user's account pin number
     * @param theBank    the Bank object that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {

        //set user's name
        this.firstName = firstName;
        this.lastName =  lastName;

        // store the pin's MD5 hash, rather than the original value, for
        // security reasons
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e){
            System.out.println("error, caught NoSuchAlgorithException");
            e.printStackTrace();
            System.exit(1);
        }//end try catch()

        // get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log message
        System.out.printf("New user %s, %s with ID %s created.\n,", lastName,
                firstName, this.uuid);

    }//end constructor()

    /**
     *  Add an account
     * @param account   the account to add
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }//end addAcount()

    /**
     * Return the user's UUID
     * @return      the uuid
     */
    public String getUUID() { return this.uuid;}//end getUUID()

    /**
     * Check whether a given pin matches the true User pin
     * @param aPin      the pin to check
     * @return               whether the pin is valid or not
     */
    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error, caught NoSuchAlgorithException");
            e.printStackTrace();
            System.exit(1);
        }//end try catch

        return false;

    }//end validatePin()

    /**
     *  Return the users first name
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }//end getFirstName()

    /**
     * Print summaries for the accounts of this user
     */
    public void printAccountsSummary() {

        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++){
            System.out.printf("  %d) %s ", a + 1,
                    this.accounts.get(a).getSummaryLine());
            System.out.println();
        }//end forloop

        System.out.println();

    }//end printAccountSummary()

    /**
     *  Get the number of accounts of the user
     * @return      the number of accounts
     */
    public int numAccounts() {
        return this.accounts.size();
    }//end numAccounts()

    public void printAcctTransHistory(int acctIdx) {

        this.accounts.get(acctIdx).printTransHistory();

    }//end printAcctTransHistory()

    /**
     *  Get the balance of the account of the use
     * @param acctIdx  the amount of balance
     * @return
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }//end getAcctBalance()

    /**
     *  Get the UUID of a particular account
     * @param acctIdx   the index of the account to use
     * @return                the UUID of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }//end getAcctUUID()

    /**
     * Add a transaction to a particular account
     * @param acctIdx       the index of the account
     * @param amount     the amount of the transaction
     * @param memo        the memo of the transaction
     */
    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }//end addAcctTransaction()
}//end class
