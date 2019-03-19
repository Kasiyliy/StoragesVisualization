package Servlets.utils;

import org.apache.commons.io.FilenameUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

/**
 * @author Assylkhan
 * on 20.03.2019
 * @project AltynAlmas
 */
@WebServlet(urlPatterns = "/getImage")
public class ImageServlet extends HttpServlet {


        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String file = request.getParameter("file");
            if(file !=null){
                File localFile = new File(file);

                if(localFile.exists()){
                    MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
                    String mimeType = fileTypeMap.getContentType(localFile.getName());
                    response.setContentType(mimeType);
                    OutputStream out = response.getOutputStream();
                    FileInputStream in = new FileInputStream(file);
                    int size = in.available();
                    byte[] content = new byte[size];
                    in.read(content);
                    out.write(content);
                    in.close();
                    out.close();
                }
            }
        }


}
