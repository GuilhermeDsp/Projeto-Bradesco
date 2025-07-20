/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import util.EstiloComponentes;
import util.Sessao;

public class TelaContatosOficiais extends JFrame {

    public TelaContatosOficiais() {
        setTitle("Contatos Oficiais dos Bancos");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Contatos Oficiais"));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(area);
        panel.add(scroll, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar ao Menu");
        EstiloComponentes.estilizaBotao(btnVoltar);
        panel.add(btnVoltar, BorderLayout.SOUTH);

        add(panel);

        // Preenchendo contatos oficiais dos bancos
        StringBuilder sb = new StringBuilder();
        sb.append("🟩 Lista Oficial de Contatos dos Bancos\n\n");
        sb.append("Banco do Brasil: 4004-0001 / 0800-729-0001\n");
        sb.append("Caixa Econômica: 4004-0104 / 0800-104-0104\n");
        sb.append("Itaú: 4004-4828 / 0800-970-4828\n");
        sb.append("Bradesco: 4002-0022 / 0800-570-0022\n");
        sb.append("Santander: 4004-3535 / 0800-702-3535\n");
        sb.append("Nubank: 4020-0185 / 0800-591-2117\n");
        sb.append("Inter: 3003-4070 / 0800-940-0007\n");
        sb.append("C6 Bank: 3003-6116 / 0800-660-6116\n");
        sb.append("BTG Pactual: 4007-2511 / 0800-772-2825\n");
        sb.append("PagBank: 4003-1775 / 0800-728-2174\n");
        area.setText(sb.toString());

        btnVoltar.addActionListener(e -> {
            dispose();
            voltarMenu();
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                voltarMenu();
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
