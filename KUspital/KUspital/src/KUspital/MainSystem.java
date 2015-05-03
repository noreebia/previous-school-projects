package KUspital;

import View.*;

public class MainSystem 
{
    private boolean status;
    public Make_Chat_Control mcc;
    public Database_input_Control DBinput;
    public Database_output_Control DBoutput;
    public int my_uno;
    public int my_type;
    public String my_name;
    public int my_age;
    public String my_gender;
    public String my_phone;
    public String my_dept;
    public String my_ID;
    public String my_PW;
    public String my_ta1;
    public String my_ta2;
    

    public MainSystem()
    {
        DBinput=new Database_input_Control();
        DBoutput=new Database_output_Control();
        Login start = new Login(this);
    }
    
    public void startChat(String ID, Waiting wt)	
    {
	mcc.makeChat(ID, wt);
    }
    
    public void setInfo(String ID,String PW)
    {
        my_uno=this.DBoutput.get_registration_num(ID, PW);
        my_type=Integer.parseInt(this.DBoutput.get_information(my_uno, "type"));
        my_name=this.DBoutput.get_information(my_uno, "name");
        my_age=Integer.parseInt(this.DBoutput.get_information(my_uno, "age"));
        my_gender=this.DBoutput.get_information(my_uno, "gender");
        my_phone=this.DBoutput.get_information(my_uno, "phone");
        my_ID=this.DBoutput.get_information(my_uno, "ID");
        my_PW=this.DBoutput.get_information(my_uno, "PW");
        my_ta1=this.DBoutput.get_information(my_uno, "ta1");
        my_ta2=this.DBoutput.get_information(my_uno, "ta2");
        switch(Integer.parseInt(this.DBoutput.get_information(my_uno, "department")))
        {
            case(1):
                my_dept="소아과";
                break;
            case(2):
                my_dept="정형외과";
                break;
            case(3):
                my_dept="정신과";
                break;
            case(4):
                my_dept="비뇨기과";
                break;
            case(1001):
                my_dept="환자";
                break;   
        }
        
        mcc = new Make_Chat_Control(my_ID, this);
    	mcc.start();
    }
    
    public void updateInfo()
    {
        my_ta1=this.DBoutput.get_information(my_uno, "ta1");
        my_ta2=this.DBoutput.get_information(my_uno, "ta2");
    }

    public void launchMain(int type){
        switch (type)
        {
            case 0:
                DoctorHome dh =new DoctorHome(this);
                break;
            case 1:
                PatientHome ph = new PatientHome(this);
                break;
            case 2:
                ph = new PatientHome(this);
                break;
        }
    }

    public void regist_id(String id, String password, String name, int age,String gender,String phone){
        DBinput.regist_id(id, password, name, age, gender, phone);
    }
    public void get_registration_num(String id, String password)
    {

    }
    public void delete_id(String id, int password){
    }

    public String get_information(String name, String kind){
        
        return null;
    }

}
