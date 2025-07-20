/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import dao.UsuarioDAO;
import model.Perfil;
import model.Usuario;
import util.EstiloComponentes;

public class TelaCadastroUsuario extends JFrame {

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Cadastrar Novo Usuário"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        EstiloComponentes.estilizaCampoTexto(txtNome);

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();
        EstiloComponentes.estilizaCampoTexto(txtTelefone);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        EstiloComponentes.estilizaCampoTexto(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setEchoChar('*');
        EstiloComponentes.estilizaCampoTexto(txtSenha);

        JButton btnCadastrar = new JButton("Cadastrar");
        EstiloComponentes.estilizaBotaoCadastro(btnCadastrar);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblNome, gbc);
        gbc.gridx = 1; panel.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblTelefone, gbc);
        gbc.gridx = 1; panel.add(txtTelefone, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(lblEmail, gbc);
        gbc.gridx = 1; panel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(lblSenha, gbc);
        gbc.gridx = 1; panel.add(txtSenha, gbc);

        gbc.gridx = 1; gbc.gridy = 4; panel.add(btnCadastrar, gbc);

        add(panel);

        btnCadastrar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String telefone = txtTelefone.getText();
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());

                if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "⚠️ Atenção: Preencha todos os campos!");
                    return;
                }

                Usuario u = new Usuario(nome, telefone, email, senha, Perfil.USUARIO);
                UsuarioDAO dao = new UsuarioDAO();
                dao.inserir(u);

                JOptionPane.showMessageDialog(this, "✅ Sucesso: Cadastro realizado com sucesso!");

                dispose();
                new TelaLogin();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Erro: " + ex.getMessage());
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(
                        null, "Deseja voltar à tela de login?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    dispose();
                    new TelaLogin();
                }
            }
        });

        setVisible(true);
    }
}