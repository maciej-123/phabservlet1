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


@WebServlet(urlPatterns=
        {
                "/text_database",
                "/create_phab_paddington",
                "/create_test_database",
                "/return_test_database",
                "/alter_test_database"
        },loadOnStartup = 1)

public class phabservlet1 extends HttpServlet {

    private Connection c;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //select driver
        try { Class.forName("org.postgresql.Driver"); } catch (Exception e) {resp.getWriter().write(e.getMessage()); }
        //connect to database
        try {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            c = DriverManager.getConnection(dbUrl);
        }
        catch (Exception e) {resp.getWriter().write(e.getMessage());}


        //
        String ending = req.getServletPath();

        if(ending.equals("/text_database")) {
            //Create simple text file form database
            textDatabase t = new textDatabase(resp);
        }


        if(ending.equals("/create_phab_paddington")) {
            createPHABPaddington(resp);
        }


        //Test database functions
        if(ending.equals("/create_test_database")) {
            createTestDatabase(resp);
        }
        if(ending.equals("/return_test_database")) {
            returnTestDatabase(resp);
        }
        if(ending.equals("/alter_test_database")) {
            alterTestDatabase(resp);
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


    private void createPHABPaddington(HttpServletResponse resp) throws IOException
    {
        try{
            resp.getWriter().write("Creating PHAB Database for Paddington\n");
            Statement s=c.createStatement();

            //select table from INFORMATION_SCHEMA.TABLES - list of all the tables
            String strSelect = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

            ResultSet rset = s.executeQuery(strSelect);

                //create test table
                s.execute("CREATE TABLE StockDBPaddington(\n" +

                        "Manufacturer varchar(50)," +
                        "Name varchar(100)," +
                        "Quantity varchar(50)," +
                        "SalesPrice float NOT NULL," +
                        "PurchasePrice float NOT NULL," +
                        "FullStock smallint NOT NULL," +
                        "LimitOne int," +
                        "CurrentStock smallint NOT NULL)"
                );


            resp.getWriter().write("Function Call Finished");
            if(rset!=null){rset.close();}
            if(s!=null){s.close();}
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }




    private void createTestDatabase(HttpServletResponse resp) throws IOException
    {

        try {
            resp.getWriter().write("CreateTestDatabase");
            Statement s=c.createStatement();

            //select table from INFORMATION_SCHEMA.TABLES - list of all the tables
            String strSelect = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

            ResultSet rset = s.executeQuery(strSelect);
            resp.getWriter().write(" #2 ");

            if (!rset.next()) {
                //create test table
                s.execute("CREATE TABLE label(\n" +

                        "id int PRIMARY KEY NOT NULL," +
                        "name varchar(45))"
                );
            }
            else
            {
                resp.getWriter().write(" Already Created ");
            }

            resp.getWriter().write("CreateTestDatabase called");
            if(rset!=null){rset.close();}
            if(s!=null){s.close();}
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void alterTestDatabase(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("AlterTestDatabase");
            Statement s=c.createStatement();


            //ResultSet rset = s.executeQuery(strSelect);
            resp.getWriter().write(" #2 ");


            s.execute("INSERT INTO public.label (id,name) VALUES (1,'2')");
            s.execute("INSERT INTO public.label (id,name) VALUES (3,'4')");

            resp.getWriter().write("alterTestDatabase called");
            if(s!=null){s.close();}

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
            //String strSelect = "SELECT *  FROM INFORMATION_SCHEMA.TABLES";
            String strSelect = "SELECT * FROM label";

            String transfer1 = new String();
            String transfer2 = new String();

            resp.getWriter().write(" #1 ");
            ResultSet rset = s.executeQuery(strSelect);
            resp.getWriter().write(" #2 ");

            while (rset.next()) {
                transfer1 += String.valueOf(rset.getInt("id"));
                transfer2 += rset.getString("name");
            }
//
            resp.getWriter().write(" #3 ");
            resp.getWriter().write(transfer1);
            resp.getWriter().write(transfer2);

            resp.getWriter().write("returnTestDatabase called");
            if(rset!=null){rset.close();}
            if(s!=null){s.close();}
        }
        catch(Exception e)
        {
            resp.getWriter().write(e.getMessage());
        }
//
    }







}