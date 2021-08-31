package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[][] board = new int[3][3];
        boolean isMatchOver = false, playerOneTurn = true;
        int number, integer = 0, remainder = 0;
        String playerOne, playerTwo;

        //preenchendo o tabuleiro com '-'
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = '-';
            }
        }

        Scanner input = new Scanner(System.in);

        //introducao
        System.out.println("\nWELCOME TO THE WORLD'S MOST AMAZING TIC TAC TOE!\n");
        System.out.println("Useful information:");
        System.out.println("\t -> Player 1 goes with 'x'.");
        System.out.println("\t -> Player 2 goes with 'o'.");
        System.out.println("\nHow to play:");
        System.out.println("\t - Step 1: Check the placeholder board and pick a number.");
        System.out.println("\t - Step 2: Wait for your turn and follow Step 1.");
        System.out.println("\n\t\t\t\tGood luck :)");


        //entrada de nome dos jogadores
        System.out.print("\nPLAYER 1 NAME: ");
        playerOne = input.nextLine();

        do {
            System.out.print("\nPLAYER 2 NAME: ");
            playerTwo = input.nextLine();

            if(!playerTwo.equals(playerOne))
                continue;

            System.out.println("Name already used. Try another one.");

        } while(playerTwo.equals(playerOne));

        //while responsavel pelo funcionamento do jogo
        while(!isMatchOver) {

            printBoard(board);

            //verifica qual jogador ira jogar
            if(playerOneTurn) {
                System.out.printf("\n%s's turn.\n", playerOne);
            } else {
                System.out.printf("\n%s's turn.\n", playerTwo);
            }

            //entrada de posicao no tabuleiro
            do {
                System.out.print("Pick a number: ");
                number = input.nextInt();

                if (number < 0 || number > 8) {
                    System.out.println("Invalid position. Try again.");
                    continue;
                }

                integer = (number / 3);
                remainder = (number % 3);

                if(board[integer][remainder] != '-') {
                    System.out.println("Position already picked.");
                }

            } while(number < 0 || number > 8 || board[integer][remainder] != '-');

            //verifica qual jogador para colocar seu respectivo caracter
             if(playerOneTurn) {
                board[integer][remainder] = 'x';
            } else {
                board[integer][remainder] = 'o';
            }

             //verifica se alguem ja venceu
            if (winner(board) == 1) {
                System.out.println("\n\t   PLAYER 1 WON!");
                isMatchOver = true;
                continue;
            } else if(winner(board) == 0) {
                System.out.println("\n\t   PLAYER 2 WON!");
                isMatchOver = true;
                continue;
            }

            //verifica se foi empate
            if(draw(board)) {
                System.out.println(" \t   DRAW!");
                isMatchOver = true;
            } else {
                playerOneTurn = !playerOneTurn;
            }
        }

        printBoard(board);
    }

    //funcao para verificar vencedor
    public static int winner(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if(board[i][0] == 'x') {
                    return 1;
                } else if(board[i][0] == 'o') {
                    return 0;
                }
            }
        }

        for(int j = 0; j < board.length; j++) {
            if(board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                if(board[0][j] == 'x') {
                    return 1;
                } else if(board[0][j] == 'o') {
                    return 0;
                }
            }
        }

        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if(board[0][0] == 'x') {
                return 1;
            } else if(board[0][0] == 'o') {
                return 0;
            }
        }

        if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if(board[0][2] == 'x') {
                return 1;
            } else if(board[0][2] == 'o') {
                return 0;
            }
        }



        return 2;
    }

    //funcao para imprimir o tabuleiro
    public static void printBoard(int[][] board) {
        int placeholderBoard = 0;

        System.out.println("\n\tG A M E   B O A R D\n");
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                System.out.printf("\t|%d|\t", placeholderBoard);
                placeholderBoard++;
            }

            System.out.println();
        }

        System.out.println();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                String TEXT_RED = "\u001B[31m";
                String TEXT_RESET = "\u001B[0m";
                String TEXT_CYAN = "\u001B[36m";
                if(board[i][j] == 'x')
                    System.out.printf("\t|" + TEXT_RED + "%c" + TEXT_RESET + "|\t", board[i][j]);
                else if(board[i][j] == 'o') {
                    System.out.printf("\t|" + TEXT_CYAN + "%c" + TEXT_RESET + "|\t", board[i][j]);
                } else {
                    System.out.printf("\t|%c|\t", board[i][j]);
                }
            }

            System.out.println();
        }

    }

    //funcoa para verificar o empate
    public static boolean draw(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }
}
