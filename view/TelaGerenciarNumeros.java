/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import dao.TelefoneDAO;
import model.Telefone;
import model.TipoTelefone;
import util.EstiloComponentes;
import util.Sessao;

public class TelaGerenciarNumeros extends JFrame {

    private JTextArea area;
    private JTextField txtId;

    public TelaGerenciarNumeros() {
        setTitle("Gerenciar Números");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Números Cadastrados"));

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(area);

        JPanel panelBotoes = new JPanel(new GridBagLayout());
        panelBotoes.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(300, 40));

        JButton btnAutorizar = new JButton("Autorizar");
        JButton btnAtualizar = new JButton("Atualizar Status");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnListar = new JButton("Listar Números");

        EstiloComponentes.estilizaBotao(btnAutorizar);
        EstiloComponentes.estilizaBotao(btnAtualizar);
        EstiloComponentes.estilizaBotaoDenuncia(btnExcluir);
        EstiloComponentes.estilizaBotao(btnListar);

        gbc.gridx = 0; gbc.gridy = 0; panelBotoes.add(new JLabel("ID do número:"), gbc);
        gbc.gridx = 1; panelBotoes.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelBotoes.add(btnAutorizar, gbc);
        gbc.gridx = 1; panelBotoes.add(btnAtualizar, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panelBotoes.add(btnExcluir, gbc);
        gbc.gridx = 1; panelBotoes.add(btnListar, gbc);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelBotoes, BorderLayout.SOUTH);
        add(panel);

        btnListar.addActionListener(e -> listarNumeros());
        btnAutorizar.addActionListener(e -> autorizarNumero());
        btnAtualizar.addActionListener(e -> atualizarStatus());
        btnExcluir.addActionListener(e -> excluirNumero());

        listarNumeros();

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

    private void listarNumeros() {
        try {
            area.setText("");
            TelefoneDAO dao = new TelefoneDAO();
            ArrayList<Telefone> lista = dao.listar();

            for (Telefone t : lista) {
                area.append("ID: " + t.getId() +
                        ", Número: " + t.getNumero() +
                        ", Status: " + t.getStatus() +
                        ", Motivo: " + (t.getDescricao() != null ? t.getDescricao() : "—") + "\n");
            }
        } catch (Exception e) {
            area.setText("❌ Erro: " + e.getMessage());
        }
    }

    private void autorizarNumero() {
        try {
            int id = Integer.parseInt(txtId.getText());
            TelefoneDAO dao = new TelefoneDAO();
            dao.autorizarNumero(id);
            JOptionPane.showMessageDialog(this, "✅ Número autorizado!");
            listarNumeros();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erro: " + e.getMessage());
        }
    }

    private void atualizarStatus() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String[] opcoes = {"AUTORIZADO", "SUSPEITO", "DESCONHECIDO"};
            String novoStatus = (String) JOptionPane.showInputDialog(this,
                    "Selecione o novo status:", "Atualizar Status",
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            if (novoStatus == null) return;

            TelefoneDAO dao = new TelefoneDAO();
            dao.atualizarStatus(id, TipoTelefone.valueOf(novoStatus));
            JOptionPane.showMessageDialog(this, "✅ Status atualizado!");
            listarNumeros();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Erro: " + e.getMessage());
        }
    }

    private void excluirNumero() {
        try {
            int id = Integer.parseInt(txtId.getText());
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir o número ID: " + id + "?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                TelefoneDAO dao = new TelefoneDAO();
                dao.excluirNumero(id);
                JOptionPane.showMessageDialog(this, "✅ Número excluído!");
                listarNumeros();
            }
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