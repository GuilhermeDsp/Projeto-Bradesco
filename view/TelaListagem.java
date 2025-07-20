/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import dao.TelefoneDAO;
import model.Telefone;
import java.util.ArrayList;
import util.EstiloComponentes;

public class TelaListagem extends JFrame {

    public TelaListagem() {
        setTitle("Listagem de Números");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Números Cadastrados"));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(area);
        panel.add(scroll, BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        EstiloComponentes.estilizaBotao(btnFechar);
        panel.add(btnFechar, BorderLayout.SOUTH);

        add(panel);

        try {
            TelefoneDAO dao = new TelefoneDAO();
            ArrayList<Telefone> lista = dao.listar();

            for (Telefone t : lista) {
                area.append("ID: " + t.getId() + ", Número: " + t.getNumero() + ", Status: " + t.getStatus() + "\n");
            }
        } catch (Exception e) {
            area.setText("❌ Erro: " + e.getMessage());
        }

        btnFechar.addActionListener(e -> {
            dispose();
            new TelaMenuUsuario();
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(
                    null, "Deseja voltar ao menu?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    dispose();
                    new TelaMenuUsuario();
                }
            }
        });

        setVisible(true);
    }
}