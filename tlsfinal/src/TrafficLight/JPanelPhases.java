/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrafficLight;

import classes.Project;
import com.sun.rowset.internal.Row;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.CellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
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
    public static  String[] columnNames = {"","From Edge", "To Edge","From Lane","To Lane","Color"};  
    public static List<List<String>> mapEdgesLanes2 = new ArrayList<List<String>>();
            ///Map<String,String> mapEdgesLanes2 = new HashMap<String,String>();
    public static int repaint = 0;
    public Object[][] data;
    public int datarow=0;
    public int datacol=0;
    
    public static Map<String, List<String>> GeneralInfo = new HashMap<String, List<String>>();  
    public static Map<String, List<List<String>>> PhaseInfo = new HashMap<String, List<List<String>>>();
    public static Map<String, List<JTable>> PhaseTabel = new HashMap<String, List<JTable>>();
       
     //public Map<String, Map <String>> Phasesinf = new HashMap<String, Map <String>>;
    
    
    
    public void setlen(String Conectionname){
        Map<String, List<List<String>>> InformationsNET = Project.getCurrentlyLoadedProject().getFromTo();
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
                "", "From Edge", "To Edge", "From Lane", "To Lane", "Color"
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
                    if(table.getValueAt(i, 0) == true){
                        aux.put(i,isselect);
                        isselect ++;
                    }
                }

                
                
                        
                if(table.getValueAt(row, 0) == true && column == 5 ){
                    
                    
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
        
    }
    
     public JPanelPhases(String Conectionname) {
        setlen(Conectionname);
        initComponents();
        data = InsertValues(Conectionname);
        updateTable(data);
        
        
    }
     
     public JTable getJtabble(){
         return jTable1; 
     }
     
     public Object[][] getdata(){
         return data;
     }
     
     public void UpadteData(List<List<String>> aux , int Op, Object[][] data){
         
         Object[][] newdata =new Object[datarow][datacol];
         //Remove
         if (Op == 0){
            for(int i =0; i< aux.size(); i++){
             
                for(int j = 0; j< datarow;j++){
  
                    if((data[j][0].toString().equals(aux.get(i).get(0))) &&  // From Edge
                       (data[j][1].toString().equals(aux.get(i).get(1))) &&  // To Edge
                       (data[j][2].toString().equals(aux.get(i).get(2))) &&  // From Lane
                       (data[j][3].toString().equals(aux.get(i).get(3)))){   // To Lane

                    }
                            
                    
                }
                
                
            }
         }
         
         
         
     }
     
    public  Object[][] InsertValues(String Conectionname){
        Object[][] data = new Object[len][7];
        
        Map<String, List<List<String>>> InformationsNET = Project.getCurrentlyLoadedProject().getFromTo();
     
        for( String keys : InformationsNET.keySet() ){
            
            if(keys.equals(Conectionname)){
               List<List<String>> auxList = InformationsNET.get(keys);
               int i = 0;
               for( List<String>  values : auxList ){
                  
                   data[i][0] = false; // Visible
                   data[i][1] = values.get(0); // From Edge
                   data[i][2] = values.get(1); // To Edge
                   data[i][3] = values.get(2); // From Lane
                   data[i][4] = values.get(3); // To Lane
                   data[i][5] = "     ";
                   // 4 - reference
                   i++;
               }
              
            }
            
 
        }
        
        //jTable1.
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/save.png"))); // NOI18N
        jButton2.setText(" Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/exit.png"))); // NOI18N
        jButton3.setText(" Cancel");
        jButton3.setMaximumSize(new java.awt.Dimension(67, 30));
        jButton3.setMinimumSize(new java.awt.Dimension(67, 30));
        jButton3.setPreferredSize(new java.awt.Dimension(67, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        mapEdgesLanes2.clear();
        
        jTable1.repaint();
        repaint = 1;
        
        for( int row = 0; row< jTable1.getRowCount(); row++  ){
            if(jTable1.getValueAt(row, 0)== true){
                
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //PhaseTabel.put(JFrameInsert.Id_Junction, null);
        //Map<String, JTable> aux = new HashMap<String, JTable>();
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        for( int row = 0; row< jTable1.getRowCount(); row++  ){
            if(jTable1.getValueAt(row, 0)== true){
              jTable1.setValueAt(false, row, 0);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
