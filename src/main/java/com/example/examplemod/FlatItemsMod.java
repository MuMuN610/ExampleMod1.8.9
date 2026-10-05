package com.example.examplemod;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.client.event.RenderEntityEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "flatitems", name = "Flat Items", version = "1.0", clientSideOnly = true)
public class FlatItemsMod {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderEntityPre(RenderEntityEvent.Pre event) {
        if (event.entity instanceof EntityItem) {
            RenderManager rm = event.renderer;
            if (rm != null) {
                rm.playerViewX = 0.0F;
            }
        }
    }
}
