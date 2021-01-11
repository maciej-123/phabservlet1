
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
                "/alter_test_database",
                "/deltest_user_database"
        },loadOnStartup = 1)

    public class phabservlet1 extends HttpServlet {

    private Connection c;


    private String dbUrl = "";
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
            dbUrl = System.getenv("JDBC_DATABASE_URL");
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
            //createPHABPaddington(resp);

            createDatabase cDB = new createDatabase(c);
            cDB.createPHABPaddington(resp);
        }

        //fill with test variable
        if (urlPattern.equals("/testfill_phab")) {
            ///testFillPHAB(resp);
            testFillDelete tFD = new testFillDelete(c);
            tFD.testFillPHAB(resp,SearchBranch);
        }

        if (urlPattern.equals("/fill_phab")) {
            fillPHAB(resp);

//            fillDeletePHAB fDP = new fillDeletePHAB(c);
//            fDP.fillPHAB(resp,SearchBranch);
        }


        if (urlPattern.equals("/return_phab_paddington")) {
            //returnPHABPaddington(resp);

            returnDatabase rDB = new returnDatabase(c);
            rDB.returnPHABPaddington(resp);
        }

        if (urlPattern.equals("/delete_phab")) {
            //delAllPHAB(resp);

            fillDeletePHAB fDP = new fillDeletePHAB(c);
            fDP.delAllPHAB(resp, SearchBranch);
        }

        if (urlPattern.equals("/testdelete_phab")) {
            //delTestPHAB(resp);
            testFillDelete tFD = new testFillDelete(c);
            tFD.delTestPHAB(resp, SearchBranch);
        }


        //get request for decreasing stock MUST called after the post request
        if (urlPattern.equals("/_decreaseStock")) {
            //decreaseStock(resp);

            searchDecreaseLimit sDL = new searchDecreaseLimit(c);
            sDL.decreaseStock(resp,SearchBranch, SearchName, SearchManufacturer);
        }


        if (urlPattern.equals("/getLimitOne")) {
            //getLimitOne(resp);
            searchDecreaseLimit sDL = new searchDecreaseLimit(c);
            sDL.getLimitOne(resp,SearchBranch, SearchName, SearchManufacturer);
        }

        if (urlPattern.equals("/searchForDrug")) {
            //searchForDrug(resp);
            searchDecreaseLimit sDL = new searchDecreaseLimit(c);
            sDL.searchForDrug(resp, SearchBranch, SearchName, SearchManufacturer);
        }


        //testing the check stock function

        if (urlPattern.equals("/_checkStock"))
        {
            //checkStock(resp);

            revenueProfitStock rPS =  new revenueProfitStock(c);
            rPS.checkStock(resp, SearchBranch);
        }

        //testing the calculate profit function

        if (urlPattern.equals("/calculateProfit"))
        {
            //calculateProfit(resp);

            revenueProfitStock rPS =  new revenueProfitStock(c);
            rPS.calculateProfit(resp,SearchBranch);
        }

        if (urlPattern.equals("/calculateRevenue"))
        {
            //calculateRevenue(resp);

            revenueProfitStock rPS =  new revenueProfitStock(c);
            rPS.calculateRevenue(resp,SearchBranch);
        }
        //End of Paddington related functions---------------------------------------------------------------------------

        //Green Park Database-------------------------------------------------------------------------------------------
        //create PHAB Paddington Database
        if(urlPattern.equals("/create_phab_greenpark")) {
            //createPHABGreenPark(resp);

            createDatabase cDB = new createDatabase(c);
            cDB.createPHABGreenPark(resp);

        }


        if(urlPattern.equals("/return_phab_greenpark")) {
            //returnPHABGreenPark(resp);

            returnDatabase rDB = new returnDatabase(c);
            rDB.returnPHABGreenPark(resp);
        }

        if(urlPattern.equals("/deltest_user_database")) {
            //deltestUserDatabase(resp);
            UserDatabase uDB = new UserDatabase(c);
            uDB.deltestUserDatabase(resp);
        }

//




        //End of Green Park related functions---------------------------------------------------------------------------

        //Mileend Databases---------------------------------------------------------------------------------------------
        //create PHAB Mileend Database
        if(urlPattern.equals("/create_phab_mileend")) {
            //createPHABMileEnd(resp);

            createDatabase cDB = new createDatabase(c);
            cDB.createPHABMileEnd(resp);
        }

        if(urlPattern.equals("/return_phab_mileend")) {
//            returnPHABMileEnd(resp);

            returnDatabase rDB = new returnDatabase(c);
            rDB.returnPHABMileEnd(resp);
        }








        // End of Mileend Database functions----------------------------------------------------------------------------


        if(urlPattern.equals("/replenishStock"))
        {
            SearchBranch = "";
            resp.getWriter().write("\nSetting Stock to Max\n");

//            SearchBranch = "Paddington";
//            delAllPHAB(resp);
//            SearchBranch = "Paddington";
//            fillPHAB(resp);
//            SearchBranch = "GreenPark";
//            delAllPHAB(resp);
//            SearchBranch = "GreenPark";
//            fillPHAB(resp);
//            SearchBranch = "MileEnd";
//            delAllPHAB(resp);
//            SearchBranch = "MileEnd";
//            fillPHAB(resp);

            fillDeletePHAB fDP1 = new fillDeletePHAB(c);
            fDP1.delAllPHAB(resp, "Paddington");
            fillDeletePHAB fDP2 = new fillDeletePHAB(c);
            fDP2.delAllPHAB(resp, "GreenPark");
            fillDeletePHAB fDP3 = new fillDeletePHAB(c);
            fDP3.delAllPHAB(resp, "MileEnd");

            fillDeletePHAB fDP4 = new fillDeletePHAB(c);
            fDP4.fillPHAB(resp, "GreenPark");
            fillDeletePHAB fDP5 = new fillDeletePHAB(c);
            fDP5.fillPHAB(resp, "Paddington");
            fillDeletePHAB fDP6 = new fillDeletePHAB(c);
            fDP6.fillPHAB(resp, "MileEnd");

            SearchBranch = "";
        }


        //Test database functions
        if(urlPattern.equals("/create_test_database")) {
            //createTestDatabase(resp);

            testDatabase testDB = new testDatabase(c);
            testDB.createTestDatabase(resp);
        }
        if(urlPattern.equals("/return_test_database")) {
            //returnTestDatabase(resp);

            testDatabase testDB = new testDatabase(c);
            testDB.returnTestDatabase(resp);
        }
        if(urlPattern.equals("/alter_test_database")) {
           //alterTestDatabase(resp);

           testDatabase testDB = new testDatabase(c);
           testDB.alterTestDatabase(resp);
        }

        //User database functions
        if(urlPattern.equals("/create_user_database")) {
            //createUserDatabase(resp);
            UserDatabase uDB = new UserDatabase(c);
            uDB.createUserDatabase(resp);
        }
        if(urlPattern.equals("/return_user_database")) {
            //returnUserDatabse(resp);
            UserDatabase uDB = new UserDatabase(c);
            uDB.returnUserDatabse(resp);
        }
        //

        ///NEED TO ADD
        //post request for warning - will check if blow 20% for everydrug - will be run after doing any other post requests
        //add to identical databases for 2 other branches
        //have an automatic revenue and profit function for all branches. - remember to edit selling price




    }




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
            //recieves data in the form of Manufacturer@Name
            String branch = message.substring(0,length);

            try {
                //put into global variables
                SearchBranch = branch;
            }
            catch (Exception e){

                resp.getWriter().write(e.getMessage());
            }

        }


        //message in the format of firstname/lastname|username@password#email

        if(urlPattern.equals("/add_user")) {
            //addUser(req, resp, message, length);
            UserDatabase uDB = new UserDatabase(c);
            uDB.addUser(req,resp, message, length);
        }
        //parsingformat: username@password
        if(urlPattern.equals("/verify_user")) {
            //verifyUser(req, resp, message, length);
            UserDatabase uDB = new UserDatabase(c);
            uDB.verifyUser(req,resp,message,length);
        }
    }

    private String SearchManufacturer;
    private String SearchName;
    private String SearchBranch;

    public void inputGlobalVars(String M, String N, String B)
    {
        SearchManufacturer = M;
        SearchName = N;
        SearchBranch = B;
    }

    public void fillPHAB(HttpServletResponse resp) throws IOException
    {
        try {
            resp.getWriter().write("Filling In PHAB "+SearchBranch+" Database\n");
            Statement s=c.createStatement();

            if(SearchBranch.equals("Paddington")) {
                //Cold and Flu
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

            if(SearchBranch.equals("GreenPark")) {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDB" + SearchBranch + " (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',4.5,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDB" + SearchBranch + " (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',6.8,5,20,0,20)");
            }

            if(SearchBranch.equals("MileEnd")) {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDB" + SearchBranch + " (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',4.5,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDB" + SearchBranch + " (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',6.8,5,20,0,20)");
            }



            resp.getWriter().write("\nalterTestDatabase called\n");
            if(s!=null){s.close();}
            //if (c!=null){c.close();}
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }



}