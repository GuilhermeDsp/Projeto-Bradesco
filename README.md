# Projeto-Bradesco
Projeto feito para apresentaçao do Bradesco


# 📞 Validação de Ligações Suspeitas — Sistema Antifraude (Java + MySQL)

## 🎯 Objetivo

Este projeto tem como objetivo criar um sistema antifraude que permite o cadastro, a consulta e a classificação de números de telefone com foco em segurança contra golpes por ligação.

### Funcionalidades principais:

- Alertar usuários sobre números suspeitos durante uma ligação simulada
- Permitir denúncias com justificativa
- Manter um banco de dados com contatos oficiais de bancos
- Oferecer orientações contra golpes
- Permitir que administradores gerenciem números, usuários e logs

O sistema foi desenvolvido em **Java (Swing)** com persistência de dados em **MySQL**, com uma interface gráfica simples e objetiva.

---

## 📌 Funcionalidade Básica

### Para Usuários:

- Consultar números de telefone
- Denunciar ligações suspeitas com motivo
- Simular uma ligação para receber alerta em tempo real caso o número seja suspeito
- Visualizar contatos oficiais de bancos
- Ler orientações contra golpes e fraudes

### Para Administradores:

- Autorizar, excluir ou alterar o status de números
- Visualizar e exportar relatórios de números e logs do sistema
- Gerenciar bloqueios e visualizar usuários cadastrados

---

## ⚙️ Instruções de Execução

Siga os passos abaixo para rodar o sistema:

### 1. Banco de Dados

- Instale o **MySQL Server**.
- Execute o script `validacao_ligacoes.sql` para criar o banco de dados e as tabelas necessárias.

### 2. Importação do Projeto

- Abra o **NetBeans**.
- Importe o projeto Java existente.

### 3. Configuração do Conector JDBC

- Baixe o driver **MySQL JDBC Connector**.
- Adicione o arquivo `.jar` do conector ao classpath do projeto.

### 4. Configuração de Conexão

Abra o arquivo:

```
src/util/ConexaoMySQL.java
```

Ajuste os campos de conexão conforme seu MySQL:

```java
private static final String USER = "root";
private static final String PASSWORD = "sua_senha";
```

### 5. Login Inicial

Ao iniciar, faça login com o seguinte usuário administrador já cadastrado:

- **Email:** admin@email.com  
- **Senha:** admin123  

---

## 💡 Exemplo de Uso

### Fluxo Básico:

1. **Login:**
   - O usuário abre o sistema e insere email e senha.

2. **Consulta de Número:**
   - No campo de consulta, o usuário digita um número de telefone.
   - Se o número for classificado como **Suspeito**, um alerta visual é exibido.

3. **Denúncia de Golpe:**
   - Caso receba uma ligação suspeita, o usuário pode denunciar o número, informando um motivo.

4. **Administração (Para administradores):**
   - O admin pode visualizar todas as denúncias.
   - Pode autorizar, excluir ou reclassificar números.
   - Pode exportar relatórios de números suspeitos ou logs de atividades em formato `.txt`.

5. **Outras Funcionalidades:**
   - Visualização de contatos oficiais de bancos.
   - Exibição de orientações educativas contra golpes.

---

## 🧬 Qualidade do Código

### ✅ Classes e Objetos (Responsabilidade Única)

Principais classes:

- **Model (Domínio):**
  - `Usuario`, `Telefone`, `TipoTelefone`, `Perfil`, `Pessoa`

- **DAO (Persistência e regras de negócio):**
  - `UsuarioDAO`, `TelefoneDAO`, `LogDAO`

- **Suporte:**
  - `Sessao`, `ConexaoMySQL`, `EstiloComponentes`

- **View (Interface Gráfica):**
  - Todas as telas organizadas no pacote `view`

---

### ✅ Encapsulamento

- Todos os atributos de classe são **`private`**
- Acesso aos atributos apenas via **getters** e **setters**
- Métodos de manipulação de dados são **públicos**
- Não há acesso direto a atributos fora das classes

Exemplo:

```java
private String email;

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}
```

---

### ✅ Relacionamentos

- **Usuário** está relacionado a um **Perfil** (enum: `ADMIN`, `USUARIO`)
- **Telefone** possui um **TipoTelefone** (enum: `SUSPEITO`, `AUTORIZADO`, `DESCONHECIDO`)
- **LogDAO** registra o relacionamento entre **usuários** e suas **ações** no sistema
- Existem relacionamentos claros entre **DAO**, **modelos** e **views**, seguindo boas práticas de arquitetura em camadas.

---

## 🧑‍💻 Enumerações (Enum)

### Perfil

Define o tipo de acesso do usuário:

```java
public enum Perfil { ADMIN, USUARIO }
```

### TipoTelefone

Define o status de um número de telefone:

```java
public enum TipoTelefone { AUTORIZADO, SUSPEITO, DESCONHECIDO }
```

---

## 🧱 Polimorfismo

### ✅ Sobrecarga de Construtores (Telefone)

A classe **Telefone** implementa **polimorfismo por sobrecarga de construtores**:

```java
public class Telefone {
    public Telefone(String numero, TipoTelefone status) { ... }

    public Telefone(String numero, TipoTelefone status, String descricao) { ... }
}
```

**Benefício:** Permite criar objetos `Telefone` com ou sem descrição, conforme a necessidade.

---

### ✅ Polimorfismo por Tipo de Usuário (Execução Condicional)

Durante a execução, o sistema decide qual tela abrir com base no perfil do usuário:

```java
if (Sessao.usuarioLogado.getPerfil().name().equals("ADMIN")) {
    new TelaPrincipal();
} else {
    new TelaMenuUsuario();
}
```

**Benefício:** Comportamento dinâmico de acordo com o tipo de usuário logado.

---

## 🏛️ Herança

### ✅ Classe Pessoa

Atributos comuns a todas as pessoas (usuários, por exemplo) estão centralizados na superclasse **Pessoa**:

```java
public class Pessoa {
    private String nome;
    private String telefone;

    // getters e setters
}
```

### ✅ Classe Usuario (Herança de Pessoa)

A classe **Usuario** herda de **Pessoa**:

```java
public class Usuario extends Pessoa {
    private String email;
    private String senha;
    private Perfil perfil;

    // getters e setters
}
```

**Benefício:** Reaproveitamento de atributos comuns, código mais limpo e reutilizável.

---

## 🧾 Estrutura do Projeto

O projeto segue a seguinte organização de pacotes:

```
├── dao
│   ├── TelefoneDAO.java
│   └── UsuarioDAO.java
│
├── model
│   ├── Usuario.java
│   ├── Telefone.java
│   ├── TipoTelefone.java
│   ├── Perfil.java
│   └── Pessoa.java
│
├── util
│   ├── ConexaoMySQL.java
│   ├── EstiloComponentes.java
│   └── LogDAO.java
│
├── view
│   ├── TelaLogin.java
│   ├── TelaConsulta.java
│   └── TelaAdmin.java
```

---

## 💬 Exemplos de Comentários no Código

```java
// Consulta o status de um número no banco de dados
public TipoTelefone consultar(String numero) throws Exception {
    ...
}
```

```java
// Insere ou atualiza o número com o novo status e motivo informado
public void inserirOuAtualizar(Telefone t) throws Exception {
    ...
}
```

```java
// Registra ações importantes no log do sistema
public static void registrar(String email, String acao) {
    ...
}
```

```java
// Carrega lista de contatos oficiais de bancos para exibir ao usuário
public List<Telefone> listarNumerosOficiais() throws Exception {
    ...
}
```

```java
// Simula uma ligação e alerta o usuário se o número for suspeito
private void simularLigacao(String numero) {
    ...
}
```

---


