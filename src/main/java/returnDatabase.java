import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class returnDatabase {

    private Connection c;

    returnDatabase(Connection _c) throws IOException
    {
        c = _c;
    }

    public void returnPHABPaddington(HttpServletResponse resp) throws IOException
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
            if (c!=null) {
                c.close();
            }
        }
        catch(Exception e)
        {
            resp.getWriter().write(e.getMessage());
        }
    }

    public void returnPHABGreenPark(HttpServletResponse resp) throws IOException
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
            if (c!=null) {
                c.close();
            }
        }
        catch(Exception e)
        {
            resp.getWriter().write(e.getMessage());
        }
    }

    public void returnPHABMileEnd(HttpServletResponse resp) throws IOException
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
            if (c!=null) {
                c.close();
            }
        }
        catch(Exception e)
        {
            resp.getWriter().write(e.getMessage());
        }
    }
}
