package com.weever.rotp_mih.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VoidSpiralParticle extends SpriteTexturedParticle {
    private final double centerX, centerY, centerZ;
    private double angle;
    private final double orbitSpeed;
    private final double startRadius;

    protected VoidSpiralParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
        super(world, x, y, z, vx, vy, vz);
        this.centerX = x; this.centerY = y; this.centerZ = z;
        this.angle = world.random.nextDouble() * Math.PI * 2;
        this.orbitSpeed = 0.12 + world.random.nextDouble() * 0.15;
        this.startRadius = 2.0 + world.random.nextDouble() * 3.0;
        this.lifetime = 50 + world.random.nextInt(40);
        this.quadSize = 0.2f + world.random.nextFloat() * 0.3f;
        this.gravity = 0;
        this.rCol = 0.25f; this.gCol = 0.05f; this.bCol = 0.35f;
        this.alpha = 0.9f;
        this.hasPhysics = false;
    }

    @Override
    public void tick() {
        this.xo = this.x; this.yo = this.y; this.zo = this.z;
        if (this.age++ >= this.lifetime) { this.remove(); return; }
        float progress = (float) this.age / this.lifetime;
        this.angle += this.orbitSpeed;
        double radius = this.startRadius * (1.0 - progress);
        this.x = centerX + Math.cos(angle) * radius;
        this.z = centerZ + Math.sin(angle) * radius;
        this.y = centerY + Math.sin(angle * 0.5) * radius * 0.3;
        this.alpha = 0.9f * (1.0f - progress);
        this.quadSize = (0.2f + progress * 0.4f);
    }

    @Override
    public IParticleRenderType getRenderType() { return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT; }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprites;
        public Factory(IAnimatedSprite sprites) { this.sprites = sprites; }
        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
            VoidSpiralParticle p = new VoidSpiralParticle(world, x, y, z, vx, vy, vz);
            p.pickSprite(this.sprites);
            return p;
        }
    }
}
