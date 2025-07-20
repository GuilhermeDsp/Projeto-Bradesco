/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import util.ConexaoMySQL;
import util.EstiloComponentes;

public class TelaListagemUsuarios extends JFrame {

    public TelaListagemUsuarios() {
        setTitle("Usuários Cadastrados");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Usuários"));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(area);

        JButton btnFechar = new JButton("Voltar ao Menu");
        EstiloComponentes.estilizaBotao(btnFechar);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnFechar, BorderLayout.SOUTH);

        add(panel);

        try {
            Connection con = ConexaoMySQL.getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            StringBuilder sb = new StringBuilder();
            sb.append("LISTA DE USUÁRIOS:\n\n");

            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(", Nome: ").append(rs.getString("nome"))
                        .append(", Telefone: ").append(rs.getString("telefone"))
                        .append(", Email: ").append(rs.getString("email"))
                        .append(", Perfil: ").append(rs.getString("perfil"))
                        .append("\n");
            }

            area.setText(sb.toString());
            con.close();
        } catch (Exception e) {
            area.setText("❌ Erro: " + e.getMessage());
        }

        btnFechar.addActionListener(e -> {
            dispose();
            new TelaPrincipal();
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
                new TelaPrincipal();
            }
        });

        setVisible(true);
    }
}
