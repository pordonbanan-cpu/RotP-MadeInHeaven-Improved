package com.weever.rotp_mih.action.stand;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

/** Knife Throw using official RotP knives (jojo:knife). */
public class KnifeThrow {

    public static final ResourceLocation ROTP_KNIFE_ID = new ResourceLocation("jojo", "knife");
    public static final int BASE_COUNT = 4;
    public static final int ACCEL_COUNT = 12;

    public static void perform(LivingEntity thrower, LivingEntity user, boolean accelerated) {
        World world = thrower.level;
        if (world.isClientSide) return;

        Item knifeItem = ForgeRegistries.ITEMS.getValue(ROTP_KNIFE_ID);
        if (knifeItem == null) return;

        int count = accelerated ? ACCEL_COUNT : BASE_COUNT;

        for (int i = 0; i < count; i++) {
            ItemStack knifeStack = new ItemStack(knifeItem, 1);
            if (user instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) user;
                knifeStack.use(world, player, player.getUsedItemHand());
            }
        }

        world.playSound(null, thrower.getX(), thrower.getY(), thrower.getZ(),
                SoundEvents.TRIDENT_THROW, SoundCategory.PLAYERS,
                1.0f, accelerated ? 1.35f : 1.0f);
    }

    public static void perform(LivingEntity standOrUser, boolean accelerated) {
        perform(standOrUser, standOrUser, accelerated);
    }
}
