package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100,
        location = "/Users/parkheejun/NHNacademy/Java/20240422/hello/src/main/java/com/nhnacademy/hello/upload"
)

@Slf4j
public class FileUploadServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/parkheejun/NHNacademy/Java/20240422/hello/src/main/java/com/nhnacademy/hello/upload";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(Part part : req.getParts()) {
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

            if(contentDisposition.contains("filename=")) {
                String fileName = extractFileName(contentDisposition);

                if(part.getSize() > 0) {
                    part.write(UPLOAD_DIR + File.separator + fileName);
                    part.delete();
                }
            } else {
                String formValue = req.getParameter(part.getName());
                log.error("{} = {}", part.getName(), formValue);
            }
        }
        resp.sendRedirect("/");
    }

    private String extractFileName(String contentDisposition) {
        log.error("cotenetDisposition : {}", contentDisposition);
        for(String token : contentDisposition.split(";")) {
            if(token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }

        return null;
    }
}
