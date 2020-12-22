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

        try { Class.forName("org.postgresql.Driver"); } catch (Exception e) {resp.getWriter().write(e.getMessage()); }
        try {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            c = DriverManager.getConnection(dbUrl);
        } catch (Exception e) {resp.getWriter().write(e.getMessage());}

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        resp.setContentType("text/html");

        resp.getWriter().write("Thank you client! "+reqBody);
    }

    private String strSelect;
    private void createTestDatabase(HttpServletResponse resp) throws IOException{

        try {
            resp.getWriter().write("CreateTestDatabase");
            Statement s=c.createStatement();
            //select table from INFORMATION_SCHEMA.TABLES - lis of all the tables


            //strSelect = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = test";

            strSelect = "SELECT * FROM INFORMATION_SCHEMA.TABLES";


            ResultSet rset = s.executeQuery(strSelect);
            resp.getWriter().write(" #2 ");


                resp.getWriter().write(" #3 ");

                s.execute("CREATE TABLE label(\n" +

                        "id int PRIMARY KEY NOT NULL,"+
                        "name varchar(45))"
                );



//                s.execute("create table test\n" +
//                        "(\n" +
//                        "    one   serial       not null\n" +
//                        "        constraint test_pkey\n" +
//                        "            primary key,\n" +
//                        "    two   varchar(128) not null,\n" +
//                        "    three varchar(128) not null\n" +
//                        ");\n" +
//                        "\n" +
//                        "alter table test\n" +
//                        "    owner to postgres;\n" +
//                        "\n" );
//
//
//                s.execute("INSERT INTO public.test (one, two, three) VALUES (1, 'a', 'b');\n");
//                s.execute("INSERT INTO public.test (one, two, three) VALUES (2, 'c', 'd');\n");
//                s.execute("INSERT INTO public.test (one, two, three) VALUES (3, 'e', 'f');");


            resp.getWriter().write("CreateTestDatabase called");

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void returnTestDatabase(HttpServletResponse resp) throws IOException
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
            resp.getWriter().write(e.getMessage());
        }

    }







}