package com.weever.rotp_mih.client;

import com.weever.rotp_mih.MadeInHeavenAddon;
import com.weever.rotp_mih.client.particle.*;
import com.weever.rotp_mih.init.InitParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MadeInHeavenAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {

    @SubscribeEvent
    public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event) {
        Minecraft mc = Minecraft.getInstance();
        mc.particleEngine.register(InitParticles.GALAXY.get(), GalaxyParticle.Factory::new);
        mc.particleEngine.register(InitParticles.WHITE_FLASH.get(), WhiteFlashParticle.Factory::new);
        mc.particleEngine.register(InitParticles.VOID_SPIRAL.get(), VoidSpiralParticle.Factory::new);
        mc.particleEngine.register(InitParticles.SPEED_LINE.get(), SpeedLineParticle.Factory::new);
        mc.particleEngine.register(InitParticles.HEAVEN_ENERGY.get(), HeavenEnergyParticle.Factory::new);
    }
}
