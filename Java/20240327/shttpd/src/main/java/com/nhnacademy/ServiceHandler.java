package com.nhnacademy;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ServiceHandler implements Runnable {
    static final String DOCUMENT_ROOT = "/www";
    static final String UPLOAD_DIR = "/";
    static final String CRLF = "\r\n";
    Socket socket;
    Logger log;

    public ServiceHandler(Socket socket) {
        this.socket = socket;
        log = LogManager.getLogger(this.getClass().getSimpleName());
    }

    String getFileList(Path path) {
        StringBuilder builder = new StringBuilder();

        try(Stream<Path> stream = Files.list(path)) {
            stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .forEach(p -> builder.append(p.toString()).append(CRLF));
        } catch(IOException ignore) {
            throw new InvalidStatusException(403);
        }

        return builder.toString();
    }

    String getFile(Path path) {
        StringBuilder builder = new StringBuilder();

        try(Stream<String> lines = Files.lines(path)) {
            lines.forEach(x -> builder.append(x).append(CRLF));
        } catch (IOException ignore) {
            throw new InvalidStatusException(403);
        }

        return builder.toString();
    }

    boolean isCheckPath(Path path) {
        Path requestedAbsolutePath = Paths.get(DOCUMENT_ROOT).resolve(path).normalize();
        Path documentRootAbsolutePath = Paths.get(DOCUMENT_ROOT).normalize();
        Path relativePath = documentRootAbsolutePath.relativize(requestedAbsolutePath);
        return relativePath.startsWith("..");
    }

    public Response process(Request request) {
        try {
            if(request.getMethod().equals("GET")) {
                Path relativePath = Paths.get("." + request.getPath());

                if(isCheckPath(relativePath)) {
                    throw new InvalidStatusException(403);
                }

                if(!Files.isReadable(relativePath)) {
                    throw new InvalidStatusException(403);
                }

                Response response = new Response(request.getVersion(), 200, "OK");
                StringBuilder contentType = new StringBuilder();
                contentType.append("text");

                if(Files.isDirectory(relativePath)) {
                    contentType.append("; charset=utf-8");
                    response.setBody(getFileList(relativePath).getBytes(StandardCharsets.UTF_8));
                } else if (Files.isRegularFile(relativePath)) {
                    String filename = relativePath.getFileName().toString();
                    // if(filename.contains(".")) {
                    //     throw new UnknownContentTypeException();
                    // }

                    contentType.append("/")
                                .append(filename.substring(filename.lastIndexOf(".") + 1)) 
                                .append("; charset=utf-8");
                    response.setBody(getFile(relativePath).getBytes(StandardCharsets.UTF_8));
                }
                response.addField("content-type", contentType.toString());

                return response;
            } else if(request.getMethod().equals("POST")) {
                Path path = Paths.get("." + UPLOAD_DIR);

                Response response = new Response(request.getVersion(), 200, "OK");
                StringBuilder contentType = new StringBuilder();
                contentType.append("multipart");

                if(isCheckPath(path)) {
                    throw new InvalidStatusException(403);
                }

                if(!Files.isReadable(path)) {
                    throw new InvalidStatusException(403);
                }

                contentType.append("/")
                                .append("form-data") 
                                .append("; charset=utf-8");
            }
            throw new InvalidStatusException(400);
        } catch (InvalidStatusException e) {
            return new Response(request.getVersion(), e.getCode());
        }
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedOutputStream writer = new BufferedOutputStream(socket.getOutputStream())) {
            while(!Thread.currentThread().isInterrupted()) {
                String requestLine = reader.readLine();

                if(requestLine == null) {
                    break;
                }

                String[] tokens = requestLine.split("\\s", 3);
                if(tokens.length != 3) {
                    throw new IllegalArgumentException();
                }

                Request request = new Request(tokens[0], tokens[1], tokens[2]);

                String fieldLine;
                while((fieldLine = reader.readLine()) != null) {
                    if(fieldLine.length() == 0) {
                        break;
                    }

                    request.addField(fieldLine);
                }

                if(request.hasField(Request.FIELD_CONTENT_LENGTH)) {
                    char[] buffer = new char[request.getContentLength()];

                    int bodyLength = reader.read(buffer, 0, request.getContentLength());
                    if(bodyLength == request.getContentLength()) {
                        request.setBody(buffer);
                    }
                }

                Response response = process(request);
                log.trace(response);

                writer.write(response.getBytes());
                writer.flush();
            }
        } catch (IOException ignore) {
            //
        } finally {
            try {
                socket.close();
            } catch (IOException ignore) {
                //
            }
        }
    }
    
}