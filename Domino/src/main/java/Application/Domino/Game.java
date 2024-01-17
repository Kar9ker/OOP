package Application.Domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final int ROUNDS = 3;
    private final int STARTER_COUNT_OF_DICES = 5;
    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;
    private List<Dice> table;
    private List<Dice> diceList;
    private List<Dice> playersHand;
    private List<Dice> AIHand;
    private GameState state;
    private int leftValue;
    private int rightValue;
    private int chosenDiceIndex; //For player
    private Direction locationOfChosenDice; // Добавить кость слева или справа
    private Direction AILocation;
    private Dice lastLeftDice;
    private Dice lastRightDice;

    public Game(int SCREEN_HEIGHT, int SCREEN_WIDTH) {
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        this.state = GameState.NOT_STARTED;
    }

    private void fillSomeDiceList() {
        playersHand = new ArrayList<>();
        playersHand.add(new Dice(3, 3, 0, 0, Direction.UP));
        playersHand.add(new Dice(3, 6, 0, 0, Direction.UP));
        playersHand.add(new Dice(1, 3, 0, 0, Direction.UP));
        playersHand.add(new Dice(2, 2, 0, 0, Direction.UP));
        playersHand.add(new Dice(2, 5, 0, 0, Direction.UP));

//        AIHand = new ArrayList<>();
//        AIHand.add(new Dice(2, 4, 0, 0, Direction.UP));
//        AIHand.add(new Dice(4, 4, 0, 0, Direction.UP));
//        AIHand.add(new Dice(3, 5, 0, 0, Direction.UP));
//        AIHand.add(new Dice(0, 2, 0, 0, Direction.UP));
//        AIHand.add(new Dice(0, 5, 0, 0, Direction.UP));
    }

    public void start() {
        int round = 0;
        while (round < ROUNDS && state != GameState.FINISHED) {
            //Для определения первого хода игрока
            leftValue = 7;
            //Начальная раздача
            diceList = getFilledDices();
            playersHand = getRandDices(diceList, STARTER_COUNT_OF_DICES);
            AIHand = getRandDices(diceList, STARTER_COUNT_OF_DICES);
            AILocation = Direction.LEFT;
            table = new ArrayList<>();
            lastLeftDice = new Dice(0, 0, 0, 0, Direction.UP);
            lastRightDice = new Dice(0, 0, 0, 0, Direction.UP);
            chosenDiceIndex = -1;
            // Определение первого хода
            state = getFirstTurn();
//            state = GameState.PLAYERS_TURN;
            //Ход раунда
            round++;
        }
    }

    public void makeTurn() {
        if (isRoundOver()) {
            System.out.println("Раунд тю - тю");
            return;
        }
        switch (state) {
            case PLAYERS_TURN -> {
                Dice chosenDice = playersHand.get(chosenDiceIndex);

                //Если первый ход
                if (leftValue > 6) {
                    if (chosenDice.isV1sameAsV2()) {
                        chosenDice.setX(SCREEN_WIDTH / 2 - Dice.getSMALL_RECT_DIAMETER() / 2);
                        chosenDice.setY(SCREEN_HEIGHT / 2 - Dice.getSMALL_RECT_DIAMETER());
                        chosenDice.setDirection(Direction.UP);
                    } else {
                        chosenDice.setX(SCREEN_WIDTH / 2 - Dice.getSMALL_RECT_DIAMETER());
                        chosenDice.setY(SCREEN_HEIGHT / 2 - Dice.getSMALL_RECT_DIAMETER() / 2);
                        chosenDice.setDirection(Direction.LEFT);
                    }
                    lastRightDice = Dice.copyOf(chosenDice);
                    lastLeftDice = Dice.copyOf(chosenDice);
                    table.add(chosenDice);
                    leftValue = chosenDice.getFirstValue();
                    rightValue = chosenDice.getSecondValue();
                } else {
                    //Не первый ход
                    switch (locationOfChosenDice) {
                        case LEFT -> {
                            if (lastLeftDice.isV1sameAsV2()) {
                                if (chosenDice.getFirstValue() == leftValue) {
                                    chosenDice.setDirection(Direction.RIGHT);
                                    leftValue = chosenDice.getSecondValue();
                                } else if (chosenDice.getSecondValue() == leftValue) {
                                    chosenDice.setDirection(Direction.LEFT);
                                    leftValue = chosenDice.getFirstValue();
                                }
                                chosenDice.setX(lastLeftDice.getX() - 2 * Dice.getSMALL_RECT_DIAMETER() - 5);
                                chosenDice.setY(lastLeftDice.getY() + Dice.getSMALL_RECT_DIAMETER() / 2);
                            } else {
                                if (chosenDice.getFirstValue() != leftValue && chosenDice.getSecondValue() != leftValue) {
                                    return;
                                }
                                if (chosenDice.isV1sameAsV2()) {
                                    chosenDice.setDirection(Direction.UP);
                                    chosenDice.setX(lastLeftDice.getX() - Dice.getSMALL_RECT_DIAMETER() - 5);
                                    chosenDice.setY(lastLeftDice.getY() - Dice.getSMALL_RECT_DIAMETER() / 2);
                                    leftValue = chosenDice.getFirstValue();
                                } else {
                                    if (chosenDice.getFirstValue() == leftValue) {
                                        chosenDice.setDirection(Direction.RIGHT);
                                        leftValue = chosenDice.getSecondValue();
                                    } else if (chosenDice.getSecondValue() == leftValue) {
                                        chosenDice.setDirection(Direction.LEFT);
                                        leftValue = chosenDice.getFirstValue();
                                    }
                                    chosenDice.setX(lastLeftDice.getX() - 2 * Dice.getSMALL_RECT_DIAMETER() - 5);
                                    chosenDice.setY(lastLeftDice.getY());
                                }
                            }
                            table.add(chosenDice);
                            lastLeftDice = Dice.copyOf(chosenDice);
                        }
                        case RIGHT -> {
                            if (lastRightDice.isV1sameAsV2()) {
                                if (chosenDice.getFirstValue() == rightValue) {
                                    chosenDice.setDirection(Direction.LEFT);
                                    rightValue = chosenDice.getSecondValue();
                                } else if (chosenDice.getSecondValue() == rightValue) {
                                    chosenDice.setDirection(Direction.RIGHT);
                                    rightValue = chosenDice.getFirstValue();
                                }
                                chosenDice.setX(lastRightDice.getX() + Dice.getSMALL_RECT_DIAMETER() + 5);
                                chosenDice.setY(lastRightDice.getY() + Dice.getSMALL_RECT_DIAMETER() / 2);
                            } else {
                                if (chosenDice.getFirstValue() != rightValue && chosenDice.getSecondValue() != rightValue) {
                                    return;
                                }
                                if (chosenDice.isV1sameAsV2()) {
                                    chosenDice.setDirection(Direction.UP);
                                    chosenDice.setX(lastRightDice.getX() + 2 * Dice.getSMALL_RECT_DIAMETER() + 5);
                                    chosenDice.setY(lastRightDice.getY() - Dice.getSMALL_RECT_DIAMETER() / 2);
                                    rightValue = chosenDice.getFirstValue();
                                } else {
                                    if (chosenDice.getFirstValue() == rightValue) {
                                        chosenDice.setDirection(Direction.LEFT);
                                        rightValue = chosenDice.getSecondValue();
                                    } else if (chosenDice.getSecondValue() == rightValue) {
                                        chosenDice.setDirection(Direction.RIGHT);
                                        rightValue = chosenDice.getFirstValue();
                                    }
                                    chosenDice.setX(lastRightDice.getX() + 2 * Dice.getSMALL_RECT_DIAMETER() + 5);
                                    chosenDice.setY(lastRightDice.getY());
                                }
                            }
                            table.add(chosenDice);
                            lastRightDice = Dice.copyOf(chosenDice);
                        }
                    }
                }
                //Конец хода
                playersHand.remove(chosenDiceIndex);
                chosenDiceIndex = -1;
                state = GameState.nextTurn(GameState.PLAYERS_TURN);
                makeTurn();
            }
            case AI_TURN -> {
                int AIndex = getIndexForAI();
                Dice chosenDice = AIHand.get(AIndex);

                //Если первый ход
                if (leftValue > 6) {
                    if (chosenDice.isV1sameAsV2()) {
                        chosenDice.setX(SCREEN_WIDTH / 2 - Dice.getSMALL_RECT_DIAMETER() / 2);
                        chosenDice.setY(SCREEN_HEIGHT / 2 - Dice.getSMALL_RECT_DIAMETER());
                        chosenDice.setDirection(Direction.UP);
                    } else {
                        chosenDice.setX(SCREEN_WIDTH / 2 - Dice.getSMALL_RECT_DIAMETER());
                        chosenDice.setY(SCREEN_HEIGHT / 2 - Dice.getSMALL_RECT_DIAMETER() / 2);
                        chosenDice.setDirection(Direction.LEFT);
                    }
                    lastRightDice = Dice.copyOf(chosenDice);
                    lastLeftDice = Dice.copyOf(chosenDice);
                    table.add(chosenDice);
                    leftValue = chosenDice.getFirstValue();
                    rightValue = chosenDice.getSecondValue();
                } else {
                    //Не первый ход
                    switch (AILocation) {
                        case LEFT -> {
                            if (lastLeftDice.isV1sameAsV2()) {
                                if (chosenDice.getFirstValue() == leftValue) {
                                    chosenDice.setDirection(Direction.RIGHT);
                                    leftValue = chosenDice.getSecondValue();
                                } else if (chosenDice.getSecondValue() == leftValue) {
                                    chosenDice.setDirection(Direction.LEFT);
                                    leftValue = chosenDice.getFirstValue();
                                }
                                chosenDice.setX(lastLeftDice.getX() - 2 * Dice.getSMALL_RECT_DIAMETER() - 5);
                                chosenDice.setY(lastLeftDice.getY() + Dice.getSMALL_RECT_DIAMETER() / 2);
                            } else {
                                if (chosenDice.getFirstValue() != leftValue && chosenDice.getSecondValue() != leftValue) {
                                    AILocation = Direction.leftOrRight(AILocation);
                                    makeTurn();
                                    return;
                                }
                                if (chosenDice.isV1sameAsV2()) {
                                    chosenDice.setDirection(Direction.UP);
                                    chosenDice.setX(lastLeftDice.getX() - Dice.getSMALL_RECT_DIAMETER() - 5);
                                    chosenDice.setY(lastLeftDice.getY() - Dice.getSMALL_RECT_DIAMETER() / 2);
                                    leftValue = chosenDice.getFirstValue();
                                } else {
                                    if (chosenDice.getFirstValue() == leftValue) {
                                        chosenDice.setDirection(Direction.RIGHT);
                                        leftValue = chosenDice.getSecondValue();
                                    } else if (chosenDice.getSecondValue() == leftValue) {
                                        chosenDice.setDirection(Direction.LEFT);
                                        leftValue = chosenDice.getFirstValue();
                                    }
                                    chosenDice.setX(lastLeftDice.getX() - 2 * Dice.getSMALL_RECT_DIAMETER() - 5);
                                    chosenDice.setY(lastLeftDice.getY());
                                }
                            }
                            table.add(chosenDice);
                            lastLeftDice = Dice.copyOf(chosenDice);
                        }
                        case RIGHT -> {
                            if (lastRightDice.isV1sameAsV2()) {
                                if (chosenDice.getFirstValue() == rightValue) {
                                    chosenDice.setDirection(Direction.LEFT);
                                    rightValue = chosenDice.getSecondValue();
                                } else if (chosenDice.getSecondValue() == rightValue) {
                                    chosenDice.setDirection(Direction.RIGHT);
                                    rightValue = chosenDice.getFirstValue();
                                }
                                chosenDice.setX(lastRightDice.getX() + Dice.getSMALL_RECT_DIAMETER() + 5);
                                chosenDice.setY(lastRightDice.getY() + Dice.getSMALL_RECT_DIAMETER() / 2);
                            } else {
                                if (chosenDice.getFirstValue() != rightValue && chosenDice.getSecondValue() != rightValue) {
                                    AILocation = Direction.leftOrRight(AILocation);
                                    makeTurn();
                                    return;
                                }
                                if (chosenDice.isV1sameAsV2()) {
                                    chosenDice.setDirection(Direction.UP);
                                    chosenDice.setX(lastRightDice.getX() + 2 * Dice.getSMALL_RECT_DIAMETER() + 5);
                                    chosenDice.setY(lastRightDice.getY() - Dice.getSMALL_RECT_DIAMETER() / 2);
                                    rightValue = chosenDice.getFirstValue();
                                } else {
                                    if (chosenDice.getFirstValue() == rightValue) {
                                        chosenDice.setDirection(Direction.LEFT);
                                        rightValue = chosenDice.getSecondValue();
                                    } else if (chosenDice.getSecondValue() == rightValue) {
                                        chosenDice.setDirection(Direction.RIGHT);
                                        rightValue = chosenDice.getFirstValue();
                                    }
                                    chosenDice.setX(lastRightDice.getX() + 2 * Dice.getSMALL_RECT_DIAMETER() + 5);
                                    chosenDice.setY(lastRightDice.getY());
                                }
                            }
                            table.add(chosenDice);
                            lastRightDice = Dice.copyOf(chosenDice);
                        }
                    }
                }
                //Конец хода
                AIHand.remove(AIndex);
                state = GameState.nextTurn(GameState.AI_TURN);
            }
        }
    }
    private int getIndexForAI() {
        List<Integer> listOfAvailableIndexes = new ArrayList<>();
        int i = 0;
        for (Dice dice : AIHand) {
            int firstValue = dice.getFirstValue();
            int secondValue = dice.getSecondValue();
            if (leftValue > 6) {
                dice.setAvailable(true);
                listOfAvailableIndexes.add(i);
            }else {
                dice.setAvailable(firstValue == leftValue || firstValue == rightValue
                        || secondValue == leftValue || secondValue == rightValue);
                if (dice.isAvailable()) {
                    listOfAvailableIndexes.add(i);
                }
            }
            i++;
        }
        int size = listOfAvailableIndexes.size();
        if (size > 0) {
            Random random = new Random();
            return listOfAvailableIndexes.get(random.nextInt(size));
        }
        return -1;
    }
    private Direction getAILocation() {
        Random random = new Random();
        int rand = random.nextInt(2);
        if (rand > 0) {
            return Direction.LEFT;
        }else {
            return Direction.RIGHT;
        }
    }

    //Есть ли возможность у игроков продолжить
    private boolean isRoundOver() {
        if (playersHand.size() == 0 || AIHand.size() == 0) {
            return true;
        }
        return !isTurnPossible(playersHand) && !isTurnPossible(AIHand);
    }

    private boolean isTurnPossible(List<Dice> list) {
        if (leftValue > 6) {
            return true;
        }
        for (Dice dice : list) {
            int firstValue = dice.getFirstValue();
            int secondValue = dice.getSecondValue();
            if (firstValue == leftValue || firstValue == rightValue
                    || secondValue == leftValue || secondValue == rightValue) {
                return true;
            }
        }
        return false;
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
        } else if (sameValuesDicesPlayer.size() == 0 && sameValuesDicesAI.size() > 0) {
            return GameState.AI_TURN;
        } else if (sameValuesDicesPlayer.size() > 0 && sameValuesDicesAI.size() > 0) {
            Dice sameValuePlayer = sameValuesDicesPlayer.get(getLowestDiceIndex(sameValuesDicesPlayer));
            Dice sameValueAI = sameValuesDicesAI.get(getLowestDiceIndex(sameValuesDicesAI));
            if (sameValuePlayer.compareTo(sameValueAI) > 0) {
                return GameState.AI_TURN;
            } else {
                return GameState.PLAYERS_TURN;
            }
        }
        //Поиск наименьшего значения
        Dice playersDice = playersHand.get(getLowestDiceIndex(playersHand));
        Dice AIDice = AIHand.get(getLowestDiceIndex(AIHand));
        if (playersDice.compareTo(AIDice) > 0) {
            return GameState.PLAYERS_TURN;
        } else if (playersDice.compareTo(AIDice) < 0) {
            return GameState.AI_TURN;
        }
        return getRandTurnState();
    }

    private GameState getRandTurnState() {
        Random random = new Random();
        int chose = random.nextInt(0, 2);
        if (chose > 0) {
            return GameState.PLAYERS_TURN;
        } else {
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

    public void setChosenDiceIndex(int chosenDiceIndex) {
        this.chosenDiceIndex = chosenDiceIndex;
    }

    public int getChosenDiceIndex() {
        return chosenDiceIndex;
    }

    public void setLocationOfChosenDice(Direction locationOfChosenDice) {
        this.locationOfChosenDice = locationOfChosenDice;
    }

    public List<Dice> getTable() {
        return table;
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public List<Dice> getPlayersHand() {
        return playersHand;
    }

    public List<Dice> getAIHand() {
        return AIHand;
    }

    public GameState getState() {
        return state;
    }

    public int getLeftValue() {
        return leftValue;
    }

    public int getRightValue() {
        return rightValue;
    }
}
