package com.weever.rotp_mih.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpeedLineParticle extends SpriteTexturedParticle {
    protected SpeedLineParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
        super(world, x, y, z, vx, vy, vz);
        this.xd = vx; this.yd = vy; this.zd = vz;
        this.lifetime = 8 + world.random.nextInt(8);
        this.quadSize = 0.08f + world.random.nextFloat() * 0.1f;
        this.gravity = 0;
        this.rCol = 0.7f; this.gCol = 0.85f; this.bCol = 1.0f;
        this.alpha = 0.8f;
        this.hasPhysics = false;
    }
    @Override
    public void tick() {
        this.xo = this.x; this.yo = this.y; this.zo = this.z;
        if (this.age++ >= this.lifetime) { this.remove(); return; }
        this.x += this.xd; this.y += this.yd; this.z += this.zd;
        this.xd *= 0.92; this.yd *= 0.92; this.zd *= 0.92;
        this.alpha = 0.8f * (1.0f - (float) this.age / this.lifetime);
    }
    @Override
    public IParticleRenderType getRenderType() { return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT; }
    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprites;
        public Factory(IAnimatedSprite sprites) { this.sprites = sprites; }
        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
            SpeedLineParticle p = new SpeedLineParticle(world, x, y, z, vx, vy, vz);
            p.pickSprite(this.sprites);
            return p;
        }
    }
}
