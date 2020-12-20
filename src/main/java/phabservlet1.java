import java.io.*;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(urlPatterns={"/text_database","/create_test_database","/return_test_database"},loadOnStartup = 1)
public class phabservlet1 extends HttpServlet {

    private Connection c;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("test");

        try { c = DriverManager.getConnection("JDBC_DATABASE_URL"); } catch (Exception e) {}

        String ending = req.getServletPath();
        if(ending.equals("/text_database")) {
            //Database to contain simple text file form database
            textDatabase t = new textDatabase(resp);
        }

        if(ending.equals("/create_test_database")) {
            createTestDatabase(resp);
        }

        if(ending.equals("/return_test_database")) {
            returnTestDatabase(resp);
        }


        ///just use /druglist in url http://localhost:8020/druglist

        //export sql file to heroku

    }

    private void createTestDatabase(HttpServletResponse resp) {
        Connection c=null;
        Statement s=null;
        ResultSet rset=null;
        try {
            resp.getWriter().write("CreateTestDatabase");
            s=c.createStatement();
            //select table from INFORMATION_SCHEMA.TABLES - lis of all the tables
            String strSelect = "SELECT * \n" +
                    "                 FROM INFORMATION_SCHEMA.TABLES \n" +
                    "                 WHERE  TABLE_NAME = test_database\n";

            rset = s.executeQuery(strSelect);
            if (!rset.next()) {
                s.execute("create table test_database\n" +
                        "(\n" +
                        "    one   serial       not null\n" +
                        "        constraint test_database_pkey\n" +
                        "            primary key,\n" +
                        "    two   varchar(128) not null,\n" +
                        "    three varchar(128) not null\n" +
                        ");\n" +
                        "\n" +
                        "alter table test_database\n" +
                        "    owner to postgres;\n" +
                        "\n" +
                        "INSERT INTO public.test_database (one, two, three) VALUES (1, 'a', 'b');\n" +
                        "INSERT INTO public.test_database (one, two, three) VALUES (2, 'c', 'd');\n" +
                        "INSERT INTO public.test_database (one, two, three) VALUES (3, 'e', 'f');");
            }

            resp.getWriter().write("createTestDatabase called");

        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void returnTestDatabase(HttpServletResponse resp)
    {
        try {
            resp.getWriter().write("ReturnTestDatabase");
            Statement s = c.createStatement();
            String strSelect = "SELECT * \n" +
                    "                 FROM INFORMATION_SCHEMA.TABLES \n" +
                    "                 WHERE  TABLE_NAME = test_database\n" +
                    "                 SELECT * FROM test_database";

            ResultSet rset = s.executeQuery(strSelect);
            while (rset.next()) {
                resp.getWriter().write(rset.getInt("one"));
                resp.getWriter().write(rset.getInt("two"));
                resp.getWriter().write(rset.getInt("three"));
            }

            resp.getWriter().write("returnTestDatabase called");
        }
        catch(Exception e)
        {

        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        resp.setContentType("text/html");

        resp.getWriter().write("Thank you client! "+reqBody);
    }


}