/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import dao.UsuarioDAO;
import model.Usuario;
import util.EstiloComponentes;
import util.Sessao;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("Login");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Acesso ao Sistema"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        EstiloComponentes.estilizaCampoTexto(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setEchoChar('*');
        EstiloComponentes.estilizaCampoTexto(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCadastrar = new JButton("Cadastrar-se");

        EstiloComponentes.estilizaBotao(btnEntrar);
        EstiloComponentes.estilizaBotaoCadastro(btnCadastrar);

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

                if (email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "⚠️ Atenção: Preencha todos os campos!");
                    return;
                }

                UsuarioDAO dao = new UsuarioDAO();
                Usuario u = dao.autenticar(email, senha);

                if (u != null) {
                    Sessao.usuarioLogado = u;
                    JOptionPane.showMessageDialog(this, "✅ Sucesso: Login realizado!");
                    dispose();

                    if (u.getPerfil().name().equals("ADMIN")) {
                        new TelaPrincipal();
                    } else {
                        new TelaMenuUsuario();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Erro: Usuário ou senha inválidos!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Erro: " + ex.getMessage());
            }
        });

        btnCadastrar.addActionListener(e -> {
            dispose();
            new TelaCadastroUsuario();
        });

        setVisible(true);
    }
}
