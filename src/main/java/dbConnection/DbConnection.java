package dbConnection;

import java.sql.Connection;

import javax.naming.Context;
import javax.sql.DataSource;

import jakarta.servlet.http.HttpServlet;

public class DbConnection extends HttpServlet {
	
	private static final DbConnection  dbConnection=new  DbConnection();
	private  DataSource dataSource ; 
private static final long serialVersionUID = 1L;
       
   
    public DbConnection() {
    	try {
        Context context=new initialcontext();
        Context encontext=(Context)context.lookup("java:/comp/env");
       DataSource dataSource=( DataSource)encontext.lookup("jdbc/School");
       
    }catch(NamingException e) {
    	e.printStackTrace();  
    	}
    
    }
    
    public static DbConnection gdbConnection() {
    	return dbConnection;
    }
    public static Connection getConnection() {
    	try {
    	return dataSource.getConnection();
    	}catch(SQLExceotion e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
