import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class revenueProfitStock {

    private Connection c;

    revenueProfitStock(Connection _c) throws IOException
    {
        c = _c;
    }

    public void checkStock(HttpServletResponse resp, String SearchBranch) throws IOException
    {

        try {
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
            if (count>=0)
                resp.getWriter().write("\n WARNING: "+ count +" stock(s) below 20% found");

        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }

    }

    public void calculateProfit(HttpServletResponse resp, String SearchBranch) throws IOException
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

    public void calculateRevenue(HttpServletResponse resp, String SearchBranch) throws IOException
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

}
