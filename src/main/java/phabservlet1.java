import java.io.*;
import java.nio.file.Path;
import java.sql.*;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(urlPatterns=
        {
                "/text_database",//text from database

                //paddington -for editing the main database we have - testfill and testdelete are dummy functions
                "/create_phab_paddington",
                "/testfill_phab_paddington",
                "/fill_phab_paddington", //DO NOT CALL ALONE
                "/testdelete_phab_paddington",
                "/delete_phab_paddington", //DO NOT CALL ALONE
                "/return_phab_paddington",
                "/_checkStockPaddington",

                "/_decreaseStockPaddington", //underscore important
                "/replenishStockPaddington",

                //green park
                "/create_phab_greenpark",
                "/testfill_phab_greenpark",
                "/fill_phab_greenpark", //DO NOT CALL ALONE
                "/testdelete_phab_greenpark",
                "/delete_phab_greenpark", //DO NOT CALL ALONE
                "/return_phab_greenpark",
                "/_checkStockGreenPark",

                "/_decreaseStockGreenPark", //underscore important
                "/replenishStockGreenPark",

                //mile end
                "/create_phab_mileend",
                "/testfill_phab_mileend",
                "/fill_phab_mileend", //DO NOT CALL ALONE
                "/testdelete_phab_mileend",
                "/delete_phab_mileend", //DO NOT CALL ALONE
                "/return_phab_mileend",
                "/_checkStockMileEnd",

                "/_decreaseStockMileEnd", //underscore important
                "/replenishStockMileEnd",


                //not important - `this is just to create a test database
                "/create_test_database",
                "/return_test_database",
                "/alter_test_database"
        },loadOnStartup = 1)

public class phabservlet1 extends HttpServlet {

    private Connection c;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //select driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            resp.getWriter().write(e.getMessage());
        }
        //connect to database
        try {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            c = DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            resp.getWriter().write(e.getMessage());
        }


        //get current URL pattern
        String urlPattern = req.getServletPath();

        //return a simple text database
        if (urlPattern.equals("/text_database")) {
            textDatabase t = new textDatabase(resp);
        }


        //Paddington Databases------------------------------------------------------------------------------------------
        //create PHAB Paddington Database
        if (urlPattern.equals("/create_phab_paddington")) {
            createPHABPaddington(resp);
        }

        //fill with test variable
        if (urlPattern.equals("/testfill_phab_paddington")) {
            testFillPHABPaddington(resp);
        }

        if (urlPattern.equals("/fill_phab_paddington")) {
            fillPHABPaddington(resp);
        }


        if (urlPattern.equals("/testdelete_phab_paddington")) {
            delTestPHABPaddington(resp);
        }

        if (urlPattern.equals("/return_phab_paddington")) {
            returnPHABPaddington(resp);
        }

        if (urlPattern.equals("/delete_phab_paddington")) {
            delAllPHABPaddington(resp);
        }


        if (urlPattern.equals("/create_test_database")) {
            createTestDatabase(resp);
        }


        //get request for decreasing stock MUST called after the post request
        if (urlPattern.equals("/_decreaseStockPaddington")) {

            decreaseStockPaddington(resp);
        }


        if (urlPattern.equals("/replenishStock")) {
            resp.getWriter().write("\nSetting Stock to Max\n");
            delAllPHABPaddington(resp);
            fillPHABPaddington(resp);

        }

        //testing the check stock function

        if (urlPattern.equals("/_checkStockPaddington"))
        {
            resp.getWriter().write("\nChecking stock test function\n");
            checkStockPaddington(resp);
        }
        //End of Paddington related functions---------------------------------------------------------------------------

        //Green Park Database-------------------------------------------------------------------------------------------
        //create PHAB Paddington Database
        if(urlPattern.equals("/create_phab_greenpark")) {
            createPHABGreenPark(resp);
        }

        //fill with test variable
        if(urlPattern.equals("/testfill_phab_greenpark")) {
            testFillPHABGreenPark(resp);
        }

        if(urlPattern.equals("/fill_phab_greenpark")) {
            fillPHABGreenPark(resp);
        }



        if(urlPattern.equals("/testdelete_phab_greenpark")) {
            delTestPHABGreenPark(resp);
        }

        if(urlPattern.equals("/return_phab_greenpark")) {
            returnPHABGreenPark(resp);
        }

        if(urlPattern.equals("/delete_phab_greenpark")) {
            delAllPHABGreenPark(resp);
        }


        //get request for decreasing stock MUST called after the post request
        if(urlPattern.equals("/_decreaseStockGreenPark")) {

            decreaseStockGreenPark(resp);
        }

        //I have not included the create test database here
        if(urlPattern.equals("/replenishStockGreenPark"))
        {
            resp.getWriter().write("\nSetting Stock to Max\n");
            delAllPHABGreenPark(resp);
            fillPHABGreenPark(resp);

        }
        if (urlPattern.equals("/_checkStockGreenPark"))
        {
            resp.getWriter().write("\nChecking stock test function\n");
            checkStockGreenPark(resp);
        }

        //End of Green Park related functions---------------------------------------------------------------------------

        //Mileend Databases---------------------------------------------------------------------------------------------
        //create PHAB Mileend Database
        if(urlPattern.equals("/create_phab_mileend")) {
            createPHABMileEnd(resp);
        }

        //fill with test variable
        if(urlPattern.equals("/testfill_phab_mileend")) {
            testFillPHABMileEnd(resp);
        }

        if(urlPattern.equals("/fill_phab_mileend")) {
            fillPHABMileEnd(resp);
        }



        if(urlPattern.equals("/testdelete_phab_mileend")) {
            delTestPHABMileEnd(resp);
        }

        if(urlPattern.equals("/return_phab_mileend")) {
            returnPHABMileEnd(resp);
        }

        if(urlPattern.equals("/delete_phab_mileend")) {
            delAllPHABMileEnd(resp);
        }


        //get request for decreasing stock MUST called after the post request
        if(urlPattern.equals("/_decreaseStockMileEnd")) {

            decreaseStockMileEnd(resp);
        }

        //I have not included the create test database here
        if(urlPattern.equals("/replenishStockMileEnd"))
        {
            resp.getWriter().write("\nSetting Stock to Max\n");
            delAllPHABMileEnd(resp);
            fillPHABMileEnd(resp);

        }
        if (urlPattern.equals("/_checkStockMileEnd"))
        {
            resp.getWriter().write("\nChecking stock test function\n");
            checkStockMileEnd(resp);
        }

        // End of Mileend Database functions----------------------------------------------------------------------------


        if(urlPattern.equals("/replenishStock"))
        {
            resp.getWriter().write("\nSetting Stock to Max\n");
            delAllPHABGreenPark(resp);
            fillPHABGreenPark(resp);
            delAllPHABPaddington(resp);
            fillPHABPaddington(resp);
            delAllPHABMileEnd(resp);
            fillPHABMileEnd(resp);
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


        ///NEED TO ADD
        //post request for warning - will check if blow 20% for everydrug - will be run after doing any other post requests
        //add to identical databases for 2 other branches
        //have an automatic revenue and profit function for all branches. - remember to edit selling price


    }

    private String SearchManufacturer;
    private String SearchName;
    private int SearchCurrentStock;
    private int SearchFullStock;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        resp.setContentType("text/html");
        resp.getWriter().write("Thank you client! "+reqBody);

        String message = reqBody;
        int length = message.length();


        String urlPattern = req.getServletPath();
        if(urlPattern.equals("/_decreaseStockPaddington"))
        {
            resp.getWriter().write("\nDecreasingStockPaddington\n");


            //recieves data in the form of Manufacturer@Name
            String manufacturer = message.substring(0,message.indexOf('@'));
            String name = message.substring(message.indexOf('@')+1,length);

            resp.getWriter().write("\n");
            resp.getWriter().write(manufacturer);
            resp.getWriter().write("\n");
            resp.getWriter().write(name);

            resp.getWriter().write("#1");
            try {
                resp.getWriter().write("\nAltering Part\n");

                //put into global variables
                SearchManufacturer = manufacturer;
                SearchName = name;


            }
            catch (Exception e){

                resp.getWriter().write(e.getMessage());
            }

            resp.getWriter().write("#2");

        }

        if(urlPattern.equals("/_decreaseStockGreenPark"))
        {
            resp.getWriter().write("\nDecreasingStockGreenPark\n");


            //recieves data in the form of Manufacturer@Name
            String manufacturer = message.substring(0,message.indexOf('@'));
            String name = message.substring(message.indexOf('@')+1,length);

            resp.getWriter().write("\n");
            resp.getWriter().write(manufacturer);
            resp.getWriter().write("\n");
            resp.getWriter().write(name);

            resp.getWriter().write("#1");
            try {
                resp.getWriter().write("\nAltering Part\n");

                //put into global variables
                SearchManufacturer = manufacturer;
                SearchName = name;


            }
            catch (Exception e){

                resp.getWriter().write(e.getMessage());
            }

            resp.getWriter().write("#2");

        }

        if(urlPattern.equals("/_decreaseStockMileEnd"))
        {
            resp.getWriter().write("\nDecreasingStockMileEnd\n");


            //recieves data in the form of Manufacturer@Name
            String manufacturer = message.substring(0,message.indexOf('@'));
            String name = message.substring(message.indexOf('@')+1,length);

            resp.getWriter().write("\n");
            resp.getWriter().write(manufacturer);
            resp.getWriter().write("\n");
            resp.getWriter().write(name);

            resp.getWriter().write("#1");
            try {
                resp.getWriter().write("\nAltering Part\n");

                //put into global variables
                SearchManufacturer = manufacturer;
                SearchName = name;


            }
            catch (Exception e){

                resp.getWriter().write(e.getMessage());
            }

            resp.getWriter().write("#2");

        }




    }


    //Paddington--------------------------------------------------------------------------------------------------------
    private void decreaseStockPaddington(HttpServletResponse resp) throws IOException
    {
        resp.getWriter().write("Decreasing Stock\n");

        resp.getWriter().write(SearchManufacturer);
        resp.getWriter().write("\n");
        resp.getWriter().write(SearchName);
        resp.getWriter().write("\n");


        try {
            resp.getWriter().write("Editing Rows Paddington\n");
            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT * FROM StockDBPaddington WHERE Name = '"+SearchName+"' AND Manufacturer = '"+SearchManufacturer+"';";


            ResultSet rset = s.executeQuery(strSelect);
            String transferStr;
            //default error value - s.execute will not be called with -1
            int cs = -1;

            while(rset.next()) {
                resp.getWriter().write(rset.getString("CurrentStock"));
                transferStr=rset.getString("CurrentStock");
                cs = Integer.valueOf(transferStr);

            }

            //subract 1 from the current value
            cs--;

            //to prevent decrementing below zero
            if(cs >= 0) {
                s.execute("UPDATE public.StockDBPaddington SET CurrentStock = " + cs + " WHERE Name = '" + SearchName + "' AND Manufacturer = '" + SearchManufacturer + "';");
            }


            resp.getWriter().write("\nDecrease Stock Called");
            if(s!=null){s.close();}

            //reset to null
            SearchName = "";
            SearchManufacturer = "";
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }


    }

    private void checkStockPaddington(HttpServletResponse resp) throws IOException {

        try {
            resp.getWriter().write("Checking Stock Paddington\n");
            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT CurrentStock FROM StockDBPaddington";
            String strFullStock = "SELECT FullStock FROM StockDBPaddington";

            ResultSet rset = s.executeQuery(strSelect);
            ResultSet rset2 = s.executeQuery(strFullStock);
            int cq = 0;
            int fs = 0;
            int count = 0;
            String transferStr;
            String transferStr2;

            while(rset.next() && rset2.next()) {
                rset.getString("CurrentStock");
                transferStr=rset.getString("CurrentStock");
                cq = Integer.valueOf(transferStr);

                rset2.getString("Fullstock");
                transferStr2=rset2.getString("FullStock");
                fs = Integer.valueOf(transferStr2);
                if (cq <= fs*0.2 )
                {
                    count++;
                }

            }
            if (count>=1)
                resp.getWriter().write("\n WARNING: "+ count +" stock(s) below 20% found");

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }

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

            boolean lockCreate = false;
            //fill database with test row

            if(lockCreate == true)
            {
                resp.getWriter().write("Creation Locked, Please Edit code\n");
            }
            else {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',4.5,3.7,15,0,0)"); //current stock actually 15 but changed to test
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',6.8,5,20,0,0)"); //current stock actually 20 but changed to test
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',8.5,7,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',9,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','max','16 caps',4.2,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','standard','10 sachets',4.5,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','day and night','16 caps',4.5,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','max','16 caps',4.2,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','mucus relief','16 caps',4.8,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','4 flu','24 caps',6,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('e45','psoriasis cream','50ml',20,16,15,0,15)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eurax','skin cream','100g',5.7,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','skin relief cream','50ml',9,7,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','face scrub','100ml',7.5,6,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','psoriasis cream','150ml',30,25,10,0,10)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','repair and Restore','100g',12,10,10,0,10)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','30g',12,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','100g',25,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','moisturising cream','50ml',10,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','exfoliating cleanser','180ml',12,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','meltlets','16 caps',4,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','express','16 caps',4,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','max strength','32 caps',7,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','standard','16 caps',4,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cuprofen','max strength','96 caps',11,9,20,1,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('solpadeine','headache','16 caps',2,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','extra','16 caps',2.3,2,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','triple action','12 caps',2,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','original','16 caps',1.8,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cisprin','soluble','32 tablets',3.6,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','blackcurrant','12 sachets',8,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','lemon','12 sachets',8,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','chewable','24 tablets',4.2,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('senokot','max','10 tablets',3,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','advance','300ml',10,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benadryl','relief','24 caps',9,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('piriteze','tabs','7 tablets',3,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('beconase','relief','100 sprays',6,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','antiseptic','500ml',3.2,3,20,0,20)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','hand sanitizer','500ml',7,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('elastoplast','plasters','20 plasters',3,2,30,0,30)");
                s.execute("INSERT INTO public.StockDBPaddington (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('tcp','liquid','200ml',4,3.2,20,0,20)");
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

            boolean deleteLock = false;

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
    //------------------------------------------------------------------------------------------------------------------


    //Green Park--------------------------------------------------------------------------------------------------------
    private void decreaseStockGreenPark(HttpServletResponse resp) throws IOException
    {
        resp.getWriter().write("Decreasing Stock\n");

        resp.getWriter().write(SearchManufacturer);
        resp.getWriter().write("\n");
        resp.getWriter().write(SearchName);
        resp.getWriter().write("\n");


        try {
            resp.getWriter().write("Editing Rows Green Park\n");
            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT * FROM StockDBGreenPark WHERE Name = '"+SearchName+"' AND Manufacturer = '"+SearchManufacturer+"';";


            ResultSet rset = s.executeQuery(strSelect);
            String transferStr;
            //default error value - s.execute will not be called with -1
            int cs = -1;

            while(rset.next()) {
                resp.getWriter().write(rset.getString("CurrentStock"));
                transferStr=rset.getString("CurrentStock");
                cs = Integer.valueOf(transferStr);

            }

            //subract 1 from the current value
            cs--;

            //to prevent decrementing below zero
            if(cs >= 0) {
                s.execute("UPDATE public.StockDBGreenPark SET CurrentStock = " + cs + " WHERE Name = '" + SearchName + "' AND Manufacturer = '" + SearchManufacturer + "';");
            }


            resp.getWriter().write("\nDecrease Stock Called");
            if(s!=null){s.close();}

            //reset to null
            SearchName = "";
            SearchManufacturer = "";
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }


    }
    private void checkStockGreenPark(HttpServletResponse resp) throws IOException {

        try {
            resp.getWriter().write("Checking Stock Green Park\n");
            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT CurrentStock FROM StockDBGreenPark";
            String strFullStock = "SELECT FullStock FROM StockDBGreenPark";

            ResultSet rset = s.executeQuery(strSelect);
            ResultSet rset2 = s.executeQuery(strFullStock);
            int cq = 0;
            int fs = 0;
            int count = 0;
            String transferStr;
            String transferStr2;

            while(rset.next() && rset2.next()) {
                rset.getString("CurrentStock");
                transferStr=rset.getString("CurrentStock");
                cq = Integer.valueOf(transferStr);

                rset2.getString("Fullstock");
                transferStr2=rset2.getString("FullStock");
                fs = Integer.valueOf(transferStr2);
                if (cq <= fs*0.2 )
                {
                    count++;
                }

            }
            if (count>=1)
                resp.getWriter().write("\n WARNING: "+ count +" stocks below 20% found");

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }

    }


    private void createPHABGreenPark(HttpServletResponse resp) throws IOException
    {
        try{
            resp.getWriter().write("Creating PHAB Database for Green Park\n");
            Statement s=c.createStatement();

            //select table from INFORMATION_SCHEMA.TABLES - list of all the tables
            String strSelect = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

            ResultSet rset = s.executeQuery(strSelect);

            //create test table
            s.execute("CREATE TABLE StockDBGreenPark(\n" +

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

    private void testFillPHABGreenPark(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("Filling In PHAB Green Park Database\n");
            Statement s=c.createStatement();

            //fill database with test row
            s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('test','test','test',11.11,22.22,10,1,10)");

            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void fillPHABGreenPark(HttpServletResponse resp) throws IOException //2x paddington
    {
        try {
            resp.getWriter().write("Filling In PHAB Green Park Database\n");
            Statement s=c.createStatement();

            boolean lockCreate = false;
            //fill database with test row

            if(lockCreate == true)
            {
                resp.getWriter().write("Creation Locked, Please Edit code\n");
            }
            else {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',9,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',13.6,5,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',17,7,30,0,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',18,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','max','16 caps',8.4,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','standard','10 sachets',9,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','day and night','16 caps',9,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','max','16 caps',8.4,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','mucus relief','16 caps',9.6,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','4 flu','24 caps',12,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('e45','psoriasis cream','50ml',40,16,15,0,15)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eurax','skin cream','100g',11.4,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','skin relief cream','50ml',18,7,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','face scrub','100ml',15,6,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','psoriasis cream','150ml',60,25,10,0,10)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','repair and Restore','100g',24,10,10,0,10)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','30g',24,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','100g',50,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','moisturising cream','50ml',20,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','exfoliating cleanser','180ml',24,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','meltlets','16 caps',8,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','express','16 caps',8,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','max strength','32 caps',14,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','standard','16 caps',8,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cuprofen','max strength','96 caps',22,9,20,1,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('solpadeine','headache','16 caps',4,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','extra','16 caps',4.6,2,30,1,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','triple action','12 caps',4,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','original','16 caps',3.6,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cisprin','soluble','32 tablets',7.2,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','blackcurrant','12 sachets',16,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','lemon','12 sachets',16,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','chewable','24 tablets',8.4,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('senokot','max','10 tablets',6,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','advance','300ml',20,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benadryl','relief','24 caps',18,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('piriteze','tabs','7 tablets',6,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('beconase','relief','100 sprays',12,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','antiseptic','500ml',6.4,3,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','hand sanitizer','500ml',14,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('elastoplast','plasters','20 plasters',6,2,30,0,30)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('tcp','liquid','200ml',8,3.2,20,0,20)");
            }

            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void delTestPHABGreenPark(HttpServletResponse resp) throws IOException
    {
        try {

            resp.getWriter().write("Deleting Test Rows Green Park\n");
            Statement s=c.createStatement();

            s.execute("DELETE FROM public.StockDBGreenPark WHERE Manufacturer='test'");
            s.execute("DELETE FROM public.StockDBGreenPark WHERE Manufacturer='Test'");

            resp.getWriter().write("\nalterTestDatabase called");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void returnPHABGreenPark(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("PHAB Stock Database Green Park Branch\n\n");

            resp.getWriter().write("Manufacturer\t|Name\t|Quantity\t|SalesPrice|PurchasePrice|FullStock|LimitOne|CurrentStock\n");

            //select GreenPark database
            String strSelect = "SELECT * FROM StockDBGreenPark";

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

    private void delAllPHABGreenPark(HttpServletResponse resp) throws IOException
    {
        try {

            boolean deleteLock = false;

            resp.getWriter().write("Deleting Test Rows Green Park\n");

            if(deleteLock == true)
            {
                resp.getWriter().write("Delete Function Locked, Check Code!\n");
            }
            else {
                Statement s = c.createStatement();

                s.execute("DELETE FROM public.StockDBGreenPark WHERE FullStock > 0 ");

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


    //Mile End--------------------------------------------------------------------------------------------------------
    private void decreaseStockMileEnd(HttpServletResponse resp) throws IOException
    {
        resp.getWriter().write("Decreasing Stock\n");

        resp.getWriter().write(SearchManufacturer);
        resp.getWriter().write("\n");
        resp.getWriter().write(SearchName);
        resp.getWriter().write("\n");


        try {
            resp.getWriter().write("Editing Rows Mile End\n");
            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT * FROM StockDBMileEnd WHERE Name = '"+SearchName+"' AND Manufacturer = '"+SearchManufacturer+"';";


            ResultSet rset = s.executeQuery(strSelect);
            String transferStr;
            //default error value - s.execute will not be called with -1
            int cs = -1;

            while(rset.next()) {
                resp.getWriter().write(rset.getString("CurrentStock"));
                transferStr=rset.getString("CurrentStock");
                cs = Integer.valueOf(transferStr);

            }

            //subract 1 from the current value
            cs--;

            //to prevent decrementing below zero
            if(cs >= 0) {
                s.execute("UPDATE public.StockDBMileEnd SET CurrentStock = " + cs + " WHERE Name = '" + SearchName + "' AND Manufacturer = '" + SearchManufacturer + "';");
            }


            resp.getWriter().write("\nDecrease Stock Called");
            if(s!=null){s.close();}

            //reset to null
            SearchName = "";
            SearchManufacturer = "";
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }


    }
    private void checkStockMileEnd(HttpServletResponse resp) throws IOException {

        try {
            resp.getWriter().write("Checking Stock Paddington\n");
            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT CurrentStock FROM StockDBMileEnd";
            String strFullStock = "SELECT FullStock FROM StockDBMileEnd";

            ResultSet rset = s.executeQuery(strSelect);
            ResultSet rset2 = s.executeQuery(strFullStock);
            int cq = 0;
            int fs = 0;
            int count = 0;
            String transferStr;
            String transferStr2;

            while(rset.next() && rset2.next()) {
                rset.getString("CurrentStock");
                transferStr=rset.getString("CurrentStock");
                cq = Integer.valueOf(transferStr);

                rset2.getString("Fullstock");
                transferStr2=rset2.getString("FullStock");
                fs = Integer.valueOf(transferStr2);
                if (cq <= fs*0.2 )
                {
                    count++;
                }

            }
            if (count>=1)
                resp.getWriter().write("\n WARNING: "+ count +" stocks below 20% found");

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }

    }


    private void createPHABMileEnd(HttpServletResponse resp) throws IOException
    {
        try{
            resp.getWriter().write("Creating PHAB Database for Mile End\n");
            Statement s=c.createStatement();

            //select table from INFORMATION_SCHEMA.TABLES - list of all the tables
            String strSelect = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

            ResultSet rset = s.executeQuery(strSelect);

            //create test table
            s.execute("CREATE TABLE StockDBMileEnd(\n" +

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

    private void testFillPHABMileEnd(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("Filling In PHAB Mile End Database\n");
            Statement s=c.createStatement();

            //fill database with test row
            s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('test','test','test',11.11,22.22,10,1,10)");

            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void fillPHABMileEnd(HttpServletResponse resp) throws IOException //2x paddington
    {
        try {
            resp.getWriter().write("Filling In PHAB Mile End Database\n");
            Statement s=c.createStatement();

            boolean lockCreate = false;
            //fill database with test row

            if(lockCreate == true)
            {
                resp.getWriter().write("Creation Locked, Please Edit code\n");
            }
            else {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',3.5,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',5.2,5,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',6.5,7,30,0,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',6.9,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','max','16 caps',3.2,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','standard','10 sachets',3.5,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','day and night','16 caps',3.5,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','max','16 caps',3.2,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','mucus relief','16 caps',3.7,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','4 flu','24 caps',4.6,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('e45','psoriasis cream','50ml',15.4,16,15,0,15)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eurax','skin cream','100g',4.4,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','skin relief cream','50ml',6.9,7,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','face scrub','100ml',5.8,6,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','psoriasis cream','150ml',23.1,25,10,0,10)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','repair and Restore','100g',9.2,10,10,0,10)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','30g',9.2,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','100g',19.2,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','moisturising cream','50ml',7.7,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','exfoliating cleanser','180ml',9.2,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','meltlets','16 caps',3.1,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','express','16 caps',3.1,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','max strength','32 caps',5.4,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','standard','16 caps',3.1,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cuprofen','max strength','96 caps',8.5,9,20,1,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('solpadeine','headache','16 caps',1.5,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','extra','16 caps',1.8,2,30,1,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','triple action','12 caps',1.5,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','original','16 caps',1.4,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cisprin','soluble','32 tablets',2.8,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','blackcurrant','12 sachets',6.2,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','lemon','12 sachets',6.2,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','chewable','24 tablets',3.2,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('senokot','max','10 tablets',2.3,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','advance','300ml',7.7,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benadryl','relief','24 caps',6.9,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('piriteze','tabs','7 tablets',2.3,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('beconase','relief','100 sprays',4.6,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','antiseptic','500ml',2.5,3,20,0,20)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','hand sanitizer','500ml',5.4,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('elastoplast','plasters','20 plasters',2.3,2,30,0,30)");
                s.execute("INSERT INTO public.StockDBMileEnd (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('tcp','liquid','200ml',3.1,3.2,20,0,20)");
            }

            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void delTestPHABMileEnd(HttpServletResponse resp) throws IOException
    {
        try {

            resp.getWriter().write("Deleting Test Rows Mile End\n");
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

    private void returnPHABMileEnd(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("PHAB Stock Database Mile End Branch\n\n");

            resp.getWriter().write("Manufacturer\t|Name\t|Quantity\t|SalesPrice|PurchasePrice|FullStock|LimitOne|CurrentStock\n");

            //select Mile End database
            String strSelect = "SELECT * FROM StockDBMileEnd";

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

    private void delAllPHABMileEnd(HttpServletResponse resp) throws IOException {
        try {

            boolean deleteLock = false;

            resp.getWriter().write("Deleting Test Rows Mile End\n");

            if (deleteLock == true) {
                resp.getWriter().write("Delete Function Locked, Check Code!\n");
            } else {
                Statement s = c.createStatement();

                s.execute("DELETE FROM public.StockDBMileEnd WHERE FullStock > 0 ");

                resp.getWriter().write("\nalterTestDatabase called");
                if (s != null) {
                    s.close();
                }
            }

        } catch (Exception e) {

            resp.getWriter().write(e.getMessage());
        }
    }

// End of Mile End Functions



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