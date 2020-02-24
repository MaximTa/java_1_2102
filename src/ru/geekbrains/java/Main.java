package ru.geekbrains.java;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int SIZE = 5;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';
    static final int DOTS_FOR_WIN = 4;//не довёл до ума

    static char[][] map;

    static Random random = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();

            if (checkWin(DOT_X)) {
                System.out.println("Игрок ПОБЕДИЛ!!!");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }
            System.out.println("Ход ИИ");
            aiTurn();
            printMap();

            if (checkWin(DOT_O)) {
                System.out.println("Искусственный интеллект ПОБЕДИЛ!!!");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате Y X (строка столбец)");
            y = sc.nextInt() - 1;
            x = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x, y;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWin(DOT_X)) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;

    }

    public static boolean checkLine(int fx, int fy, int dx, int dy, char symbol) {
        for (int i = 0; i < SIZE; i++) {
            if (map[fx + i * dx][fy + i * dy] != symbol)
                return false;
        }
        return true;
    }

    int count = 0;

    public static boolean checkWin(char symbol) {
        {

            for (int i = 0; i < SIZE; i++) {
                if (checkLine(i, 0, 0, 1, symbol)) {
                    return true;
                }

                if (checkLine(0, i, 1, 0, symbol)) {
                    return true;
                }

            }
                            if (checkLine(0, 0, 1, 1, symbol)) {
                                return true;
                            }
                            if (checkLine(0, SIZE - 1, 1, -1, symbol)) {
                                return true;
                            }
                        }
                     return false;
                    }
        }

