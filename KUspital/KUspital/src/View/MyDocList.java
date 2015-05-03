package View;
import KUspital.*;

public class MyDocList extends MyList {
MainSystem ms;

    public MyDocList(MainSystem ms) {
        super();
        
        this.ms=ms;
        initComponents();
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
        buttonModi();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void buttonModi()
    {
                this.jButton6.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){

                if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r1"))!= 0)
                {                 
                    OutsideDocInfo di=new OutsideDocInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r1")) );                   
                }
                else
                {
                    Empty e=new Empty();
                }

            }
        });
        
        this.jButton1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r2"))!= 0)
                {              
                OutsideDocInfo di=new OutsideDocInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r2")) );                   
                }
                else
                {
                    Empty e=new Empty();
                }
            }
        });
        this.jButton10.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r3"))!= 0)
                {                  
                OutsideDocInfo di=new OutsideDocInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r3")) );                   
                }
                else
                {
                    Empty e=new Empty();
                }
            }
        });
        this.jButton12.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r4"))!= 0)
                {                 
                OutsideDocInfo di=new OutsideDocInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r4")) );                   
                }
                else
                {
                    Empty e=new Empty();
                }
            }
        });
        this.jButton8.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                 if(Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r5"))!= 0)
                {                
                OutsideDocInfo di=new OutsideDocInfo(ms,Integer.parseInt(ms.DBoutput.get_information(ms.my_uno, "r5")) );                   
                }
                else
                {
                    Empty e=new Empty();
                }
            }
        });    
        this.jButton13.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
            PatientHome ph=new PatientHome(ms);
            dispose();
            }
        });   

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
