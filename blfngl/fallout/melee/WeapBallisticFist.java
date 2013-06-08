package blfngl.fallout.melee;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class WeapBallisticFist extends ItemSword
{
	public WeapBallisticFist(int itemID, EnumToolMaterial toolMaterial)
	{
		super(itemID, toolMaterial);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		par2EntityLiving.playSound("random.explode", 2.0F, 1.0F);
		par1ItemStack.damageItem(1, par3EntityLiving);
		par2EntityLiving.spawnExplosionParticle();
		par2EntityLiving.spawnExplosionParticle();
		par2EntityLiving.spawnExplosionParticle();
		par2EntityLiving.spawnExplosionParticle();
		par2EntityLiving.spawnExplosionParticle();
		par2EntityLiving.spawnExplosionParticle();
		return true;
	}

	public void func_94581_a(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("blfngl" + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
}
