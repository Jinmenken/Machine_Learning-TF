/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import clasificadores.CustomOutputStream;
import KNN.KNN;
import NaiveBayes.NaiveBayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;

/**
 *
 * @author Valky
 */
public class ClasificadoresFrame extends javax.swing.JFrame {

    /**
     * Creates new form ClasificadoresFrame
     */
    public ClasificadoresFrame() {
        initComponents();
        setTitle("Clasificadores");
        
        PrintStream printStream = new PrintStream(new CustomOutputStream(jTxtOutput));
        PrintStream standardOut = System.out;
        System.setOut(printStream);
        System.setErr(printStream);
        
        fc = new JFileChooser();
        
        atributos = new ArrayList<String>();
        valores = new ArrayList<ArrayList<String>>();
        data = new ArrayList<ArrayList<String>>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnAbrirArch = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTxtOutput = new javax.swing.JTextArea();
        jBtnRun = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSpnK = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jCbxDistancia = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jSpnPercent = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTxtDataset = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLstAtributos = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLstValores = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBtnAbrirArch.setText("Abrir archivo");
        jBtnAbrirArch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAbrirArchActionPerformed(evt);
            }
        });

        jTxtOutput.setEditable(false);
        jTxtOutput.setColumns(20);
        jTxtOutput.setRows(5);
        jScrollPane3.setViewportView(jTxtOutput);

        jBtnRun.setText("Run");
        jBtnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRunActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("KNN"));

        jLabel6.setText("K:");

        jSpnK.setModel(new javax.swing.SpinnerNumberModel(1, 1, 20, 1));

        jLabel7.setText("Distancia:");

        jCbxDistancia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Euclidiana", "Hamming" }));
        jCbxDistancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbxDistanciaActionPerformed(evt);
            }
        });

        jLabel8.setText("Porcentaje:");

        jSpnPercent.setModel(new javax.swing.SpinnerNumberModel(75, 10, 95, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpnK, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCbxDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpnPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jSpnK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel7)
                .addComponent(jCbxDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8)
                .addComponent(jSpnPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dataset"));

        jLabel3.setText("Nombre:");

        jTxtDataset.setEditable(false);

        jLabel1.setText("Atributos:");

        jScrollPane1.setViewportView(jLstAtributos);

        jLabel2.setText("Valores:");

        jScrollPane2.setViewportView(jLstValores);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtDataset))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTxtDataset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnAbrirArch)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jBtnAbrirArch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAbrirArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAbrirArchActionPerformed
        int returnVal = fc.showOpenDialog(ClasificadoresFrame.this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            abrirArchivo(file.getPath());
            
            jTxtDataset.setText(nomDataset);
            
            DefaultListModel modelA = new DefaultListModel();
            for(int i = 0; i < atributos.size(); i++)
               modelA.addElement(atributos.get(i));
            jLstAtributos.setModel(modelA);
            
            DefaultListModel modelB = new DefaultListModel();
            for(int i = 0; i < valores.size(); i++){
                for(int j = 0; j < valores.get(i).size(); j++)
                    modelB.addElement(valores.get(i).get(j) + " (" + atributos.get(i) + ")");
            }
            jLstValores.setModel(modelB);
        }
    }//GEN-LAST:event_jBtnAbrirArchActionPerformed

    private void jCbxDistanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbxDistanciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbxDistanciaActionPerformed

    private void jBtnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRunActionPerformed
        jTxtOutput.setText(null);
        
        int k = (Integer)jSpnK.getValue();
        int percent = (Integer)jSpnPercent.getValue();
        String distancia = (String)jCbxDistancia.getSelectedItem();
        
        KNN knn = new KNN(atributos, valores, data, k, percent, distancia);
        int[][] confMatrix = knn.test();
        reporte(confMatrix, percent, "KNN");
        
        NaiveBayes nb = new NaiveBayes(atributos, valores, data, percent);
        confMatrix = nb.test();
        reporte(confMatrix, percent, "Naive Bayes");
    }//GEN-LAST:event_jBtnRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClasificadoresFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClasificadoresFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClasificadoresFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClasificadoresFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClasificadoresFrame().setVisible(true);
            }
        });
    }

    JFileChooser fc;
    
    String nomDataset;
    ArrayList<String> atributos;
    ArrayList<ArrayList<String>> valores;
    ArrayList<ArrayList<String>> data;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAbrirArch;
    private javax.swing.JButton jBtnRun;
    private javax.swing.JComboBox jCbxDistancia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jLstAtributos;
    private javax.swing.JList jLstValores;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpnK;
    private javax.swing.JSpinner jSpnPercent;
    private javax.swing.JTextField jTxtDataset;
    private javax.swing.JTextArea jTxtOutput;
    // End of variables declaration//GEN-END:variables

public void abrirArchivo(String path){
        
        try {

            FileReader fileReader = new FileReader(path);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;

            int estado = 1;

            while((line = bufferedReader.readLine()) != null) {

                line = line.trim();
                String lineAux = line.toLowerCase();

                if(estado==1 && lineAux.contains("@relation")){
                    String[] campos = line.split(" ");
                    nomDataset = campos[1];
                    estado = 2;
                } else
                    if(estado==2 && lineAux.contains("@attribute")){

                        String cad = line;

                        String[] campAtr = cad.split(" ", 3);

                        campAtr[1] = campAtr[1].replaceAll("'", "");
                        atributos.add(campAtr[1]);

                        campAtr[2] = campAtr[2].replaceAll(" ", "").replaceAll("\\{", "").replaceAll("}", "").replaceAll("'", "");
                        String[] campos2 = campAtr[2].split(",");

                        ArrayList<String> lista = new ArrayList<String>();
                        for(int i=0; i<campos2.length; i++){
                            lista.add(campos2[i]);
                        }

                        valores.add(lista);
                    } else
                        if(lineAux.contains("@data")){
                            estado = 3;
                        } else
                            if(estado == 3 && !lineAux.contains("%")){
                                String cad = line;
                                cad = cad.replaceAll("'", "");
                                String[] campos3 = cad.split(",");
                                ArrayList<String> values = new ArrayList<String>();
                                for(int j=0; j<campos3.length; j++)
                                    values.add(campos3[j]);
                                data.add(values);
                            }
            }

            bufferedReader.close();
       }
       catch(FileNotFoundException ex) {
            //System.out.println("Unable to open file '" + path + "'");
       }
       catch(IOException ex) {
            //System.out.println("Error reading file '" + path + "'");
       }
    }

public void reporte(int[][] confMatrix, int percent, String clasificador){
    int matrixSize = valores.get(valores.size()-1).size();
    int tot_train = data.size()*percent/100;
    
    int vp = confMatrix[0][0];
    int fn = confMatrix[0][1];
    int fp = confMatrix[1][0];
    int vn = confMatrix[1][1];


    //System.out.println(vp);
    //System.out.println(fn);
    //System.out.println(fp);
    //System.out.println(vn);

    //precision, exhaustividad y f-measure
    double precision = (double)vp / (vp + fp);
    double exhaustividad = (double)vp / (vp + fn);
    double f1 = (precision*exhaustividad / (precision + exhaustividad))*2;

    System.out.println(clasificador);
    System.out.println("----------------------------------------");
    System.out.println();
    
    System.out.print("Numero de instancias clasificadas: ");
    System.out.println(data.size()-tot_train);
    System.out.print("precision: ");
    System.out.println(precision);
    System.out.print("exhaustividad: ");
    System.out.println(exhaustividad);
    System.out.print("f1: ");
    System.out.println(f1);

    System.out.println();

    //Matriz de confusion

    System.out.print("  ");

    for(int i=0; i<matrixSize; i++){
        System.out.print(valores.get(valores.size()-1).get(i));
        System.out.print(" ");
    }

    System.out.println();

    for(int i=0; i<matrixSize; i++){
        System.out.print(valores.get(valores.size()-1).get(i));
        System.out.print(" ");
        for(int j=0; j<matrixSize; j++){
            System.out.print(confMatrix[i][j]);
            System.out.print(" ");
        }
        System.out.println();
    }
    
    System.out.println();
    System.out.println();
    System.out.println();
}

}
