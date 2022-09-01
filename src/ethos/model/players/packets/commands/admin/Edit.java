package ethos.model.players.packets.commands.admin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.player.PlayerCharacterContext;
import ethos.runehub.entity.player.PlayerSaveData;
import ethos.runehub.entity.player.PlayerSaveDataSerializer;
import org.runehub.api.util.StringUtils;

import java.util.Optional;

/**
 * Empty the inventory of the player.
 *
 * @author Emiel
 */
public class Edit extends Command {

    @Override
    public void execute(Player c, String input) {
        final String[] args = input.split(" ");
        final String targetPlayerName = args[0].replaceAll("_", " ");
        final String targetKey = args[1];
        final Object targetValue = args[2].replaceAll("_", " ");
        Optional<String> nestedKey = Optional.empty();
        if (args.length == 4)
            nestedKey = Optional.of(args[3].replaceAll("_", " "));
        final long targetPlayerId = StringUtils.longFromUUID(StringUtils.stringToUUID(targetPlayerName));
        final PlayerSaveDataSerializer serializer = new PlayerSaveDataSerializer();
        final PlayerSaveData saveData = PlayerCharacterContextDataAccessObject.getInstance().read(targetPlayerId).getPlayerSaveData();
        final PlayerCharacterContext characterContext = PlayerCharacterContextDataAccessObject.getInstance().read(targetPlayerId);
        final String saveDataJson = serializer.serialize(PlayerCharacterContextDataAccessObject.getInstance().read(targetPlayerId).getPlayerSaveData());
        final JsonElement element = new Gson().toJsonTree(saveData, PlayerSaveData.class);
        final JsonObject targetObject = element.getAsJsonObject().get(targetKey).getAsJsonObject();

        System.out.println("Target ID: " + targetPlayerId);
        System.out.println("Save Data: " + saveData);
        System.out.println("Save Data JSON: " + saveDataJson);
        System.out.println("Target Object: " + targetObject);
        System.out.println(element.getAsJsonObject().entrySet());

        if (targetValue instanceof Number) {
            targetObject.addProperty(nestedKey.orElse(targetKey), (Number) targetValue);
        } else if (targetValue instanceof String) {
            targetObject.addProperty(nestedKey.orElse(targetKey), (String) targetValue);
        } else if (targetValue instanceof Boolean) {
            targetObject.addProperty(nestedKey.orElse(targetKey), (Boolean) targetValue);
        }

        System.out.println(element);

        characterContext.setPlayerSaveData(element.toString());
        PlayerCharacterContextDataAccessObject.getInstance().update(characterContext);
    }
}
