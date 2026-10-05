package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "flatitems", name = "Flat Items", version = "0.2", clientSideOnly = true)
public class FlatItemsMod {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        RenderManager rm = mc.getRenderManager();
        
        rm.entityRenderMap.put(EntityItem.class, new FixedRenderEntityItem(rm, mc.getRenderItem()));
    }

    private static class FixedRenderEntityItem extends RenderEntityItem {
        public FixedRenderEntityItem(RenderManager renderManager, net.minecraft.client.renderer.entity.RenderItem renderItem) {
            super(renderManager, renderItem);
        }

        @Override
        public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {
            if (this.renderManager == null) {
                super.doRender(entity, x, y, z, entityYaw, partialTicks);
                return;
            }

            // 元の playerViewX を退避
            float originalPitch = this.renderManager.playerViewX;

            try {
                // 描画計算中に参照されるカメラ上下角を一時的に0（水平）に偽装する
                this.renderManager.playerViewX = 0.0F;
                
                super.doRender(entity, x, y, z, entityYaw, partialTicks);
            } finally {
                // 描画が終わったら他の描画処理に影響が出ないよう元に戻す
                this.renderManager.playerViewX = originalPitch;
            }
        }
    }
}
