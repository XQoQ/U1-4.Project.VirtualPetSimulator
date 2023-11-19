import java.util.Scanner;

public class VirtualPetRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        boolean run = true;

        System.out.println("It was late and freezing, you just finished working and were walking home with that worn" +
                "-out body.\n\nMeow..\n\nSuddenly, you heard some noise on your right. You turned to look for the source " +
                "of the noise, there was a small shadow running toward the back\nof a street lamp. It was a kitten, " +
                "shivering, you decided to take it home with you...\n\nWhat should you name it: ");
        String action = s.nextLine();
        VirtualPet shiro = new VirtualPet(action, 100);

        while (run) {
            System.out.println("-------------------------");
            System.out.print(shiro);
            System.out.println("-------------------------");
            System.out.println("What would you like your pet to do (" + ANSI_YELLOW + "eat," + ANSI_BLUE + " drink," + ANSI_CYAN + " work; " + ANSI_RED + "\"end\" to end " + ANSI_RESET + "):");
            action = s.nextLine();
            shiro.setCurtAction(action);
            System.out.println("Choose one of the following: ");
            System.out.println(shiro.getChoiceInfo());
            if (!shiro.getChoiceInfo().equalsIgnoreCase("invalid input") && !action.equalsIgnoreCase("end")) {
                action = s.nextLine();
                shiro.setActionChoice(action);
            }
            System.out.println(shiro.getMessage());


            if (shiro.isSick()) {
                System.out.println(shiro.getName() + " doesn't look good, what should you do?\n");
                System.out.println(shiro.getChoiceInfo());
                action = s.nextLine();
                shiro.setActionChoice(action);
                System.out.println(shiro.getMedMessage());
            }


            if (action.equals("end")) {
                run = false;
            }
        }
        s.close();
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



