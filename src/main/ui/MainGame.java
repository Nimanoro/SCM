package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainGame {

    private Manager manager;
    private Team team;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Player p5;
    private Player p6;
    private Player p7;
    private Player p8;
    private Player p9;
    private Player p10;
    private Player p11;
    private Predefinedteams t1;
    private Predefinedteams t2;
    private Predefinedteams t3;
    private Predefinedteams t4;
    private Predefinedteams t5;
    private Predefinedteams t6;
    private Predefinedteams t7;
    private Predefinedteams t8;
    private Predefinedteams t9;

    private League league;
    private Fixture fixture;
    private Scanner input;
    private Game game;
    private int week;

    public MainGame() {
        runMain();
    }

    private void runMain() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMainMenu();
            input = new Scanner(System.in);
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandMain(command);
            }
        }

        System.out.println("Thanks for playing! Bye bye!");
    }

    private void mainMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMainMenu();
            input = new Scanner(System.in);
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandMain(command);
            }
        }

        System.out.println("Goodbye!");
    }

    private void displayMainMenu() {
        System.out.println("\nselect from:");
        System.out.println("start game");
        System.out.println("squad");
        System.out.println("standing");
        System.out.println("Quit");


    }

    private void processCommandMain(String command) {
        if (command.equals("s")) {
            startGame();
        } else if (command.equals("squad")) {
            showSquad();
        } else if (command.equals("standing")) {
            showStanding();
        } else {
            System.out.println("Not a valid selection...");
        }
    }

    private void init() {
        System.out.println("Enter your username");
        String name = null;
        Scanner sc = new Scanner(System.in);
        name = sc.next();
        manager = new Manager();
        manager.setName(name);
        makePlayers(manager);
        makePredefinedTeams();
        makeLeague();
        makeFixture();
        week = 0;

    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void makePlayers(Manager m) {
        p1 = new Player(m, 1);
        p1.setName("Buffon");
        team = new Team(m);
        team.addPlayer(p1);
        p2 = new Player(m, 1);
        p2.setName("Sergio Ramos");
        team.addPlayer(p2);
        p3 = new Player(m, 1);
        p3.setName("Pepe");
        team.addPlayer(p3);
        p4 = new Player(m, 1);
        p4.setName("Marcelo");
        team.addPlayer(p4);
        p5 = new Player(m, 1);
        p5.setName("Philip Lahm");
        team.addPlayer(p5);
        p6 = new Player(m, 1);
        p6.setName("Toni Kroos");
        team.addPlayer(p6);
        p7 = new Player(m, 1);
        p7.setName("Xavi");
        team.addPlayer(p7);
        p8 = new Player(m, 1);
        p8.setName("Modric");
        team.addPlayer(p8);
        p9 = new Player(m, 1);
        p9.setName("Messi");
        team.addPlayer(p9);
        p10 = new Player(m, 2);
        p10.setName("Ronaldo");
        team.addPlayer(p10);
        p11 = new Player(m, 1);
        p11.setName("Pele");
        team.addPlayer(p11);
    }

    private void makePredefinedTeams() {
        t1 = new Predefinedteams("Arsenal");
        t1.setOverall(2);
        t2 = new Predefinedteams("Barcelona");
        t2.setOverall(1);
        t3 = new Predefinedteams("Real Madrid");
        t3.setOverall(5);
        t4 = new Predefinedteams("Bayern");
        t4.setOverall(4);
        t5 = new Predefinedteams("Tottenham");
        t5.setOverall(3);
        t6 = new Predefinedteams("Atlanta");
        t6.setOverall(2);
        t7 = new Predefinedteams("Malavan FC");
        t7.setOverall(1);
        t8 = new Predefinedteams("PSG");
        t8.setOverall(4);
        t9 = new Predefinedteams("BVB");
        t9.setOverall(2);

    }

    private void makeLeague() {
        league = new League();
        ArrayList<Predefinedteams> list = new ArrayList<Predefinedteams>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        list.add(t8);
        list.add(t9);
        list.add(team);
        league.setTeams(list);
    }

    private void makeFixture() {
        fixture = new Fixture(team);
        fixture.addPreDTeams(t1);
        fixture.addPreDTeams(t2);
        fixture.addPreDTeams(t3);
        fixture.addPreDTeams(t4);
        fixture.addPreDTeams(t5);
        fixture.addPreDTeams(t6);
        fixture.addPreDTeams(t7);
        fixture.addPreDTeams(t8);
        fixture.addPreDTeams(t9);
        fixture.setGames();
    }

    private void showStanding() {
        league.sortTeams();
        for (int i = 0; i < league.getTeams().size(); i++) {
            Predefinedteams team = league.getTeams().get(i);
            System.out.println(team.getName() + " points:" + team.getPoint());
        }
    }


    private void showSquad() {
        boolean keepGoing = true;
        String command = null;
        for (Integer i = 0; i < team.getPlayers().size(); i++) {
            Player player = team.getPlayers().get(i);
            System.out.println(Integer.toString(i) + " " + player.getName() + " Stars:" + player.getStar());
        }
        displaySquadMenu();
        input = new Scanner(System.in);


        while (keepGoing) {
            input = new Scanner(System.in);
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandSquad(command);
            }
        }

        mainMenu();

    }

    public void processCommandSquad(String command) {
        if (command.equals("update")) {
            updatePlayer();
        }

    }

    public void updatePlayer() {
        input = new Scanner(System.in);
        System.out.println("Enter the number of the player you want to update (cost: 10 coin)");
        System.out.println("Your coin:" + Integer.toString(manager.getCoin()));
        Integer number = input.nextInt();
        Player player = team.getPlayers().get(number);
        System.out.println(player.getName());
        System.out.println("Do you want to update this player? y/n");
        String response = input.next();
        processCommandUpdate(response, player);

    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void processCommandUpdate(String response, Player player) {
        if (response.equals("y")) {
            if (manager.getCoin() >= 10) {
                if (player.improvePlayer()) {
                    System.out.println(player.getName() + " was updated");
                    System.out.println("Current Stars " + player.getName() + ":" + player.getStar());
                    showSquad();
                } else if (!player.improvePlayer()) {
                    System.out.println("This player is maxed out");
                } else {
                    Integer missing;
                    missing = (10 - manager.getCoin());
                    System.out.println("You don't have enough coin to update this player.");
                    System.out.println("You need " + Integer.toString(missing) + " coins");
                    wait(2000);
                    showSquad();
                }
            } else if (response.equals("n")) {
                updatePlayer();
            } else if (response.equals("q")) {
                updatePlayer();
            } else {
                System.out.println("Invalid Statement... Back to Squad menu!");
                showSquad();
            }
        }
    }


    public void displaySquadMenu() {
        System.out.println("update for updating your players");
        System.out.println("Q for going back to the main menu");
    }


    private void startGame() {
        fixture.setGames();
        Game game1 = fixture.getGames().get(week);
        System.out.println("Week " + Integer.toString(week + 1));
        System.out.println("This is " + game1.getT1().getName() + " VS " + game1.getT2().getName());
        wait(5000);
        game1.generateResult();
        game1.updatePoints();
        game1.updateCoins();
        Predefinedteams t1 = game1.getT1();
        System.out.println("Result: " + t1.getName() + " " + game1.getGoal1() + "-"
                + game1.getGoal2() + " " + team.getName());
        if (game1.getGoal1() > game1.getGoal2()) {
            System.out.println("you lost! Keep trying " + manager.getName());
        } else if (game1.getGoal1() == game1.getGoal2()) {
            System.out.println("It was a tie. Good job " + manager.getName() + " !");
        } else {
            System.out.println("You did Great " + manager.getName() + "! You won the match.");
        }
        System.out.println("Your coin after this game:" + manager.getCoin());
        System.out.println("your points after this game:" + team.getPoint());
        week = week + 1;
        wait(5000);
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}