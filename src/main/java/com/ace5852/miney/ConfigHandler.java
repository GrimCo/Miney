package com.ace5852.miney;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler
{
    public static void init(File file)
    {
        Configuration config = new Configuration(file);

        config.load();

        Miney.enableBank = config.getBoolean("enabled", "Bank", true, "Enable or Disable the Bank.");
        Miney.intrestRate = config.getFloat("InterestRate", "Bank", 0.05f, 0.00f, 2.00f, "Set's the interest rate for the bank accounts.");
        Miney.intrestTime = config.getInt("InterestTime", "Bank", 86400, 0, Integer.MAX_VALUE, "Sets the time between interest payments. Payments are processed once per login per period. Missed Periods are ignored. Value In Seconds. ");
        Miney.startingMiney = config.getFloat("StartingMiney", "Miney", 200.0f, 0.0f, 5000.0f, "Amount of miney given on first login.");
        Miney.amountLostOnDeath = config.getFloat("LostOnDeath", "Miney", 0.50f, 0.0f, 1.0f, "Percentage of miney lost on death. 0 = no loss. 1 = all money not in the bank lost. Banked miney is always safe.");

        config.save();


        //Convert Seconds to Milliseconds
        Miney.intrestTime = Miney.intrestTime * 1000;
    }
}
