package blfngl.fallout.melee;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BaseMelee extends Item
{
	public int DAM;
	public int CRITmul;
	public int CRITcha;
	public String name;
	public int CND;
	public int SPD;
	public int itemHp;
	Random rand = new Random();

	public BaseMelee(int id, int damage, int speed, int durability, int Critmul, int Critcha)
	{
		super(id);
		setMaxDamage(durability);
		DAM = damage;
		SPD = speed;
		CRITmul = Critmul;
		CRITcha = Critcha;
		CND = durability;
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		par1ItemStack.damageItem(1, par3EntityLiving);
		return true;
	}

	public int getDamageVsEntity()
	{
		return DAM;
		//if(rand.nextInt(100)+1<=CRITcha)
		//{
		//	return DAM; //Math.round(Math.round(((DAM * CND)*4)/4)*CRITmul);
		//}
		//
		//else
		//{
		//	return DAM; //(Math.round((DAM * CND)*4)/4);
		//}
	}

	public void func_94581_a(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("blfngl" + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
	{
		itemHp = var1.getItemDamage();
		//CND = (var1.getMaxDamage()-var1.getItemDamage())/var1.getMaxDamage();
		var3.add("\u00A74DAM: " + DAM); //+ Math.round((DAM/2 * CND)*4)/4 + " Heart(s)");
		var3.add("\u00A76CND: " + (CND - itemHp));

		if(name !=null)
		{
			var3.add("\u00A7eCrafted By: " + name);
		}

		else
		{
			var3.add("\u00A7eNo owner.");
		}
	}

	public void onUpdate(ItemStack par1ItemStack, World var2, Entity par3Entity, int par4, boolean par5) 
	{
		EntityPlayer player = (EntityPlayer)par3Entity;
		//CND = (par1ItemStack.getMaxDamage()-par1ItemStack.getItemDamage())/par1ItemStack.getMaxDamage();
		if(player.getHeldItem()==par1ItemStack)
		{
			if(SPD==3){player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20*1, 2));}
			if(SPD==2){player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20*1, 1));}
			if(SPD==1){player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20*1, 0));}
			if(SPD == 0){};
			if(SPD==-1){player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20*1, 0));}
			if(SPD==-2){player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20*1, 1));}
			if(SPD==-3){player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20*1, 2));}
		}
	}
}