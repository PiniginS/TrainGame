public class Game {
    public static void main(String[] args) {
        Train train = new Train(100, true); //первый параметр задает максимальное количество вагонов, второй включает или выключает текстовое описание игры

        int derection = -1;
        int answer = 1;
        while (true) {
            if (!train.checkLight())
                train.turnLight();
            if (derection == -1)
                train.moveLeft();
            else train.moveRight();
            if (!train.checkLight())
                train.turnLight();

            derection = derection * -1;
            for (int i = 0; i < answer; i++) {
                if (derection == -1) train.moveLeft();
                else train.moveRight();
                if (!(i==answer-1)) if (train.checkLight()) train.turnLight();
            }
            if (!train.checkLight()) {
                train.checkAnswer(answer-1);
                return;
            }
            answer++;
        }
    }
}
