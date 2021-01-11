
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
            //fillPHAB(resp);

            fillDeletePHAB fDP = new fillDeletePHAB(c);
            fDP.fillPHAB(resp,SearchBranch);
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



}