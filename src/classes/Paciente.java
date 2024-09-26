package classes;

import conexao.ConectarBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Paciente {
    protected String nome;
    protected String telefone;
    protected String sexo;

    protected Paciente(String nome, String telefone, String sexo) {
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
    }

    public abstract void mostrarDadosCadastro();

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public abstract String getAtividadeEspecifica();

    public String[] getServicosRealizados() {
        return new String[0];
    }

    public abstract double getPesoIdeal();

    public abstract double getPeso();

    public abstract void salvarNoBanco();

    public abstract void removerDoBanco();

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Telefone: " + getTelefone() + ", Sexo: " + getSexo();
    }
}
