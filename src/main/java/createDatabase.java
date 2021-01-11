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
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }

    public void createPHABGreenPark(HttpServletResponse resp) throws IOException
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
        }
        catch (Exception e){

            resp.getWriter().write(e.getMessage());
        }
    }
}
