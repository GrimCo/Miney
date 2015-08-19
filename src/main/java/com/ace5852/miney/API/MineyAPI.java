package com.ace5852.miney.API;

import com.ace5852.miney.DataHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MineyAPI
{
    /***
     * Gets the balance of the player's bank account.
     * @param player The player whose bank account you want.
     * @return The balance of the bank account
     */
    public double getBankBalance(EntityPlayer player)
    {
        DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");
        return data.getHand();
    }

    /***
     * Gets the player's on hand balance.
     * @param player The player whose on hand balance you want.
     * @return The player's on hand balance.
     */
    public double getMineyBalance(EntityPlayer player)
    {
        DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");
        return data.getBank();
    }

    /***
     * Charge the Player. This is recommended for all transactions and comes out of the on hand balance.
     * @param player The player to charge.
     * @param amount The amount to charge/
     * @return If the transaction was successful.
     * Currently fails if there is not enough money to complete the transaction.
     */
    public boolean chargePlayer(EntityPlayer player, double amount)
    {
        DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");
        double currentCurrencyHand = data.getHand();
        if (amount > currentCurrencyHand)
        {
            return false;
        }

        double newCurrencyHand = currentCurrencyHand - amount;
        data.setHand(newCurrencyHand);
        return true;
    }

    /***
     * Charges the Player's bank account directly. This is recommended for use in ATMs and other things that should
     * interact directly with the bank. It is not recommended to use for normal purchases.
     * @param player The player whose bank account you wish to charge.
     * @param amount The amount to charge.
     * @return If the transaction was successful.
     * Currently fails if there is not enough money to complete the transaction.
     */
    public boolean chargePlayerBank(EntityPlayer player, double amount)
    {
        DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");
        double currentCurrencyBank = data.getBank();
        if (amount > currentCurrencyBank)
        {
            return false;
        }

        double newCurrencyBank = currentCurrencyBank - amount;
        data.setBank(newCurrencyBank);
        return true;
    }


}
