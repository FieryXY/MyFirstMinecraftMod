package com.fieryxy.firstmod;

import com.fieryxy.firstmod.entities.CopperGolemEntityRenderer;
import com.fieryxy.firstmod.screens.TableScreen;
import com.fieryxy.firstmod.screens.TableScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class MyFirstMinecraftModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(MyFirstMinecraftMod.COPPER_GOLEM, (context) -> new CopperGolemEntityRenderer(context));
        ScreenRegistry.register(MyFirstMinecraftMod.TABLE_SCREEN_HANDER_TYPE, (ScreenRegistry.Factory<TableScreenHandler, TableScreen>) (handler, inventory, title) -> new TableScreen(handler, inventory, title));
    }
}
