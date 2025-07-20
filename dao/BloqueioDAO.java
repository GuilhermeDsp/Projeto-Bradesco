/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import util.ConexaoMySQL;

public class BloqueioDAO {

    public void registrarBloqueio(String numero) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "INSERT INTO bloqueios (numero) VALUES (?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, numero);
        stmt.executeUpdate();
        con.close();
    }
}
