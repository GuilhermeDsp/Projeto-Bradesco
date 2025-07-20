/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import util.EstiloComponentes;
import util.Sessao;

public class TelaMenuUsuario extends JFrame {

    public TelaMenuUsuario() {
        setTitle("Menu do Usuário");
        setSize(420, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JButton btnConsultar = new JButton("Consultar Número");
        JButton btnDenunciar = new JButton("Denunciar Número");
        JButton btnSimular = new JButton("Simular Ligação");
        JButton btnContatos = new JButton("Contatos Oficiais");
        JButton btnOrientacao = new JButton("Orientação para o Usuário"); // ✅ Novo botão
        JButton btnSair = new JButton("Sair");

        EstiloComponentes.estilizaBotao(btnConsultar);
        EstiloComponentes.estilizaBotaoDenuncia(btnDenunciar);
        EstiloComponentes.estilizaBotao(btnSimular);
        EstiloComponentes.estilizaBotao(btnContatos);
        EstiloComponentes.estilizaBotao(btnOrientacao);
        EstiloComponentes.estilizaBotao(btnSair);

        panel.add(btnConsultar);
        panel.add(btnDenunciar);
        panel.add(btnSimular);
        panel.add(btnContatos);
        panel.add(btnOrientacao); // ✅ Botão adicionado
        panel.add(btnSair);

        add(panel);

        btnConsultar.addActionListener(e -> {
            dispose();
            new TelaConsulta();
        });

        btnDenunciar.addActionListener(e -> {
            dispose();
            new TelaDenuncia();
        });

        btnSimular.addActionListener(e -> {
            dispose();
            new TelaSimulacaoLigacao();
        });

        btnContatos.addActionListener(e -> {
            dispose();
            new TelaContatosOficiais();
        });

        btnOrientacao.addActionListener(e -> {
            dispose();
            new TelaOrientacao();
        });

        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin();
        });

        setVisible(true);
    }
}