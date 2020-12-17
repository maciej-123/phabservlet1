import java.io.*;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(urlPatterns={"/druglist","/drugstock"},loadOnStartup = 1)
public class phabservlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("test");

        String ending = req.getServletPath();
        if(ending.equals("/druglist")) {
            resp.getWriter().write("<b>List of Drugs Database</b>");


            //ResultSet result = statement.executeQuery(sql);

//            String sqlfile = "postgres_public_test_database.sql";
//
//            convertFiletoString FtoS = new convertFiletoString(sqlfile);
//            resp.getWriter().write(FtoS.getStringOutput());
            
              resp.getWriter().write(" TEST ");

        }

        if(ending.equals("/drugstock")) {
            resp.getWriter().write("<b>Drug Stock</b>");
            resp.getWriter().write(" TEST ");
        }



        ///just use /druglist in url http://localhost:8020/druglist

        //export sql file to heroku

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        resp.setContentType("text/html");

        resp.getWriter().write("Thank you client! "+reqBody);
    }


}