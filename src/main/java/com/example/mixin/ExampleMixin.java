package com.example.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntity.class)
public class ExampleMixin {

	@Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
	private void restrictBlockPickup(CallbackInfo ci) {
		EndermanEntity enderman = (EndermanEntity) (Object) this;
		World world = enderman.getWorld();
		Random random = world.getRandom();
		int i = MathHelper.floor(enderman.getX() - 2.0 + random.nextDouble() * 4.0);
		int j = MathHelper.floor(enderman.getY() + random.nextDouble() * 3.0);
		int k = MathHelper.floor(enderman.getZ() - 2.0 + random.nextDouble() * 4.0);
		BlockPos blockPos = new BlockPos(i, j, k);
		BlockState blockState = world.getBlockState(blockPos);
		if (blockState.isOf(Blocks.STRUCTURE_BLOCK)) {
			enderman.setCarriedBlock(blockState);
			world.removeBlock(blockPos, false);
		} else {
			ci.cancel();
		}
	}
}