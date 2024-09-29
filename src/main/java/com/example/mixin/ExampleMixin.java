package com.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
public class ExampleMixin {

	@Inject(method = "tick()V", at = @At("HEAD"), cancellable = true)
	private void cancelBlockPickup(CallbackInfo ci) {
		ci.cancel();
	}
}
