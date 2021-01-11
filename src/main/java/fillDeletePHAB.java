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

    public void fillPHAB(HttpServletResponse resp, String SearchBranch) throws IOException
    {
        try {
            resp.getWriter().write("Filling In PHAB "+SearchBranch+" Database\n");
            Statement s=c.createStatement();

            boolean lockCreate = false;
            //fill database with test row

            if(lockCreate == true)
            {
                resp.getWriter().write("Creation Locked, Please Edit code\n");
            }
            else if (SearchBranch == "Paddington"){
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

            else if (SearchBranch == "GreenPark")
            {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',9,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',13.6,5,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',17,7,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',18,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','max','16 caps',8.4,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','standard','10 sachets',9,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','day and night','16 caps',9,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','max','16 caps',8.4,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','mucus relief','16 caps',9.6,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','4 flu','24 caps',12,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('e45','psoriasis cream','50ml',40,16,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eurax','skin cream','100g',11.4,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','skin relief cream','50ml',18,7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','face scrub','100ml',15,6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','psoriasis cream','150ml',60,25,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','repair and Restore','100g',24,10,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','30g',24,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','100g',50,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','moisturising cream','50ml',20,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','exfoliating cleanser','180ml',24,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','meltlets','16 caps',8,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','express','16 caps',8,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','max strength','32 caps',14,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','standard','16 caps',8,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cuprofen','max strength','96 caps',22,9,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('solpadeine','headache','16 caps',4,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','extra','16 caps',4.6,2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','triple action','12 caps',4,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','original','16 caps',3.6,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cisprin','soluble','32 tablets',7.2,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','blackcurrant','12 sachets',16,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','lemon','12 sachets',16,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','chewable','24 tablets',8.4,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('senokot','max','10 tablets',6,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','advance','300ml',20,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benadryl','relief','24 caps',18,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('piriteze','tabs','7 tablets',6,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('beconase','relief','100 sprays',12,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','antiseptic','500ml',6.4,3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','hand sanitizer','500ml',14,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('elastoplast','plasters','20 plasters',6,2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('tcp','liquid','200ml',8,3.2,20,0,20)");
            }

            else if (SearchBranch == "MileEnd")
            {
                //Cold and Flu
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','vaporub','100g',3.5,3.7,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('vicks','first defence','15ml',5.2,5,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',6.5,7,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gsk','night nurse','160ml',6.9,7.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','max','16 caps',3.2,3.7,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('lemsip','standard','10 sachets',3.5,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','day and night','16 caps',3.5,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('sudafed','max','16 caps',3.2,3.2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','mucus relief','16 caps',3.7,3.2,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benylin','4 flu','24 caps',4.6,4.9,20,0,20)");

                //Skincare
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('e45','psoriasis cream','50ml',15.4,16,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eurax','skin cream','100g',4.4,4.2,15,0,15)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','skin relief cream','50ml',6.9,7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('eucerin','face scrub','100ml',5.8,6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','psoriasis cream','150ml',23.1,25,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','repair and Restore','100g',9.2,10,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','30g',9.2,9.7,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dermalex','eczema cream','100g',19.2,22.2,5,0,5)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','moisturising cream','50ml',7.7,7.6,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cetaphil','exfoliating cleanser','180ml',9.2,10.1,20,0,20)");

                //Headaches and Pain Relief
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','meltlets','16 caps',3.1,3.7,40,0,40)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','express','16 caps',3.1,3.5,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','max strength','32 caps',5.4,6.2,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('nurofen','standard','16 caps',3.1,3.2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cuprofen','max strength','96 caps',8.5,9,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('solpadeine','headache','16 caps',1.5,1.6,20,1,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','extra','16 caps',1.8,2,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','triple action','12 caps',1.5,1.9,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('anadin','original','16 caps',1.4,1.5,30,1,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('cisprin','soluble','32 tablets',2.8,2.8,20,1,20)");

                //Digestion
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','blackcurrant','12 sachets',6.2,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dioralyte','lemon','12 sachets',6.2,7.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','chewable','24 tablets',3.2,3.5,25,0,25)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('senokot','max','10 tablets',2.3,2.7,10,0,10)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('gaviscon','advance','300ml',7.1,8.1,10,0,10)");

                //Allergy
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('benadryl','relief','24 caps',6.9,7.1,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('piriteze','tabs','7 tablets',2.3,2.3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('beconase','relief','100 sprays',4.6,4,20,0,20)");

                //First Aid
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','antiseptic','500ml',2.5,3,20,0,20)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('dettol','hand sanitizer','500ml',5.4,6.3,50,0,50)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('elastoplast','plasters','20 plasters',2.3,2,30,0,30)");
                s.execute("INSERT INTO public.StockDB"+SearchBranch+" (Manufacturer,Name,Quantity,SalesPrice,PurchasePrice,FullStock,LimitOne,CurrentStock) VALUES ('tcp','liquid','200ml',3.1,3.2,20,0,20)");
            }


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

