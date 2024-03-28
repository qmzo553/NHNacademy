package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Shttpd {
    
    public static void main(String[] args) {
        int port = 80;

        Options options = new Options();

        Option helpOption = new Option("h", false, "Help");
        options.addOption(helpOption);
        
        Option portOpt = Option.builder("p")
                            .longOpt("port")
                            .hasArg()
                            .desc("Input Port Number")
                            .build();
        options.addOption(portOpt);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("scurl", options);
            }

            if (cmd.hasOption(portOpt.getOpt())) {
                port = Integer.parseInt(cmd.getOptionValue(portOpt.getOpt()));
            }

            ShttpServer server = new ShttpServer(port);
            Thread thread = new Thread(server);
            thread.start();
            thread.join();
        } catch (org.apache.commons.cli.ParseException ignore) {
            //
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }
}
