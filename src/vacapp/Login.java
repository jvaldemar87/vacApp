/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacapp;

import Clase.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jvald
 */
public class Login extends javax.swing.JFrame {
    int counterFalses=0;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/user.jpg"))); // NOI18N

        jLabel4.setText("User:");

        jLabel5.setText("Password:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegister))
                    .addComponent(txtPassword)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnRegister))
                .addGap(76, 76, 76))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        try {
            if (userExist(txtUsuario.getText()) == "false") {
                counterFalses =0;
                JOptionPane.showMessageDialog(null, "Usuario incorrecto");
            } else {
                if(consultingUserBlock(txtUsuario.getText()) == true){
                    JOptionPane.showMessageDialog(null, "Usuario Bloqueado");
                }
                else{
                    if (passCorrect(txtPassword.getText()) == "false") {
                    counterFalses++;
                    JOptionPane.showMessageDialog(
                            null, "Contraseña incorrecta \n intento numero "+ counterFalses
                            +"\n despues del 3º intento el usuario se bloqueara");
                    if(counterFalses>3){
                        bockUser(txtUsuario.getText());
                        JOptionPane.showMessageDialog(
                                null, "favor de contactar con un administrador para que se desbloquee al usuario:\n"
                                +txtUsuario.getText());
                    } 
                } else {
                        if(userType(txtUsuario.getText()) == "1"){
                            SupervisorView supervisorView = new SupervisorView();
                            supervisorView.show();
                            dispose();
                        }
                        else if(userType(txtUsuario.getText()) == "2"){
                            EmployeeView employeeView = new EmployeeView();
                            employeeView.show();
                            dispose();
                        }
                        
                    
                }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        Register register = new Register();
        register.show();
        dispose();
    }//GEN-LAST:event_btnRegisterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Connection conection = conectorSQL.getInstance().getConnection();

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    public static String userExist(String user) throws SQLException {
        Connection conection = conectorSQL.getInstance().getConnection();
        ResultSet rs = null;
        Statement st = null;
        String sql = "SELECT * FROM usuarios where email='" + user + "';";
        st = conection.createStatement();
        rs = st.executeQuery(sql);

        if (rs.first()) {
            rs.close();
            return user;
        } else {
            return "false";
        }
    }

    private String passCorrect(String pass) throws SQLException {
        Connection conection = conectorSQL.getInstance().getConnection();
        ResultSet rs = null;
        Statement st = null;
        String sql = "SELECT * FROM usuarios where password='" + pass + "';";
        st = conection.createStatement();
        rs = st.executeQuery(sql);

        if (rs.first()) {
            rs.close();
            return pass;
        } else {
            return "false";
        }
    }

    private void bockUser(String user) throws SQLException {
        Connection conection = conectorSQL.getInstance().getConnection();
        ResultSet rs = null;
        Statement st = null;
        String sql = "UPDATE `usuarios` SET `Habilitado` = '0' WHERE `usuarios`.`email` = '"
                + user + "';";
        st = conection.createStatement();
        st.executeUpdate(sql);
    }

    private boolean consultingUserBlock(String user) throws SQLException {
        Connection conection = conectorSQL.getInstance().getConnection();
        ResultSet rs = null;
        Statement st = null;
        String sql = "SELECT * FROM usuarios where email='" + user + "'and Habilitado=0;";
        st = conection.createStatement();
        rs = st.executeQuery(sql);
        if (rs.first()) {
            rs.close();
            return true;
        } else {
            return false;
        }
    }

    private String userType(String user) throws SQLException {
        String type="null";
        Connection conection = conectorSQL.getInstance().getConnection();
        ResultSet rs = null;
        Statement st = null;
        String sql = "SELECT tipo from usuarios where email ='"
                + user+"';";
        st = conection.createStatement();
        rs = st.executeQuery(sql);
        if(rs.first()){
            JOptionPane.showMessageDialog(null, "tipo: "+sql);
            return sql;
        }
        else
            return "falso";
    }
}
