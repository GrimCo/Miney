package com.ace5852.miney.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandPay extends CommandBase
{

    @Override
    public String getCommandName()
    {
        return "pay";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/pay [user] [amount]";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    @Override
    public void processCommand(ICommandSender cmd, String[] args)
    {
        if (args.length != 2)
        {
            cmd.addChatMessage(new ChatComponentText(getCommandUsage(cmd)));
        }

        NBTTagCompound otherPlayer = cmd.getEntityWorld().getPlayerEntityByName(args[0]).getEntityData();
        NBTTagCompound tag = cmd.getEntityWorld().getPlayerEntityByName(cmd.getCommandSenderName()).getEntityData();

        double currentCurrency = tag.getDouble("Miney");
        double currentCurrencyOther = otherPlayer.getDouble("Miney");
        double toPay = Double.parseDouble(args[1]);

        if (toPay >= 0)
        {
            cmd.addChatMessage(new ChatComponentText("You must send an amount greater than 0."));
        }
        double newCurrency = Math.round((toPay - currentCurrency)*100)/100.0;
        double newCurrencyOther = Math.round((toPay + currentCurrencyOther)*100)/100.0;

        if (newCurrency < 0.0)
        {
            cmd.addChatMessage(new ChatComponentText("You do not have enough Miney for that."));
        }
        else
        {
            tag.setDouble("Miney", newCurrency);
            otherPlayer.setDouble("Miney", newCurrencyOther);

            // cmd.getEntityWorld().getPlayerEntityByName(cmd.getCommandSenderName()).writeToNBT(new NBTTagCompound().setDouble("Miney", newCurrency));

            cmd.addChatMessage(new ChatComponentText("You now have " + newCurrency + " Miney "));
        }
    }
}