package com.ace5852.miney.Commands;

import com.ace5852.miney.DataHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

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
                sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }

            World world = sender.getEntityWorld();
            EntityPlayer otherPlayer;
            EntityPlayer player;

            otherPlayer = world.getPlayerEntityByName(args[0]);
            if (otherPlayer == null)
            {
                sender.addChatMessage(new ChatComponentText("Invalid Player"));
                return;
            }
            player = (EntityPlayer) sender;


            DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");
            DataHandler dataOther = (DataHandler) player.getExtendedProperties("MineyDataHandler");

            double currentCurrency = data.getHand();
            double currentCurrencyOther = dataOther.getHand();
            double toPay;
            try
            {
                toPay = Double.parseDouble(args[1]);
            }
            catch(Exception e)
            {
                sender.addChatMessage(new ChatComponentText("Invalid Amount"));
                return;
            }

            if (toPay >= 0)
            {
                sender.addChatMessage(new ChatComponentText("You must send an amount greater than 0."));
            }
            double newCurrency = currentCurrency - toPay;
            double newCurrencyOther = currentCurrencyOther + toPay;

            if (newCurrency < 0.0)
            {
                sender.addChatMessage(new ChatComponentText("You do not have enough Miney for that."));
            }
            else
            {
                data.setHand(newCurrency);
                dataOther.setHand(newCurrencyOther);
                sender.addChatMessage(new ChatComponentText("You have sent " + toPay + " Miney "));
            }
        }
    }
}