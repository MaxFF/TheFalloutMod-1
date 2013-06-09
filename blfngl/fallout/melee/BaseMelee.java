package blfngl.fallout.melee;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BaseMelee extends Item
{
	public int DAM;
	public double CRITmul;
	public int CRITcha;
	public String name;
	public double CND;
	public double SPD;
	public String EF1;
	public int EF1D;
	public String EF2;
	public int EF2D;
	Random rand = new Random();
	
	public BaseMelee(int id, int damage, int speed, int durability, double Critmul, int Critcha)
	{
		super(id);
		this.setMaxDamage(durability);
		DAM = damage;
		SPD = speed;
		CRITmul = Critmul;
		CRITcha = Critcha;
	}
	public BaseMelee(int id, int damage, int speed, int durability, double Critmul, int Critcha, String effect1, int effect1Duration)
	{
		super(id);
		this.setMaxDamage(durability);
		DAM = damage;
		SPD = speed;
		CRITmul = Critmul;
		CRITcha = Critcha;
		EF1=effect1;
		EF1D=effect1Duration;
	}
	public BaseMelee(int id, int damage, int speed, int durability, double Critmul, int Critcha, String effect1, int effect1Duration, String effect2, int effect2Duration)
	{
		super(id);
		this.setMaxDamage(durability);
		DAM = damage;
		SPD = speed;
		CRITmul = Critmul;
		CRITcha = Critcha;
		EF1=effect1;
		EF1D=effect1Duration;
		EF2=effect2;
		EF2D=effect2Duration;
	}
	
	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		par1ItemStack.damageItem(1, par3EntityLiving);
		/*int X;
		int Z;
        par2EntityLiving.addVelocity(par2EntityLiving.posX-par3EntityLiving.posX, 0.1D, par2EntityLiving.posZ-par3EntityLiving.posZ);*/
        return true;
	}
    
    /**
     * Returns the damage against a given entity.
     */
	public int getDamageVsEntity(Entity par1Entity)
	{
		if(EF1=="Knockout" && rand.nextInt(10)<=1)
		{
			par1Entity.addVelocity(0, 0.5D, 0);
			((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 20*EF1D, 0));
			((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*EF1D, 4));
		}
		if(EF2=="Knockout" && rand.nextInt(10)<=1)
		{
			par1Entity.addVelocity(0, 0.5D, 0);
			((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 20*EF2D, 0));
			((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*EF2D, 4));
		}
		if(EF1=="Fire"){par1Entity.setFire(EF1D);}
		if(EF2=="Fire"){par1Entity.setFire(EF2D);}
		
		if((int) (Math.round(((double)DAM * CND)*4)/(double)4) < 1)
		{
			par1Entity.attackEntityFrom(DamageSource.generic, 0);
			return 0;
		}
		else
		{
			if(rand.nextInt(100)+1<=CRITcha)
			{
				return (int)Math.round(Math.round((((double)DAM * CND)*4)/(double)4)+Math.round((((double)DAM * CND)*4)/(double)4)*CRITmul);
			}
			else
			{
				return (int) (Math.round(((double)DAM * CND)*4)/(double)4);
			}
		}
	}
	
	public void func_94581_a(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("blfngl" + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	
	public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
	{
		CND = ((double)var1.getMaxDamage()-(double)var1.getItemDamage())/(double)var1.getMaxDamage();
		var3.add("\u00A74DAM: " + Math.round(((double)DAM/2 * CND)*4)/(double)4 + " Heart(s)");
		var3.add("\u00A79CND: "+Math.round((((double)var1.getMaxDamage()-(double)var1.getItemDamage())/(double)var1.getMaxDamage())*100)+"%");
		var3.add("\u00A79Durability: "+(var1.getMaxDamage()-var1.getItemDamage())+"/"+var1.getMaxDamage());
		if(name !=null)
		{var3.add("\u00A79Crafted By: " + name);}
		else
		{var3.add("\u00A79Not Crafted");}
		if(EF1!=null){var3.add("EFFECTS:");}
		if(EF1=="CritChaB"){var3.add("Bonus Critical Chance");}
		if(EF1=="CritDamB"){var3.add("Bonus Critical Damage");}
		if(EF1=="Knockout"){var3.add("Inflicts KO");}
		if(EF1=="LimbDamB"){var3.add("Bonus Limb Damage");}
		if(EF2=="CritChaB"){var3.add("Bonus Critical Chance");}
		if(EF2=="CritDamB"){var3.add("Bonus Critical Damage");}
		if(EF2=="Knockout"){var3.add("Inflicts KO");}
		if(EF2=="LimbDamB"){var3.add("Bonus Limb Damage");}
		
	}
	
	public void onUpdate(ItemStack par1ItemStack, World var2, Entity par3Entity, int par4, boolean par5) 
	{
		EntityPlayer player = (EntityPlayer)par3Entity;
		CND = ((double)par1ItemStack.getMaxDamage()-(double)par1ItemStack.getItemDamage())/(double)par1ItemStack.getMaxDamage();
		if(player.getHeldItem()==par1ItemStack)
		{
			if(SPD==3){player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20*1, 2));}
			if(SPD==2){player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20*1, 1));}
			if(SPD==1){player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20*1, 0));}
			if(SPD==-1){player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20*1, 0));}
			if(SPD==-2){player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20*1, 1));}
			if(SPD==-3){player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20*1, 2));}
		}
	}
}
