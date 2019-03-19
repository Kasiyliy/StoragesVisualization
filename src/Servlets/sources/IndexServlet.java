package Servlets.sources;

import models.Source;
import repos.SourceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/sources")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SourceRepository sourceRepository = new SourceRepository();
        List<Source> list = sourceRepository.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/views/sources/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("_method") != null && req.getParameter("_method").equalsIgnoreCase("delete")){
            doDelete(req,resp);
            return;
        }

        String name = req.getParameter("name");
        String latitude = req.getParameter("latitude");
        String longitude = req.getParameter("longitude");
        Source source = new Source(null, name,longitude, latitude);
        SourceRepository sourceRepository = new SourceRepository();
        sourceRepository.add(source);
        resp.sendRedirect("/sources");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id  = null;
        String redirect = req.getHeader("referer");
        try{
            id = Long.valueOf(req.getParameter("id"));
            SourceRepository sourceRepository = new SourceRepository();
            if(!sourceRepository.deleteById(id)){
                redirect += "?error=2";
            };
        }catch (Exception e){
            redirect += "?error=1";
        }

        resp.sendRedirect(redirect);
    }
}
