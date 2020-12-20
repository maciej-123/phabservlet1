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


@WebServlet(urlPatterns={"/druglist","/drugstock","/create_test_database","/return_test_database"},loadOnStartup = 1)
public class phabservlet1 extends HttpServlet {


    private Connection c;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("test");

        try {
            c = DriverManager.getConnection("JDBC_DATABASE_URL");
        }
        catch (Exception e)
        {

        }

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

        if(ending.equals("/create_test_database")) {
            makeTestDatabase();
        }

        if(ending.equals("/return_test_database")) {
            returnTestDatabase();
        }








        ///just use /druglist in url http://localhost:8020/druglist

        //export sql file to heroku

    }

    private void makeTestDatabase() {
        Connection c=null;
        Statement s=null;
        ResultSet rset=null;
        try {
            s=c.createStatement();
            //select table from INFORMATION_SCHEMA.TABLES - lis of all the tables
            String strSelect = "SELECT * \n" +
                    "                 FROM INFORMATION_SCHEMA.TABLES \n" +
                    "                 WHERE  TABLE_NAME = test_database2\n";


            System.out.println("Creating table");
            s.execute("create table test_database2\n" +
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
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void returnTestDatabase()
    {
        try {
            Statement s = c.createStatement();
            String strSelect = "SELECT * \n" +
                    "                 FROM INFORMATION_SCHEMA.TABLES \n" +
                    "                 WHERE  TABLE_NAME = test_database\n";

            String sqlStr = "SELECT * FROM test_database2;";
            ResultSet rset = s.executeQuery(sqlStr);
            while (rset.next()) {
                System.out.println(rset.getInt("one"));
                System.out.println(rset.getInt("two"));
                System.out.println(rset.getInt("three"));
            }
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