package com.github.h1ppyChick.modmenuext;

import com.github.h1ppyChick.modmenuext.util.Log;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
/**
 * 
 * @author H1ppyChick
 * @since 08/11/2020
 * 
 * See README.md
 */
public class ModMenuExt implements ModInitializer {
	/***************************************************
	 *              CONSTANTS
	 **************************************************/
	public static final String MOD_ID = "modmenuext";
	public static final String LOAD_JAR_DIR = "loadedJars/";
	public static final String CONFIG_DIR = "config/";
	public static final String MM_PARENT_KEY = "modmenu:parent";
	public static final String NEW_LIST_NAME = "newModList";
	
	public static final TranslatableText TEXT_SUCCESS = new TranslatableText(MOD_ID + ".success");
	public static final TranslatableText TEXT_ERROR = new TranslatableText(MOD_ID + ".error");
	public static final TranslatableText TEXT_WARNING = new TranslatableText(MOD_ID + ".warning");
	public static final TranslatableText TEXT_RESTART = new TranslatableText(MOD_ID + ".restart");
	public static final TranslatableText TEXT_NOT_IMPL = new TranslatableText(MOD_ID + ".notimpl");
	public static final TranslatableText TEXT_SAVE_TOOLTIP = new TranslatableText(ModMenuExt.MOD_ID + ".save.tooltip");
	public static final TranslatableText TEXT_ADD_SUCCESS = new TranslatableText(ModMenuExt.MOD_ID + ".add.success");
	public static final TranslatableText TEXT_ADD_ERROR = new TranslatableText(ModMenuExt.MOD_ID + ".add.error");
	public static final TranslatableText TEXT_SAVE_SUCCESS = new TranslatableText(ModMenuExt.MOD_ID + ".save.success");
	public static final TranslatableText TEXT_SAVE_ERROR = new TranslatableText(ModMenuExt.MOD_ID + ".save.error");
	public static final TranslatableText TEXT_ADD_TOOLTIP = new TranslatableText(ModMenuExt.MOD_ID + ".add.tooltip");

	public static final Identifier SAVE_BUTTON_LOCATION = new Identifier(ModMenuExt.MOD_ID, "save.png");
	public static final Identifier ADD_BUTTON_LOCATION = new Identifier(ModMenuExt.MOD_ID, "add.png");
	/***************************************************
	 *              INSTANCE VARIABLES
	 **************************************************/
	private static Log LOG = new Log("ModMenuExt");
	@Override
	public void onInitialize() {
		LOG.enter("onInitialize");
		// No mod setup currently
		LOG.exit("onInitialize");
	}
}
