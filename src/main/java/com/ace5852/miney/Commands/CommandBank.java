package com.ace5852.miney.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class CommandBank extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "bank";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/bank [command] {amt}";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    @Override
    public void processCommand(ICommandSender cmd, String[] args)
    {
        if(args.length != 2)
        {
            cmd.addChatMessage(new ChatComponentText("Possible Banking Options: "));
            cmd.addChatMessage(new ChatComponentText("/bank deposit [amt]"));
            cmd.addChatMessage(new ChatComponentText("/bank withdraw [amt]"));
            return;
        }

        if (args[0].equals("deposit"))
        {
            NBTTagCompound tag = cmd.getEntityWorld().getPlayerEntityByName(cmd.getCommandSenderName()).getEntityData();
            double amt = Double.parseDouble(args[1]);
            double onHand = tag.getDouble("Miney");
            double inBank = tag.getDouble("MineyBank");
            if (amt > onHand)
            {
                cmd.addChatMessage(new ChatComponentText("You do not have enough money."));
                return;
            }
            onHand = Math.round((onHand - amt)*100)/100.0;
            inBank = Math.round((inBank + amt)*100)/100.0;

            tag.setDouble("Miney", onHand);
            tag.setDouble("MineyBank", inBank);
        }
        else if (args[0].equals("withdraw"))
        {
            NBTTagCompound tag = cmd.getEntityWorld().getPlayerEntityByName(cmd.getCommandSenderName()).getEntityData();
            double amt = Double.parseDouble(args[1]);
            double onHand = tag.getDouble("Miney");
            double inBank = tag.getDouble("MineyBank");
            if (amt > inBank)
            {
                cmd.addChatMessage(new ChatComponentText("You do not have enough money in the bank."));
                return;
            }
            onHand = Math.round((onHand + amt)*100)/100.0;
            inBank = Math.round((inBank - amt)*100)/100.0;

            tag.setDouble("Miney", onHand);
            tag.setDouble("MineyBank", inBank);
        }
        else
        {
            cmd.addChatMessage(new ChatComponentText("Possible Banking Options: "));
            cmd.addChatMessage(new ChatComponentText("/bank deposit [amt]"));
            cmd.addChatMessage(new ChatComponentText("/bank withdraw [amt]"));
        }
    }
}
