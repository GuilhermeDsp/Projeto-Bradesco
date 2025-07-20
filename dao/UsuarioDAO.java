/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Perfil;
import model.Usuario;
import util.ConexaoMySQL;
import dao.LogDAO;

public class UsuarioDAO {

    public void inserir(Usuario u) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "INSERT INTO usuarios (nome, telefone, email, senha, perfil) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, u.getNome());
        stmt.setString(2, u.getTelefone());
        stmt.setString(3, u.getEmail());
        stmt.setString(4, u.getSenha());
        stmt.setString(5, u.getPerfil().name());
        stmt.executeUpdate();
        con.close();

        LogDAO.registrar(u.getEmail(), "Cadastro de novo usuário");
    }

    public Usuario autenticar(String email, String senha) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario(
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("senha"),
                Perfil.valueOf(rs.getString("perfil"))
            );
            u.setId(rs.getInt("id"));

            LogDAO.registrar(email, "Login realizado com sucesso");
            con.close();
            return u;
        }

        con.close();
        return null;
    }

    public ArrayList<Usuario> listar() throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "SELECT * FROM usuarios";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Usuario> lista = new ArrayList<>();
        while (rs.next()) {
            Usuario u = new Usuario(
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("senha"),
                Perfil.valueOf(rs.getString("perfil"))
            );
            u.setId(rs.getInt("id"));
            lista.add(u);
        }

        con.close();
        return lista;
    }
}