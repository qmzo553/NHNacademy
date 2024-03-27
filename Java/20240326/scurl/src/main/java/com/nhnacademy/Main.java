package com.nhnacademy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                Option verboseOption = Option.builder("v")
                                .longOpt("verbose")
                                .desc("Prints the request, response header.")
                                .build();
                options.addOption(verboseOption);

                Option headerOption = Option.builder("H")
                                .longOpt("line")
                                .hasArg()
                                .desc("Sends any header to the server.")
                                .build();
                options.addOption(headerOption);

                Option dataOption = Option.builder("d")
                                .longOpt("data")
                                .hasArg()
                                .desc("Transfer data to POST, PUT, etc.")
                                .build();
                options.addOption(dataOption);

                Option methodOption = Option.builder("X")
                                .longOpt("command")
                                .hasArg()
                                .desc("Specify the method to use. If not specified, the default is GET")
                                .build();
                options.addOption(methodOption);

                Option lOption = Option.builder("L")
                                .desc("If the server's response is 30x series, it follows the next response.")
                                .build();
                options.addOption(lOption);

                Option fOption = Option.builder("F")
                                .desc("Configure and send multipart/form-data. You can use @filename for the content part.")
                                .build();
                options.addOption(fOption);

                Option help = Option.builder("h")
                                .longOpt("help")
                                .desc("Help")
                                .build();
                options.addOption(help);

                CommandLineParser parser = new DefaultParser();
                CommandLine cmd = parser.parse(options, args);

                if (cmd.getArgs().length == 0 && !cmd.hasOption("H")) {
                        System.err.println("URL이 필요합니다. ");
                        System.exit(0);
                }

                String version = "HTTP/1.1";
                String method = "GET";
                String location = "/get";
                String header = "";
                String host = cmd.getArgs()[0];
                String message = "";

                String pattern = "(http://)?(.*?)/(.*)";
                Pattern p = Pattern.compile(pattern);
                Matcher matcher = p.matcher(host);
                while (matcher.find()) {
                        host = matcher.group(2);
                        location = "/" + matcher.group(3);
                }

                Scurl scurl = new Scurl();

                if (cmd.hasOption(("h"))) {
                        HelpFormatter formatter = new HelpFormatter();
                        formatter.printHelp("ls", options);
                        System.exit(0);
                }

                if (cmd.hasOption("d")) {
                        message = cmd.getOptionValue(dataOption.getOpt());
                        message = "Content-Length: " + message.length() + "\r\n\r\n" + message;
                        method = "POST"; // default값 변경. 밑에서 바꿔줌
                        location = "/post";
                }
                if (cmd.hasOption("X")) {
                        method = cmd.getOptionValue(methodOption.getOpt());
                }
                if (cmd.hasOption("v")) {
                        scurl.setVerboseOn();
                }
                if (cmd.hasOption("H")) {
                        header = cmd.getOptionValue(headerOption.getOpt()) + "\r\n";
                }
                if (cmd.hasOption("L")) {
                        scurl.setRedirectCount(5);
                }

                scurl.setCommand(CommandLineGenerator.generate(method, location, version, host, header + message));

                Thread scurlThread = new Thread(scurl);
                scurlThread.start();
        }
}
