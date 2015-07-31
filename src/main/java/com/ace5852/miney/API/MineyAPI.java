package com.ace5852.miney.API;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MineyAPI
{
    public double getBankBalance(EntityPlayer player)
    {
        double currentCurrencyBank = player.getEntityData().getDouble("MineyBank");
        return currentCurrencyBank;
    }

    public double getMineyBalance(EntityPlayer player)
    {
        double currentCurrencyHand = player.getEntityData().getDouble("Miney");
        return currentCurrencyHand;
    }

    public boolean chargePlayer(EntityPlayer player, double amount)
    {
        NBTTagCompound tag = player.getEntityData();
        double currentCurrencyHand = tag.getDouble("Miney");
        if (amount > currentCurrencyHand)
        {
            return false;
        }

        double newCurrencyHand = currentCurrencyHand - amount;
        newCurrencyHand = Math.round((newCurrencyHand)*100)/100.0;
        tag.setDouble("Miney", newCurrencyHand);
        return true;
    }

    public boolean chargePlayerBank(EntityPlayer player, double amount)
    {
        NBTTagCompound tag = player.getEntityData();
        double currentCurrencyBank = tag.getDouble("MineyBank");
        if (amount > currentCurrencyBank)
        {
            return false;
        }

        double newCurrencyBank = currentCurrencyBank - amount;
        newCurrencyBank = Math.round((newCurrencyBank)*100)/100.0;
        tag.setDouble("MineyBank", newCurrencyBank);
        return true;
    }


}
