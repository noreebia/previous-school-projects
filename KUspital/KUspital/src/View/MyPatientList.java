package View;
import KUspital.*;

public class MyPatientList extends MyList {
    MainSystem ms;
 
    public MyPatientList(MainSystem ms) {      
       super();
       setTitle("환자 목록");
       this.ms=ms;        
       if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r1")) != 0)
        {
            jLabel3.setText(ms.DBoutput.get_information(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r1")), "name"));
        }
        if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r2")) != 0)
        {
            jLabel1.setText(ms.DBoutput.get_information(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r2")), "name"));
        }
        if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r3")) != 0)
        {
            jLabel5.setText(ms.DBoutput.get_information(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r3")), "name"));
        }
        if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r4")) != 0)
        {
            jLabel6.setText(ms.DBoutput.get_information(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r4")), "name"));
        }
        if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r5")) != 0)
        {
            jLabel4.setText(ms.DBoutput.get_information(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r5")), "name"));
        }
        initComponents();
        buttonModi();        
        pack();
        setVisible(true);
    }
    
    public void buttonModi()
    {
                this.jButton6.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){

                if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r1"))!= 0)
                {                    
                SecondOutsidePatientInfo sodi=new SecondOutsidePatientInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r1")) );
                }
                else
                {
                    Empty e=new Empty();
                    e.jLabel1.setText("등록된 환자가 없습니다.");
                }

            }
        });
        
        this.jButton1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r2"))!= 0)
                {                 
                SecondOutsidePatientInfo sodi=new SecondOutsidePatientInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r2")) );                  
                }
                else
                {
                    Empty e=new Empty();
                    e.jLabel1.setText("등록된 환자가 없습니다.");
                }
            }
        });
        this.jButton10.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r3"))!= 0)
                {                
                SecondOutsidePatientInfo sodi=new SecondOutsidePatientInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r3")) );                  
                }
                else
                {
                    Empty e=new Empty();
                    e.jLabel1.setText("등록된 환자가 없습니다.");
                }
            }
        });
        this.jButton12.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r4"))!= 0)
                {                 
                SecondOutsidePatientInfo sodi=new SecondOutsidePatientInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r4")) );               
                }
                else
                {
                    Empty e=new Empty();
                    e.jLabel1.setText("등록된 환자가 없습니다.");
                }
            }
        });
        this.jButton8.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                 if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r5"))!= 0)
                {
                  
                SecondOutsidePatientInfo sodi=new SecondOutsidePatientInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r5")) );              
                }
                else
                {
                    Empty e=new Empty();
                    e.jLabel1.setText("등록된 환자가 없습니다.");
                }
            }
        });    
        this.jButton13.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
            DoctorHome ph=new DoctorHome(ms);
            dispose();
            }
        });   
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 323, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
