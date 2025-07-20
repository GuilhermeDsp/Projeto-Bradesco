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
import util.Sessao;

public class TelaGerenciarBloqueios extends JFrame {

    private JTextArea area;
    private JTextField txtNumero;

    public TelaGerenciarBloqueios() {
        setTitle("Gerenciar Bloqueios");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Bloqueios Registrados"));

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(area);

        JPanel panelBotoes = new JPanel(new GridBagLayout());
        panelBotoes.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblNumero = new JLabel("Número:");
        txtNumero = new JTextField();
        txtNumero.setPreferredSize(new Dimension(300, 40));
        txtNumero.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtNumero.setBackground(Color.WHITE);

        JButton btnListar = new JButton("Listar Bloqueios");
        JButton btnBloquear = new JButton("Bloquear Número");
        JButton btnVoltar = new JButton("Voltar");

        EstiloComponentes.estilizaBotao(btnListar);
        EstiloComponentes.estilizaBotaoDenuncia(btnBloquear);
        EstiloComponentes.estilizaBotao(btnVoltar);

        gbc.gridx = 0; gbc.gridy = 0; panelBotoes.add(lblNumero, gbc);
        gbc.gridx = 1; panelBotoes.add(txtNumero, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelBotoes.add(btnBloquear, gbc);
        gbc.gridx = 1; panelBotoes.add(btnListar, gbc);

        gbc.gridx = 1; gbc.gridy = 2; panelBotoes.add(btnVoltar, gbc);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelBotoes, BorderLayout.SOUTH);

        add(panel);

        btnListar.addActionListener(e -> listarBloqueios());
        btnBloquear.addActionListener(e -> bloquearNumero());
        btnVoltar.addActionListener(e -> {
            dispose();
            voltarMenu();
        });

        listarBloqueios();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(
                    null, "Deseja voltar ao menu?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    dispose();
                    voltarMenu();
                }
            }
        });

        setVisible(true);
    }

    private void listarBloqueios() {
        try {
            area.setText("");
            Connection con = ConexaoMySQL.getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bloqueios");

            while (rs.next()) {
                area.append("ID: " + rs.getInt("id") +
                        ", Número: " + rs.getString("numero") +
                        ", Motivo: " + rs.getString("motivo") +
                        ", Data: " + rs.getTimestamp("data_bloqueio") + "\n");
            }

            con.close();
        } catch (Exception e) {
            area.setText("❌ Erro: " + e.getMessage());
        }
    }

    private void bloquearNumero() {
        try {
            String numero = txtNumero.getText();
            if (numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Atenção: Informe o número para bloquear!");
                return;
            }

            String motivo = JOptionPane.showInputDialog("Digite o motivo do bloqueio:");

            Connection con = ConexaoMySQL.getConexao();
            String sql = "INSERT INTO bloqueios (numero, motivo) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, numero);
            stmt.setString(2, motivo);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "✅ Número bloqueado!");
            listarBloqueios();

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erro: " + e.getMessage());
        }
    }

    private void voltarMenu() {
        if (Sessao.usuarioLogado.getPerfil().name().equals("ADMIN")) {
            new TelaPrincipal();
        } else {
            new TelaMenuUsuario();
        }
    }
}