package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class SimpleCurl {

    public static void main(String[] args) {
        String header;
        String method;
        String data;
        boolean verbose;
        String url = args[args.length - 1];

        Options options = new Options();

        Option helpOption = new Option("h", false, "Help");
        options.addOption(helpOption);

        Option verboseOpt = Option.builder("v")
                .longOpt("verbose")
                .desc("Print Request or Response header")
                .build();
        options.addOption(verboseOpt);

        Option dataOpt = Option.builder("d")
                .longOpt("data")
                .hasArg()
                .argName("data")
                .desc("Input Data")
                .build();
        options.addOption(dataOpt);

        Option headerOpt = Option.builder("H")
                .longOpt("header")
                .hasArg()
                .argName("line")
                .desc("Input Header")
                .build();
        options.addOption(headerOpt);

        Option methodOpt = Option.builder("X")
                .longOpt("method")
                .hasArg()
                .argName("command")
                .desc("select use method")
                .build();
        options.addOption(methodOpt);

        Option redirectOpt = Option.builder("L")
                .longOpt("redirect")
                .desc("redirect next response")
                .build();
        options.addOption(redirectOpt);

        Option formOpt = Option.builder("F")
                .longOpt("form")
                .hasArg()
                .argName("content")
                .desc("content from")
                .build();
        options.addOption(formOpt);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("scurl", options);
            }

            SimpleCurlClient simpleCurlClient = new SimpleCurlClient(url);

            if(commandLine.hasOption(verboseOpt.getOpt())) {
                verbose = true;
                simpleCurlClient.setVerbose(verbose);
            } 

            if(commandLine.hasOption(headerOpt.getOpt())) {
                header = commandLine.getOptionValue(headerOpt.getOpt());
                simpleCurlClient.setHeader(header);
            }

            if (commandLine.hasOption(methodOpt.getOpt())) {
                method = commandLine.getOptionValue(methodOpt.getOpt());
                simpleCurlClient.setMethod(method);

                if(commandLine.hasOption(dataOpt.getOpt())) {
                    data = commandLine.getOptionValue(dataOpt.getOpt());
                    simpleCurlClient.setData(data);
                }
            } 

            simpleCurlClient.connect();

        } catch (org.apache.commons.cli.ParseException ignore) {
            //
        }
    }
}
