import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class fillDeletePHAB {

    private Connection c;

    fillDeletePHAB(Connection _c) throws IOException
    {
        c = _c;
    }

    public void delAllPHAB(HttpServletResponse resp, String SearchBranch) throws IOException
    {
        try {

            boolean deleteLock = false;

            resp.getWriter().write("Deleting Test Rows "+SearchBranch+"\n");

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
                if (c!=null) {
                    c.close();
                }
            }

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

}

