package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        Option helpOption = new Option("h", false, "Help");
        options.addOption(helpOption);

        Option addData = Option.builder("a")
                .longOpt("add")
                .desc("Add Data")
                .build();
        options.addOption(addData);

        Option dataType = Option.builder("t")
                .longOpt("type")
                .hasArg()
                .argName("type")
                .desc("Data Type")
                .build();
        options.addOption(dataType);

        Option dataId = Option.builder("i")
                .longOpt("id")
                .hasArg()
                .argName("id")
                .desc("Data Id")
                .build();
        options.addOption(dataId);

        Option dataName = Option.builder("n")
                .longOpt("name")
                .hasArg()
                .argName("name")
                .desc("Data Name")
                .build();
        options.addOption(dataName);

        Option dataList = Option.builder("l")
                .longOpt("list")
                .hasArg()
                .argName("Data List")
                .desc("Add Data")
                .build();
        options.addOption(dataList);

        Option machesCount = Option.builder("c")
                .longOpt("count")
                .desc("Show Maches")
                .build();
        options.addOption(machesCount);

        Option winCount = Option.builder("W")
                .longOpt("Win")
                .desc("Show Win Count")
                .build();
        options.addOption(winCount);

        Option dataEnergy = Option.builder("e")
                .longOpt("energy")
                .hasArg()
                .argName("energy")
                .desc("Item Energy")
                .build();
        options.addOption(dataEnergy);

        Option dataPower = Option.builder("p")
                .longOpt("power")
                .hasArg()
                .argName("power")
                .desc("Item Power")
                .build();
        options.addOption(dataPower);

        Option dataDefense = Option.builder("d")
                .longOpt("defence")
                .hasArg()
                .argName("defense")
                .desc("Item Defense")
                .build();
        options.addOption(dataDefense);

        Option dataMovingSpeed = Option.builder("m")
                .longOpt("Moving-speed")
                .hasArg()
                .argName("movingSpeed")
                .desc("Item Moving Speed")
                .build();
        options.addOption(dataMovingSpeed);

        Option dataAttackSpeed = Option.builder("A")
                .longOpt("attack-speed")
                .hasArg()
                .argName("attackSpeed")
                .desc("Item Attack Speed")
                .build();
        options.addOption(dataAttackSpeed);

        Option history = Option.builder("L")
                .longOpt("history")
                .desc("Show History")
                .build();
        options.addOption(history);

        Option dbFile = Option.builder("f")
                .longOpt("db-file")
                .desc("Show DB File")
                .build();
        options.addOption(dbFile);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("recoder", options);
                System.exit(0);
            } 
            if(commandLine.hasOption(addData.getOpt())) {
                
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }
}