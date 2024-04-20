package utilities;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;

        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }
    public static class PlayerConstants {
        public static final int IDLE_DOWN = 0;
        public static final int IDLE_RIGHT = 1;
        public static final int IDLE_UP = 2;
        public static final int RUNNING_DOWN = 3;
        public static final int RUNNING_RIGHT = 4;
        public static final int RUNNING_UP = 5;
        public static final int FIGHT_DOWN = 6;
        public static final int FIGHT_RIGHT = 7;
        public static final int FIGHT_UP = 8;
        public static final int DIE = 9;


        public static int GetSpriteAmount(int player_action){
            return switch (player_action) {
                case IDLE_DOWN, IDLE_RIGHT, IDLE_UP, RUNNING_DOWN, RUNNING_RIGHT, RUNNING_UP -> 6;
                case FIGHT_DOWN, FIGHT_RIGHT, FIGHT_UP, DIE -> 4;
                default -> 1;
            };
        }
    }
    public static class CrystalConstants {
        public static final int IDLE = 0;
        public static final int TAKE_DAMAGE = 1;
        public static final int BREAK = 3;
    }
}
