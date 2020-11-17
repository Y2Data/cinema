package cinema;

import java.util.Arrays;
import java.util.Scanner;



public class Cinema {
    static int ticketsSold = 0;
    static int currentIncome = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] floor = askAndMakeFloor();


        while (true) {
            int menu = askMenu();
            if (menu == 0) {
                break;
            }
            else if (menu == 1) {
                printFloor(floor);
            }
            else if (menu == 2) {
                bookSeat(floor);
                ticketsSold = ticketsSold + 1;
            } else if (menu == 3) {
                showStatistics(floor);
            }
        }
    }

    public static void showStatistics(char[][] floor) {
        int totalSeats = floor.length * floor[0].length;
        System.out.println("Number of purchased tickets: " + ticketsSold);
        System.out.printf("Percentage: %.2f%%\n", (100 * ticketsSold / (double) totalSeats));
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalPrice(floor));
    }



    public static int askMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return sc.nextInt();
    }


    public static char[][] askAndMakeFloor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = sc.nextInt();

        char[][] floor = new char[rows][cols];
        for (char[] chars : floor) {
            Arrays.fill(chars, 'S');
        }
        return floor;
    }


    public static int totalPrice(char[][] floor) {
        int rows = floor.length;
        int cols = floor[0].length;
        int totalSeats = rows * cols;
        int totalPrice;
        if (totalSeats <= 60) {
            totalPrice = totalSeats * 10;
        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;
            totalPrice = frontRows * cols * 10 + backRows * cols * 8;
        }
        return totalPrice;
    }

    public static int seatPrice(char[][] floor, int row) {
        int rows = floor.length;
        int cols = floor[0].length;
        if (rows * cols <= 60) {
            return 10;
        } else {
            if (row <= rows / 2) {
                return 10;
            } else
                return 8;
        }
    }

    public static void printFloor(char[][] floor) {
        System.out.println("Cinema:");
        int rows = floor.length;
        int cols = floor[0].length;
        System.out.print("  ");
        for (int i = 0; i < cols; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        for (int i = 0; i < floor.length; i++) {
            char[] chars = floor[i];
            System.out.print(i+1 + " ");
            for (int j = 0; j < chars.length; j++) {
                char aChar = chars[j];
                System.out.print(aChar + " ");
            }
            System.out.println();
        }

    }

    public static void bookSeat(char[][] floor) {
        Scanner sc = new Scanner(System.in);
        boolean sold = false;
        int rows = floor.length;
        int cols = floor[0].length;

        while (!sold) {
            System.out.println("Enter a row number: ");
            int row = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int col = sc.nextInt();

            if (row > rows || col > cols) {
                System.out.println("Wrong input!");
            } else if (floor[row-1][col-1] == 'S') {
                floor[row-1][col-1] = 'B';
                sold = true;
                currentIncome = currentIncome + seatPrice(floor, row);
                System.out.println("Ticket prices: $" + seatPrice(floor, row));
            } else if (floor[row-1][col-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                sold = false;
            }
        }
    }
}