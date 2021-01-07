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
                "/testfill_phab",
                "/fill_phab", //DO NOT CALL ALONE
                "/testdelete_phab",
                "/delete_phab", //DO NOT CALL ALONE
                "/_checkStock",
                "/searchForDrug",

                "/replenishStock",
                "/getLimitOne",
                "/calculateProfit",
                "/calculateRevenue",
                "/_decreaseStock", //underscore important


                //for post requests
                "/inputMN",
                "/inputB",


                //paddington
                "/create_phab_paddington",
                "/return_phab_paddington",


                //green park
                "/create_phab_greenpark",
                "/return_phab_greenpark",


                //mile end
                "/create_phab_mileend",
                "/return_phab_mileend",



                //URL patterns for user database
                "/create_user_database",
                "/return_user_database",
                "/add_user",
                "/verify_user",
                //new URL patterns for post requests

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
        if (urlPattern.equals("/testfill_phab")) {
            testFillPHAB(resp);
        }

        if (urlPattern.equals("/fill_phab")) {
            fillPHAB(resp);
        }


        if (urlPattern.equals("/return_phab_paddington")) {
            returnPHABPaddington(resp);
        }

        if (urlPattern.equals("/delete_phab")) {
            delAllPHAB(resp);
        }

        if (urlPattern.equals("/testdelete_phab")) {
            delTestPHAB(resp);
        }


        if (urlPattern.equals("/create_test_database")) {
            createTestDatabase(resp);
        }


        //get request for decreasing stock MUST called after the post request
        if (urlPattern.equals("/_decreaseStock")) {

            decreaseStock(resp);
        }


        if (urlPattern.equals("/getLimitOne")) {

            getLimitOne(resp);
        }

        if (urlPattern.equals("/searchForDrug")) {
            searchForDrug(resp);
        }


        //testing the check stock function

        if (urlPattern.equals("/_checkStock"))
        {
            resp.getWriter().write("\nChecking stock test function\n");
            checkStock(resp);
        }

        //testing the calculate profit function

        if (urlPattern.equals("/calculateProfit"))
        {
            calculateProfit(resp);
        }

        if (urlPattern.equals("/calculateRevenue"))
        {
            calculateRevenue(resp);
        }
        //End of Paddington related functions---------------------------------------------------------------------------

        //Green Park Database-------------------------------------------------------------------------------------------
        //create PHAB Paddington Database
        if(urlPattern.equals("/create_phab_greenpark")) {
            createPHABGreenPark(resp);
        }


        if(urlPattern.equals("/return_phab_greenpark")) {
            returnPHABGreenPark(resp);
        }






        //End of Green Park related functions---------------------------------------------------------------------------

        //Mileend Databases---------------------------------------------------------------------------------------------
        //create PHAB Mileend Database
        if(urlPattern.equals("/create_phab_mileend")) {
            createPHABMileEnd(resp);
        }

        if(urlPattern.equals("/return_phab_mileend")) {
            returnPHABMileEnd(resp);
        }








        // End of Mileend Database functions----------------------------------------------------------------------------


        if(urlPattern.equals("/replenishStock"))
        {
            SearchBranch = "";
            resp.getWriter().write("\nSetting Stock to Max\n");
            SearchBranch = "Paddington";
            delAllPHAB(resp);
            SearchBranch = "Paddington";
            fillPHAB(resp);
            SearchBranch = "GreenPark";
            delAllPHAB(resp);
            SearchBranch = "GreenPark";
            fillPHAB(resp);
            SearchBranch = "MileEnd";
            delAllPHAB(resp);
            SearchBranch = "MileEnd";
            fillPHAB(resp);
            SearchBranch = "";
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

        //User database functions
        if(urlPattern.equals("/create_user_database")) {
            createUserDatabase(resp);
        }
        if(urlPattern.equals("/return_user_database")) {
            returnUserDatabse(resp);
        }
        

        ///NEED TO ADD
        //post request for warning - will check if blow 20% for everydrug - will be run after doing any other post requests
        //add to identical databases for 2 other branches
        //have an automatic revenue and profit function for all branches. - remember to edit selling price




    }

    private String SearchManufacturer;
    private String SearchName;
    private String SearchBranch;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        resp.setContentType("text/html");
        resp.getWriter().write("Thank you client! "+reqBody);

        String message = reqBody;
        int length = message.length();

        //one function to change the global variables
        String urlPattern = req.getServletPath();
        if(urlPattern.equals("/inputMN"))
        {
            resp.getWriter().write("\nInputting Manufacturer and Name\n");


            //recieves data in the form of Manufacturer@Name
            String manufacturer = message.substring(0,message.indexOf('@'));
            String name = message.substring(message.indexOf('@')+1,length);

            resp.getWriter().write("\n");
            resp.getWriter().write(manufacturer);
            resp.getWriter().write("\n");
            resp.getWriter().write(name);

            try {
                //put into global variables
                SearchManufacturer = manufacturer;
                SearchName = name;
            }
            catch (Exception e){

                resp.getWriter().write(e.getMessage());
            }

            resp.getWriter().write("Input Manufacturer and Name ended");

        }

        if(urlPattern.equals("/inputB"))
        {
            resp.getWriter().write("\nInputting Branch Name\n");

            //recieves data in the form of Manufacturer@Name
            String branch = message.substring(0,length);

            try {
                //put into global variables
                SearchBranch = branch;
            }
            catch (Exception e){

                resp.getWriter().write(e.getMessage());
            }
            resp.getWriter().write("Input Branch ended");
        }

        

        //message in the format of firstname/lastname|username@password#email

        if(urlPattern.equals("/add_user")) {
            try {
                Statement s = c.createStatement();
                String firstname = message.substring(0,message.indexOf('/'));
                String lastname = message.substring(message.indexOf('/')+1,message.indexOf('|'));
                String username = message.substring(message.indexOf('|')+1,message.indexOf('@'));
                String password = message.substring(message.indexOf('@')+1,message.indexOf('#'));
                String email    = message.substring(message.indexOf('#')+1,length);

                //testing blocks onlyf
                resp.getWriter().write("User name: ");
                resp.getWriter().write(username+"\n");
                resp.getWriter().write("First name: ");
                resp.getWriter().write(firstname+"\n");
                resp.getWriter().write("Last name: ");
                resp.getWriter().write(lastname+"\n");
                resp.getWriter().write("Email: ");
                resp.getWriter().write(email+"\n");
                //testing blocks only -- non-important

                String checkuserexist = "SELECT * FROM Users WHERE Username= '"+username+"';";

                ResultSet rset = s.executeQuery(checkuserexist);

                if(rset.wasNull()) {
                    resp.getWriter().write("username unavailable");
                }
                else {
                    resp.getWriter().write("username available");
                    String addUser = "INSERT INTO Users (Username, FirstName, LastName, Email, Password) VALUES ("
                                    +"'"+username+"',"+"'"+firstname+"',"+"'"+lastname+"',"+"'"+email+"',"+"'"+password+"')\n";

                    s.execute(addUser);
                    
                    resp.getWriter().write("Inserted user: ");
                    resp.getWriter().write(username);
                }
            }
            catch(Exception e) {
                resp.getWriter().write(e.getMessage());
            }



        }
        //same parsing format
        if(urlPattern.equals("/verify_user")) {
            try {
                Statement s = c.createStatement();
                String firstname = message.substring(0,message.indexOf('/'));
                String lastname = message.substring(message.indexOf('/')+1,message.indexOf('|'));
                String username = message.substring(message.indexOf('|')+1,message.indexOf('@'));
                String password = message.substring(message.indexOf('@')+1,message.indexOf('#'));
                String email    = message.substring(message.indexOf('#')+1,length);
                



            }

            catch(Exception e) {
                resp.getWriter().write(e.getMessage());
            }
        }
    }



    //Common Functions
    private void checkStock(HttpServletResponse resp) throws IOException {

        try {
            resp.getWriter().write("Checking Stock \n");
            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT CurrentStock FROM StockDB"+SearchBranch;
            String strFullStock = "SELECT FullStock FROM StockDB"+SearchBranch;


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

    private void calculateProfit(HttpServletResponse resp) throws IOException
    {
        try {
            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT CurrentStock FROM StockDB"+SearchBranch;
            String strFullStock = "SELECT FullStock FROM StockDB"+SearchBranch;
            String strSalesPrice = "SELECT SalesPrice FROM StockDB"+SearchBranch;
            String strPurchasePrice = "SELECT PurchasePrice FROM StockDB"+SearchBranch;

            ResultSet rset = s.executeQuery(strSelect);
            ResultSet rset2 = s.executeQuery(strFullStock);
            ResultSet rset3 = s.executeQuery(strSalesPrice);
            ResultSet rset4 = s.executeQuery(strPurchasePrice);
            int cq = 0;
            int fs = 0;
            double sp = 0;
            double pp = 0;
            double profit = 0;


            String transferStr;
            String transferStr2;


            while(rset.next() && rset2.next() && rset3.next() && rset4.next()) {
                rset.getString("CurrentStock");
                transferStr=rset.getString("CurrentStock");
                cq = Integer.valueOf(transferStr);

                rset2.getString("Fullstock");
                transferStr2=rset2.getString("FullStock");
                fs = Integer.valueOf(transferStr2);

                sp = rset3.getDouble("SalesPrice");
                pp = rset4.getDouble("PurchasePrice");

                profit = profit + (fs-cq)*(sp-pp);

            }

            resp.getWriter().write("\n Profit: "+ profit +" pounds");

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }

    }

    private void calculateRevenue(HttpServletResponse resp) throws IOException
    {
        try {

            Statement s=c.createStatement();

            //first find current stock
            String strSelect = "SELECT CurrentStock FROM StockDB"+SearchBranch;
            String strFullStock = "SELECT FullStock FROM StockDB"+SearchBranch;
            String strSalesPrice = "SELECT SalesPrice FROM StockDB"+SearchBranch;


            ResultSet rset = s.executeQuery(strSelect);
            ResultSet rset2 = s.executeQuery(strFullStock);
            ResultSet rset3 = s.executeQuery(strSalesPrice);

            int cq = 0;
            int fs = 0;
            double sp = 0;

            double rev = 0;


            String transferStr;
            String transferStr2;


            while(rset.next() && rset2.next() && rset3.next()) {
                rset.getString("CurrentStock");
                transferStr=rset.getString("CurrentStock");
                cq = Integer.valueOf(transferStr);

                rset2.getString("Fullstock");
                transferStr2=rset2.getString("FullStock");
                fs = Integer.valueOf(transferStr2);

                sp = rset3.getDouble("SalesPrice");

                rev = rev + (fs-cq)*(sp);

            }

            resp.getWriter().write("\n Revenue: "+ rev +" pounds");

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void testFillPHAB(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("Filling In PHAB Paddington Database\n");
            Statement s=c.createStatement();

            //fill database with test row
            s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('test','test','test',11.11,22.22,10,1,10)");

            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void fillPHAB(HttpServletResponse resp) throws IOException
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
            else if (SearchBranch == "Paddington"){
                //Cold and Flu
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',4.5,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',6.8,5,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',8.5,7,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',9,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','max','16 caps',4.2,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','standard','10 sachets',4.5,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','day and night','16 caps',4.5,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','max','16 caps',4.2,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','mucus relief','16 caps',4.8,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','4 flu','24 caps',6,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('e45','psoriasis cream','50ml',20,16,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eurax','skin cream','100g',5.7,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','skin relief cream','50ml',9,7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','face scrub','100ml',7.5,6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','psoriasis cream','150ml',30,25,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','repair and Restore','100g',12,10,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','30g',12,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','100g',25,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','moisturising cream','50ml',10,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','exfoliating cleanser','180ml',12,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','meltlets','16 caps',4,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','express','16 caps',4,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','max strength','32 caps',7,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','standard','16 caps',4,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cuprofen','max strength','96 caps',11,9,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('solpadeine','headache','16 caps',2,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','extra','16 caps',2.3,2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','triple action','12 caps',2,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','original','16 caps',1.8,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cisprin','soluble','32 tablets',3.6,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','blackcurrant','12 sachets',8,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','lemon','12 sachets',8,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','chewable','24 tablets',4.2,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('senokot','max','10 tablets',3,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','advance','300ml',10,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benadryl','relief','24 caps',9,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('piriteze','tabs','7 tablets',3,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('beconase','relief','100 sprays',6,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','antiseptic','500ml',3.2,3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','hand sanitizer','500ml',7,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('elastoplast','plasters','20 plasters',3,2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('tcp','liquid','200ml',4,3.2,20,0,20)");
            }

            else if (SearchBranch == "GreenPark")
            {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',9,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',13.6,5,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',17,7,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',18,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','max','16 caps',8.4,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','standard','10 sachets',9,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','day and night','16 caps',9,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','max','16 caps',8.4,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','mucus relief','16 caps',9.6,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','4 flu','24 caps',12,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('e45','psoriasis cream','50ml',40,16,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eurax','skin cream','100g',11.4,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','skin relief cream','50ml',18,7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','face scrub','100ml',15,6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','psoriasis cream','150ml',60,25,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','repair and Restore','100g',24,10,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','30g',24,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','100g',50,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','moisturising cream','50ml',20,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','exfoliating cleanser','180ml',24,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','meltlets','16 caps',8,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','express','16 caps',8,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','max strength','32 caps',14,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','standard','16 caps',8,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cuprofen','max strength','96 caps',22,9,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('solpadeine','headache','16 caps',4,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','extra','16 caps',4.6,2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','triple action','12 caps',4,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','original','16 caps',3.6,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cisprin','soluble','32 tablets',7.2,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','blackcurrant','12 sachets',16,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','lemon','12 sachets',16,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','chewable','24 tablets',8.4,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('senokot','max','10 tablets',6,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','advance','300ml',20,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benadryl','relief','24 caps',18,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('piriteze','tabs','7 tablets',6,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('beconase','relief','100 sprays',12,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','antiseptic','500ml',6.4,3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','hand sanitizer','500ml',14,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('elastoplast','plasters','20 plasters',6,2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('tcp','liquid','200ml',8,3.2,20,0,20)");
            }

            else if (SearchBranch == "MileEnd")
            {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',3.5,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',5.2,5,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',6.5,7,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',6.9,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','max','16 caps',3.2,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','standard','10 sachets',3.5,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','day and night','16 caps',3.5,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','max','16 caps',3.2,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','mucus relief','16 caps',3.7,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','4 flu','24 caps',4.6,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('e45','psoriasis cream','50ml',15.4,16,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eurax','skin cream','100g',4.4,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','skin relief cream','50ml',6.9,7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','face scrub','100ml',5.8,6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','psoriasis cream','150ml',23.1,25,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','repair and Restore','100g',9.2,10,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','30g',9.2,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','100g',19.2,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','moisturising cream','50ml',7.7,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','exfoliating cleanser','180ml',9.2,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','meltlets','16 caps',3.1,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','express','16 caps',3.1,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','max strength','32 caps',5.4,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','standard','16 caps',3.1,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cuprofen','max strength','96 caps',8.5,9,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('solpadeine','headache','16 caps',1.5,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','extra','16 caps',1.8,2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','triple action','12 caps',1.5,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','original','16 caps',1.4,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cisprin','soluble','32 tablets',2.8,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','blackcurrant','12 sachets',6.2,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','lemon','12 sachets',6.2,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','chewable','24 tablets',3.2,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('senokot','max','10 tablets',2.3,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','advance','300ml',7.1,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benadryl','relief','24 caps',6.9,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('piriteze','tabs','7 tablets',2.3,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('beconase','relief','100 sprays',4.6,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','antiseptic','500ml',2.5,3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','hand sanitizer','500ml',5.4,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('elastoplast','plasters','20 plasters',2.3,2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('tcp','liquid','200ml',3.1,3.2,20,0,20)");
            }


            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void delTestPHAB(HttpServletResponse resp) throws IOException
    {
        try {

            resp.getWriter().write("Deleting Test Rows Paddington\n");
            Statement s=c.createStatement();

            s.execute("DELETE FROM public.StockDB"+SearchBranch+" WHERE Manufacturer='test'");
            s.execute("DELETE FROM public.StockDB"+SearchBranch+" WHERE Manufacturer='Test'");

            resp.getWriter().write("\nalterTestDatabase called");
            if(s!=null){s.close();}

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    private void delAllPHAB(HttpServletResponse resp) throws IOException
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

                s.execute("DELETE FROM public.StockDB"+SearchBranch+" WHERE FullStock > 0 ");

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

    private void getLimitOne(HttpServletResponse resp) throws IOException
    {

        try {
            String strSelect = "SELECT * FROM StockDB"+SearchBranch+" WHERE Name = '" + SearchName + "' AND Manufacturer = '" + SearchManufacturer + "';";

            Statement s = c.createStatement();
            ResultSet rset = s.executeQuery(strSelect);

            resp.getWriter().write("\n");
            while (rset.next()) {
                resp.getWriter().write(rset.getString(7));
            }

            //close connection
            if (rset != null) {
                rset.close();
            }
            if (s != null) {
                s.close();
            }
        }
        catch(Exception e)
        {

        }
    }

    private void searchForDrug(HttpServletResponse resp) throws IOException
    {

        try {
            String strSelect = "SELECT * FROM StockDB"+SearchBranch+" WHERE Name = '" + SearchName + "' AND Manufacturer = '" + SearchManufacturer + "';";

            Statement s = c.createStatement();
            ResultSet rset = s.executeQuery(strSelect);

            resp.getWriter().write("\n");
            while (rset.next()) {
                for(int n=1;n<=8;n++) {
                    resp.getWriter().write(rset.getString(n));
                    resp.getWriter().write("\t");
                }
            }

            //close connection
            if (rset != null) {
                rset.close();
            }
            if (s != null) {
                s.close();
            }
        }
        catch(Exception e)
        {

        }
    }


    //Paddington--------------------------------------------------------------------------------------------------------
    private void decreaseStock(HttpServletResponse resp) throws IOException
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
            String strSelect = "SELECT * FROM StockDB"+SearchBranch+" WHERE Name = '"+SearchName+"' AND Manufacturer = '"+SearchManufacturer+"';";


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
                s.execute("UPDATE public.StockDB"+SearchBranch+" SET CurrentStock = " + cs + " WHERE Name = '" + SearchName + "' AND Manufacturer = '" + SearchManufacturer + "';");
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

    //------------------------------------------------------------------------------------------------------------------


    //Green Park--------------------------------------------------------------------------------------------------------


    private void createPHABGreenPark(HttpServletResponse resp) throws IOException
    {
        try{
            resp.getWriter().write("Creating PHAB Database for Green Park\n");
            Statement s=c.createStatement();

            boolean lockCreate = false;
            //fill database with test rot

            if(lockCreate == true)
            {
                resp.getWriter().write("Creation Locked, Please Edit code\n");
            }
            else {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',9,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',13.6,5,20,0,20)");
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',17,7,30,0,0)");//actual 30
                s.execute("INSERT INTO public.StockDBGreenPark (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',18,7.5,30,0,30)");//actual 30
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
        }}
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





    //Mile End--------------------------------------------------------------------------------------------------------


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



// End of Mile End Functions



    //test database

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

    private void createUserDatabase(HttpServletResponse resp) throws IOException {
        try{
            resp.getWriter().write("Creating User Database\n");
            Statement s = this.c.createStatement();

            String strSelect = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

            ResultSet rset = s.executeQuery(strSelect);

            s.execute("CREATE TABLE Users(\n" +
                "Username varchar(50) NOT NULL," +
                "FirstName varchar(100) NOT NULL," +
                "LastName varchar(100) NOT NULL," +
                "Email varchar(100) NOT NULL," +
                "Password varchar(100) NOT NULL)"
                
            );

            resp.getWriter().write("Function Call Finished");
            if(rset!=null){rset.close();}
            if(s!=null){s.close();}

        } 
        
        
        catch (Exception e) {
            resp.getWriter().write(e.getMessage());

        }  
    }

    
    private void returnUserDatabse(HttpServletResponse resp) throws IOException {
        try{
            resp.getWriter().write("User Database\n");
            Statement s = this.c.createStatement();
            String strSelect = "SELECT * FROM Users";
            
            ResultSet rset = s.executeQuery(strSelect);

            resp.getWriter().write("Table Start");

            ResultSetMetaData rsmd = rset.getMetaData();
            int colNum = rsmd.getColumnCount();

            resp.getWriter().write(("\n"));

            while(rset.next()) {
                for(int n=1; n<=colNum; n++) {
                    resp.getWriter().write(rset.getString(n) + "\t");
                }
                resp.getWriter().write("\n");;
            }

            resp.getWriter().write("Table End");

            resp.getWriter().write("\n\nPrint Table Complete");

            if(rset!=null){rset.close();}
            if(s!=null){s.close();}
        }
        catch (Exception e) {
            resp.getWriter().write(e.getMessage());

        }

    }
    
    
}