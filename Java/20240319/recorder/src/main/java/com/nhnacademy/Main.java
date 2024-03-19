package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    static String userId;
    static String userNickName;
    static String itemId;
    static String itemName;
    static int itemEnergy;
    static int itemPower;
    static int itemDefense;
    static int itemMovingSpeed;
    static int itemAttackSpeed;
    static String path;
    static String type;

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

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
                .desc("Show Data List")
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
                .hasArg()
                .argName("Data Base Name")
                .desc("Save To DB File Path")
                .build();
        options.addOption(dbFile);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("recoder", options);
            }

            if(commandLine.hasOption(dataType.getOpt())) {
                type = commandLine.getOptionValue(dataType.getOpt());
            }

            if(commandLine.hasOption(dbFile.getOpt())) {
                path = commandLine.getOptionValue(dbFile.getOpt());
            }

            if (commandLine.hasOption(addData.getOpt())) {
                if (type.equals("user")) {
                    userId = commandLine.getOptionValue(dataId.getOpt());
                    userNickName = commandLine.getOptionValue(dataName.getOpt());

                    User user = new User(userId, userNickName);
                    dataBase.addUser(user);
                    dataBase.save(path);
                } else if (type.equals("item")) {
                    itemId = commandLine.getOptionValue(dataId.getOpt());
                    itemName = commandLine.getOptionValue(dataName.getOpt());
                    itemEnergy = Integer.parseInt(commandLine.getOptionValue(dataEnergy.getOpt()));
                    itemPower = Integer.parseInt(commandLine.getOptionValue(dataPower.getOpt()));
                    itemDefense = Integer.parseInt(commandLine.getOptionValue(dataDefense.getOpt()));
                    itemMovingSpeed = Integer.parseInt(commandLine.getOptionValue(dataMovingSpeed.getOpt()));
                    itemAttackSpeed = Integer.parseInt(commandLine.getOptionValue(dataAttackSpeed.getOpt()));

                    Item item = new Item(itemId, itemName, itemEnergy, itemPower, itemDefense, itemMovingSpeed,
                            itemAttackSpeed);
                    dataBase.addItem(item);
                    dataBase.save(path);
                }
            }

            if (commandLine.hasOption(dataList.getOpt())) {
                dataBase.read(path);
                if (type.equals("user")) {
                    dataBase.printUsers();
                } else if (type.equals("item")) {
                    dataBase.printItems();
                }
            }

            if(commandLine.hasOption(machesCount.getOpt())) {
                dataBase.printMatches();
            }

            if(commandLine.hasOption(winCount.getOpt())) {
                dataBase.printWins();
            }

            if(commandLine.hasOption(history.getOpt())) {
                dataBase.read(path);
                dataBase.printChangeLog();
            }

        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}