import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String[] FIGHTING_STYLES = {"Striking", "Grappling", "Balanced"};
    private static final String[] COMMENTARY = {"What a good punch!", "Good pressure!", "Nice defense!", "Great combination!",
            "Amazing footwork!", "Brilliant take down!", "Excellent counter!", "Powerful kick!", "Shoots for take down", "Careful",
            "Nice exchanges", "What a leg kick", "He'll feel that in the morning", "Pretty balanced", "Impressive faint", "That body shot is something"};

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean continueContender = true;
        boolean continueFight = true;
        boolean isFirstFight = true;
        int consecutiveWins = 0;

        System.out.println("Hello there! I'm with UFC Scouting, very impressive out there kid, what's your name?");
        String name = scanner.nextLine();

        System.out.println("Nice to meet you " + name + ". I'm going to book you a fight at the Contender Series. " +
                "If you impress the boss there, welcome to the UFC!");

        System.out.println("What's your preferred fighting style: striking, grappling, or balanced?");
        String style = scanner.nextLine().toLowerCase();

        int striking = 50;
        int grappling = 50;
        int stamina = 50;

        if (style.contains("balanced")) {
            style = "balanced";
            striking = 60;
            grappling = 60;
            stamina = 60;
        } else if (style.contains("striking") || style.contains("striker")) {
            style = "striker";
            striking = 70;
            grappling = 40;
            stamina = 50;
        } else if (style.contains("grappling") || style.contains("grapple")) {
            style = "grappler";
            striking = 40;
            grappling = 70;
            stamina = 50;
        } else {
            System.out.println("Did not understand, please say striking, grappling or balanced.");
        }
        Thread.sleep(3600);
        System.out.println("Some time later...");
        Thread.sleep(3000);
        Fighter player = new Fighter(name, striking, grappling, stamina, style);
        System.out.println("Contender series fight confirmed, you will be fighting: Opponent " + random.nextInt(100) + ". Better start training!");

        Fighter opponent = generateRandomOpponent(random);
        Fighter currentOppenent = generateRandomOpponent(random);

        while (continueContender) {
            System.out.println("Choose what you want to do next:");
            System.out.println("a: Fight");
            System.out.println("b: See details");

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "a":
                    System.out.println("You chose to fight.");
                    fight(player, opponent, random, isFirstFight, false);
                    continueContender = false;
                    isFirstFight = false;
                    break;
                case "b":
                    System.out.println("Your details:");
                    player.displayDetails();
                    System.out.println("\nOpponent's details:");
                    opponent.displayDetails();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a or b.");
            }
        }
        Thread.sleep(5000);
        System.out.println("WELCOME TO THE UFC");

        Thread.sleep(3000);
        System.out.println("Very impressive, your debut UFC fight will be versus a top 15 ranked fighter");

        while (continueFight) {
            System.out.println("Choose what you want to do next:");
            System.out.println("a: Fight");
            System.out.println("b: See details");
            System.out.println("c: Train");

            String choice2 = scanner.nextLine().toLowerCase();
            switch (choice2) {
                case "a":
                    boolean isTitleShot = consecutiveWins == 3;
                    if (isTitleShot) {
                        System.out.println("You have a title shot fight!");
                    }
                    currentOppenent = generateRandomOpponent(random);
                    if (!fight(player, currentOppenent, random, isFirstFight, isTitleShot)) {
                        System.out.println("You lost the fight. Do you want to fight again? (yes/no)");
                        String fightAgain = scanner.nextLine().toLowerCase();
                        if (fightAgain.equals("yes")) {
                            currentOppenent = generateRandomOpponent(random);
                            consecutiveWins = 1; // Reset consecutive wins after a loss
                        } else {
                            continueFight = false;
                        }
                    } else {
                        consecutiveWins++;
                        if (consecutiveWins == 3) {
                            System.out.println("Congratulations! You have won 3 fights in a row and earned a title shot!");
                        }
                        if(isTitleShot){
                            System.out.println(player.name + " gets the job done to be the UFC champion");
                            continueFight = false;
                        }
                    }
                    break;
                case "b":
                    System.out.println("Your details:");
                    player.displayDetails();
                    System.out.println("\nOpponent's details:");
                    currentOppenent.displayDetails();
                    break;
                case "c":
                    System.out.println("Choose what you want to train: striking, grappling, or stamina.");
                    String trainingChoice = scanner.nextLine().toLowerCase();
                    player.train(trainingChoice);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a, b, or c.");
            }
        }
        Thread.sleep(4000);
        System.out.println("Well done champ, well deserved");
        Thread.sleep(1500);
        System.out.println("You finally made it!");
    }

    public static Fighter generateRandomOpponent(Random random) {
        String name = "Opponent " + random.nextInt(100);
        String fightingStyle = FIGHTING_STYLES[random.nextInt(FIGHTING_STYLES.length)];
        int striking = random.nextInt(70) + 30;
        int grappling = random.nextInt(70) + 30;
        int stamina = random.nextInt(70) + 30;
        return new Fighter(name, striking, grappling, stamina, fightingStyle);
    }

    public static boolean fight(Fighter fighter, Fighter opponent, Random random, boolean isFirstFight, boolean isTitleShot) throws InterruptedException {
        int rounds = isTitleShot ? 5 : 3; // Title shot fights are 5 rounds
        for (int round = 1; round <= rounds; round++) {
            System.out.println("Round " + round + " FIGHT...");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(random.nextInt(3000) + 1000);
                System.out.println(COMMENTARY[random.nextInt(COMMENTARY.length)]);
            }
            if (random.nextInt(100) < 50 && !isFirstFight) {
                String outcome = random.nextBoolean() ? "KO" : "submission";
                System.out.println("OOOOOOOO WHAT A " + outcome.toUpperCase() + "!");
                String winner = random.nextBoolean() ? fighter.name : opponent.name;
                System.out.println("The fight ends in round " + round + " by " + outcome + "!");
                System.out.println("Winner: " + winner);
                if (isTitleShot && winner.equals(fighter.name)) {
                    System.out.println("Congratulations! " + fighter.name + " is the new UFC champion of the world!");
                }
                return winner.equals(fighter.name);
            }
            Thread.sleep(3300);
            System.out.println("DING DING DING Round " + round + " finishes");
            Thread.sleep(2679);
        }
        Thread.sleep(2000);
        System.out.println("The fight goes the distance! Let's go to the judges' scorecards.");
        String winner = isFirstFight ? fighter.name : (random.nextBoolean() ? fighter.name : opponent.name);
        System.out.println("And the winner by decision is... " + winner + "!");
        if (isTitleShot && winner.equals(fighter.name)) {
            System.out.println("Congratulations! " + fighter.name + " is the new UFC champion of the world!");
        }
        return winner.equals(fighter.name);
    }
}