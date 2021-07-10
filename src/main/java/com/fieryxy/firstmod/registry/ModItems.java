package com.fieryxy.firstmod.registry;

import com.fieryxy.firstmod.MyFirstMinecraftMod;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item CHOCOLATE_BAR = new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(5).alwaysEdible().build()));
    public static final Item PLATE = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(MyFirstMinecraftMod.MOD_ID, "chocolate_bar"), CHOCOLATE_BAR);
        Registry.register(Registry.ITEM, new Identifier("first", "plate"), PLATE);
    }

}
