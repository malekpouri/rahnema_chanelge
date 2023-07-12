package ir.utux;


import java.util.*;
public class EsmFamil {


    static class Player {
        String name;
        Map<String, String> answers = new HashMap<>();

        Player(String name) {
            this.name = name;
        }

        void addAnswer(String category, String answer) {
            answers.put(category, answer);
        }

        String getAnswer(String category) {
            return answers.get(category);
        }
    }

    static class Game {
        List<Player> players = new ArrayList<>();
        Map<String, Integer> scores = new HashMap<>();
        String[] categories = {"نام", "شهر", "غذا", "رنگ"};

        void addPlayer(Player player) {
            players.add(player);
            scores.put(player.name, 0);
        }

        void startRound(char letter) {
            for (Player player : players) {
                for (String category : categories) {
                    String answer = player.getAnswer(category);
                    if (answer != null && answer.charAt(0) == letter) {
                        int score = isUnique(answer) ? 10 : 5;
                        scores.put(player.name, scores.get(player.name) + score);
                    }
                }
            }
        }

        boolean isUnique(String answer) {
            int count = 0;
            for (Player player : players) {
                for (String category : categories) {
                    if (answer.equals(player.getAnswer(category))) {
                        count++;
                    }
                }
            }
            return count == 1;
        }

        void printScores() {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                System.out.println("Player: " + entry.getKey() + ", Score: " + entry.getValue());
            }
        }
    }
    public static void main(String[] args) {
        Game game = new Game();

        Player player1 = new Player("Ali");
        player1.addAnswer("نام", "Ali");
        player1.addAnswer("شهر", "Amsterdam");
        player1.addAnswer("غذا", "Apple");
        player1.addAnswer("رنگ", "Aqua");

        Player player2 = new Player("Reza");
        player2.addAnswer("نام", "Reza");
        player2.addAnswer("شهر", "Rome");
        player2.addAnswer("غذا", "Rice");
        player2.addAnswer("رنگ", "Red");

        game.addPlayer(player1);
        game.addPlayer(player2);

        game.startRound('A');
        game.printScores();

        game.startRound('R');
        game.printScores();
    }

}
