package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.GameFrame;
import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public class Player extends DynamicObject {
    private static final int stepSize = 1;

    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE);
    }

    public void move(Direction direction) {
        super.move(direction, stepSize);
        if (GameMaster.getInstance().getCoins().stream()
                .anyMatch(coin -> this.collision(coin))) {
            GameMaster.getInstance().deleteCoins(this.xPosition, this.yPosition);
        }
        if (GameMaster.getInstance().getCoins().size() == 0) {
            GameMaster.getInstance().isCoinsCollected = true;
            GameMaster.getInstance().temp_exit_character = '1';
        }
        if (GameMaster.getInstance().getExit().stream()
                .anyMatch(exit -> this.collision(exit))) {
            GameMaster.getInstance().isVictory = true;
        }
    }

    @Override
    public String toString() {
        return "Player{[" + xPosition + ":" + yPosition + "]}";
    }
}
