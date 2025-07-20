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

public class TelaRelatorios extends JFrame {

    private JTextArea area;

    public TelaRelatorios() {
        setTitle("Relatórios");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Relatórios do Sistema"));

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(area);

        JPanel botoes = new JPanel(new FlowLayout());
        botoes.setBackground(new Color(240, 248, 255));

        JButton btnResumo = new JButton("Ver Resumo por Status");
        JButton btnDetalhado = new JButton("Listar Detalhado");
        JButton btnExportar = new JButton("Exportar para TXT");
        JButton btnExportarLogs = new JButton("Exportar Logs");

        EstiloComponentes.estilizaBotao(btnResumo);
        EstiloComponentes.estilizaBotao(btnDetalhado);
        EstiloComponentes.estilizaBotao(btnExportar);
        EstiloComponentes.estilizaBotao(btnExportarLogs);

        botoes.add(btnResumo);
        botoes.add(btnDetalhado);
        botoes.add(btnExportar);
        botoes.add(btnExportarLogs);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(botoes, BorderLayout.SOUTH);

        add(panel);

        btnResumo.addActionListener(e -> listarResumo());
        btnDetalhado.addActionListener(e -> listarDetalhado());
        btnExportar.addActionListener(e -> exportarParaTxt());
        btnExportarLogs.addActionListener(e -> exportarLogs());

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                voltarMenu();
            }
        });

        setVisible(true);
    }

    private void listarResumo() {
        try {
            area.setText("");
            Connection con = ConexaoMySQL.getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT status, COUNT(*) AS total FROM telefones GROUP BY status");

            area.append("RESUMO DE NÚMEROS POR STATUS:\n\n");
            while (rs.next()) {
                area.append("Status: " + rs.getString("status") +
                            " | Total: " + rs.getInt("total") + "\n");
            }

            con.close();
        } catch (Exception e) {
            area.setText("❌ Erro: " + e.getMessage());
        }
    }

    private void listarDetalhado() {
        try {
            area.setText("");
            Connection con = ConexaoMySQL.getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM telefones");

            area.append("LISTA DETALHADA DE NÚMEROS:\n\n");
            while (rs.next()) {
                area.append("ID: " + rs.getInt("id") +
                            ", Número: " + rs.getString("numero") +
                            ", Status: " + rs.getString("status") +
                            ", Motivo: " + (rs.getString("descricao") != null ? rs.getString("descricao") : "—") + "\n");
            }

            con.close();
        } catch (Exception e) {
            area.setText("❌ Erro: " + e.getMessage());
        }
    }

    private void exportarParaTxt() {
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
            JOptionPane.showMessageDialog(this, "❌ Erro ao exportar: " + e.getMessage());
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

    private void voltarMenu() {
        dispose();
        new TelaPrincipal();
    }
}