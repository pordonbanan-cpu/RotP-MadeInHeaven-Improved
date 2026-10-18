package com.weever.rotp_mih.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WhiteFlashParticle extends SpriteTexturedParticle {

    protected WhiteFlashParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z, 0, 0, 0);
        this.lifetime = 25;
        this.quadSize = 0.5f;
        this.gravity = 0;
        this.rCol = 1.0f; this.gCol = 1.0f; this.bCol = 1.0f;
        this.alpha = 1.0f;
        this.hasPhysics = false;
    }

    @Override
    public void tick() {
        this.xo = this.x; this.yo = this.y; this.zo = this.z;
        if (this.age++ >= this.lifetime) { this.remove(); return; }
        float progress = (float) this.age / this.lifetime;
        this.quadSize = 0.5f + progress * 8.0f;
        this.alpha = 1.0f - progress;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprites;
        public Factory(IAnimatedSprite sprites) { this.sprites = sprites; }
        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            WhiteFlashParticle particle = new WhiteFlashParticle(world, x, y, z);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}
