/* 
@filename - consoleCricket.java
@description -  Created a Console Based Cricket Game Of 2 Overs.
@author - Divyansh Tak
*/



import java.util.*;

public class consoleCricket{

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static String[] teams = {"India", "Australia", "England", "Pakistan", "New Zealand", "South Africa"};
    static int wicketsLost = 0; // Tracks wickets lost in current innings

    public static void main(String[] args) {
        // ------------------ Welcome Message ------------------
        System.out.println("\n=================================================");
        System.out.println("          Welcome to the Cricket Match");
        System.out.println("                2 Overs per Side");
        System.out.println("=================================================\n");

        // ------------------ Display Teams ------------------
        System.out.println("Available Teams:");
        for (int i = 0; i < teams.length; i++) {
            System.out.println("   " + (i + 1) + ". " + teams[i]);
        }

        // ------------------ Team Selection Loop ------------------
        String team1 = "", team2 = "";
        while (true) {
            try {
                System.out.print("\nSelect Team 1 (enter number): ");
                int t1 = sc.nextInt() - 1;

                System.out.print("Select Team 2 (enter number): ");
                int t2 = sc.nextInt() - 1;

                // Validate selection
                if (t1 < 0 || t1 >= teams.length || t2 < 0 || t2 >= teams.length || t1 == t2) {
                    System.out.println("  Invalid team selection! Try again.");
                    continue;
                }

                team1 = teams[t1];
                team2 = teams[t2];
                break;
            } catch (InputMismatchException e) {
                System.out.println("  Invalid input! Enter numbers only.");
                sc.nextLine(); // Clear invalid input
            }
        }

        // ------------------ Match Info ------------------
        System.out.println("\n-------------------------------------------------");
        System.out.println("Match between " + team1 + " and " + team2 + " begins!");
        System.out.println("Each side will play 2 overs (12 legal balls)");
        System.out.println("-------------------------------------------------\n");

        // ------------------ Toss ------------------
        String tossWinner = rand.nextBoolean() ? team1 : team2;
        boolean tossChoice = rand.nextBoolean(); // true = bat first, false = bowl first
        String battingFirst = tossChoice ? tossWinner : (tossWinner.equals(team1) ? team2 : team1);
        String bowlingFirst = battingFirst.equals(team1) ? team2 : team1;
        System.out.println("Toss Result: " + tossWinner + " won the toss and chose to " + (tossChoice ? "bat" : "bowl") + " first.\n");

        // ------------------ First Innings ------------------
        System.out.println("-------- FIRST INNINGS: " + battingFirst + " Batting -----------");
        int score1 = playInnings(battingFirst);

        // ------------------ Second Innings ------------------
        System.out.println("\n-------- SECOND INNINGS: " + bowlingFirst + " Chasing Target (" + (score1 + 1) + ") ----------");
        int score2 = playInningsChase(bowlingFirst, score1);

        // ------------------ Final Result ------------------
        System.out.println("\n================== FINAL RESULT ==================");
        if (score1 > score2) {
            System.out.println("  " + battingFirst + " wins by " + (score1 - score2) + " runs!");
        } else if (score2 > score1) {
            System.out.println("  " + bowlingFirst + " wins by " + (10 - wicketsLost) + " wickets!");
        } else {
            System.out.println("  The match is tied!");
        }
        System.out.println("=================================================\n");
        System.out.println("Thanks for umpiring this match!\n");
    }

    // ==================== FIRST INNINGS FUNCTION ====================
    public static int playInnings(String teamName) {
        int runs = 0, balls = 0, totalBalls = 12;
        wicketsLost = 0;
        List<String> overProgress = new ArrayList<>(); // Tracks progress in current over

        while (balls < totalBalls && wicketsLost < 10) {
            try {
                // ------------------ Ball Outcome Input ------------------
                System.out.println("\n-------------------------------------------------");
                System.out.println("Ball " + (balls + 1) + " -> Choose outcome:");
                System.out.println("  0 1 2 3 4 6 W WD NB");
                System.out.print("Your choice: ");
                String input = sc.next().toUpperCase();

                // ------------------ Process Input ------------------
                switch (input) {
                    // Normal runs
                    case "0": case "1": case "2": case "3": case "4": case "6":
                        runs += Integer.parseInt(input);
                        balls++;
                        overProgress.add(input);
                        break;

                    // Wide ball with optional runs
                    case "WD":
                        int wdRun = -1;
                        while (wdRun < 0 || wdRun > 4) {
                            try {
                                System.out.print("  Runs scored on Wide (0-4): ");
                                wdRun = sc.nextInt();
                                if (wdRun < 0 || wdRun > 4)
                                    System.out.println("  Invalid! Enter 0-4 only.");
                            } catch (Exception e) {
                                System.out.println("  Invalid input! Enter a number.");
                                sc.nextLine();
                            }
                        }
                        runs += 1 + wdRun;
                        overProgress.add(wdRun > 0 ? "WD+" + wdRun : "WD");
                        break;

                    // No ball with optional runs
                    case "NB":
                        int nbRun = -1;
                        while (nbRun < 0 || nbRun > 6) {
                            try {
                                System.out.print("  Runs scored on No Ball (0-6): ");
                                nbRun = sc.nextInt();
                                if (nbRun < 0 || nbRun > 6)
                                    System.out.println("  Invalid! Enter 0-6 only.");
                            } catch (Exception e) {
                                System.out.println("  Invalid input! Enter a number.");
                                sc.nextLine();
                            }
                        }
                        runs += 1 + nbRun;
                        overProgress.add(nbRun > 0 ? "NB+" + nbRun : "NB");
                        break;

                    // Wicket
                    case "W":
                        wicketsLost++;
                        balls++;
                        overProgress.add("W");
                        System.out.println("  WICKET! Total wickets: " + wicketsLost);
                        break;

                    // Invalid input
                    default:
                        System.out.println("  Invalid input! Try again.");
                        continue;
                }

                // ------------------ Display Score & Progress ------------------
                System.out.println();
                int over = balls / 6, ballInOver = balls % 6;
                System.out.println("  Score: " + runs + "/" + wicketsLost + " (" + over + "." + ballInOver + " overs)");
                System.out.println("  Over Progress: " + String.join(" ", overProgress));

                // ------------------ Over Complete ------------------
                if (ballInOver == 0 && balls > 0) {
                    System.out.println("---------- Over " + over + " Complete ----------\n");
                    overProgress.clear();
                }

            } catch (Exception e) {
                System.out.println("  Error! Try again.");
                sc.nextLine();
            }
        }

        System.out.println("\n" + teamName + " Innings Complete: " + runs + "/" + wicketsLost + "\n");
        return runs;
    }

    // ==================== SECOND INNINGS FUNCTION ====================
    public static int playInningsChase(String teamName, int target) {
        int runs = 0, balls = 0, totalBalls = 12;
        wicketsLost = 0;
        List<String> overProgress = new ArrayList<>();

        while (balls < totalBalls && wicketsLost < 10 && runs <= target) {
            try {
                // ------------------ Ball Outcome Input ------------------
                System.out.println("\n-------------------------------------------------");
                System.out.println("Ball " + (balls + 1) + " -> Choose outcome:");
                System.out.println("  0 1 2 3 4 6 W WD NB");
                System.out.print("Your choice: ");
                String input = sc.next().toUpperCase();

                // ------------------ Process Input ------------------
                switch (input) {
                    case "0": case "1": case "2": case "3": case "4": case "6":
                        runs += Integer.parseInt(input);
                        balls++;
                        overProgress.add(input);
                        break;

                    case "WD":
                        int wdRun = -1;
                        while (wdRun < 0 || wdRun > 4) {
                            try {
                                System.out.print("  Runs scored on Wide (0-4): ");
                                wdRun = sc.nextInt();
                                if (wdRun < 0 || wdRun > 4)
                                    System.out.println("  Invalid! Enter 0-4 only.");
                            } catch (Exception e) {
                                System.out.println("  Invalid input! Enter a number.");
                                sc.nextLine();
                            }
                        }
                        runs += 1 + wdRun;
                        System.out.println("  Wide Ball! +1 run + " + wdRun + " runs = " + (1 + wdRun));
                        overProgress.add(wdRun > 0 ? "WD+" + wdRun : "WD");
                        break;

                    case "NB":
                        int nbRun = -1;
                        while (nbRun < 0 || nbRun > 6) {
                            try {
                                System.out.print("  Runs scored on No Ball (0-6): ");
                                nbRun = sc.nextInt();
                                if (nbRun < 0 || nbRun > 6)
                                    System.out.println("  Invalid! Enter 0-6 only.");
                            } catch (Exception e) {
                                System.out.println("  Invalid input! Enter a number.");
                                sc.nextLine();
                            }
                        }
                        runs += 1 + nbRun;
                        System.out.println("  No Ball! +1 run + " + nbRun + " runs = " + (1 + nbRun));
                        overProgress.add(nbRun > 0 ? "NB+" + nbRun : "NB");
                        break;

                    case "W":
                        wicketsLost++;
                        balls++;
                        overProgress.add("W");
                        System.out.println("  WICKET! Total wickets: " + wicketsLost);
                        break;

                    default:
                        System.out.println("  Invalid input! Try again.");
                        continue;
                }

                // ------------------ Display Score & Target Info ------------------
                System.out.println();
                int over = balls / 6, ballInOver = balls % 6;
                int ballsLeft = totalBalls - balls;
                int runsNeeded = (target + 1) - runs;

                System.out.println("  Score: " + runs + "/" + wicketsLost + " (" + over + "." + ballInOver + " overs)");
                System.out.println("  Over Progress: " + String.join(" ", overProgress));
                if (runs <= target)
                    System.out.println("  Runs needed: " + runsNeeded + " from " + ballsLeft + " balls");

                // ------------------ Over Complete ------------------
                if (ballInOver == 0 && balls > 0) {
                    System.out.println("---------- Over " + over + " Complete ----------\n");
                    overProgress.clear();
                }

                if (runs > target) break;

            } catch (Exception e) {
                System.out.println("  Error! Try again.");
                sc.nextLine();
            }
        }

        System.out.println("\n" + teamName + " Innings Complete: " + runs + "/" + wicketsLost + "\n");
        return runs;
    }
}

