package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@WebServlet(name = "fileDownloadServlet", urlPatterns = "/file/fileDownload")
@Slf4j
public class FileDownloadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "/Users/parkheejun/NHNacademy/Java/20240422/hello/src/main/java/com/nhnacademy/hello/upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        if(Objects.isNull(fileName) || fileName.isEmpty()) {
            resp.sendError(400, "fileName parameter is needed");
            return;
        }

        String filePath = UPLOAD_DIR + File.separator + fileName;
        Path path = Path.of(filePath);

        if(!Files.exists(path)) {
            log.error("File not found: " + filePath);
            resp.sendError(404, "File not found: " + filePath);
            return;
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (InputStream is = Files.newInputStream(path);
        OutputStream os = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];

            int n;
            while(-1 != (n = is.read(buffer))) {
                os.write(buffer, 0, n);
            }
        } catch(IOException e) {
            log.error("", e);
        }
    }
}
