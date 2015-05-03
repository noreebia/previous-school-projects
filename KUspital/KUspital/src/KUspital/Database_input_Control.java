package KUspital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_input_Control {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps=null;
	String connectionUrl = "jdbc:mysql://localhost:3306/kuspital";
	String connectionUser = "root";
	String connectionPassword = "kuspital";	

	public void regist_patient(int usernumber){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps=conn.prepareStatement("update user set type=1 where uno=?");
			ps.setInt(1, usernumber);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}	
	}
        public void changePassword(int usernumber,String PW){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps=conn.prepareStatement("update user set PW=? where uno=?");
			ps.setString(1, PW);
                        ps.setInt(2, usernumber);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}	
	}
        public void uploadPhoto(int usernumber,String pic){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps=conn.prepareStatement("update user set pic=? where uno=?");
			ps.setString(1, pic);
                        ps.setInt(2, usernumber);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}	
	}
        public void createRelation(int oppositeusernumber,int myusernumber){
		try{
                    Database_output_Control doc= new Database_output_Control();
                    int i=0;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
                        PreparedStatement ps=null;
                        if(Integer.parseInt(doc.get_information(myusernumber, "r1"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation1=? where uno=?");
                        }
                        else if(Integer.parseInt(doc.get_information(myusernumber, "r2"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation2=? where uno=?");   
                        }
                        else if(Integer.parseInt(doc.get_information(myusernumber, "r3"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation3=? where uno=?");
                        }
                        else  if(Integer.parseInt(doc.get_information(myusernumber, "r4"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation4=? where uno=?");
                        }
                        else if(Integer.parseInt(doc.get_information(myusernumber, "r5"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation5=? where uno=?");  
                        }
			ps.setInt(1, oppositeusernumber);
                        ps.setInt(2, myusernumber);
			ps.executeUpdate();
                        if(Integer.parseInt(doc.get_information(oppositeusernumber, "r1"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation1=? where uno=?");
                        }
                        else if(Integer.parseInt(doc.get_information(oppositeusernumber, "r2"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation2=? where uno=?");  
                        }
                        else if(Integer.parseInt(doc.get_information(oppositeusernumber, "r3"))==0)
                        {
                         ps=conn.prepareStatement("update user set relation3=? where uno=?");
                        }
                        else  if(Integer.parseInt(doc.get_information(oppositeusernumber, "r4"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation4=? where uno=?");  
                        }
                        else if(Integer.parseInt(doc.get_information(oppositeusernumber, "r5"))==0)
                        {
                        ps=conn.prepareStatement("update user set relation5=? where uno=?");
                        }
			ps.setInt(1, myusernumber);
                        ps.setInt(2, oppositeusernumber);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}	
	}
        public void deleteRelation(int oppositeusernumber,int myusernumber){
		try{
                    Database_output_Control doc= new Database_output_Control();
                    int i=0;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
                        PreparedStatement ps=null;
                        if(Integer.parseInt(doc.get_information(myusernumber, "r1"))==oppositeusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation1=? where uno=?");
                        }
                        else if(Integer.parseInt(doc.get_information(myusernumber, "r2"))==oppositeusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation2=? where uno=?");   
                        }
                        else if(Integer.parseInt(doc.get_information(myusernumber, "r3"))==oppositeusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation3=? where uno=?");
                        }
                        else  if(Integer.parseInt(doc.get_information(myusernumber, "r4"))==oppositeusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation4=? where uno=?");
                        }
                        else if(Integer.parseInt(doc.get_information(myusernumber, "r5"))==oppositeusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation5=? where uno=?");  
                        }
			ps.setInt(1, 0);
                        ps.setInt(2, myusernumber);
			ps.executeUpdate();
                        if(Integer.parseInt(doc.get_information(oppositeusernumber, "r1"))==myusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation1=? where uno=?");
                        }
                        else if(Integer.parseInt(doc.get_information(oppositeusernumber, "r2"))==myusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation2=? where uno=?");  
                        }
                        else if(Integer.parseInt(doc.get_information(oppositeusernumber, "r3"))==myusernumber)
                        {
                         ps=conn.prepareStatement("update user set relation3=? where uno=?");
                        }
                        else  if(Integer.parseInt(doc.get_information(oppositeusernumber, "r4"))==myusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation4=? where uno=?");  
                        }
                        else if(Integer.parseInt(doc.get_information(oppositeusernumber, "r5"))==myusernumber)
                        {
                        ps=conn.prepareStatement("update user set relation5=? where uno=?");
                        }
			ps.setInt(1, 0);
                        ps.setInt(2, oppositeusernumber);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}	
	}
        
	public void regist_id(String id, String password, String name, int age,String gender,String phone){
		int i=0;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps= conn.prepareStatement("insert into user values(default, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 2);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, gender);
			ps.setString(5, phone);
			ps.setString(6, id);
			ps.setString(7, password);
			ps.setString(8, null);
			ps.setString(9, null);
			ps.setInt(10, 1001);		
                        ps.setInt(11, 0);
                        ps.setInt(12, 0);	
                        ps.setInt(13, 0);	
                        ps.setInt(14, 0);	
                        ps.setInt(15, 0);	
                        ps.setString(16, null);
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if(rs.next())
			{
				i=rs.getInt(1);
			}
			ps=conn.prepareStatement("ALTER TABLE pat_info AUTO_INCREMENT =?");
			ps.setInt(1,i);
			ps.executeUpdate();
			ps=conn.prepareStatement("insert into pat_info values(default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, 0);
			ps.setInt(2, 0);
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			ps.setInt(9, 0);
			ps.setInt(10, 0);
			ps.setInt(11, 0);
			ps.setInt(12, 0);
			ps.setInt(13, 0);
			ps.setInt(14, 0);
			ps.setInt(15, 0);
			ps.setInt(16, 0);
			ps.setInt(17, 0);
			ps.setInt(18, 0);
			ps.setInt(19, 0);
			ps.setInt(20, 0);
			ps.setInt(21, 0);
			ps.setInt(22, 0);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
	}
	public void searchdoctor()
	{
		String i;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			rs=stmt.executeQuery("SELECT name from user WHERE dno=(select deptno from department where deptname ='doctor');");
			while(rs.next())
			{
				i=rs.getString(1);
				System.out.println("Registration Number: "+ i);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}			
	}
        public void updateInfo(int usernumber, String update, String update2)
        {
            		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps=conn.prepareStatement("update user set ta1= ? where uno=?");
			ps.setString(1, update);
			ps.setInt(2, usernumber);
                        ps.executeUpdate();
			ps=conn.prepareStatement("update user set ta2= ? where uno=?");
			ps.setString(1, update2);
			ps.setInt(2, usernumber);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
            
        }
        public void updateSymptoms(int bool,String sym,int usernumber)
        {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps=conn.prepareStatement("update pat_info set "+sym+" = "+bool+" where uno ="+usernumber);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
        }

	public void regist_doctor(String id,String password, String name, int age, String gender, String phone,int dept){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps= conn.prepareStatement("insert into user values(default, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, 0);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, gender);
			ps.setString(5, phone);
			ps.setString(6, id);
			ps.setString(7, password);		
			ps.setString(8, null);
			ps.setString(9, null);
			ps.setInt(10, dept);	
                        ps.setInt(11, 0);
                        ps.setInt(12, 0);
                        ps.setInt(13, 0);
                        ps.setInt(14, 0);
                        ps.setInt(15, 0);
                        ps.setString(16, null);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}			

	}

	public void delete_id(int usernumber){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps= conn.prepareStatement("delete from user where uno=?;");
			ps.setInt(1, usernumber);
			ps.executeUpdate();
			ps= conn.prepareStatement("delete from pat_info where uno=?;");
			ps.setInt(1, usernumber);
			ps.executeUpdate();	
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
	}
 

	public void downgrade(int usernumber){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();		
			PreparedStatement ps=conn.prepareStatement("update user set type=2 where uno=?");
			ps.setInt(1, usernumber);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}    	
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}	
	}
}
