package com.ace5852.miney.API;

import com.ace5852.miney.DataHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MineyAPI
{
    public double getBankBalance(EntityPlayer player)
    {
        DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");
        return data.getHand();
    }

    public double getMineyBalance(EntityPlayer player)
    {
        DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");
        return data.getBank();
    }

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
