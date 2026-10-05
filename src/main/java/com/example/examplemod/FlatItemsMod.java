package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "flatitems", name = "Flat Items", version = "1.0", clientSideOnly = true)
public class FlatItemsMod {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        RenderManager rm = mc.getRenderManager();
        
        // アイテムの標準レンダラーをカスタムレンダラーに差し替える
        rm.entityRenderMap.put(EntityItem.class, new CustomRenderEntityItem(rm, mc.getRenderItem()));
    }

    // 上下視点回転（X軸）を強制固定するカスタムレンダラー
    private static class CustomRenderEntityItem extends RenderEntityItem {
        public CustomRenderEntityItem(RenderManager renderManager, net.minecraft.client.renderer.entity.RenderItem renderItem) {
            super(renderManager, renderItem);
        }

        @Override
        public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {
            // レンダラー実行直前に視点上下角を0.0F（水平固定）に上書き
            if (this.renderManager != null) {
                this.renderManager.playerViewX = 0.0F;
            }
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }
}
