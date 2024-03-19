package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {

        Options options = new Options();

        Option classPath = Option.builder("classpath")
                .longOpt("class-path")
                .hasArg()
                .argName("path")
                .desc("Class Path")
                .build();

        options.addOption(classPath);

        Option module = Option.builder("m")
                .longOpt("module")
                .hasArg()
                .argName("module")
                .desc("Modules")
                .build();

        options.addOption(module);

        Option group = Option.builder("g")
                .desc("Global")
                .build();

        options.addOption(group);

        Option help = Option.builder("h")
        .longOpt("help")
        .desc("Help")
        .build();

        options.addOption(help);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if(cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ls", options);
            System.exit(0);
        }

        if (cmd.hasOption(classPath.getOpt())) {
            String classPathValues = cmd.getOptionValue(classPath.getOpt());
            System.out.println("Class Path : " + classPathValues);
        }

        if (cmd.hasOption(module.getOpt())) {
            System.out.println("Module : ");
        }

        if (cmd.hasOption(group.getOpt())) {
            System.out.println("Group : ");
        }

        System.out.println(cmd.getArgList());
    }
}