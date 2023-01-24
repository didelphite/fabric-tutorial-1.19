package net.opossumite.tutorialmod;

import net.fabricmc.api.ModInitializer;
import net.opossumite.tutorialmod.block.ModBlocks;
import net.opossumite.tutorialmod.item.ModItems;
import net.opossumite.tutorialmod.networking.ModMessages;
import net.opossumite.tutorialmod.painting.ModPaintings;
import net.opossumite.tutorialmod.util.ModLootTableModifiers;
import net.opossumite.tutorialmod.villager.ModVillagers;
import net.opossumite.tutorialmod.world.feature.ModConfiguredFeatures;
import net.opossumite.tutorialmod.world.gen.ModOreGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Very important comment
public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModPaintings.registerPaintings();

		ModVillagers.registerVillagers();
		ModVillagers.registerTrades();

		ModOreGeneration.generateOres();
		ModConfiguredFeatures.registerConfiguredFeatures();

		ModLootTableModifiers.modifyLootTables();

		ModMessages.registerC2SPackets();
	}
}
