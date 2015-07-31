package com.ace5852.miney.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

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
        if (MinecraftServer.getServer().getConfigurationManager().func_152596_g(sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).getGameProfile()))
        {
            return true;
        }
        return false;
    }

    @Override
    public void processCommand(ICommandSender cmd, String[] args)
    {
        if (args.length > 1 || args.length > 2)
        {
            cmd.addChatMessage(new ChatComponentText(getCommandName()));
            return;
        }

        NBTTagCompound tag;

        if (args.length == 1)
        {
           tag = cmd.getEntityWorld().getPlayerEntityByName(cmd.getCommandSenderName()).getEntityData();
        }
        else
        {
            tag = cmd.getEntityWorld().getPlayerEntityByName(args[1]).getEntityData();
        }


        double currentCurrency = tag.getDouble("Miney");
        double toAdd = Double.parseDouble(args[0]);
        double newCurrency = Math.round((toAdd + currentCurrency)*100)/100.0;
        tag.setDouble("Miney", newCurrency);

       // cmd.getEntityWorld().getPlayerEntityByName(cmd.getCommandSenderName()).writeToNBT(new NBTTagCompound().setDouble("Miney", newCurrency));

        cmd.addChatMessage(new ChatComponentText("You now have " + newCurrency + " Miney "));
    }
}