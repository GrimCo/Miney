package com.ace5852.miney.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandMoney extends CommandBase
{

    @Override
    public String getCommandName()
    {
        return "miney";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/miney";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    @Override
    public void processCommand(ICommandSender cmd, String[] args)
    {
        double currentCurrencyHand = cmd.getEntityWorld().getPlayerEntityByName(cmd.getCommandSenderName()).getEntityData().getDouble("Miney");
        double currentCurrencyBank = cmd.getEntityWorld().getPlayerEntityByName(cmd.getCommandSenderName()).getEntityData().getDouble("MineyBank");
        cmd.addChatMessage(new ChatComponentText("You have " + currentCurrencyHand + " Miney on hand."));
        cmd.addChatMessage(new ChatComponentText("You have " + currentCurrencyBank + " Miney in the bank."));
    }
}
