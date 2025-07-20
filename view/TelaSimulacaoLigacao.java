/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import dao.TelefoneDAO;
import model.TipoTelefone;
import util.EstiloComponentes;
import util.Sessao;

public class TelaSimulacaoLigacao extends JFrame {

    public TelaSimulacaoLigacao() {
        setTitle("Simulação de Ligação");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Simular Recebimento de Ligação"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lblNumero = new JLabel("Número que está ligando:");
        JTextField txtNumero = new JTextField();
        txtNumero.setPreferredSize(new Dimension(300, 40));

        JButton btnSimular = new JButton("Simular Ligação");
        EstiloComponentes.estilizaBotao(btnSimular);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblNumero, gbc);
        gbc.gridx = 1; panel.add(txtNumero, gbc);

        gbc.gridx = 1; gbc.gridy = 1; panel.add(btnSimular, gbc);

        add(panel);

        btnSimular.addActionListener(e -> {
            try {
                String numero = txtNumero.getText();
                TelefoneDAO dao = new TelefoneDAO();
                TipoTelefone status = dao.consultar(numero);

                if (status == TipoTelefone.SUSPEITO) {
                    JOptionPane.showMessageDialog(this, "⚠️ Atenção: este número é suspeito! Cuidado ao atender.",
                            "Alerta de Golpe", JOptionPane.WARNING_MESSAGE);
                } else if (status == TipoTelefone.AUTORIZADO) {
                    JOptionPane.showMessageDialog(this, "✅ Sucesso: Número autorizado. Ligação segura.");
                } else {
                    JOptionPane.showMessageDialog(this, "⚠️ Atenção: Número desconhecido.");
                }

                dispose();
                voltarMenu();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Erro: " + ex.getMessage());
            }
        });

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

    private void voltarMenu() {
        if (Sessao.usuarioLogado.getPerfil().name().equals("ADMIN")) {
            new TelaPrincipal();
        } else {
            new TelaMenuUsuario();
        }
    }
}