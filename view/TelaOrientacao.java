/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import util.EstiloComponentes;
import util.Sessao;

public class TelaOrientacao extends JFrame {

    public TelaOrientacao() {
        setTitle("Orientação para o Usuário");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 240));
        panel.setBorder(BorderFactory.createTitledBorder("⚠️ Alerta de Segurança"));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Serif", Font.PLAIN, 15));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(area);

        JButton btnVoltar = new JButton("Voltar ao Menu");
        EstiloComponentes.estilizaBotao(btnVoltar);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnVoltar, BorderLayout.SOUTH);
        add(panel);

        area.setText("""
        🔒 ORIENTAÇÃO IMPORTANTE AOS USUÁRIOS

        Golpistas têm se passado por bancos ou instituições financeiras para aplicar fraudes por telefone.

        🚫 CUIDADO com ligações de supostas centrais de atendimento solicitando:
        - Senhas
        - Tokens
        - Número de cartão
        - Confirmação de transferências
        - Cancelamentos com intermediação de operador

        ✅ O que fazer:
        - Desligue a chamada imediatamente
        - Consulte este aplicativo para verificar se o número é suspeito
        - Entre em contato com o banco usando os números oficiais

        ⚠️ Nunca forneça dados pessoais ou bancários por telefone, mesmo que a pessoa mencione seu nome ou CPF.

        🛡️ Em caso de dúvida, consulte os contatos oficiais dos bancos disponíveis no menu.

        Fique alerta e proteja suas informações!
        """);

        btnVoltar.addActionListener(e -> {
            dispose();
            if (Sessao.usuarioLogado.getPerfil().name().equals("ADMIN")) {
                new TelaPrincipal();
            } else {
                new TelaMenuUsuario();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
                if (Sessao.usuarioLogado.getPerfil().name().equals("ADMIN")) {
                    new TelaPrincipal();
                } else {
                    new TelaMenuUsuario();
                }
            }
        });

        setVisible(true);
    }
}