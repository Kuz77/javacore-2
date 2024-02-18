package org.example;

import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private int size;
    private int winCount;

    public TicTacToe(int size, int winCount) {
        this.size = size;
        this.winCount = winCount;
        board = new char[size][size];
        currentPlayer = 'X';
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - winCount; j++) {
                if (checkRowCol(board[i][j], board[i][j + 1], board[i][j + 2], board[i][j + 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - winCount; j++) {
                if (checkRowCol(board[j][i], board[j + 1][i], board[j + 2][i], board[j + 3][i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        for (int i = 0; i <= size - winCount; i++) {
            for (int j = 0; j <= size - winCount; j++) {
                if (checkRowCol(board[i][j], board[i + 1][j + 1], board[i + 2][j + 2], board[i + 3][j + 3]) ||
                        checkRowCol(board[i][j + 3], board[i + 1][j + 2], board[i + 2][j + 1], board[i + 3][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRowCol(char c1, char c2, char c3, char c4) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3) && (c3 == c4));
    }

    public void changePlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    public void placeMark(int row, int col) {
        if ((row >= 0) && (row < size) && (col >= 0) && (col < size)) {
            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
            }
        }
    }

    public void computerMove() {
        int row, col;
        do {
            row = (int) (Math.random() * size);
            col = (int) (Math.random() * size);
        } while (board[row][col] != '-');
        board[row][col] = currentPlayer;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(5, 4);
        Scanner scanner = new Scanner(System.in);

        while (!game.isBoardFull()) {
            game.printBoard();
            if (game.currentPlayer == 'O') {
                System.out.println("ход комрьютера:");
                game.computerMove();
            } else {
                System.out.println("ваш ход введите номер ячейки(0-4):");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                game.placeMark(row, col);
            }
            if (game.checkForWin()) {
                game.printBoard();
                if (game.currentPlayer == 'O') {
                    System.out.println("поражение");
                } else {
                    System.out.println("победа");
                }
                break;
            }
            game.changePlayer(); // Переключение игрока после хода
        }
        if (!game.checkForWin() && game.isBoardFull()) {
            game.printBoard();
            System.out.println("ничья");
        }
        scanner.close();
    }
}
