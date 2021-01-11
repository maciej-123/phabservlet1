import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class searchDecreaseLimit {
    private Connection c;

    searchDecreaseLimit(Connection _c) throws IOException
    {
        c = _c;
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
}
