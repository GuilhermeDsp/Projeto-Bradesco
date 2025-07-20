/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BloqueadorDeTelefone {

    public static void bloquearNumero(String numero) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "INSERT INTO bloqueios (numero, motivo) VALUES (?, 'Bloqueio automático por suspeita')";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, numero);
        stmt.executeUpdate();
        con.close();
    }
}
