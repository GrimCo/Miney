package com.ace5852.miney;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class DataHandler implements IExtendedEntityProperties
{

    private double mineyHand;
    private double mineyBank;
    private long lastInterest;


    public DataHandler(EntityPlayer Player)
    {
        mineyHand = Miney.startingMiney;
        mineyBank = 0;
        lastInterest = System.currentTimeMillis();
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("Hand", mineyHand);
        tag.setDouble("Bank", mineyBank);
        tag.setLong("LastInterest", lastInterest);
        compound.setTag("Miney", tag);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = compound.getCompoundTag("Miney");
        mineyHand = tag.getDouble("Hand");
        mineyBank = tag.getDouble("Bank");
        lastInterest = tag.getLong("LastInterest");
    }

    @Override
    public void init(Entity entity, World world)
    {

    }


    public double getHand()
    {
        return mineyHand;
    }

    public void setHand(double newHand)
    {
        mineyHand = Math.round(newHand*100.0)/100.0;
    }

    public double getBank()
    {
        return mineyBank;
    }

    public void setBank(double newBank)
    {
        mineyBank = Math.round(newBank*100.0)/100.0;
    }

    public long getLastInterest()
    {
        return lastInterest;
    }

    public void setLastInterest(long newTime)
    {
        lastInterest = newTime;
    }



}
