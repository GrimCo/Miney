package com.ace5852.miney.Commands;

import com.ace5852.miney.DataHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.util.List;

public class CommandMiney extends CommandBase
{

    @Override
    public String getCommandName()
    {
        return "miney";
    }

    @Override
    public List getCommandAliases()
    {
        List<String> aliases = new LinkedList<String>();
        aliases.add("money");
        return aliases;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/miney";
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
        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");
            sender.addChatMessage(new ChatComponentText("You have " + data.getHand() + " Miney on hand."));
            sender.addChatMessage(new ChatComponentText("You have " + data.getBank() + " Miney in the bank."));
        }
    }
}
