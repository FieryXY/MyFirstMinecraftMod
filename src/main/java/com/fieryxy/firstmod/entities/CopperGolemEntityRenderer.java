package com.fieryxy.firstmod.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModels;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CopperGolemEntityRenderer extends MobEntityRenderer<CopperGolemEntity, CopperGolemEntityModel> {


    public CopperGolemEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CopperGolemEntityModel(CopperGolemEntityModel.getTexturedModelData().createModel()), 1.0f);
    }


    @Override
    public Identifier getTexture(CopperGolemEntity entity) {

        return new Identifier("first", "textures/entity/copper_golem/copper_golem.png");
    }


}

