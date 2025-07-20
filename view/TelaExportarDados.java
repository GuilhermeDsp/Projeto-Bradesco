/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.sql.*;
import util.ConexaoMySQL;
import util.EstiloComponentes;
import util.Sessao;

public class TelaExportarDados extends JFrame {

    public TelaExportarDados() {
        setTitle("Exportar Dados do Sistema");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JButton btnExportarNumeros = new JButton("Exportar Relatório de Números");
        JButton btnExportarLogs = new JButton("Exportar Logs de Auditoria");
        JButton btnVoltar = new JButton("Voltar ao Menu");

        EstiloComponentes.estilizaBotao(btnExportarNumeros);
        EstiloComponentes.estilizaBotao(btnExportarLogs);
        EstiloComponentes.estilizaBotao(btnVoltar);

        btnExportarNumeros.addActionListener(e -> exportarRelatorio());
        btnExportarLogs.addActionListener(e -> exportarLogs());

        btnVoltar.addActionListener(e -> {
            dispose();
            if (Sessao.usuarioLogado.getPerfil().name().equals("ADMIN")) {
                new TelaPrincipal();
            } else {
                new TelaMenuUsuario();
            }
        });

        panel.add(btnExportarNumeros);
        panel.add(btnExportarLogs);
        panel.add(btnVoltar);

        add(panel);
        setVisible(true);
    }

    private void exportarRelatorio() {
        try {
            Connection con = ConexaoMySQL.getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM telefones");

            StringBuilder sb = new StringBuilder();
            sb.append("RELATÓRIO DE NÚMEROS\n\n");

            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(", Número: ").append(rs.getString("numero"))
                        .append(", Status: ").append(rs.getString("status"))
                        .append(", Motivo: ").append(rs.getString("descricao") != null ? rs.getString("descricao") : "—")
                        .append("\n");
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new java.io.File("relatorio_telefones.txt"));
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
                    fw.write(sb.toString());
                    JOptionPane.showMessageDialog(this, "✅ Relatório exportado com sucesso!");
                }
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erro: " + e.getMessage());
        }
    }

    private void exportarLogs() {
        try {
            Connection con = ConexaoMySQL.getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM logs");

            StringBuilder sb = new StringBuilder();
            sb.append("RELATÓRIO DE LOGS DO SISTEMA\n\n");

            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(", Email: ").append(rs.getString("usuario_email"))
                        .append(", Ação: ").append(rs.getString("acao"))
                        .append(", Data: ").append(rs.getTimestamp("data_log"))
                        .append("\n");
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new java.io.File("logs_sistema.txt"));
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
                    fw.write(sb.toString());
                    JOptionPane.showMessageDialog(this, "✅ Logs exportados com sucesso!");
                }
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erro ao exportar logs: " + e.getMessage());
        }
    }
}