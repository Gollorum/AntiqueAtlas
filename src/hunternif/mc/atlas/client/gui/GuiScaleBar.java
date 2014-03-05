package hunternif.mc.atlas.client.gui;

import hunternif.mc.atlas.client.Textures;
import hunternif.mc.atlas.util.AtlasRenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * A scale bar that displays pixel-to-block ratio. To fit into the overall
 * Atlas style it is rendered at half-scale.
 */
public class GuiScaleBar extends GuiComponent {
	public static final int WIDTH = 40, HEIGHT = 24;
	
	/** Pixel-to-block ratio. */
	private double mapScale = 1;
	
	public void setMapScale(double scale) {
		this.mapScale = scale;
	}
	
	/** Returns the background texture depending on the scale. */
	private ResourceLocation getTexture() {
		return mapScale == 0.5f ? Textures.SCALEBAR_2 :
			   mapScale == 1 ? Textures.SCALEBAR_1 :
			   mapScale == 2 ? Textures.SCALEBAR_05 :
			null;
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		ResourceLocation texture = getTexture();
		if (texture == null) return;
		GL11.glPushMatrix();
		GL11.glScaled(0.5, 0.5, 1);
		AtlasRenderHelper.drawFullTexture(texture, getGuiX()*2, getGuiY()*2, WIDTH, HEIGHT);
		if (fontRenderer.getUnicodeFlag()) {
			// If the font is "small", don't downscale it:
			GL11.glPopMatrix();
			fontRenderer.drawString(I18n.getString("gui.antiqueatlas.scalebar"), getGuiX() - 1, getGuiY() - 1, 0x000000);
			//TODO: it doesn't actually fit!
		} else {
			fontRenderer.drawString(I18n.getString("gui.antiqueatlas.scalebar"), getGuiX()*2 + 3, getGuiY()*2 + 3, 0x000000);
			GL11.glPopMatrix();
		}
	}
}