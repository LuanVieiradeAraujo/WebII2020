package ufpr.ex3;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    
    public Usuario(){

    }
    
    public Usuario(String newNome, String newLogin, String newSenha){
        this.nome = newNome;
        this.login = newLogin;
        this.senha = newSenha;
    }
    
    public String getNome(){
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
}
