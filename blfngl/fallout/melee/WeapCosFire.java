package blfngl.fallout.melee;

import javax.swing.text.html.parser.Entity;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import blfngl.fallout.Fallout;

public class WeapCosFire extends ItemSword
{
	protected int damage;
	protected int enchantability;

	public WeapCosFire(int par1){
		super(par1, Fallout.COSFIRE);
		setMaxDamage(400);
		damage = 7; 
		enchantability = 14;
	}
	public void func_94581_a(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("blfngl" + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	public int getDamageVsEntity(Entity par1Entity)
	{
		return damage;
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par1ItemStack.addEnchantment(Enchantment.fireAspect, 3);
	}

	public int getItemEnchantability()
	{
		return enchantability;
	}

}


