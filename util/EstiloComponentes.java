/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javax.swing.*;
import java.awt.*;

public class EstiloComponentes {

    public static void estilizaCampoTexto(JTextField campo) {
        campo.setPreferredSize(new Dimension(300, 40));
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public static void estilizaBotao(JButton botao) {
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(new Color(30, 144, 255)); // Azul
        botao.setForeground(Color.WHITE);
        botao.setPreferredSize(new Dimension(250, 40));
    }

    public static void estilizaBotaoCadastro(JButton botao) {
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(new Color(0, 153, 0)); // Verde
        botao.setForeground(Color.WHITE);
        botao.setPreferredSize(new Dimension(250, 40));
    }

    public static void estilizaBotaoDenuncia(JButton botao) {
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(new Color(220, 20, 60)); // Vermelho
        botao.setForeground(Color.WHITE);
        botao.setPreferredSize(new Dimension(250, 40));
    }
}