
package KUspital;
import java.sql.*;

public class Database_output_Control {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps=null;
	String connectionUrl = "jdbc:mysql://localhost:3306/kuspital";
	String connectionUser = "root";
	String connectionPassword = "kuspital";	
        
        public int[] getMyDocList(int usernumber)
        {

                int[] mydoclist=new int[5];
                for(int a=0;a<5;a++)
                {
                    mydoclist[a]=0;
                }
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM user WHERE uno="+usernumber);
			if(rs.next())
			{
                                    
                                    mydoclist[0]=rs.getInt(12);
                                    mydoclist[1]=rs.getInt(13);
                                    mydoclist[2]=rs.getInt(14);
                                    mydoclist[3]=rs.getInt(15);
                                    mydoclist[4]=rs.getInt(16);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
		return mydoclist;            
        }
        
        public int[] get_doclist(int deptno)
        {
		int i=0;
                int[] docnolist=new int[20];
                for(int a=0;a<20;a++)
                {
                    docnolist[a]=0;
                }
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM user WHERE dno="+deptno);
			while(rs.next())
			{
				docnolist[i]=rs.getInt(1);
				System.out.println("Registration Number: "+ i);
                                i++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
		return docnolist;            
        }
        public String getDeptCode(int deptno){
            String i="";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM department WHERE deptno=\""+deptno+"\"");
			if(rs.next())
			{
				i=rs.getString(3);
				System.out.println("department code: "+ i);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
		return i;
	}
        public String getPhoto(int userno){
            String i="";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM user WHERE uno="+userno);
			if(rs.next())
			{
				i=rs.getString(17);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
		return i;
	}
        
        
	public String get_information(int registration_num, String kind){
		String ki = " ";		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();				
			String a=kind;
			rs = stmt.executeQuery("SELECT * FROM user WHERE UNO="+registration_num);
			if(rs.next())
			{	
				String id = rs.getString(1);
				switch(a)
				{
				case ("type"):
					ki=rs.getString(2);
				break;
				case ("name"):
					ki=rs.getString(3);
				break;
				case ("age"):
					ki=rs.getString(4);
				break;
				case ("gender"):
					ki=rs.getString(5);
				break;
				case ("phone"):
					ki=rs.getString(6);
				break;
                                case ("ID"):
					ki=rs.getString(7);
				break;
                                case ("PW"):
					ki=rs.getString(8);
				break;
                                case ("ta1"):
					ki=rs.getString(9);
				break;
                                case ("ta2"):
					ki=rs.getString(10);
				break;
				case ("department"):
					ki=rs.getString(11);
				break;
                                case ("r1"):
					ki=rs.getString(12);
				break;
                                case ("r2"):
					ki=rs.getString(13);
				break;
                                case ("r3"):
					ki=rs.getString(14);
				break;
                                case ("r4"):
					ki=rs.getString(15);
				break;
                                case ("r5"):
					ki=rs.getString(16);
				break;
				}
				System.out.println("UNO: " + id + ", "+a+":" + ki);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return ki;
	}
        public int get_symptom_status(int usernumber, String sym)
        {
            int i=0;
            int discern=0;
            switch(sym)
            {
                case "scalp_bleeding":
                    discern=2;
                    break;
                case "balding":
                    discern=3;
                    break;
                case "brittle_hair":
                    discern=4;
                    break;
                case "headaches":
                    discern=5;
                    break;
                case "bleeding_eye":
                    discern=6;
                    break;
                case "blindness":
                    discern=7;
                    break;
                case "eye_irritation":
                    discern=8;
                    break;
                case "red_eye":
                    discern=9;
                    break;
                case "nosebleed":
                    discern=10;
                    break;
                case "runny_nose":
                    discern=11;
                    break;
                case "bleeding_gums":
                    discern=12;
                    break;
                case "chapped_lips":
                    discern=13;
                    break;
                case "grinding_teeth":
                    discern=14;
                    break;
                case "jaw_locking":
                    discern=15;
                    break;
                case "ear_ache":
                    discern=16;
                    break;
                case "joint_paint":
                    discern=17;
                    break;
                case "broken_bones":
                    discern=18;
                    break;
                case "coughing":
                    discern=19;
                    break;
                case "constipation":
                    discern=20;
                    break;
                case "nausea":
                    discern=21;
                    break;
                case "back_pain":
                    discern=22;
                    break;
                case "knee_pain":
                    discern=23;
                    break;

            }
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM pat_info WHERE uno=\""+usernumber+"\"");
			if(rs.next())
			{
				i=rs.getInt(discern);
				System.out.println(sym+"status: "+ i);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
		return i;
        }
        public int availability(String ID)
        {
                int i=0;
		try{
			String n=ID;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM user WHERE ID=\""+n+"\"");
			if(rs.next())
			{
				i=1;
				System.out.println(i);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
		return i;   
        }

	public int get_registration_num(String ID, String Password){
		int i=0;
		try{
			String n=ID;
			String m=Password;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM user WHERE ID=\""+n+"\" AND PW=\""+m+"\"");
			if(rs.next())
			{
				i=rs.getInt(1);
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
		return i;
	}
	public int get_registration_num(String name){
		int i=0;
		try{
			String n=name;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM user WHERE name=\""+n+"\"");
			if(rs.next())
			{
				i=rs.getInt(1);
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
		return i;
	}
        public int get_registration_nums(String ID){
            int i = 0;
		try{
			String n=ID;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();			
			rs=stmt.executeQuery("SELECT * FROM user WHERE ID=\""+n+"\"");
			if(rs.next())
			{
				i=rs.getInt(1);
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
		return i;
	}
}