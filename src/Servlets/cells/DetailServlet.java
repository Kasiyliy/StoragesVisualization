package Servlets.cells;

import models.Source;
import models.Storage;
import repos.CellRepository;
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
@WebServlet(urlPatterns = "/cells/details")
public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        CellRepository cellRepository = new CellRepository();
        req.setAttribute("cell" ,cellRepository.getById(id));
        req.getRequestDispatcher("/views/cells/details.jsp").forward(req,resp);
    }
}
