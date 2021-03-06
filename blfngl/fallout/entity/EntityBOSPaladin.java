package blfngl.fallout.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import blfngl.fallout.Fallout;

public class EntityBOSPaladin extends EntityMob
{
	private static final int HeldItem = Fallout.SatHeatFist.itemID;

	public EntityBOSPaladin (World world)
	{
		super (world);
		texture = "/blfngl/fallout/textures/T45Power_1.png";
		moveSpeed = 0.5F;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
		this.tasks.addTask(2, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
		this.tasks.addTask(3, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 25.0F, 0, true));
	}
	
	@Override
    public void initCreature()
    {
    	super.initCreature();
    	this.setCurrentItemOrArmor(0, new ItemStack(Item.swordDiamond));    	
    	
        for (int i = 0; i < this.equipmentDropChances.length; ++i)
        {
            this.equipmentDropChances[i] = 0F;
        }
    }

	public boolean isAIEnabled()
	{
		return true;
	}
	
	public void setAttackTarget(EntityLiving par1EntityLiving)
	{
		super.setAttackTarget(par1EntityLiving);
	}

	public int getMaxHealth()
	{
		return 100;
	}
	
	protected String getHurtSound()
	{
		return "mob.zombiehurt";
	}
	
	protected String getDeathSound()
	{
		return "mob.zombiedeath";
	}

	public int getAttackStrength(Entity par1Entity)
	{
		return 7;
	}

	protected int getDropItemId()
	{
		return Fallout.ingotTungsten.itemID;
	}

	protected void dropRareDrop(int par1)
	{
		switch (this.rand.nextInt(3))
		{
		case 0:
			//this.dropItem(FalloutMain.LaserRifle.itemID, 1);
			break;

		case 1:
			this.dropItem(Fallout.cellMF.itemID, 3);
			break;

		case 2:
			//this.dropItem(FalloutMain.Incinerator.itemID, 1);
		}
	}
} 