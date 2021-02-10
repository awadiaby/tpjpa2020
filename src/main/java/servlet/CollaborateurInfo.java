package servlet;

import dao.CollaborateurDAO;
import entities.Collaborateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "collaborateurInfo",
        urlPatterns = {"/CollaborateurInfo"})
public class CollaborateurInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        Collaborateur clb = new Collaborateur();
        clb.setEmail(request.getParameter("email"));
        clb.setNom(request.getParameter("name"));
        clb.setPrenom(request.getParameter("firstname"));

        CollaborateurDAO dao = new CollaborateurDAO();
        dao.saveCollaborateur(clb);

        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + request.getParameter("name") + "\n" +
                " <LI>Prenom: "
                + request.getParameter("firstname") + "\n" +
                " <LI>Email: "
                + request.getParameter("email") + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");
    }
}

