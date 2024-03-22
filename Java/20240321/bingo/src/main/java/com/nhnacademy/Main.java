package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    
    static int port;
    static String address;

    public static void main(String[] args) {
        Options options = new Options();
        Option helpOption = new Option("h", false, "Help");
        options.addOption(helpOption);

        Option bingo = Option.builder("b")
                .longOpt("bingo")
                .desc("bingo start option")
                .build();
        options.addOption(bingo);

        Option modeListen = Option.builder("l")
                .longOpt("listen")
                .desc("change listen mode")
                .build();
        options.addOption(modeListen);

        Option addressOption = Option.builder("a")
                .longOpt("address")
                .hasArg()
                .argName("IP Adress")
                .desc("Input Ip Adress")
                .build();
        options.addOption(addressOption);

        Option portOption = Option.builder("p")
                .longOpt("port")
                .hasArg()
                .argName("Port Number")
                .desc("Input Port")
                .build();
        options.addOption(portOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("bingo", options);
            }

            if(commandLine.hasOption(bingo.getOpt())) {
                if (commandLine.hasOption(portOption.getOpt())) {
                    port = Integer.parseInt(commandLine.getOptionValue(portOption.getOpt()));
                }
    
                if(commandLine.hasOption(modeListen)) {
                    BingoServer bingoServer = new BingoServer(port);
                    bingoServer.start();
                }
    
                if(commandLine.hasOption(addressOption.getOpt())) {
                    address = commandLine.getOptionValue(addressOption.getOpt());
                    User user  = new User(address, port);
                    Thread thread = new Thread(user);
                    thread.start();
                }
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}
