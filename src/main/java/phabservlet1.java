import java.io.*;
import java.nio.file.Path;
import java.sql.*;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(urlPatterns=
        {
                "/text_database",

                "/create_phab_paddington",
                "/testfill_phab_paddington",
                "/fill_phab_paddington",
                "/testdelete_phab_paddington",
                "/delete_phab_paddington",
                "/return_phab_paddington",

                "/_decreaseStock",
                "/replenishStock",

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


        //get current URL pattern
        String urlPattern = req.getServletPath();

        //return a simple text database
        if(urlPattern.equals("/text_database")) {
            textDatabase t = new textDatabase(resp);
        }


        //Paddington Databases
        //create PHAB Paddington Database
        if(urlPattern.equals("/create_phab_paddington")) {
            createPHABPaddington(resp);
        }

        //fill with test variable
        if(urlPattern.equals("/testfill_phab_paddington")) {
            testFillPHABPaddington(resp);
        }

        if(urlPattern.equals("/fill_phab_paddington")) {
            fillPHABPaddington(resp);
        }



        if(urlPattern.equals("/testdelete_phab_paddington")) {
            delTestPHABPaddington(resp);
        }

        if(urlPattern.equals("/return_phab_paddington")) {
            returnPHABPaddington(resp);
        }

        if(urlPattern.equals("/delete_phab_paddington")) {
            delAllPHABPaddington(resp);
        }





        if(urlPattern.equals("/create_test_database")) {
            createTestDatabase(resp);
        }

        if(urlPattern.equals("/_decreaseStock")) {
            resp.getWriter().write("TEST SERVER ERROR");
        }




        //Test database functions
        if(urlPattern.equals("/create_test_database")) {
            createTestDatabase(resp);
        }
        if(urlPattern.equals("/return_test_database")) {
            returnTestDatabase(resp);
        }
        if(urlPattern.equals("/alter_test_database")) {
            alterTestDatabase(resp);
        }




    }

    private String SearchManufacturer;
    private String SearchName;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        resp.setContentType("text/html");
        resp.getWriter().write("Thank you client! "+reqBody);

        String message = reqBody;
        int length = message.length();


        String urlPattern = req.getServletPath();
        if(urlPattern.equals("/decreaseStock"))
        {
            resp.getWriter().write("\nDecreasingStock\n");

            String manufacturer = message.substring(0,message.indexOf('@'));
            String name = message.substring(message.indexOf('@')+1,length);

            resp.getWriter().write("\n");
            resp.getWriter().write(manufacturer);
            resp.getWriter().write("\n");
            resp.getWriter().write(name);

            resp.getWriter().write("#1");
            try {
                resp.getWriter().write("\nAltering Part\n");

                SearchManufacturer = manufacturer;
                SearchName = name;

                //Statement s=c.createStatement();
//
//                //fill database with test row
//                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('test','test','test',11.11,22.22,10,1,10)");
//
//                resp.getWriter().write("\nalterTestDatabase called\n");
//                if(s!=null){s.close();}

            }
            catch (Exception e){

                resp.getWriter().write(e.getMessage());
            }

            resp.getWriter().write("#2");

        }


        if(urlPattern.equals("/replenishStock"))
        {
            resp.getWriter().write("\nSetting Stock to Max\n");
        }


    }



    private void decreaseStock(HttpServletResponse resp) throws IOException
    {


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

    private void testFillPHABPaddington(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("Filling In PHAB Paddington Database\n");
            Statement s=c.createStatement();

            //fill database with test row
            s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('test','test','test',11.11,22.22,10,1,10)");

            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void fillPHABPaddington(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("Filling In PHAB Paddington Database\n");
            Statement s=c.createStatement();

            boolean lockCreate = true;
            //fill database with test row

            if(lockCreate == true)
            {
                resp.getWriter().write("Creation Locked, Please Edit code\n");
            }
            else {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Vicks      ','Vaporub             ','100g       ',4.5,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Vicks      ','First Defence       ','15ml       ',6.8,5,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Gsk        ','Night Nurse         ','160ml      ',8.5,7,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Gsk        ','Night Nurse         ','160ml      ',9,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Lemsip     ','Max                 ','16 caps    ',4.2,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Lemsip     ','Standard            ','10 sachets ',4.5,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Sudafed    ','Day and Night       ','16 caps    ',4.5,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Sudafed    ','Max                 ','16 caps    ',4.2,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Benylin    ','Mucus relief        ','16 caps    ',4.8,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Benylin    ','4 flu               ','24 caps    ',6,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('E45        ','Psoriasis cream     ','50ml       ',20,16,15,0,15)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Eurax      ','Skin cream          ','100g       ',5.7,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Eucerin    ','Skin relief cream   ','50ml       ',9,7,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Eucerin    ','Face scrub          ','100ml      ',7.5,6,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Dermalex   ','Psoriasis cream     ','150ml      ',30,25,10,0,10)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Dermalex   ','Repair and Restore  ','100g       ',12,10,10,0,10)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Dermalex   ','Eczema cream        ','30g        ',12,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Dermalex   ','Eczema cream        ','100g       ',25,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Cetaphil   ','Moisturising cream  ','50ml       ',10,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Cetaphil   ','Exfoliating cleanser','180ml      ',12,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Nurofen    ','Meltlets            ','16 caps    ',4,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Nurofen    ','Express             ','16 caps    ',4,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Nurofen    ','Max strength        ','32 caps    ',7,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Nurofen    ','Standard            ','16 caps    ',4,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Cuprofen   ','Max strength        ','96 caps    ',11,9,20,1,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Solpadeine ','Headache            ','16 caps    ',2,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Anadin     ','Extra               ','16 caps    ',2.3,2,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Anadin     ','Triple action       ','12 caps    ',2,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Anadin     ','Original            ','16 caps    ',1.8,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Disprin    ','Soluble             ','32 tablets ',3.6,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Dioralyte  ','Blackcurrant        ','12 sachets ',8,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Dioralyte  ','Lemon               ','12 sachets ',8,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Gaviscon   ','Chewable            ','24 tablets ',4.2,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Senokot    ','Max                 ','10 tablets ',3,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Gaviscon   ','Advance             ','300ml      ',10,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Benadryl   ','Relief              ','24 caps    ',9,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Piriteze   ','tabs                ','7 tablets  ',3,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Beconase   ','Relief              ','100 sprays ',6,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Dettol     ','Antiseptic          ','500ml      ',3.2,3,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Dettol     ','Hand sanitizer      ','500ml      ',7,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('Elastoplast','plasters            ','20 plasters',3,2,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('TCP        ','Liquid              ','200ml      ',4,3.2,20,0,20)");
            }

            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void returnPHABPaddington(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("PHAB Stock Database Paddington Branch\n\n");

            resp.getWriter().write("Manufacturer\t|Name\t|Quantity\t|SalesPrice|PurchasePrice|FullStock|LimitOne|CurrentStock\n");

            //select Paddington database
            String strSelect = "SELECT * FROM StockDBPaddington";

            //execute selection command
            Statement s = c.createStatement();
            ResultSet rset = s.executeQuery(strSelect);

            resp.getWriter().write(" Table Start ");

            //get number of columns
            ResultSetMetaData rsmd = rset.getMetaData();
            int colNum = rsmd.getColumnCount();

            resp.getWriter().write( "\n");
            while (rset.next()) {
                //https://stackoverflow.com/questions/15444982/how-to-display-or-print-the-contents-of-a-database-table-as-is
                //print entire table
                for(int n = 1; n <= colNum; n++)
                {
                    resp.getWriter().write(rset.getString(n) + "\t");
                }
                resp.getWriter().write( "\n");
            }

            resp.getWriter().write(" Table End ");

            resp.getWriter().write("\n\nPrint Table Complete");

            //close connection
            if(rset!=null){rset.close();}
            if(s!=null){s.close();}
        }
        catch(Exception e)
        {
            resp.getWriter().write(e.getMessage());
        }
    }

    private void delTestPHABPaddington(HttpServletResponse resp) throws IOException
    {
        try {

            resp.getWriter().write("Deleting Test Rows Paddington\n");
            Statement s=c.createStatement();

            s.execute("DELETE FROM public.StockDBPaddington WHERE Manufacturer='test'");
            s.execute("DELETE FROM public.StockDBPaddington WHERE Manufacturer='Test'");

            resp.getWriter().write("\nalterTestDatabase called");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void delAllPHABPaddington(HttpServletResponse resp) throws IOException
    {
        try {

            boolean deleteLock = true;

            resp.getWriter().write("Deleting Test Rows Paddington\n");

            if(deleteLock == true)
            {
                resp.getWriter().write("Delete Function Locked, Check Code!\n");
            }
            else {
                Statement s = c.createStatement();

                s.execute("DELETE FROM public.StockDBPaddington WHERE FullStock > 0 ");

                resp.getWriter().write("\nalterTestDatabase called");
                if (s != null) {
                    s.close();
                }
            }

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