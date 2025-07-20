/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import util.ConexaoMySQL;

public class LogDAO {

    public static void registrar(String email, String acao) {
        try {
            Connection con = ConexaoMySQL.getConexao();
            String sql = "INSERT INTO logs (usuario_email, acao) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email != null ? email : "Sistema");
            stmt.setString(2, acao);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.err.println("Erro ao registrar log: " + e.getMessage());
        }
    }
}


    
    

