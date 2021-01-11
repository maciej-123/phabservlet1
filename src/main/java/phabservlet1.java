import org.graalvm.compiler.lir.LIRInstruction;

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
            //testFillPHAB(resp);
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
            //returnPHABMileEnd(resp);

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

            fillDeletePHAB fDP = new fillDeletePHAB(c);
            fDP.delAllPHAB(resp, "Paddington");
            fDP.fillPHAB(resp, "Paddington");
            fDP.delAllPHAB(resp, "GreenPark");
            fDP.fillPHAB(resp, "GreenPark");
            fDP.delAllPHAB(resp, "MileEnd");
            fDP.fillPHAB(resp, "MileEnd");

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



    ////###


    public void createUserDatabase(HttpServletResponse resp) throws IOException {
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

    public void returnUserDatabse(HttpServletResponse resp) throws IOException {
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

    public void addUser(HttpServletRequest req, HttpServletResponse resp, String message, int length) throws IOException {
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

            if(rset.next()) {
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

    public void verifyUser(HttpServletRequest req, HttpServletResponse resp, String message, int length) throws IOException {
        try {
            resp.reset();
            Statement s = c.createStatement();
            String username = message.substring(0,message.indexOf('@'));
            String password = message.substring(message.indexOf('@')+1, length);

            String verifyUser = "SELECT * FROM Users WHERE Username= '"+username+"';";

            ResultSet rset = s.executeQuery(verifyUser);
            if(rset.next()) {

                String usr = rset.getString("Username");
                String pwd = rset.getString("Password");
                if(usr.equals(username) && pwd.equals(password)) {
                    resp.getWriter().write("Login successful");
                }
                else resp.getWriter().write("User does not exist or password incorrect");

            }

            else {
                resp.getWriter().write("User does not exist.");
            }




        }

        catch(Exception e) {
            resp.getWriter().write(e.getMessage());
        }
    }

    public void deltestUserDatabase(HttpServletResponse resp) throws IOException {
        try {
            resp.getWriter().write("Deleting test database");
            Statement s = this.c.createStatement();
            String deltest = "DELETE FROM Users WHERE Username = 'test'";
            String deltest2 = "DELETE FROM Users WHERE Username = 'test2123";

            s.executeQuery(deltest);
            s.executeQuery(deltest2);

            resp.getWriter().write("Test users have been deleted");

        }
        catch(Exception e) {
            resp.getWriter().write(e.getMessage());
        }
    }

    public void delTestPHAB(HttpServletResponse resp, String SearchBranch) throws IOException
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

    public void testFillPHAB(HttpServletResponse resp, String SearchBranch) throws IOException
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

    public void createTestDatabase(HttpServletResponse resp) throws IOException
    {

        try {
            resp.getWriter().write("CreateTestDatabase");
            Statement s=c.createStatement();

            //select table from INFORMATION_SCHEMA.TABLES - list of all the tables
            String strSelect = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

            ResultSet rset = s.executeQuery(strSelect);

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

    public void returnTestDatabase(HttpServletResponse resp) throws IOException
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

    public void alterTestDatabase(HttpServletResponse resp) throws IOException
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

    public void getLimitOne(HttpServletResponse resp, String SearchBranch, String SearchName, String SearchManufacturer) throws IOException
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

    public void searchForDrug(HttpServletResponse resp, String SearchBranch, String SearchName, String SearchManufacturer) throws IOException
    {

        try {
            String strSelect = "SELECT * FROM StockDB"+SearchBranch+" WHERE Name = '" + SearchName + "' AND Manufacturer = '" + SearchManufacturer + "';";

            Statement s = c.createStatement();
            ResultSet rset = s.executeQuery(strSelect);

            resp.getWriter().write("\n");
            while (rset.next()) {
                for(int n=1;n<=8;n++) {
                    resp.getWriter().write(rset.getString(n));
                    resp.getWriter().write(",");
                    resp.getWriter().write(" ");
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
            resp.getWriter().write(e.getMessage());
        }
    }

    public void decreaseStock(HttpServletResponse resp, String SearchBranch, String SearchName, String SearchManufacturer) throws IOException
    {
        resp.getWriter().write("Decreasing Stock\n");

        resp.getWriter().write(SearchManufacturer);
        resp.getWriter().write("\n");
        resp.getWriter().write(SearchName);
        resp.getWriter().write("\n");
//

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
    ///###

}