package com.weever.rotp_mih.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HeavenEnergyParticle extends SpriteTexturedParticle {
    protected HeavenEnergyParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
        super(world, x, y, z, vx, vy, vz);
        this.xd = vx * 0.1; this.yd = vy * 0.1 + 0.02; this.zd = vz * 0.1;
        this.lifetime = 20 + world.random.nextInt(15);
        this.quadSize = 0.12f + world.random.nextFloat() * 0.15f;
        this.gravity = -0.01f;
        this.rCol = 1.0f; this.gCol = 0.85f; this.bCol = 0.3f;
        this.alpha = 0.9f;
        this.hasPhysics = false;
    }
    @Override
    public void tick() {
        this.xo = this.x; this.yo = this.y; this.zo = this.z;
        if (this.age++ >= this.lifetime) { this.remove(); return; }
        this.x += this.xd; this.y += this.yd; this.z += this.zd;
        this.alpha = 0.9f * (1.0f - (float) this.age / this.lifetime);
        this.quadSize *= 0.97f;
    }
    @Override
    public IParticleRenderType getRenderType() { return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT; }
    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprites;
        public Factory(IAnimatedSprite sprites) { this.sprites = sprites; }
        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
            HeavenEnergyParticle p = new HeavenEnergyParticle(world, x, y, z, vx, vy, vz);
            p.pickSprite(this.sprites);
            return p;
        }
    }
}
