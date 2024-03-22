package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class ChatRoom {
    static int port = 0;

    public static void main(String[] args) {
        Options options = new Options();

        Option helpOption = new Option("h", false, "Help");
        options.addOption(helpOption);

        Option modeListen = Option.builder("l")
                .longOpt("listen")
                .desc("Change Listen Mode")
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
                formatter.printHelp("chatroom", options);
            }

            if (commandLine.hasOption(portOption.getOpt())) {
                port = Integer.parseInt(commandLine.getOptionValue(portOption.getOpt()));
            } else {
                port = 1234;
            }

            if (commandLine.hasOption(modeListen.getOpt())) {
                ChatRoomServer chatRoomServer = new ChatRoomServer(port); 
                Thread thread = new Thread(chatRoomServer);
                thread.start();
                System.out.println("서버가 만들어 졌습니다.");
            } else {
                ChatRoomClient chatRoomClient = new ChatRoomClient(port);
                Thread thread = new Thread(chatRoomClient);
                thread.start();
            }
        } catch (org.apache.commons.cli.ParseException ignore) {
            //
        }
    }

}
