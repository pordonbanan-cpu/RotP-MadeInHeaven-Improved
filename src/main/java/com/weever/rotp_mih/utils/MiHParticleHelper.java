package com.weever.rotp_mih.utils;

import com.weever.rotp_mih.init.InitParticles;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MiHParticleHelper {

    public static void spawnGalaxySwirl(World world, Vector3d center, int count, double radius) {
        if (world.isClientSide) {
            for (int i = 0; i < count; i++) {
                double angle = world.random.nextDouble() * Math.PI * 2;
                double r = radius * (0.3 + world.random.nextDouble() * 0.7);
                double x = center.x + Math.cos(angle) * r;
                double z = center.z + Math.sin(angle) * r;
                double y = center.y + (world.random.nextDouble() - 0.5) * 2.0;
                world.addParticle(InitParticles.GALAXY.get(), x, y, z, 0, 0, 0);
            }
        } else if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            for (int i = 0; i < count; i++) {
                double angle = world.random.nextDouble() * Math.PI * 2;
                double r = radius * (0.3 + world.random.nextDouble() * 0.7);
                double x = center.x + Math.cos(angle) * r;
                double z = center.z + Math.sin(angle) * r;
                double y = center.y + (world.random.nextDouble() - 0.5) * 2.0;
                serverWorld.sendParticles(InitParticles.GALAXY.get(), x, y, z, 1, 0, 0, 0, 0);
            }
        }
    }

    public static void spawnWhiteFlash(World world, Vector3d pos) {
        if (world.isClientSide) {
            for (int i = 0; i < 12; i++) {
                world.addParticle(InitParticles.WHITE_FLASH.get(), pos.x, pos.y, pos.z, 0, 0, 0);
            }
        } else if (world instanceof ServerWorld) {
            ((ServerWorld) world).sendParticles(InitParticles.WHITE_FLASH.get(),
                    pos.x, pos.y, pos.z, 15, 0.5, 0.5, 0.5, 0.01);
        }
    }

    public static void spawnBall(World world, IParticleData particle, Vector3d center, int count, double size) {
        if (!(world instanceof ServerWorld)) return;
        ServerWorld sw = (ServerWorld) world;
        for (int i = 0; i < count; i++) {
            double ox = (world.random.nextDouble() - 0.5) * size;
            double oy = (world.random.nextDouble() - 0.5) * size;
            double oz = (world.random.nextDouble() - 0.5) * size;
            sw.sendParticles(particle, center.x + ox, center.y + oy, center.z + oz, 1, 0, 0, 0, 0);
        }
    }
}
