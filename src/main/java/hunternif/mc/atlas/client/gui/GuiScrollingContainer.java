package hunternif.mc.atlas.client.gui;

import hunternif.mc.atlas.client.Textures;

public class GuiScrollingContainer extends GuiComponent {
	protected final GuiViewport viewport;
	protected final GuiHScrollbar scrollbarHor;
	protected final GuiVScrollbar scrollbarVer;
	
	public GuiScrollingContainer() {
		viewport = new GuiViewport();
		scrollbarHor = new GuiHScrollbar(viewport);
		scrollbarHor.setTexture(Textures.SCROLLBAR_HOR, 8, 7, 2);
		scrollbarVer = new GuiVScrollbar(viewport);
		scrollbarVer.setTexture(Textures.SCROLLBAR_VER, 7, 8, 2);
		setWheelScrollsVertially();
		this.addChild(viewport);
		this.addChild(scrollbarHor);
		this.addChild(scrollbarVer);
	}
	
	/** Add scrolling content. Use removeContent to remove it.
	 * @return the child added */
	public GuiComponent addContent(GuiComponent child) {
		return viewport.addContent(child);
	}
	/** @return the child removed */
	public GuiComponent removeContent(GuiComponent child) {
		return viewport.removeContent(child);
	}
	public void removeAllContent() {
		viewport.removeAllContent();
	}
	
	public void setViewportSize(int width, int height) {
		viewport.setSize(width, height);
		scrollbarHor.setRelativeCoords(0, height);
		scrollbarHor.setSize(width, scrollbarHor.getHeight());
		scrollbarVer.setRelativeCoords(width, 0);
		scrollbarVer.setSize(scrollbarVer.getWidth(), height);
	}
	
	@Override
	protected void validateSize() {
		super.validateSize();
		scrollbarHor.updateContent();
		scrollbarVer.updateContent();
	}
	
	/** Mouse wheel will affect <b>horizontal</b> scrolling and not vertical.
	 * This is the default behavior.*/
	public void setWheelScrollsHorizontally() {
		scrollbarHor.setUsesWheel(true);
		scrollbarVer.setUsesWheel(false);
	}
	/** Mouse wheel will affect <b>vertical</b> scrolling and not horizontal. */
	public void setWheelScrollsVertially() {
		scrollbarHor.setUsesWheel(false);
		scrollbarVer.setUsesWheel(true);
	}
	
	/** Scroll to the specified point relative to the content's top left corner.
	 * The container attempts to place the specified point at the top left
	 * corner of the viewport as well. */
	public void scrollTo(int x, int y) {
		scrollbarHor.setScrollPos(x);
		scrollbarVer.setScrollPos(y);
	}
}