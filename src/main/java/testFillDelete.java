import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class testFillDelete {
    private Connection c;

    testFillDelete(Connection _c) throws IOException
    {
        c = _c;
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
            if (c!=null) {
                c.close();
            }

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
            if (c!=null) {
                c.close();
            }

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }
}
