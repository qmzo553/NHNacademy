package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class BingoServer {
    static final int BOARD_SIZE = 5;
    Socket socket1;
    Socket socket2;
    BufferedReader userInput1;
    BufferedReader userInput2;
    BufferedWriter userOutput1;
    BufferedWriter userOutput2;
    int port;
    String[][] bingoBoard1 = new String[BOARD_SIZE][BOARD_SIZE];
    String[][] bingoBoard2 = new String[BOARD_SIZE][BOARD_SIZE];
    Random random = new Random();

    public BingoServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 시작되었습니다.");
            System.out.println("참가자들 기다리는 중 ....");
            socket1 = serverSocket.accept();
            System.out.println("User 1 준비 완료.");
            socket2 = serverSocket.accept();
            System.out.println("User 2 준비 완료.");

            userInput1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            userInput2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            userOutput1 = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
            userOutput2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));

            while (!Thread.currentThread().isInterrupted()) {
                startGame();
            }
        } catch (Exception e) {
            //
        }
    }

    private void startGame() throws IOException {
        System.out.println("빙고보드 만들기 단계");
        makeBingoBorad();
        System.out.println("빙고 숫자 선택 단계");
        choiceBingoNum();
    }

    private void makeBingoBorad() throws IOException {
        socket2.getOutputStream().write("User1 이 빙고판을 만드는 중입니다....\n".getBytes());
        addBoardNum(socket1, bingoBoard1, userInput1);
        showBoard(bingoBoard1, userOutput1);
        socket1.getOutputStream().write("User2 가 빙고판을 만드는 중입니다....\n".getBytes());
        addBoardNum(socket2, bingoBoard2, userInput2);
        showBoard(bingoBoard2, userOutput2);
    }

    private void addBoardNum(Socket socket, String[][] bingoBoard, BufferedReader userInput) throws IOException {
        String num;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                socket.getOutputStream().write("빙고판의 숫자를 입력 해주세요 : \n".getBytes());
                num = userInput.readLine();
                bingoBoard[i][j] = num;
            }
        }
    }

    private void showBoard(String[][] bingoBoard, BufferedWriter userOutput) throws IOException {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                userOutput.write(bingoBoard[i][j] + " ");
            }
            userOutput.write("\n");
            userOutput.write("\n");
        }
        userOutput.flush();
    }

    private void choiceBingoNum() throws IOException {
        boolean gameOver = false;
        boolean user1Win = false;
        boolean user2Win = false;
    
        while (!gameOver) {
            user1Win = choice(socket1, userInput1, bingoBoard1);
            showBoard(bingoBoard1, userOutput1);
            if (user1Win) {
                gameOver = true;
                userOutput1.write("축하합니다! 빙고!\n");
                userOutput1.flush();
                userOutput2.write("아쉽게도 졌습니다.\n");
                userOutput2.flush();
                break;
            }
    
            user2Win = choice(socket2, userInput2, bingoBoard2);
            showBoard(bingoBoard2, userOutput2);
            if (user2Win) {
                gameOver = true;
                userOutput2.write("축하합니다! 빙고!\n");
                userOutput2.flush();
                userOutput1.write("아쉽게도 졌습니다.\n");
                userOutput1.flush();
                break;
            }
        }

        userInput1.close();
        userInput2.close();
        userOutput1.close();
        userOutput2.close();
        socket1.close();
        socket2.close();
    }

    private boolean choice(Socket socket, BufferedReader userInput, String[][] bingoBoard) throws IOException {
        socket.getOutputStream().write("선택할 숫자를 입력 해주세요. : \n".getBytes());
        String numStr = userInput.readLine();
        int num = Integer.parseInt(numStr); // 입력받은 문자열을 정수로 변환
        boolean found = false;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (Integer.parseInt(bingoBoard[i][j]) == num) { // 보드의 숫자와 비교
                    bingoBoard[i][j] = "O";
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            socket.getOutputStream().write(("입력한 숫자 " + num + "는 보드에 없습니다.\n").getBytes());
        }

        return checkForBingo(bingoBoard);
    }

    public boolean checkForBingo(String[][] bingoBoard) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (checkRow(row, bingoBoard)) {
                return true;
            }
        }

        for (int col = 0; col < BOARD_SIZE; col++) {
            if (checkCol(col, bingoBoard)) {
                return true;
            }
        }

        if (checkDiagonal(0, 0, 1, 1, bingoBoard) || checkDiagonal(0, BOARD_SIZE - 1, 1, -1, bingoBoard)) {
            return true;
        }

        return false;
    }

    private boolean checkRow(int row, String[][] bingoBoard) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (!bingoBoard[row][col].equals("O")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(int col, String[][] bingoBoard) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (!bingoBoard[row][col].equals("O")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(int startRow, int startCol, int rowStep, int colStep, String[][] bingoBoard) {
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!bingoBoard[row][col].equals("O")) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }
}