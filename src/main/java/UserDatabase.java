import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class UserDatabase {

    private Connection c;

    UserDatabase(Connection _c) throws IOException
    {
        c = _c;
    }

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

}