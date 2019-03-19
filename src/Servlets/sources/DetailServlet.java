package Servlets.sources;

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
import java.util.List;

/**
 * @author Assylkhan
 * on 20.03.2019
 * @project AltynAlmas
 */
@WebServlet(urlPatterns = "/sources/details")
public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        SourceRepository sourceRepository = new SourceRepository();
        Source source = sourceRepository.getById(id);
        StorageRepository storageRepository = new StorageRepository();
        List<Storage> storageList = storageRepository.getAllBySource(source.getId());
        req.setAttribute("source" ,source);
        req.setAttribute("storageList" ,storageList);
        req.getRequestDispatcher("/views/sources/details.jsp").forward(req,resp);
    }
}
