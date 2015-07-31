package com.ace5852.miney;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraft.nbt.NBTTagCompound;

public class EventHandler
{
    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
        NBTTagCompound tag = event.player.getEntityData();
        boolean firstLogin = tag.getBoolean("MineyStarting");
        System.out.println(firstLogin);
        if(firstLogin)
        {
            tag.setBoolean("MineyStarting", true);
            tag.setDouble("Miney", Miney.startingMiney);
        }

        if (Miney.enableBank && Miney.intrestRate > 0)
        {
            long lastIntrest = tag.getLong("MineyIntrest");
            if (lastIntrest <= System.currentTimeMillis() - Miney.intrestTime)
            {
                double currBank = tag.getDouble("MineyBank");
                double intrest = currBank * Miney.intrestRate;
                double newBank =  Math.round((currBank + intrest)*100)/100.0;
                tag.setDouble("MineyBank", newBank);
                tag.setLong("MineyIntrest", System.currentTimeMillis());
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        if (Miney.amountLostOnDeath != 0)
        {
            NBTTagCompound tag = event.player.getEntityData();
            double amtToKeep = 1 - Miney.amountLostOnDeath;
            double currHand = tag.getDouble("Miney");
            double newHand = Math.round((currHand * amtToKeep)*100)/100.0;
            tag.setDouble("Miney", newHand);

        }
    }

}
