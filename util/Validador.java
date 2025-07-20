/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

public class Validador {

    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$");
    }

    public static boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches("\\d{8,15}");
    }

    public static boolean validarSenha(String senha) {
        return senha != null && senha.length() >= 6;
    }
}

