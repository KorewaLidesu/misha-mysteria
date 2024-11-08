package misha.mishamysteria.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBackpack extends ModelBiped {
	private final ModelRenderer bone;

	public ModelBackpack() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(8.0F, 24.0F, -8.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 15, -12.5F, -24.25F, 5.5F, 9, 8, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -13.25F, -24.0F, 10.0F, 10, 11, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 28, -3.25F, -19.5F, 10.5F, 3, 6, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 28, 0, -16.25F, -19.5F, 10.5F, 3, 6, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}