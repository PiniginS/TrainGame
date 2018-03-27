

import java.util.ArrayList;
import java.util.Random;

public class Train {

    private final int CREATE_STAGE = 0;
    private final int CHECK_LIGHT_STAGE = 1;
    private final int TURN_LIGHT_STAGE = 2;
    private final int MOVE_LEFT_STAGE = 3;
    private final int MOVE_RIGHT_STAGE = 4;
    private final int CHECK_ANSWER_STAGE = 5;

    private ArrayList<Boolean> carriages = new ArrayList<Boolean>();
    private int player_position = 0;
    private int total_carriages;
    private int max_carriages;
    private int answer;
    private boolean story_mod = true;

    public Train(int max_carriages, boolean story_mod) {
        this.max_carriages = max_carriages;
        this.story_mod = story_mod;
        createNewTrain();
    }

    private void createNewTrain() {
        getConsoleOutput(CREATE_STAGE);
        Random rnd = new Random();
        total_carriages = rnd.nextInt(max_carriages) + 1;
        for (int i = 0; i < total_carriages; i++) {
            carriages.add(rnd.nextBoolean());
        }
    }

    public boolean checkLight() {
        getConsoleOutput(CHECK_LIGHT_STAGE, carriages.get(player_position));
        return carriages.get(player_position);
    }


    public void turnLight() {
        getConsoleOutput(TURN_LIGHT_STAGE);
        carriages.set(player_position, !carriages.get(player_position));
    }

    public void moveLeft() {
        getConsoleOutput(MOVE_LEFT_STAGE);
        if (--player_position < 0)
            player_position = total_carriages - 1;
    }

    public void moveRight() {
        getConsoleOutput(MOVE_RIGHT_STAGE);
        if (++player_position == total_carriages)
            player_position = 0;
    }

    public boolean checkAnswer(int answer) {
        this.answer = answer;
        if (answer == total_carriages) {
            getConsoleOutput(CHECK_ANSWER_STAGE, true);
            return true;
        } else {
            getConsoleOutput(CHECK_ANSWER_STAGE, false);
            createNewTrain();
            return false;
        }
    }

    private void getConsoleOutput(int stage) {
        getConsoleOutput(stage, false);
    }

    private void getConsoleOutput(int stage, boolean state) {
        if (story_mod)
            switch (stage) {
                case CREATE_STAGE:
                    System.out.println("Роланд проснулся в салоне поезда. Из висящего на стене динамика донесся механический голос Блейна Моно: \"Отгадай мою загадку стрелок и я дарую тебе жизнь. Сколько вагонов в моём составе?\"");
                    break;
                case CHECK_LIGHT_STAGE:
                    if (state) System.out.println("Свет горит");
                    else System.out.println("Свет не горит");
                    break;
                case TURN_LIGHT_STAGE:
                    System.out.println("Щелк...");
                    break;
                case MOVE_LEFT_STAGE:
                    System.out.println("Стрелок пошел влево");
                    break;
                case MOVE_RIGHT_STAGE:
                    System.out.println("Стрелок пошел вправо");
                    break;
                case CHECK_ANSWER_STAGE:
                    System.out.println("Стрелок предположил что " + answer);
                    if (state) {
                        System.out.println("Да! Ты угадал.");
                        System.out.println("Блейн доставил стрелка прямо до Тёмной Башни");
                    } else {
                        System.out.print("Блейн оглушительно засмеялся. Роланд услышал грохот сходящего с рельс поезда. Последний стрелок погиб. ");
                        System.out.println("...Но может это был всего лишь сон?");
                        System.out.println("");
                    }
                    break;
            }
    }
}
