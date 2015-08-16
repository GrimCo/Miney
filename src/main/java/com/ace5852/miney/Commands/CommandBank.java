package com.ace5852.miney.Commands;

import com.ace5852.miney.DataHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

import javax.swing.text.html.parser.Entity;
import javax.xml.crypto.Data;

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
        if (sender instanceof EntityPlayer)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (sender instanceof EntityPlayer)
        {
            if (args.length != 2)
            {
                sender.addChatMessage(new ChatComponentText("Possible Banking Options: "));
                sender.addChatMessage(new ChatComponentText("/bank deposit [amt]"));
                sender.addChatMessage(new ChatComponentText("/bank withdraw [amt]"));
                return;
            }

            EntityPlayer player = (EntityPlayer) sender;
            DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");

            //Try to parse the amount;
            double amt;
            try
            {
                amt = Double.parseDouble(args[1]);
                if (amt < 0)
                {
                    sender.addChatMessage(new ChatComponentText("Amount must be greater than zero."));
                    return;
                }
            }
            catch(Exception e)
            {
                sender.addChatMessage(new ChatComponentText("Invalid Amount"));
                return;
            }

            if (args[0].equals("deposit"))
            {
                double onHand = data.getHand();
                double inBank = data.getBank();
                if (amt > onHand)
                {
                    sender.addChatMessage(new ChatComponentText("You do not have enough money."));
                    return;
                }
                onHand = onHand - amt;
                inBank = inBank + amt;
                data.setHand(onHand);
                data.setBank(inBank);
            }
            else if (args[0].equals("withdraw"))
            {
                double onHand = data.getHand();
                double inBank = data.getBank();
                if (amt > onHand)
                {
                    sender.addChatMessage(new ChatComponentText("You do not have enough money."));
                    return;
                }
                onHand = onHand + amt;
                inBank = inBank - amt;
                data.setHand(onHand);
                data.setBank(inBank);
            }
            else
            {
                sender.addChatMessage(new ChatComponentText("Possible Banking Options: "));
                sender.addChatMessage(new ChatComponentText("/bank deposit [amt]"));
                sender.addChatMessage(new ChatComponentText("/bank withdraw [amt]"));
            }
        }
    }
}
