package com.weever.rotp_mih.init;

import com.weever.rotp_mih.MadeInHeavenAddon;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MadeInHeavenAddon.MOD_ID);

    public static final RegistryObject<BasicParticleType> GALAXY =
            PARTICLES.register("galaxy", () -> new BasicParticleType(true));

    public static final RegistryObject<BasicParticleType> VOID_SPIRAL =
            PARTICLES.register("void_spiral", () -> new BasicParticleType(true));

    public static final RegistryObject<BasicParticleType> WHITE_FLASH =
            PARTICLES.register("white_flash", () -> new BasicParticleType(true));

    public static final RegistryObject<BasicParticleType> SPEED_LINE =
            PARTICLES.register("speed_line", () -> new BasicParticleType(false));

    public static final RegistryObject<BasicParticleType> HEAVEN_ENERGY =
            PARTICLES.register("heaven_energy", () -> new BasicParticleType(false));
}
