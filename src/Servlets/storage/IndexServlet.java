package Servlets.storage;


import models.Source;
import models.Storage;

import repos.SourceRepository;
import repos.StorageRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/storages")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("_method") != null && request.getParameter("_method").equalsIgnoreCase("delete")){
            doDelete(request,response);
            return;
        }
        String redirect = request.getHeader("referer");
        String name = request.getParameter("name");
        Long id = Long.valueOf(request.getParameter("sourceId"));
        SourceRepository sourceRepository = new SourceRepository();
        Source source = sourceRepository.getById(id);
        if(source!=null){
            Storage storage = new Storage(null, name,source);
            StorageRepository storageRepository = new StorageRepository();
            storageRepository.add(storage);
            response.sendRedirect(redirect);
        }else{
            response.sendRedirect(redirect+"?error=1");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StorageRepository storageRepository = new StorageRepository();
        SourceRepository sourceRepository = new SourceRepository();

        request.setAttribute("storageList", storageRepository.getAll());
        request.setAttribute("sourceList", sourceRepository.getAll());

        request.getRequestDispatcher("/views/storages/index.jsp").forward(request,response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String redirect = req.getHeader("referer");
        try{
            StorageRepository storageRepository = new StorageRepository();
            storageRepository.deleteById(Long.valueOf(req.getParameter("storageId")));
            response.sendRedirect(redirect);
        }catch (Exception e){
            response.sendRedirect(redirect+"?error=1");
        }
    }
}
