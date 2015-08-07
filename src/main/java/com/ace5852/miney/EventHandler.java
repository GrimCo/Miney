package com.ace5852.miney;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public enum EventHandler
{
    INSTANCE;

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
        NBTTagCompound tag = event.player.getEntityData();
        boolean firstLogin = !(tag.getBoolean("MineyStarting"));
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
    public void onLivindDeath(LivingDeathEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            if (Miney.amountLostOnDeath != 0)
            {
                EntityPlayer player = (EntityPlayer) event.entity;
                NBTTagCompound tag = player.getEntityData();
                System.out.println(player.getDisplayName());
                double amtToKeep = 1.0 - Miney.amountLostOnDeath;
                System.out.println(amtToKeep);
                double currHand = tag.getDouble("Miney");
                System.out.println(currHand);
                double newHand = Math.round((currHand * amtToKeep)*100)/100.0;
                System.out.println(newHand);
                tag.setDouble("Miney", newHand);

            }
        }
    }


    public static void register()
    {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        FMLCommonHandler.instance().bus().register(INSTANCE);
    }

}
