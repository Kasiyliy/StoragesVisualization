package Servlets.cells;

import models.Cell;
import models.Storage;
import org.apache.commons.io.FileUtils;
import repos.CellRepository;
import repos.SourceRepository;
import repos.StorageRepository;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/cells")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100)
public class IndexServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "images";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("_method") != null && request.getParameter("_method").equalsIgnoreCase("delete")){
            doDelete(request,response);
            return;
        }


        String name = request.getParameter("name");
        String invNumber = request.getParameter("invNumber");
        Double crn = Double.valueOf(request.getParameter("crn"));
        Double costPrice = Double.valueOf(request.getParameter("costPrice"));
        Long storageId = Long.valueOf(request.getParameter("storageId"));

        String expireDateStr = request.getParameter("expireDate");
        String ownDateStr = request.getParameter("ownDate");
        String redirect = request.getHeader("referer");
        try{
            Date expireDate = convertStrToDate(expireDateStr);
            Date ownDate = convertStrToDate(ownDateStr);
            StorageRepository storageRepository = new StorageRepository();
            Storage storage = storageRepository.getById(storageId);
            Part filePart = request.getPart("image");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            File targetFile = new File("views/images/" + fileName);
            FileUtils.copyInputStreamToFile(fileContent, targetFile);
            CellRepository cellRepository = new CellRepository();
            Cell cell =  new Cell(null, name, invNumber, expireDate, ownDate, crn, costPrice, storage, targetFile.getPath());
            cellRepository.add(cell);
            response.sendRedirect(redirect);
        }catch (Exception e){
            response.sendRedirect(redirect+"?error=1");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id  = null;
        String redirect = req.getHeader("referer");
        try{
            id = Long.valueOf(req.getParameter("id"));
            CellRepository cellRepository = new CellRepository();
            if(!cellRepository.deleteById(id)){
                redirect += "?error=2";
            };
        }catch (Exception e){
            redirect += "?error=1";
        }

        resp.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CellRepository cellRepository = new CellRepository();
        StorageRepository storageRepository = new StorageRepository();
        List<Cell> list = cellRepository.getAll();
        List<Storage> storageList = storageRepository.getAll();
        request.setAttribute("list", list);
        request.setAttribute("storageList", storageList);
        request.getRequestDispatcher("/views/cells/index.jsp").forward(request,response);
    }

    private Date convertStrToDate(String dateStr) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        return formatter.parse(dateStr);
    }
}
