/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import dao.TelefoneDAO;
import model.Telefone;
import model.TipoTelefone;
import util.EstiloComponentes;
import util.Sessao;

public class TelaDenuncia extends JFrame {

    public TelaDenuncia() {
        setTitle("Denunciar Número");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Denunciar Número Suspeito"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblNumero = new JLabel("Número:");
        JTextField txtNumero = new JTextField();
        txtNumero.setPreferredSize(new Dimension(300, 40));

        JLabel lblMotivo = new JLabel("Motivo:");
        JTextField txtMotivo = new JTextField();
        txtMotivo.setPreferredSize(new Dimension(300, 40));

        JButton btnDenunciar = new JButton("Denunciar");
        EstiloComponentes.estilizaBotaoDenuncia(btnDenunciar);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblNumero, gbc);
        gbc.gridx = 1; panel.add(txtNumero, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblMotivo, gbc);
        gbc.gridx = 1; panel.add(txtMotivo, gbc);

        gbc.gridx = 1; gbc.gridy = 2; panel.add(btnDenunciar, gbc);

        add(panel);

        btnDenunciar.addActionListener(e -> {
            try {
                String numero = txtNumero.getText();
                String motivo = txtMotivo.getText();

                if (numero.isEmpty() || motivo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "⚠️ Atenção: Preencha o número e o motivo!");
                    return;
                }

                if (!numero.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "❌ Erro: Apenas números são permitidos!");
                    return;
                }

                TelefoneDAO dao = new TelefoneDAO();
                Telefone t = new Telefone(numero, TipoTelefone.SUSPEITO, motivo);
                dao.inserirOuAtualizar(t);

                JOptionPane.showMessageDialog(this, "✅ Sucesso: Número denunciado como SUSPEITO!");

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