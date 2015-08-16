package com.ace5852.miney;

import com.ace5852.miney.Commands.CommandAddMiney;
import com.ace5852.miney.Commands.CommandBank;
import com.ace5852.miney.Commands.CommandMiney;
import com.ace5852.miney.Commands.CommandPay;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Miney.MODID, version = Miney.VERSION)
public class Miney
{
    public static final String MODID = "miney";
    public static final String VERSION = "0.0.1";


    //Config variables
    public static boolean enableBank;
    public static double amountLostOnDeath;
    public static double intrestRate;
    public static double startingMiney;
    public static int intrestTime;



    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //MinecraftForge.EVENT_BUS.register(new EventHandler());
        EventHandler.register();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void onServerLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandMiney());
        event.registerServerCommand(new CommandAddMiney());
        event.registerServerCommand(new CommandPay());
        event.registerServerCommand(new CommandBank());
    }

}
