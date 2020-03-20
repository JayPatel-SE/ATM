/*
 * Jay Patel
 * ATM MACHINE - Bank class
 */

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    /**
     *  The name of the bank.
     */
    private String name;

    /**
     *  The list of users.
     */
    private ArrayList<User> users;

    /**
     *  The list of accounts.
     */
    private ArrayList<Account> accounts;

    /**
     * Create a new Bank object with empty list of users and acounts
     * @param name    the name of the bank
     */
    public Bank(String name) {

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }//end constructor()

    /**
     * Generate a new univerally unique ID for a user.
     * @return
     */
    public String getNewUserUUID(){

        //inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique = false;

        // continue looping until we get a unique ID
        do {

            //generate the number
            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }//end for(i)

            //check to make sure it's unique
            for(User u : this.users){
                nonUnique = false;
                if(uuid.compareTo(u.getUUID()) == 0){
                    nonUnique= true;
                    break;
                }//end if
            }//end for(User)

        } while (nonUnique);

        return uuid;
    }//end getNewUserUUID()

    /**
     * Generate a new universally unique ID for an account
      * @return
     */
    public String getNewAccountUUID() {

        //inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique = false;

        // continue looping until we get a unique ID
        do {

            //generate the number
            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }//end for(i)

            //check to make sure it's unique
            for(Account a : this.accounts){
                nonUnique = false;
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique= true;
                    break;
                }//end if
            }//end for(User)

        } while (nonUnique);

        return uuid;
    }//end getNewAccountUUID()

    /**
     *  Add an accont
     * @param account    the account to add
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }//end addAccount()

    /**
     * Create a new user of the bank
     * @param firstName     the user's first name
     * @param lastName      the user's last name
     * @param pin                 the user's pin
     * @return                        the new User object
     */
    public User addUser(String firstName, String lastName, String pin){

        //create a new user object and add it to the list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a saving account for the user
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }//end addUser()

    /**
     * Get the User object associated with a particular userid and pin, if they
     * are valid
     * @param userID        the UUID of the user to log in
     * @param pin              the pin of the user
     * @return                     The user obejct, if the logn is successful, or null, if
     *                                     it is not
     */
    public User userLogin(String userID, String pin){

        //search through the list of users
        for(User u : this.users){

            //check user ID is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }//end if
        }//end forloop()

        //if we haven't found the user or incorrect pin
        return null;

    }//end userLogin

    public String getName(){
        return  this.name;
    }//end getName

}//end class
