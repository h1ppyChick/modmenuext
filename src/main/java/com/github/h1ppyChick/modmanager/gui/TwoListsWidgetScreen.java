package com.github.h1ppyChick.modmanager.gui;

import com.github.h1ppyChick.modmanager.util.Log;

import io.github.prospector.modmenu.gui.ModListEntry;
import io.github.prospector.modmenu.gui.ModListWidget;
import io.github.prospector.modmenu.gui.ModsScreen;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public abstract class TwoListsWidgetScreen extends Screen {
	// Constants
	private TranslatableText CONFIRM_RESTART_TITLE = new TranslatableText("modmanager.restart.title");
	private TranslatableText CONFIRM_RESTART_MSG = new TranslatableText("modmanager.restart.msg");
	// Instance Variables
	private Log LOG = new Log("TwoListsWidgetScreen");
	protected TwoListsWidget availableMods;
	protected TwoListsWidget selectedMods;
	protected ModListEntry selected;
	protected double scrollPercent = 0;
	protected ModsScreen priorScreen;
	protected ModListWidget availableModList;
	protected ModListWidget selectedModList;
	protected int paneY;
	protected int paneWidth;
	protected int rightPaneX;
	protected Text tooltip;
	protected Text title;
	protected boolean restartRequired = false;
	
	// Constructors
	protected TwoListsWidgetScreen(Text title) {
		super(title);
		this.title = title;
	}
	
	public TwoListsWidgetScreen(Screen previousScreen, String titleId) {
		this(new TranslatableText(titleId));
		this.priorScreen = (ModsScreen) previousScreen;
		availableModList = new ModListWidget(this.client, paneWidth, this.height, paneY + getY1Offset(), this.height + getY2Offset(), 36, "", this.availableModList, priorScreen);
		selectedModList = new ModListWidget(this.client, paneWidth, this.height, paneY + getY1Offset(), this.height + getY2Offset(), 36, "", this.selectedModList, priorScreen);
	}
	
	// Methods
	public ModListEntry getSelectedEntry() {
		return selected;
	}
	
	public void updateSelectedEntry(ModListEntry entry) {
		if (entry != null) {
			this.selected = entry;
		}
	}
	
	public double getScrollPercent() {
		return scrollPercent;
	}
	
	public void updateScrollPercent(double scrollPercent) {
		this.scrollPercent = scrollPercent;
	}
	
	@Override
	public boolean mouseScrolled(double double_1, double double_2, double double_3) {
		if (selectedModList.isMouseOver(double_1, double_2)) {
			return this.selectedModList.mouseScrolled(double_1, double_2, double_3);
		}
		if (availableModList.isMouseOver(double_1, double_2)) {
			return this.availableModList.mouseScrolled(double_1, double_2, double_3);
		}
		return false;
	}
	protected void setTooltip(Text tooltip) {
		this.tooltip = tooltip;
	}
	
	protected void setTooltip(String tooltip) {
		this.tooltip = new LiteralText(tooltip);
	}
	
	protected int getY1Offset()
	{
		return 10;
	}
	
	protected int getY2Offset()
	{
		return -36;
	}
	private void onRestartConfirmed(boolean restart) {
		if (restart) {
			if (this.client.world != null)
			{
				this.client.world.disconnect();
			}
			this.client.disconnect();
			this.client.scheduleStop();
		}
		closeScreen();
	}
	protected void closeScreen()
	{
		this.selectedModList.close();
		this.availableModList.close();
		this.selected = null;
		for(Object obj: priorScreen.children())
		{
			if (obj instanceof ModListWidget)
			{
				LOG.info("Found mod list widget");
				ModListWidget w = (ModListWidget) obj;
				w.reloadFilters();
				w.close();
			}
		}
		this.client.openScreen(priorScreen);
	}
	
	@Override
	public void onClose() {
		if (restartRequired)
		{
			this.client.openScreen(new ConfirmScreen(this::onRestartConfirmed, CONFIRM_RESTART_TITLE, CONFIRM_RESTART_MSG, ScreenTexts.YES, ScreenTexts.NO));
		}
		else
		{
			closeScreen();
		}

	}
	/**
    * Adds a button to this screen.
    * This method should be preferred over {@link Screen#addChild(Element)} since buttons are automatically rendered when added to a screen.
    */
   public <T extends AbstractButtonWidget> T addButton(T button) {
      return super.addButton(button);
   }
   
   /**
    * Adds a child element to this screen.
    * If the child element is an {@link net.minecraft.client.gui.widget.AbstractButtonWidget}, you should use {@link Screen#addButton(AbstractButtonWidget)} instead.
    * 
    * <p>Adding a child element to a screen does not guarantee the widget is rendered or ticked.
    * @see net.minecraft.client.gui.screen.Screen#addButton(AbstractButtonWidget)
    */
   public <T extends Element> T addChild(T child) {
      return super.addChild(child);
   }
   
   public <T extends Element> boolean removeChild(T child) {
      return this.children.remove(child);
   }
}