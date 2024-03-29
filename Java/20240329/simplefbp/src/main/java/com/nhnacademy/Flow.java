package com.nhnacademy;

public class Flow {
    public static void main(String[] args) {
        flowOne();
        // flowTwo();
        // flowThree();
    }

    public static void flowOne() {
        ConsoleInNode consoleInNode1 = new ConsoleInNode("V1", "V1");
        ConsoleInNode consoleInNode2 = new ConsoleInNode("V2", "V2");

        FunctionNode functionNode1 = new FunctionNode("+", "+", "+");

        TerminalOutNode terminalOutNode1 = new TerminalOutNode("R1", "R1");

        Pipe pipe1 = new Pipe(5, "pipe1");
        Pipe pipe2 = new Pipe(5, "pipe2");
        Pipe pipe3 = new Pipe(5, "pipe3");

        consoleInNode1.setOutputPipe(pipe1);
        consoleInNode2.setOutputPipe(pipe2);

        functionNode1.setInputPipe(pipe1);
        functionNode1.setInputPipe(pipe2);
        functionNode1.setOutputPipe(pipe3);

        terminalOutNode1.setInputPipe(pipe3);

        consoleInNode1.start();
        consoleInNode2.start();

        functionNode1.start();
       
        terminalOutNode1.start();
    }

    public static void flowTwo() {
        ConsoleInNode consoleInNode1 = new ConsoleInNode("V1", "V1");
        ConsoleInNode consoleInNode2 = new ConsoleInNode("V2", "V2");
        ConsoleInNode consoleInNode3 = new ConsoleInNode("V3", "V3");

        FunctionNode functionNode1 = new FunctionNode("+", "+", "+");
        FunctionNode functionNode2 = new FunctionNode("/", "/", "/");

        TerminalOutNode terminalOutNode1 = new TerminalOutNode("R", "R");

        Pipe pipe1 = new Pipe(5, "pipeA");
        Pipe pipe2 = new Pipe(5, "pipeB");
        Pipe pipe3 = new Pipe(5, "pipeC");
        Pipe pipe4 = new Pipe(5, "pipeD");
        Pipe pipe5 = new Pipe(5, "pipeE");

        consoleInNode1.setOutputPipe(pipe1);
        consoleInNode2.setOutputPipe(pipe2);
        consoleInNode3.setOutputPipe(pipe3);

        functionNode1.setInputPipe(pipe1);
        functionNode1.setInputPipe(pipe2);
        functionNode1.setOutputPipe(pipe3);

        functionNode2.setInputPipe(pipe3);
        functionNode2.setInputPipe(pipe4);
        functionNode2.setOutputPipe(pipe5);

        terminalOutNode1.setInputPipe(pipe5);

        consoleInNode1.start();
        consoleInNode2.start();
        consoleInNode3.start();

        functionNode1.start();
        functionNode2.start();

        terminalOutNode1.start();
    }

    public static void flowThree() {
        ConsoleInNode consoleInNode1 = new ConsoleInNode("V1", "V1");
        ConsoleInNode consoleInNode2 = new ConsoleInNode("V2", "V2");
        ConsoleInNode consoleInNode3 = new ConsoleInNode("V3", "V3");
        ConsoleInNode consoleInNode4 = new ConsoleInNode("V4", "V4");

        FunctionNode functionNode1 = new FunctionNode("+", "+", "+");
        FunctionNode functionNode2 = new FunctionNode("-", "-", "-");
        FunctionNode functionNode3 = new FunctionNode("*", "*", "*");

        TerminalOutNode terminalOutNode1 = new TerminalOutNode("R", "R");

        Pipe pipe1 = new Pipe(5, "pipe1");
        Pipe pipe2 = new Pipe(5, "pipe2");
        Pipe pipe3 = new Pipe(5, "pipe3");
        Pipe pipe4 = new Pipe(5, "pipe4");
        Pipe pipe5 = new Pipe(5, "pipe5");
        Pipe pipe6 = new Pipe(5, "pipe6");
        Pipe pipe7 = new Pipe(5, "pipe7");

        consoleInNode1.setOutputPipe(pipe1);
        consoleInNode2.setOutputPipe(pipe2);
        consoleInNode3.setOutputPipe(pipe4);
        consoleInNode4.setOutputPipe(pipe5);

        functionNode1.setInputPipe(pipe1);
        functionNode1.setInputPipe(pipe2);
        functionNode1.setOutputPipe(pipe3);

        functionNode2.setInputPipe(pipe4);
        functionNode2.setInputPipe(pipe5);
        functionNode2.setOutputPipe(pipe6);

        functionNode3.setInputPipe(pipe3);
        functionNode3.setInputPipe(pipe6);
        functionNode3.setOutputPipe(pipe7);

        terminalOutNode1.setInputPipe(pipe7);

        consoleInNode1.start();
        consoleInNode2.start();
        consoleInNode3.start();
        consoleInNode4.start();

        functionNode1.start();
        functionNode2.start();
        functionNode3.start();

        terminalOutNode1.start();
    }
}
