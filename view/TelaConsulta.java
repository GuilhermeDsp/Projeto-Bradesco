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

public class TelaConsulta extends JFrame {

    public TelaConsulta() {
        setTitle("Consultar Número");
        setSize(480, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Consulta de Número"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lblNumero = new JLabel("Número:");
        JTextField txtNumero = new JTextField();
        txtNumero.setPreferredSize(new Dimension(300, 40));

        JButton btnConsultar = new JButton("Consultar");
        EstiloComponentes.estilizaBotao(btnConsultar);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblNumero, gbc);
        gbc.gridx = 1; panel.add(txtNumero, gbc);

        gbc.gridx = 1; gbc.gridy = 1; panel.add(btnConsultar, gbc);

        add(panel);

        btnConsultar.addActionListener(e -> {
            try {
                String numero = txtNumero.getText();
                TelefoneDAO dao = new TelefoneDAO();
                TipoTelefone status = dao.consultar(numero);

                String msg;
                switch (status) {
                    case AUTORIZADO:
                        msg = "✅ Este número está AUTORIZADO.";
                        break;
                    case SUSPEITO:
                        msg = "⚠️ Atenção: Este número está registrado como SUSPEITO.";
                        break;
                    default:
                        msg = "❌ Este número é DESCONHECIDO.";
                        break;
                }

                JOptionPane.showMessageDialog(this, msg);
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
