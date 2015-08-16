package com.ace5852.miney;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public enum EventHandler
{
    INSTANCE;

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event)
    {
        if (Miney.enableBank && Miney.intrestRate > 0)
        {
            EntityPlayer player = event.player;
            DataHandler playerData = (DataHandler) player.getExtendedProperties("MineyDataHandler");
            long lastIntrest = playerData.getLastInterest();
            if (lastIntrest <= System.currentTimeMillis() - Miney.intrestTime)
            {
                double currBank = playerData.getBank();
                double interest = currBank * Miney.intrestRate;
                double newBank =  currBank + interest;
                playerData.setBank(newBank);
                playerData.setLastInterest(System.currentTimeMillis());
            }
        }
    }


    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            if (Miney.amountLostOnDeath != 0)
            {
                EntityPlayer player = (EntityPlayer) event.entity;
                DataHandler playerData = (DataHandler) player.getExtendedProperties("MineyDataHandler");
                double amtToKeep = 1.0 - Miney.amountLostOnDeath;
                double currHand = playerData.getHand();
                double newHand = currHand * amtToKeep;
                playerData.setHand(newHand);
            }
        }
    }


    @SubscribeEvent
    public void onEntityConstruction(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            if(event.entity.getExtendedProperties("MineyDataHandler") == null)
            {
                event.entity.registerExtendedProperties("MineyDataHandler", new DataHandler((EntityPlayer)event.entity));
            }
        }
    }


    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        NBTTagCompound tag = new NBTTagCompound();
        event.original.getExtendedProperties("MineyDataHandler").saveNBTData(tag);
        event.entityPlayer.getExtendedProperties("MineyDataHandler").loadNBTData(tag);
    }



    public static void register()
    {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        FMLCommonHandler.instance().bus().register(INSTANCE);
    }

}
