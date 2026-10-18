package com.weever.rotp_mih.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GalaxyParticle extends SpriteTexturedParticle {

    private final double orbitSpeed;
    private final double centerX, centerY, centerZ;
    private double angle;

    protected GalaxyParticle(ClientWorld world, double x, double y, double z,
                             double vx, double vy, double vz) {
        super(world, x, y, z, vx, vy, vz);
        this.centerX = x;
        this.centerY = y;
        this.centerZ = z;
        this.angle = world.random.nextDouble() * Math.PI * 2;
        this.orbitSpeed = 0.08 + world.random.nextDouble() * 0.12;
        this.lifetime = 40 + world.random.nextInt(30);
        this.quadSize = 0.15f + world.random.nextFloat() * 0.25f;
        this.gravity = 0;
        float colorPick = world.random.nextFloat();
        if (colorPick < 0.4f) {
            this.rCol = 0.6f; this.gCol = 0.3f; this.bCol = 1.0f;
        } else if (colorPick < 0.7f) {
            this.rCol = 0.3f; this.gCol = 0.5f; this.bCol = 1.0f;
        } else {
            this.rCol = 0.9f; this.gCol = 0.9f; this.bCol = 1.0f;
        }
        this.alpha = 0.85f;
    }

    @Override
    public void tick() {
        this.xo = this.x; this.yo = this.y; this.zo = this.z;
        if (this.age++ >= this.lifetime) { this.remove(); return; }
        this.angle += this.orbitSpeed;
        double radius = 1.5 * (1.0 - (double) this.age / this.lifetime);
        this.x = centerX + Math.cos(angle) * radius;
        this.z = centerZ + Math.sin(angle) * radius;
        this.y = centerY + (this.yd * 0.1);
        this.alpha = 0.85f * (1.0f - (float) this.age / this.lifetime);
        this.quadSize *= 0.98f;
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
            GalaxyParticle particle = new GalaxyParticle(world, x, y, z, vx, vy, vz);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}
