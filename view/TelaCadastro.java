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

public class TelaCadastro extends JFrame {

    public TelaCadastro() {
        setTitle("Cadastro de Número");
        setSize(480, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Cadastrar Número"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNumero = new JLabel("Número:");
        JTextField txtNumero = new JTextField();
        txtNumero.setPreferredSize(new Dimension(300, 40));

        JLabel lblStatus = new JLabel("Status:");
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"AUTORIZADO", "SUSPEITO"});
        cmbStatus.setPreferredSize(new Dimension(300, 40));

        JButton btnSalvar = new JButton("Salvar");
        EstiloComponentes.estilizaBotaoCadastro(btnSalvar);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblNumero, gbc);
        gbc.gridx = 1; panel.add(txtNumero, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblStatus, gbc);
        gbc.gridx = 1; panel.add(cmbStatus, gbc);

        gbc.gridx = 1; gbc.gridy = 2; panel.add(btnSalvar, gbc);

        add(panel);

        btnSalvar.addActionListener(e -> {
            try {
                String numero = txtNumero.getText();

                if (!numero.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "❌ Erro: Apenas números são permitidos!");
                    return;
                }

                TelefoneDAO dao = new TelefoneDAO();

                if (dao.consultar(numero) != TipoTelefone.DESCONHECIDO) {
                    JOptionPane.showMessageDialog(this, "⚠️ Atenção: Número já cadastrado!");
                    return;
                }

                Telefone t = new Telefone(numero, TipoTelefone.valueOf(cmbStatus.getSelectedItem().toString()));
                dao.inserirOuAtualizar(t);

                JOptionPane.showMessageDialog(this, "✅ Sucesso: Número cadastrado com sucesso!");

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