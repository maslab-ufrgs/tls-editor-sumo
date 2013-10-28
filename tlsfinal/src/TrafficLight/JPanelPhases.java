/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrafficLight;

import classes.Project;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;

/**
 *
 * @author luiz
 */
public class JPanelPhases extends javax.swing.JPanel {

    /**
     * Creates new form JPanelPhases
     */
    private int len;
    public static  String[] columnNames = {"","From Edge", "To Edge","From Lane","To Lane","Split"};
    public void setlen(String Conectionname){
        Map<String, List<List<String>>> InformationsNET = Project.getCurrentlyLoadedProject().getFromTo();
        for( String keys : InformationsNET.keySet() ){
            
            if(keys.equals(Conectionname)){
             this.len = InformationsNET.get(keys).size();
            }
        }
    }
    
    public JPanelPhases(String Conectionname) {
        
        setlen(Conectionname);
        initComponents();
        jTable1.setModel(
                new javax.swing.table.DefaultTableModel(
                InsertValues(Conectionname), columnNames){
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
                
        jTable1.setEnabled(true);
        jTable1.setVisible(true);
        

    }
    
    public  Object[][] InsertValues(String Conectionname){
        System.out.println("Tamanho: "+ len);
        Object[][] data = new Object[len][6];
        
        Map<String, List<List<String>>> InformationsNET = Project.getCurrentlyLoadedProject().getFromTo();
     
        for( String keys : InformationsNET.keySet() ){
            
            if(keys.equals(Conectionname)){
               List<List<String>> auxList = InformationsNET.get(keys);
               int i = 0;
               for( List<String>  values : auxList ){
                   
                   System.out.println("Valores: "+ values);
                   
                   data[i][0] = false; // Visible
                   data[i][1] = values.get(0); // From Edge
                   data[i][2] = values.get(1); // To Edge
                   data[i][3] = values.get(2); // From Lane
                   data[i][4] = values.get(3); // To Lane
                   data[i][5] = values.get(5); // Slice
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
                "", "From Edge", "To Edge", "From Lane", "To Lane", "Split"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
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

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/delete.png"))); // NOI18N
        jButton4.setText("Delete");
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}