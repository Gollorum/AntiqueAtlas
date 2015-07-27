package hunternif.mc.atlas.ext;

import hunternif.mc.atlas.api.AtlasAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Puts an skull marker to the player's death spot.
 * @author Hunternif
 */
public class DeathWatcher {
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			for (int atlasID : AtlasAPI.getPlayerAtlases(player)) {
				AtlasAPI.getMarkerAPI().putMarker(player.worldObj, true, atlasID, "tomb",
						"gui.antiqueatlas.marker.tomb " + player.getCommandSenderName(),
						(int)player.posX, (int)player.posZ);
			}
		}
	}
}
