package classes;

import conexao.ConectarBD;
import interfaces.Atividades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Estetica extends Paciente implements Atividades {
    private String tipoAtividade;
    private String[] servicosRealizados;

    public Estetica(String nome, String telefone, String sexo, String tipoAtividade, String[] servicos) {
        super(nome, telefone, sexo);
        this.tipoAtividade = tipoAtividade;
        realizarServicos(servicos);
    }

    @Override
    public void mostrarDadosCadastro() {
        System.out.println("Tipo de Atividade: " + tipoAtividade);
        if (servicosRealizados != null && servicosRealizados.length > 0) {
            System.out.println("Serviços Realizados: " + String.join(", ", servicosRealizados));
        }
    }

    @Override
    public String getAtividadeEspecifica() {
        return tipoAtividade;
    }

    @Override
    public String[] getServicosRealizados() {
        return servicosRealizados;
    }

    @Override
    public double getPesoIdeal() {
        // Implemente conforme necessário
        return 0;
    }

    @Override
    public double getPeso() {
        // Implemente conforme necessário
        return 0;
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
        if (servicos.length <= 3) {
            this.servicosRealizados = Arrays.copyOf(servicos, servicos.length);
        } else {
            System.out.println("Limite de serviços excedido. Apenas 3 serviços podem ser realizados.");
        }
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
            String sql = "INSERT INTO estetica (nome, telefone, sexo, tipo_atividade, servicos_realizados) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, telefone);
                preparedStatement.setString(3, sexo);
                preparedStatement.setString(4, tipoAtividade);
                preparedStatement.setString(5, String.join(", ", servicosRealizados));
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
