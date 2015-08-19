package com.ace5852.miney.API;

public interface IDebitCard
{

    /***
     * This method is used to retrieve the balance
     * @return The Balance of the Card
     */
    public double getBalance();

    /***
     * This method is used to charge the card for a purchase.
     * @param amt The Amount to Charge the Card.
     * @return True if the transaction was successful. False if the transaction failed.
     * Return false if there is not enough money to complete the transaction.
     */
    public boolean chargeCard(double amt);


}
