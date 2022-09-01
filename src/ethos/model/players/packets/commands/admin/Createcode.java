package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.TimeUtils;
import ethos.runehub.loot.RewardCode;
import ethos.runehub.loot.RewardCodeController;
import org.runehub.api.util.IDManager;

import java.util.Optional;

public class Createcode extends Command {
    @Override
    public void execute(Player player, String input) {
        final String inputArgs[] = input.split(" ");
        if(inputArgs.length > 0 && inputArgs[0].length() > 1) {
            final String defaultArgs[] = new String[4];


            defaultArgs[0] = inputArgs[0];
            defaultArgs[1] = "7";
            defaultArgs[2] = "85";
            defaultArgs[3] = "1";

            final String userCode = defaultArgs[0];
            final Optional<String> durationArg = Optional.of(defaultArgs[1]);
            final Optional<String> itemIdArg = Optional.of(defaultArgs[2]);
            final Optional<String> amountArg = Optional.of(defaultArgs[3]);

            int duration = 7;
            int itemId = 85;
            int amount = 1;

            duration = Integer.parseInt(durationArg.get());

            itemId = Integer.parseInt(itemIdArg.get());
            amount = Integer.parseInt(amountArg.get());

            RewardCodeController.getInstance().createCode(player, new RewardCode(
                    IDManager.getUUID(),
                    userCode,
                    TimeUtils.getDaysAsMS(duration),
                    System.currentTimeMillis(),
                    itemId,
                    amount
            ));
        } else {
            player.sendMessage("Auto-Generated Code: $" + RewardCodeController.getInstance().generateCode());
        }
    }
}
