/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Telefone;
import model.TipoTelefone;
import util.ConexaoMySQL;
import dao.LogDAO;

public class TelefoneDAO {

    public void inserirOuAtualizar(Telefone t) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "INSERT INTO telefones (numero, status, descricao) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE status = VALUES(status), descricao = VALUES(descricao)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, t.getNumero());
        stmt.setString(2, t.getStatus().name());
        stmt.setString(3, t.getDescricao());
        stmt.executeUpdate();
        con.close();

        LogDAO.registrar(null, "Número inserido ou atualizado: " + t.getNumero());
    }

    public TipoTelefone consultar(String numero) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "SELECT status FROM telefones WHERE numero = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, numero);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            TipoTelefone status = TipoTelefone.valueOf(rs.getString("status"));
            con.close();
            return status;
        }

        con.close();
        return TipoTelefone.DESCONHECIDO;
    }

    public ArrayList<Telefone> listar() throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "SELECT * FROM telefones";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Telefone> lista = new ArrayList<>();
        while (rs.next()) {
            Telefone t = new Telefone(
                rs.getString("numero"),
                TipoTelefone.valueOf(rs.getString("status")),
                rs.getString("descricao")
            );
            t.setId(rs.getInt("id"));
            lista.add(t);
        }

        con.close();
        return lista;
    }

    public void excluirNumero(int id) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "DELETE FROM telefones WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        con.close();

        LogDAO.registrar(null, "Número excluído (ID): " + id);
    }

    public void autorizarNumero(int id) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "UPDATE telefones SET status = 'AUTORIZADO', descricao = NULL WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        con.close();

        LogDAO.registrar(null, "Número autorizado (ID): " + id);
    }

    public void atualizarStatus(int id, TipoTelefone novoStatus) throws Exception {
        Connection con = ConexaoMySQL.getConexao();
        String sql = "UPDATE telefones SET status = ? WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, novoStatus.name());
        stmt.setInt(2, id);
        stmt.executeUpdate();
        con.close();

        LogDAO.registrar(null, "Status do número (ID: " + id + ") alterado para " + novoStatus.name());
    }
}