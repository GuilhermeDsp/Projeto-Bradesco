/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import util.EstiloComponentes;
import util.Sessao;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Menu Administrador");
        setSize(420, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(9, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JButton btnCadastro = new JButton("Cadastrar Número");
        JButton btnConsulta = new JButton("Consultar Número");
        JButton btnGerenciar = new JButton("Gerenciar Números");
        JButton btnBloqueios = new JButton("Bloqueios");
        JButton btnUsuarios = new JButton("Usuários");
        JButton btnRelatorios = new JButton("Relatórios");
        JButton btnExportar = new JButton("Exportar Dados");
        JButton btnOrientacao = new JButton("Orientação para o Usuário"); // ✅ Novo botão
        JButton btnSair = new JButton("Sair");

        // Estilo dos botões
        EstiloComponentes.estilizaBotaoCadastro(btnCadastro);
        EstiloComponentes.estilizaBotao(btnConsulta);
        EstiloComponentes.estilizaBotao(btnGerenciar);
        EstiloComponentes.estilizaBotao(btnBloqueios);
        EstiloComponentes.estilizaBotao(btnUsuarios);
        EstiloComponentes.estilizaBotao(btnRelatorios);
        EstiloComponentes.estilizaBotao(btnExportar);
        EstiloComponentes.estilizaBotao(btnOrientacao);
        EstiloComponentes.estilizaBotao(btnSair);

        // Adiciona os botões ao painel
        panel.add(btnCadastro);
        panel.add(btnConsulta);
        panel.add(btnGerenciar);
        panel.add(btnBloqueios);
        panel.add(btnUsuarios);
        panel.add(btnRelatorios);
        panel.add(btnExportar);
        panel.add(btnOrientacao); // ✅ Novo botão
        panel.add(btnSair);

        add(panel);

        // Ações dos botões
        btnCadastro.addActionListener(e -> {
            dispose();
            new TelaCadastro();
        });

        btnConsulta.addActionListener(e -> {
            dispose();
            new TelaConsulta();
        });

        btnGerenciar.addActionListener(e -> {
            dispose();
            new TelaGerenciarNumeros();
        });

        btnBloqueios.addActionListener(e -> {
            dispose();
            new TelaGerenciarBloqueios();
        });

        btnUsuarios.addActionListener(e -> {
            dispose();
            new TelaListagemUsuarios();
        });

        btnRelatorios.addActionListener(e -> {
            dispose();
            new TelaRelatorios();
        });

        btnExportar.addActionListener(e -> {
            dispose();
            new TelaExportarDados();
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
