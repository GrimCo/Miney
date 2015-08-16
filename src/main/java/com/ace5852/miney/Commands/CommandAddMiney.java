package com.ace5852.miney.Commands;

import com.ace5852.miney.DataHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class CommandAddMiney extends CommandBase
{

    @Override
    public String getCommandName()
    {
        return "addminey";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/addminey [amt] {user}";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        if (!(sender instanceof EntityPlayer))
        {
            return false;
        }
        if (MinecraftServer.getServer().getConfigurationManager().func_152596_g(sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).getGameProfile()))
        {
            return true;
        }
        return false;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (sender instanceof EntityPlayer)
        {
            if (args.length < 1 || args.length > 2)
            {
                sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
                return;
            }

            World world = sender.getEntityWorld();
            EntityPlayer player;

            if (args.length == 1)
            {
                player = (EntityPlayer) sender;
            }
            else
            {
                player = world.getPlayerEntityByName(args[1]);
                if (player == null)
                {
                    sender.addChatMessage(new ChatComponentText("Invalid Player"));
                    return;
                }
            }

            DataHandler data = (DataHandler) player.getExtendedProperties("MineyDataHandler");


            double currentCurrency = data.getHand();
            double toAdd;
            try
            {
                toAdd = Double.parseDouble(args[0]);
            }
            catch (Exception e)
            {
                sender.addChatMessage(new ChatComponentText("Invalid Amount"));
                return;
            }
            double newCurrency = toAdd + currentCurrency;
            data.setHand(newCurrency);

            sender.addChatMessage(new ChatComponentText("Miney Added Successfully"));
        }
    }
}