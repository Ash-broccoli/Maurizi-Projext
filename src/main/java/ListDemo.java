import java.util.ArrayList;

/**
 * A program that keeps your watched anime in order. Animes you want to watch are also here.
 * Some recommended links to websites to watch anime are also given.
 *
 * @author Alyssa Heimlicher
 * @version 1.0
 * @since 2021.06.29
 */
public class ListDemo {
    Einleser e = new Einleser();
    String userChoice = "";
    ArrayList<Show> toWatchshows = new ArrayList<>();
    ArrayList<Show> alreadyWatchedShows = new ArrayList<>();

    public static void main(String[] args) {
        new ListDemo().run();
    }

    public void run() {
        Show aot = new Show("Attack on Titan", 4, 25);
        Show dn = new Show("Death Note", 1, 39);
        Show mha = new Show("My Hero Academia", 5, 24);
        Show ds = new Show("Demon Slayer", 1, 24);

        toWatchshows.add(aot);
        toWatchshows.add(dn);
        toWatchshows.add(mha);

        alreadyWatchedShows.add(ds);
        while (!userChoice.equals("x")) {
            start();
        }
    }


    public void start() {
        try {
            System.out.println();
            System.out.println("\n------------Welcome to your watchlist!------------");
            System.out.println("""
                    || t To watch       ||
                    || a Already watched||
                    || w Where to watch ||
                    || x exit           ||
                    """);
            System.out.print("What would you like to do? > ");
            userChoice = e.readString();


            if (userChoice.equals("t") || userChoice.equals("a") || userChoice.equals("w") || userChoice.equals("x")) {
                switch (userChoice) {
                    case "t" -> toWatchMenu();
                    case "a" -> alreadyWatchedMenu();
                    case "w" -> whereToWatch();
                }
            } else if (Character.isDigit(userChoice.charAt(0))) {
                System.err.println("Error. Enter a letter. Numbers are invalid!");
            } else {
                System.err.println("Error. Enter a valid letter!");
            }
        } catch (Exception e) {
            System.err.println("Error! Empty input is invalid!");
        }
    }

    public void toWatchMenu() {
        String toWatchMenu;
        System.out.println("\n---To watch---");
        System.out.println("""
                || l View List      ||
                || a Add show       ||
                || d Delete a show  ||
                || w Watched a show ||
                """);
        System.out.print("What would you like to do? > ");
        toWatchMenu = e.readString();
        if (toWatchMenu.equals("l") || toWatchMenu.equals("a") || toWatchMenu.equals("d") || toWatchMenu.equals("w")) {
            switch (toWatchMenu) {
                case "l" -> viewToWatchList();
                case "a" -> addToWatch();
                case "d" -> deleteToWatch();
                case "w" -> watchedAShow();
            }
        } else if (Character.isDigit(toWatchMenu.charAt(0))) {
            System.err.println("Error. Enter a letter. Numbers are invalid!");
        } else {
            System.err.println("Error. Enter a valid letter!");
        }
    }

    public void viewToWatchList() {
        System.out.println();
        System.out.println("---View list---");
        displayList(toWatchshows);
        System.out.println("------------------------------------");
        System.out.println("""
                || Any key Back to menu ||
                || x exit               ||
                """);
        System.out.print("What would you like to do? > ");
        userChoice = e.readString();
    }

    public void addToWatch() {
        String name;
        int amtSeason;
        int amtEpisodes;

        System.out.println("\n---Add show---");
        System.out.println();
        System.out.print("Enter the name of the show > ");
        name = e.readString();
        System.out.print("Enter the number of seasons > ");
        amtSeason = e.readInt();
        System.out.print("Enter the number of episodes per season > ");
        amtEpisodes = e.readInt();
        Show s = new Show(name, amtSeason, amtEpisodes);
        toWatchshows.add(s);
        System.out.println("""
                ----------------------------------------------
                ▷Your entry was saved.
                ----------------------------------------------""");
    }

    public void deleteToWatch() {
        try {
            if (toWatchshows.isEmpty()) {
                System.out.println("""
                        -----------------------------------------------------
                        You're list is empty. Nothing to delete!
                        -----------------------------------------------------
                        """);
            } else {

                int deleteShowId;
                System.out.println("\n---Delete a show---");
                displayList(toWatchshows);
                System.out.print("----------------------------------------------\n" +
                        "Which show do you want deleted? > ");
                deleteShowId = e.readInt();
                deleteShowId -= 1;
                System.out.println("==============================================\n" +
                        "▷" + toWatchshows.get(deleteShowId).getTitle() + " was deleted.\n" +
                        "----------------------------------------------");
                toWatchshows.remove(deleteShowId);
            }

        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error. Out of range!");
        }

    }

    public void watchedAShow() {
        try {
            if (toWatchshows.isEmpty()) {
                System.out.println("""
                        -----------------------------------------------------
                        You're list is empty. Nothing to move!
                        -----------------------------------------------------
                        """);
            } else {
                int watchedShow;
                System.out.println("\n---Watched a show---");
                displayList(toWatchshows);
                System.out.print("----------------------------------------------\n" +
                        "Which show did you watch? > ");
                watchedShow = e.readInt();
                watchedShow -= 1;
                System.out.println("==============================================\n" +
                        "▷" + toWatchshows.get(watchedShow).getTitle() + " was moved.\n" +
                        "----------------------------------------------");
                moveASpecificValue(toWatchshows, alreadyWatchedShows, watchedShow);
            }
        }catch(IndexOutOfBoundsException e){
            System.err.println("Error. Out of range!");
        }
    }

    public void alreadyWatchedMenu() {

        String alreadyWatched;
        System.out.println("\n---Already Watched---");
        System.out.println("""
                || l View List      ||
                || a Add show       ||
                || d delete a show  ||""");
        System.out.print("What would you like to do? > ");
        alreadyWatched = e.readString();
        if (alreadyWatched.equals("l") || alreadyWatched.equals("a") || alreadyWatched.equals("d")) {
            switch (alreadyWatched) {
                case "l" -> viewAlreadyWatchedList();
                case "a" -> addAlreadyWatched();
                case "d" -> deleteAlreadyWatch();
            }
        } else if (Character.isDigit(alreadyWatched.charAt(0))) {
            System.err.println("Error. Enter a letter. Numbers are invalid!");
        } else {
            System.err.println("Error. Enter a valid letter!");
        }
    }

    public void viewAlreadyWatchedList() {
        System.out.println("\n---View list---");
        displayList(alreadyWatchedShows);
        System.out.println("------------------------------------");
        System.out.println("""
                || Any key Back to menu ||
                || x exit               ||
                """);
        System.out.print("What would you like to do? > ");
        userChoice = e.readString();
    }

    public void addAlreadyWatched() {
        String name;
        int amtSeason;
        int amtEpisodes;

        System.out.println("\n---Add show---");
        System.out.println();
        System.out.print("Enter the name of the show > ");
        name = e.readString();
        System.out.print("Enter the number of seasons > ");
        amtSeason = e.readInt();
        System.out.print("Enter the number of episodes per season > ");
        amtEpisodes = e.readInt();
        Show s = new Show(name, amtSeason, amtEpisodes);
        alreadyWatchedShows.add(s);
        System.out.println("""
                ----------------------------------------------
                ▷Your entry was saved.
                ----------------------------------------------""");

    }

    public void deleteAlreadyWatch() {
        try{
        if (alreadyWatchedShows.isEmpty()) {
            System.out.println("""
                    -----------------------------------------------------
                    You're list is empty. Nothing to delete!
                    -----------------------------------------------------
                    """);
        } else {
            int deleteShowId;
            System.out.println("\n---Delete a show---");
            displayList(alreadyWatchedShows);
            System.out.print("----------------------------------------------\n" +
                    "Which show do you want deleted? > ");
            deleteShowId = e.readInt();
            deleteShowId -= 1;
            System.out.println("==============================================\n" +
                    "▷" + alreadyWatchedShows.get(deleteShowId).getTitle() + " was deleted.\n" +
                    "----------------------------------------------");
            alreadyWatchedShows.remove(deleteShowId);
        }
        }catch(IndexOutOfBoundsException e){
            System.err.println("Error. Out of range!");
        }
    }

    public void whereToWatch() {
        System.out.println("\n---Where to watch---");
        System.out.println("""
                https://www.crunchyroll.com/\s
                https://www.funimation.com/\s
                https://www.netflix.com/ch-en/""");
        System.out.println("----------------------------------------------");
        System.out.println("""
                || Any key Back to menu ||
                || x exit               ||
                """);
        System.out.print("What would you like to do? > ");
        userChoice = e.readString();

    }

    public static void moveASpecificValue(ArrayList<Show> source, ArrayList<Show> destination, int indexOfSpecificValue) {
        destination.add(source.remove(indexOfSpecificValue));
    }

    public void displayList(ArrayList<Show> source) {
        if (!source.isEmpty()) {
            for (int i = 0; i < source.size(); i++) {
                System.out.println(i + 1 + ") " + source.get(i));
            }
        } else System.out.println("Your list is empty.");

    }

}
