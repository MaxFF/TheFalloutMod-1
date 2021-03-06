package blfngl.fallout.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import blfngl.fallout.Fallout;

public class ArmorBaseHellfire extends ItemArmor implements IArmorTextureProvider
{

	public ArmorBaseHellfire(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
	}

	public void func_94581_a(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("blfngl" + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	public String getArmorTextureFile(ItemStack par1)
	{
		if ( par1.itemID==Fallout.HellHelm.itemID|| par1.itemID==Fallout.HellChest.itemID|| par1.itemID==Fallout.HellBoots.itemID)
		{
			return "/blfngl/fallout/textures/ADV2_1.png";
		}
		if(par1.itemID==Fallout.HellLegs.itemID)
		{
			return "/blfngl/fallout/textures/ADV2_2.png";
		}
		return "/blfngl/fallout/textures/ADV2_1.png";
	}      
}