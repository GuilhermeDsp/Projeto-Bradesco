/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/validacao_ligacoes";
    private static final String USUARIO = "root";
    private static final String SENHA = "141828@!Gd";

    public static Connection getConexao() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
