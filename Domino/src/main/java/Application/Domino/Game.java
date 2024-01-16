package Application.Domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Dice> table;
    private List<Dice> diceList;
    private List<Dice> playersHand;
    private List<Dice> AIHand;
    private GameState state;

    public Game() {
        this.diceList = getFilledDices();
        this.playersHand = getRandDices(diceList, 5);
        this.AIHand = getRandDices(diceList, 5);
        this.state = GameState.NOT_STARTED;
    }
    private void fillSomeDiceList() {
        playersHand = new ArrayList<>();
        playersHand.add(new Dice(3, 6, 0, 0, Direction.UP));
        playersHand.add(new Dice(4, 5, 0, 0, Direction.UP));
        playersHand.add(new Dice(1, 3, 0, 0, Direction.UP));
        playersHand.add(new Dice(2, 2, 0, 0, Direction.UP));
        playersHand.add(new Dice(2, 5, 0, 0, Direction.UP));

        AIHand = new ArrayList<>();
        AIHand.add(new Dice(2, 4, 0, 0, Direction.UP));
        AIHand.add(new Dice(4, 4, 0, 0, Direction.UP));
        AIHand.add(new Dice(3, 5, 0, 0, Direction.UP));
        AIHand.add(new Dice(0, 2, 0, 0, Direction.UP));
        AIHand.add(new Dice(0, 5, 0, 0, Direction.UP));
    }
    public void start() {
        // Определение первого хода
        state = getFirstTurn();

    }
    private void playing() {
        switch (state) {
            case PLAYERS_TURN -> {
                Dice chosenDice = choseDice(getChosenDiceIndex());
                putDice(chosenDice);
            }
            case AI_TURN -> {
                System.out.println();
            }
        }
    }
    private void putDice(Dice dice) {

    }
    private int getHighestDiceIndex(List<Dice> list) {
        int index = 0;
        int maxSum = 0;
        for (Dice d : list) {
            int currSum = d.getFirstValue() + d.getSecondValue();
            maxSum = Math.max(currSum, maxSum);
        }
        for (Dice d : list) {
            int currSum = d.getFirstValue() + d.getSecondValue();
            if (currSum == maxSum) {
                return index;
            }
            index++;
        }
        return index;
    }
    private int getLowestDiceIndex(List<Dice> list) {
        int index = 0;
        int minSum = 13;
        for (Dice d : list) {
            int currSum = d.getFirstValue() + d.getSecondValue();
            minSum = Math.min(currSum, minSum);
        }
        for (Dice d : list) {
            int currSum = d.getFirstValue() + d.getSecondValue();
            if (currSum == minSum) {
                return index;
            }
            index++;
        }
        return index;
    }
    private GameState getFirstTurn() {
        // Проверка на 1:1 или меньшую одинаковую пару или меньшую кость
        List<Dice> sameValuesDicesPlayer = new ArrayList<>();
        for (Dice dice : playersHand) {
            if (dice.getFirstValue() == 1 && dice.getSecondValue() == 1) {
                return GameState.PLAYERS_TURN;
            }
            if (dice.getFirstValue() == dice.getSecondValue()) {
                sameValuesDicesPlayer.add(dice);
            }
        }
        List<Dice> sameValuesDicesAI = new ArrayList<>();
        for (Dice dice : AIHand) {
            if (dice.getFirstValue() == 1 && dice.getSecondValue() == 1) {
                return GameState.AI_TURN;
            }
            if (dice.getFirstValue() == dice.getSecondValue()) {
                sameValuesDicesAI.add(dice);
            }
        }
        //Поиск наименьшей пары
        if (sameValuesDicesPlayer.size() > 0 && sameValuesDicesAI.size() == 0) {
            return GameState.PLAYERS_TURN;
        }else if (sameValuesDicesPlayer.size() == 0 && sameValuesDicesAI.size() > 0) {
            return GameState.AI_TURN;
        }else if (sameValuesDicesPlayer.size() > 0 && sameValuesDicesAI.size() > 0) {
            Dice sameValuePlayer = sameValuesDicesPlayer.get(getLowestDiceIndex(sameValuesDicesPlayer));
            Dice sameValueAI = sameValuesDicesAI.get(getLowestDiceIndex(sameValuesDicesAI));
            if (sameValuePlayer.compareTo(sameValueAI) > 0) {
                return GameState.AI_TURN;
            }else {
                return GameState.PLAYERS_TURN;
            }
        }
        //Поиск наименьшего значения
        Dice playersDice = playersHand.get(getLowestDiceIndex(playersHand));
        Dice AIDice = AIHand.get(getLowestDiceIndex(AIHand));
        if (playersDice.compareTo(AIDice) > 0) {
            return GameState.PLAYERS_TURN;
        }else if (playersDice.compareTo(AIDice) < 0) {
            return GameState.AI_TURN;
        }
        return getRandTurnState();
    }
    private GameState getRandTurnState() {
        Random random = new Random();
        int chose = random.nextInt(0, 2);
        if (chose > 0) {
            return GameState.PLAYERS_TURN;
        }else {
            return GameState.AI_TURN;
        }
    }
    private List<Dice> getFilledDices() {
        List<Dice> list = new ArrayList<>();
        int x = 50;
        int y = 20;
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                if (!isSameValue(i, j, list)) {
                    list.add(new Dice(i, j, x, y, Direction.UP));
                    x += 60;
                }
            }
            x = 50;
            y += 110;
        }
        return list;
    }
    private boolean isSameValue(int firstV, int secondV, List<Dice> list) {
        if (list == null) {
            System.err.println("List is null");
            return false;
        }
        for (Dice d : list) {
            if (d.getFirstValue() == firstV && d.getSecondValue() == secondV
                    || d.getFirstValue() == secondV && d.getSecondValue() == firstV) {
                return true;
            }
        }
        return false;
    }
    private List<Dice> getRandDices(List<Dice> list, int count) {
        List<Dice> finalList = new ArrayList<>(count);
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            int num = rnd.nextInt(0, list.size());
            finalList.add(list.get(num));
            list.remove(num);
        }
        return finalList;
    }
    private <T> void printList(List<T> list) {
        for (T l : list) {
            System.out.print(l.toString() + " ");
        }
    }
}
