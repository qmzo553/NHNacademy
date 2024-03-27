package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Scurl implements Runnable {
    String host = "httpbin.org";
    int port = 80;
    int redirectCount = 0;
    String location="";

    String command;
    boolean verbose = false;

    public void setVerboseOn() {
        verbose = true;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setRedirectCount(int redirectCount) {
        this.redirectCount = redirectCount;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(host, port)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(command);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder data = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                data.append((char) c);
            }

            DataExtractor dataExtractor = new DataExtractor(data.toString());

            if (verbose) {
                System.out.print(dataExtractor.header);
            }
            System.out.print(dataExtractor.body + "\n");

            if (redirectCount > 0 && (dataExtractor.header.contains("301") || dataExtractor.header.contains("302")
                    || dataExtractor.header.contains("307") || dataExtractor.header.contains("308"))) {
                redirectCount--;

                if(data.toString().contains("ocation")){
                    location = data.substring(data.indexOf("ocation: ") + 9);
                    location = location.substring(0, location.indexOf("\r"));
                }

                String[] commandSplits = command.split(" ");
                commandSplits[1] = location;
                System.out.println("new Location: " + location);

                StringBuilder sb = new StringBuilder();
                for (String string : commandSplits) {
                    sb.append(string).append(" ");
                }
                command = sb.toString();

                System.out.println("==============\n" + command);

                run();
            }
        } catch (IOException e) {
            //
        }
    }
}
