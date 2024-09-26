package classes;

import conexao.ConectarBD;
import interfaces.Atividades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Emagrecimento extends Paciente implements Atividades {
    private String tipoAtividade;
    private double peso;
    private double altura;
    private double pesoIdeal;

    public Emagrecimento(String nome, String telefone, String sexo, String tipoAtividade, double peso, double altura) {
        super(nome, telefone, sexo);
        this.tipoAtividade = tipoAtividade;
        this.peso = peso;
        this.altura = altura;
        this.pesoIdeal = CalculoPeso.pesoIdeal(altura, sexo);
    }

    @Override
    public void mostrarDadosCadastro() {
        System.out.println("Tipo de Atividade: " + tipoAtividade);
        System.out.println("Peso Ideal: " + pesoIdeal);
    }

    @Override
    public String getAtividadeEspecifica() {
        return tipoAtividade;
    }

    @Override
    public double getPesoIdeal() {
        return pesoIdeal;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    @Override
    public String caminharBosque() {
        return null;
    }

    @Override
    public String nadarPiscina() {
        return null;
    }

    @Override
    public String exercitarAcademia() {
        return null;
    }

    @Override
    public void realizarServicos(String[] servicos) {
        // Não faz nada, pois Emagrecimento não utiliza serviços
    }

    @Override
    public void setAtividade(String novaAtividade) {
        this.tipoAtividade = novaAtividade;
    }

    @Override
    public String getAtividade() {
        return tipoAtividade;
    }

    @Override
    public void salvarNoBanco() {
        ConectarBD conectarBD = new ConectarBD();
        try (Connection connection = conectarBD.conectar()) {
            String sql = "INSERT INTO emagrecimento (nome, telefone, sexo, tipo_atividade, peso, altura, peso_ideal) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, telefone);
                preparedStatement.setString(3, sexo);
                preparedStatement.setString(4, tipoAtividade);
                preparedStatement.setDouble(5, peso);
                preparedStatement.setDouble(6, altura);
                preparedStatement.setDouble(7, pesoIdeal);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    @Override
    public void removerDoBanco() {
        // Implemente a remoção do banco, se necessário
    }
}
