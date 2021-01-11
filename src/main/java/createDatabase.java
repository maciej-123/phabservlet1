import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class createDatabase {
    private Connection c;

    createDatabase(Connection _c) throws IOException
    {
        c = _c;
    }

    public void createPHABPaddington(HttpServletResponse resp) throws IOException
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
            if (c!=null) {
                c.close();
            }
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    public void createPHABGreenPark(HttpServletResponse resp) throws IOException
    {
        try{
            resp.getWriter().write("Creating PHAB Database for GreenPark\n");
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
            if (c!=null) {
                c.close();
            }
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    public void createPHABMileEnd(HttpServletResponse resp) throws IOException
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
            if (c!=null) {
                c.close();
            }
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }
}
