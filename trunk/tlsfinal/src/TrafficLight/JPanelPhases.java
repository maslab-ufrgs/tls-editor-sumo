/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrafficLight;

import classes.Project;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import ui.Display;

/**
 *
 * @author luiz
 */
public class JPanelPhases extends javax.swing.JPanel {

    /**
     * Creates new form JPanelPhases
     */
    private int len;
    public static  String[] columnNames = {"Open","From Edge", "To Edge","From Lane","To Lane","Color"};  
    public static List<List<String>> mapEdgesLanes2 = new ArrayList<List<String>>();
            ///Map<String,String> mapEdgesLanes2 = new HashMap<String,String>();
    public static int repaint = 0;
    public Object[][] data;
    public int datarow=0;
    public int datacol=0;
    
    public static Map<String, List<String>> GeneralInfo = new HashMap<String, List<String>>();  
    public static Map<String, List<List<String>>> PhaseInfo = new HashMap<String, List<List<String>>>();
    public static Map<String, List<List<String>>> SavedPhaseInfo = new HashMap<String, List<List<String>>>();
    public static Map<String, List<JTable>> PhaseTabel = new HashMap<String, List<JTable>>();
    
    public static Map<String, List<List<String>>> Phasedatas = new HashMap<String, List<List<String>>>();

       
     //public Map<String, Map <String>> Phasesinf = new HashMap<String, Map <String>>;
       
    
    
    public static void setPhaseTabelModel(){  
        
        for(String Id_Junction : PhaseTabel.keySet()){  
          List<List<String>> DataJunction = new ArrayList<List<String>>();
          int index = PhaseTabel.get(Id_Junction).size();
          for (int j =0 ; j< index; j++){
            List<String> datas = new ArrayList<String>();
            for( int i =0;i< PhaseTabel.get(Id_Junction).get(j).getRowCount();i++){
             datas.add(""+PhaseTabel.get(Id_Junction).get(j).getValueAt(i, 0));
            }
            DataJunction.add(datas);
          }
          Phasedatas.remove(Id_Junction);
          Phasedatas.put(Id_Junction, DataJunction);
        }
        
    }
    
    public void setlen(String Conectionname){
        Map<String, List<List<String>>> InformationsNET  = Project.getCurrentlyLoadedProject().getFromTo();
        for( String keys : InformationsNET.keySet() ){
            if(keys.equals(Conectionname)){
             this.len = InformationsNET.get(keys).size();
            }
        }
    }
    
    public void updateTable(Object[][] data){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String [] {
                "Open", "From Edge", "To Edge", "From Lane", "To Lane", "Color"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        
        jTable1.getColumn("Color").setCellRenderer(
                
            
            new DefaultTableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int  isselect = 0;
                
                Map<Integer,Integer> aux = new HashMap<Integer,Integer>();
                
                for( int i =0;i< table.getRowCount();i++){
                    if(table.getValueAt(i, 0).equals(true)){
                        aux.put(i,isselect);
                        isselect ++;
                    }
                }


                        
                if(table.getValueAt(row, 0).equals(true) && column == 5 ){
                    
                    
                renderer.setBackground(Display.getColor(aux.get(row)));
                }else{
                renderer.setBackground(null);    
                }
                
                return renderer;
                
            }
            
              
            }
        );
       
        setVisible(true);

        datarow = jTable1.getRowCount();
        datacol = jTable1.getColumnCount();
 
        
        
         jTable1.setEnabled(true);
         jTable1.setVisible(true);

        //jTable1.sorterChanged(null);
         //setPhaseTabelModel();
        
    }
    
    public void UpdateSelect(int index, String Id_Junction){
        
        if(PhaseTabel.containsKey(Id_Junction)){
                       if(PhaseTabel.get(Id_Junction).get(index)!=null){
                       for( int i =0;i< PhaseTabel.get(Id_Junction).get(index).getRowCount();i++){
                           ////System.out.print(" "+PhaseTabel.get(Id_Junction).get(index).getValueAt(i, 0));
                           jTable1.setValueAt(PhaseTabel.get(Id_Junction).get(index).getValueAt(i, 0), i, 0);
                       }
            }
        
        }
         
        //setPhaseTabelModel();
        
    }
    

    
    
     public JPanelPhases(String Conectionname, int phasesnumber) {
        setlen(Conectionname);
        initComponents();
        
        data = InsertValues(Conectionname,phasesnumber);
        //JFrameInsert.PhasesCount ++;
        updateTable(data);

    }
     
     public JTable getJtabble(){

         return jTable1; 
     }
     
     public void setJTable(JTable tabel){
         
         //jTable1 = tabel;
         
         Object[][] numdata= new Object[tabel.getModel().getRowCount()][tabel.getModel().getColumnCount()];;
         for (int count = 0; count < tabel.getModel().getRowCount(); count++){
         for (int j = 0;j<tabel.getModel().getColumnCount(); j++ ){
             jTable1.getModel().setValueAt(tabel.getValueAt(count, j), count, j);
             numdata[count][j] = tabel.getValueAt(count, j);
         } 
         }
         
         
         //numdata;
 
         //updateTable(data);
         jTable1.repaint();
         repaint();
         //setPhaseTabelModel();
     }
     
     
     public Object[][] getdata(){
         return data;
     }
    
     public List<List<String>> OrdenarList (List<List<String>> inList){
         ////System.out.println("inList: "+inList);
         List<List<String>> NewauxList = new ArrayList<List<String>>();
         
         for( List<String>  outvalues : inList ){
             int m = 0;
             List<String> listString = new ArrayList<String>();
             for( List<String>  values : inList ){
                 if(!NewauxList.contains(values)){
                  if ( m == 0){listString = values; m=1; }else{
                        // Compara FromEdge - se for menor atualiza
                         if( listString.get(0).toString().compareToIgnoreCase(values.get(0).toString()) > 0){
                             listString = values;
                         } else {
                             // Compara FromEdge - se forem iguais - verifica o To Edge
                             if(listString.get(0).toString().compareToIgnoreCase(values.get(0).toString()) == 0){
                                // Compara ToEdge - se for menor atualiza
                                if(listString.get(1).toString().compareToIgnoreCase(values.get(1).toString()) > 0  ){
                                    listString = values; 
                                }else{
                                     // Compara ToEdge - se forem iguais - verifica FromLane
                                     if(listString.get(1).toString().compareToIgnoreCase(values.get(1).toString()) == 0){
                                        // Compara FromLane
                                        if( listString.get(2).toString().compareToIgnoreCase(values.get(2).toString()) > 0 ){
                                            listString = values; 
                                        }else{
                                            // Compara FromLane - se forem iguais - verifica o To Lane
                                            if( listString.get(2).toString().compareToIgnoreCase(values.get(2).toString()) == 0 ){
                                                if( listString.get(3).toString().compareToIgnoreCase(values.get(3).toString()) > 0  ){
                                                 listString = values; 
                                                }
                                            }
                                        }
                                     }
                                }
                             }
                         }
                  }
                 }   
         }
             if(!NewauxList.contains(listString)){
                NewauxList.add(listString);
             }
         }

         return NewauxList;
     }
     
    public  Object[][] InsertValues(String Conectionname, int PhasesCount){
        Object[][] data = new Object[len][7];
        
         Map<String, List<List<String>>> InformationsNET  = Project.getCurrentlyLoadedProject().getFromTo();
        // Map<String, List<JTable>> PhaseTabel
        //System.out.println(Conectionname+ "----\n" +InformationsNET);
         
         
        for( String keys : InformationsNET.keySet() ){
            
            if(keys.equals(Conectionname)){
               ////System.out.println("----"+Conectionname);
               List<List<String>> auxList = InformationsNET.get(keys);      
               int i = 0;       
               ////System.out.println("auxList"+auxList);
               for( List<String>  values : OrdenarList(InformationsNET.get(keys)) ){
                   if(Phasedatas.containsKey(Conectionname)){
                   ////System.out.println("Phases:"+Phasedatas.get(Conectionname).size()+" and "+PhasesCount);
                       
                   if(Phasedatas.containsKey(Conectionname) &&  PhasesCount < Phasedatas.get(Conectionname).size()){
                   data[i][0] = Boolean.parseBoolean(Phasedatas.get(Conectionname).get(PhasesCount).get(i)); // Visible
                   data[i][1] = values.get(0); // From Edge
                   data[i][2] = values.get(1); // To Edge
                   data[i][3] = values.get(2); // From Lane
                   data[i][4] = values.get(3); // To Lane
                   data[i][5] = "     ";    
                   }else{
                   data[i][0] = false; // Visible
                   data[i][1] = values.get(0); // From Edge
                   data[i][2] = values.get(1); // To Edge
                   data[i][3] = values.get(2); // From Lane
                   data[i][4] = values.get(3); // To Lane
                   data[i][5] = "     "; 
                   }
                   }
                   else{
                   data[i][0] = false; // Visible
                   data[i][1] = values.get(0); // From Edge
                   data[i][2] = values.get(1); // To Edge
                   data[i][3] = values.get(2); // From Lane
                   data[i][4] = values.get(3); // To Lane
                   data[i][5] = "     ";
                   }
                   // 4 - reference
                   i++;
               }
            }                 
        }        
       return data; 
    }


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "From Edge", "To Edge", "From Lane", "To Lane", "Color"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName("JTablePhases");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/zoom.png"))); // NOI18N
        jButton1.setText("  Visualize");
        jButton1.setMaximumSize(new java.awt.Dimension(67, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(67, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(67, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/new.png"))); // NOI18N
        jButton4.setText("Clear");
        jButton4.setMaximumSize(new java.awt.Dimension(67, 30));
        jButton4.setMinimumSize(new java.awt.Dimension(67, 30));
        jButton4.setPreferredSize(new java.awt.Dimension(67, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTable1.repaint();
        
        mapEdgesLanes2.clear();
        
        jTable1.repaint();
        repaint = 1;
        
        for( int row = 0; row< jTable1.getRowCount(); row++  ){
            if(jTable1.getValueAt(row, 0).equals(true)){
                
                String From = ""+jTable1.getValueAt(row, 1).toString() +"_"+jTable1.getValueAt(row, 3);
                if(!mapEdgesLanes2.contains(Arrays.asList(jTable1.getValueAt(row, 1).toString(), From))){
                    mapEdgesLanes2.add(Arrays.asList(jTable1.getValueAt(row, 1).toString(), From));
                }
                
                String To = ""+jTable1.getValueAt(row, 2).toString() +"_"+jTable1.getValueAt(row, 4);
                
                if(!mapEdgesLanes2.contains(Arrays.asList(jTable1.getValueAt(row, 2).toString(), To))){
                    mapEdgesLanes2.add(Arrays.asList(jTable1.getValueAt(row, 2).toString(), To));
                }
                
            }else{
   
            }
        } 
        
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        for( int row = 0; row< jTable1.getRowCount(); row++  ){
            if(jTable1.getValueAt(row, 0).equals(true)){
              jTable1.setValueAt(false, row, 0);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
