package com.github.elemocrack.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board;

        Scanner scanner = new Scanner(System.in);
        boolean shiftA = true;
        char player;
        int posY = 0;
        int posX = 0;
        System.out.println("Bienvenido al ta te ti de Elmo\n");
        while (true) {
            while (true) {
                System.out.println("Ingrese un numero de casillas");
                if (scanner.hasNextInt()) {
                    int slotAmount = scanner.nextInt();
                    if (slotAmount > 0) {
                        board = new char[slotAmount][slotAmount];
                    } else {
                        System.out.println("La cantidad de casillas es un numero positivo");
                        scanner.nextLine();
                        break;
                    }
                } else {
                    System.out.println("Tiene que ingresar un monto numérico");
                    scanner.nextLine();
                    break;
                }

                System.out.println("Ingrese un carácter para el tablero");
                char boardCharacter = scanner.next().charAt(0);
                System.out.println("Jugador 1 elija su símbolo");
                char playerA = scanner.next().charAt(0);
                if (playerA == boardCharacter) {
                    System.out.println("Los jugadores no pueden tener el mismo carácter que el tablero");
                    scanner.nextLine();
                    break;
                }
                System.out.println("Jugador 2 elija su símbolo");
                char playerB = scanner.next().charAt(0);
                if (playerB == boardCharacter) {
                    System.out.println("Los jugadores no pueden tener el mismo carácter que el tablero");
                    scanner.nextLine();
                    break;
                }
                if (playerA == playerB) {
                    System.out.println("Los jugadores no pueden tener el mismo carácter");
                    scanner.nextLine();
                    break;
                }
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        board[i][j] = boardCharacter;
                    }
                }
                while (true) {
                    printBoard(board);
                    while (true) {
                        if (shiftA) {
                            player = playerA;
                        } else {
                            player = playerB;
                        }

                        System.out.println("Donde quiere colocar su " + player + " en la posición y");
                        if (scanner.hasNextInt()) {
                            posY = scanner.nextInt();
                        } else {
                            System.out.println("Tienes que ingresar un valor numérico");
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        }
                        System.out.println("Donde quiere colocar su " + player + " en la posición x");
                        if (scanner.hasNextInt()) {
                            posX = scanner.nextInt();
                        } else {
                            System.out.println("Tienes que ingresar un valor numérico");
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        }

                        if (posY >= board.length || posX >= board.length || posY < 0 || posX < 0) {
                            System.out.println("No existe esa posición");
                            printBoard(board);
                        } else if (board[posY][posX] != boardCharacter) {
                            System.out.println("Ya hay un jugador en esa posición");
                            printBoard(board);
                        } else {
                            board[posY][posX] = player;
                            printBoard(board);
                            shiftA = !shiftA;
                        }
                        if (winnerVerification(playerA, board) || winnerVerification(playerB, board)) {
                            return;
                        }
                        if (boardFull(boardCharacter, board)) {
                            return;
                        }

                    }

                }
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static boolean winnerVerification(char player, char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            count = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == board.length) {
                System.out.println("El jugador " + player + " ganó");
                return true;
            }
        }
        count = 0;
        for (int i = 0; i < board.length; i++) {
            count = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == player) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == board.length) {
                System.out.println("El jugador " + player + " ganó");
                return true;
            }
        }
        count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == player) {
                count++;
            } else {
                break;
            }
            if (count == board.length) {
                System.out.println("El jugador " + player + " ganó");
                return true;
            }
        }
        count = 0;
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[board.length - i - 1][i] == player) {
                count++;
            } else {
                break;
            }
            if (count == board.length) {
                System.out.println("El jugador " + player + " ganó");
                return true;
            }
        }
        return false;
    }

    public static boolean boardFull(char boardCharacter, char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != boardCharacter) {
                    count++;
                } else {
                    return false;
                }
            }
        }
        if (count == board.length * board.length) {
            System.out.println("Nadie ganó");
            return true;
        }
        return false;
    }
}