/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import dao.UsuarioDAO;
import dao.LogDAO;
import model.Usuario;

public class TelaLoginUsuario extends JFrame {

    public TelaLoginUsuario() {
        setTitle("Login de Usuário");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (confirmarSaida()) System.exit(0);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Acesso ao Sistema"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCadastrar = new JButton("Cadastrar-se");

        estilizaBotao(btnEntrar);
        estilizaBotao(btnCadastrar);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblEmail, gbc);
        gbc.gridx = 1; panel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblSenha, gbc);
        gbc.gridx = 1; panel.add(txtSenha, gbc);

        gbc.gridx = 1; gbc.gridy = 2; panel.add(btnEntrar, gbc);
        gbc.gridy = 3; panel.add(btnCadastrar, gbc);

        add(panel);

        btnEntrar.addActionListener(e -> {
            try {
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());

                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuario = dao.autenticar(email, senha);

                if (usuario != null) {
                    new LogDAO().registrar(usuario.getEmail(), "Login realizado com sucesso");
                    JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

                    if (usuario.getPerfil().equals("ADMIN")) {
                        new TelaPrincipal();
                    } else {
                        new TelaMenuUsuario();
                    }
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        btnCadastrar.addActionListener(e -> {
            new TelaCadastroUsuario();
            this.dispose();
        });

        setVisible(true);
    }

    private boolean confirmarSaida() {
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    private void estilizaBotao(JButton btn) {
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
    }
}