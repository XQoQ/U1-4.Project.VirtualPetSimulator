import java.lang.Math;

/**
* The virtualPet Class represents a VirtualPet. A VirtualPet is an animal with a name, statuses including hunger, thirst
* money, healthiness, mood, and sickness that can perform actions of various choice
 */
public class VirtualPet {
    private int hunger;
    private int thirst;
    private double money;
    private int healthiness;
    private int mood;
    private String curtAction;
    private String actionChoice;
    private boolean sick;
    private String name;

    /**
     * The first constructor for the VirtualPet Class. It creates a new instance of a VirtualPet given the below parameter
     *
     * @param name represents the name of the VirtualPet
     */
    public VirtualPet(String name) {
        this.hunger = 100;
        this.thirst = 100;
        this.money = 0;
        this.healthiness = 100;
        this.mood = 100;
        this.curtAction = "";
        this.actionChoice = "";
        this.sick = false;
        this.name = name;
    }

    /**
     * The second constructor for the VirtualPet Class. It creates a new instance of a VirtualPEt with an initial asset
     * of money given the below parameters
     *
     * @param name represents the name of the VirtualPet
     * @param money represents the initial asset of money of the VirtualPet
     */
    public VirtualPet(String name, double money) {
        this.hunger = 100;
        this.thirst = 100;
        this.money = money;
        this.healthiness = 100;
        this.mood = 100;
        this.curtAction = "";
        this.actionChoice = "";
        this.sick = true;
        this.name = name;
    }

    /**
     * The setCurtAction method will set the current action of the VirtualPet given an action
     *
     * @param action represents the action that the VirtualPet will perform
     */
    public void setCurtAction(String action) {
        this.curtAction = action;
    }

    /**
     * The setActionChoice method will set the action choice of the VirtualPet given a choice
     *
     * @param choice represents the choice of the action that the VirtualPet will perform
     */
    public void setActionChoice(String choice) {
        this.actionChoice = choice;
        updateStatus();
    }

    /**
     * The getName method will return a String which represents the name of the VirtualPet
     *
     * @return returns a String representing the name of the VirtualPet
     */
    public String getName() {
        return name;
    }

    /**
     * The isMoneyEnough method will check if the VirtualPet has enough money to purchase a desired item
     *
     * @return returns a boolean value representing the feasibility of the VirtualPet purchasing a desired item
     */
    private boolean isMoneyEnough() {
        boolean result = false;
        if (curtAction.equalsIgnoreCase("eat")) {
            if (actionChoice.equalsIgnoreCase("pizza") && money >= 15) {
                result = true;
            } else if (actionChoice.equalsIgnoreCase("congee") && money >= 3) {
                result = true;
            } else if (actionChoice.equalsIgnoreCase("ibuprofen") && money >= 60) {
                result = true;
            }
        } else if (curtAction.equalsIgnoreCase("drink")) {
            if (actionChoice.equalsIgnoreCase("water") && money >= 1.5) {
                result = true;
            } else if (actionChoice.equalsIgnoreCase("bubble tea") && money >= 8) {
                result = true;
            }
        }


        return result;
    }

    /**
     * The isSick method will determine if the VirtualPet is sick
     *
     * @return returns a boolean value representing the state of sickness of the VirtualPet
     */
    public boolean isSick() {
        if (healthiness <= 30) {
            int randint = (int) (Math.random() * 4) + 1;
            if (randint == 1) {
                sick = true;
            }
        }


        return sick;
    }

    /**
     * The getMedMessage method will return a String in formatted sentence showing the message after the VirtualPet has
     * done a medical-related action
     *
     * @return returns a String in a properly formatted sentence telling the message after a medical-related action is performed
     */
    public String getMedMessage() {
        String message = "";
        if (sick) {
            message = "You didn't buy the medicine for " + name;
        } else {
            message = name + " feels much better now";
        }


        return message;
    }

    /**
     * The getChoiceInfo method will return a String in a formatted sentence containing all the information about the
     * choices of the action that the VirtualPet will perform
     *
     * @return returns a String in a properly formatted sentence showing all the information about the choices of an action
     */
    public String getChoiceInfo() {
        String info = "";
        if (curtAction.equalsIgnoreCase("eat")) {
            info = "Pizza: -$15, +25 hunger, -10 thirst, -1 healthiness, +15 mood\nCongee: -$3, +30 hunger, +20 thirst, " +
                    "+1 healthiness, +2 mood\nibuprofen: -$60, +60 healthiness";
        } else if (curtAction.equalsIgnoreCase("drink")) {
            info = "Water: -$1.5, +40 thirst, +5 healthiness, +5 mood\nBubble Tea: -$8, +30 thirst, -5 healthiness, " +
                    "+25 mood";
        } else if (curtAction.equalsIgnoreCase("work")) {
            info = "Lay Bricks: +$50, -40 hunger, -50 thirst, -20 healthiness, -40 mood\nMcDonald: +$35, -20 hunger, -30 thirst, -10 healthiness, -20 mood";
        } else if (curtAction.equalsIgnoreCase("end")) {
            info = name + ": I will miss you, master";
        } else {
            info = "Invalid input";
        }


        return info;
    }

    /**
     * The getMessage method will return a String in a formatted sentence showing a message in formatted sentence about
     * a choice of action performed by the VirtualPet. The method will also return additional message in formatted sentence
     * telling about the mood of the VirtualPet when certain condition is met
     *
     * @return returns a String in a properly formatted sentence showing the message about the performed choice of action
     * and possibly about the mood of the VirtualPet
     */
    public String getMessage() {
        String message = "";
        String choiceList = "pizza,congee,water,bubble tea,ibuprofen";

        if (curtAction.equalsIgnoreCase("eat")) {
            if (actionChoice.equalsIgnoreCase("pizza") && isMoneyEnough()) {
                message = name + " ate a pizza";
            } else if (actionChoice.equalsIgnoreCase("congee") && isMoneyEnough()) {
                message = name + " had some congee";
            } else if (actionChoice.equalsIgnoreCase("ibuprofen") && isMoneyEnough()) {
                message = "You bought some ibuprofen for " + name;
            } else if (!choiceList.contains(actionChoice)) {
                message = "You didn't buy anything for " + name;
            } else {
                message = VirtualPet.ANSI_RED + "You don't have enough money to buy " + ANSI_YELLOW + actionChoice + ANSI_RED + " for " + ANSI_CYAN + name + ANSI_RESET;
            }
        } else if (curtAction.equalsIgnoreCase("drink")) {
            if (actionChoice.equalsIgnoreCase("water") && isMoneyEnough()) {
                message = name + " drank some water";
            } else if (actionChoice.equalsIgnoreCase("bubble tea") && isMoneyEnough()) {
                message = name + " loved bubble tea!";
            } else if (!choiceList.contains(actionChoice)) {
                message = "You didn't buy anything for " + name;
            } else {
                message = VirtualPet.ANSI_RED + "You don't have enough money to buy " + ANSI_YELLOW + actionChoice + ANSI_RED + " for " + ANSI_CYAN + name + ANSI_RESET;
            }
        } else if (curtAction.equalsIgnoreCase("work")) {
            if (actionChoice.equalsIgnoreCase("lay bricks")) {
                message = name + " loves laying bricks QAQ";
            } else if (actionChoice.equalsIgnoreCase("McDonald")) {
                message = "McDonald pays terrible :(";
            } else {
                message = "You didn't do anything";
            }
        } else if (curtAction.equalsIgnoreCase("end")) {
            message = "Bye bye";
        } else {
            message = "You didn't do anything with " + name;
        }


        if (mood <= 30) {
            message += "\n" + name + " doesn't look happy";
        }


        return message;
    }

    /**
     * The updateStatus method will update instance variables of the VirtualPet according to the choice of action being
     * performed
     */
    private void updateStatus() {
        if (curtAction.equalsIgnoreCase("eat")) {
            if (actionChoice.equalsIgnoreCase("pizza") && isMoneyEnough()) {
                hunger += 25;
                thirst -= 10;
                money -= 15;
                healthiness -= 1;
                mood += 15;
            } else if (actionChoice.equalsIgnoreCase("congee") && isMoneyEnough()) {
                hunger += 30;
                thirst += 20;
                money -= 3;
                healthiness += 1;
                mood += 2;
            } else if (actionChoice.equalsIgnoreCase("ibuprofen") && isMoneyEnough()) {
                money -= 60;
                healthiness += 60;
                sick = false;
            }
        } else if (curtAction.equalsIgnoreCase("drink")) {
            if (actionChoice.equalsIgnoreCase("water") && isMoneyEnough()) {
                thirst += 40;
                money -= 1.5;
                healthiness += 5;
                mood += 5;
            } else if (actionChoice.equalsIgnoreCase("bubble tea") && isMoneyEnough()) {
                thirst += 30;
                money -= 8;
                healthiness -= 5;
                mood += 25;
            }
        } else if (curtAction.equalsIgnoreCase("work")) {
            if (actionChoice.equalsIgnoreCase("Lay Bricks")) {
                hunger -= 40;
                thirst -= 50;
                money += 80;
                healthiness -= 20;
                mood -= 40;
            } else if (actionChoice.equalsIgnoreCase("McDonald")) {
                hunger -= 20;
                thirst -= 30;
                money += 35;
                healthiness -= 10;
                mood -= 20;
            }
        }


        if (hunger >= 100) {
            hunger = 100;
        } else if (hunger <= 0) {
            hunger = 0;
        }
        if (thirst >= 100) {
            thirst = 100;
        } else if (thirst <= 0) {
            thirst = 0;
        }
        if (healthiness >= 100) {
            healthiness = 100;
        } else if (healthiness <= 0) {
            healthiness = 0;
        }
        if (mood >= 100) {
            mood = 100;
        } else if (mood <= 0) {
            mood = 0;
        }

    }

    /**
     * The toString method will return a String in formatted permutation containing some information, excluding healthiness,
     * state of sickness, action, and choice of action, about a VirtualPet
     *
     * @return returns a String in a properly formatted permutation containing certain information about a VirtualPet
     */
    public String toString() {
        String status = name + "'s Current Status: \n";
        status += "Hunger: " + hunger + "\n";
        status += "Thirst: " + thirst + "\n";
        status += "Money: " + money + "\n";
        status += "Mood: " + mood + "\n";


        return status;
    }


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

}
