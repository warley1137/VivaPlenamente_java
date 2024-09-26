package classes;

import conexao.ConectarBD;
import interfaces.Atividades;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GestaoPacientes {
    private Set<Paciente> pacientes;
    private Connection conexao;

    public GestaoPacientes() {
        this.pacientes = new HashSet<>();
        this.conexao = ConectarBD.conectar();

        if (this.conexao == null) {
            // Tratar o erro de conexão aqui, se necessário
            System.err.println("Erro ao conectar ao banco de dados.");
            // Pode ser interessante lançar uma exceção ou tomar alguma outra ação adequada.
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public void desconectar() {
        ConectarBD.desconectar(conexao);
    }

    public void cadastrarPaciente(Paciente paciente, String[] servicos) {
        pacientes.add(paciente);

        // Cadastrar no banco de acordo com o tipo de paciente
        paciente.salvarNoBanco();

        if (paciente instanceof Atividades) {
            ((Atividades) paciente).setAtividade("Nova Atividade"); // Exemplo, ajuste conforme necessário
        }

        if (paciente instanceof Estetica) {
            ((Estetica) paciente).realizarServicos(servicos);
        }
    }

    public List<String> pesquisarPacientes(String comando, String nome) {
        List<String> resultados = new ArrayList<>();

        try (Connection connection = ConectarBD.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(comando)) {

            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Os detalhes da implementação dependem da sua estrutura de banco de dados
                // Adaptar de acordo com as colunas do seu banco
                String resultado = "Nome: " + resultSet.getString("nome") +
                        "\nSexo: " + resultSet.getString("sexo") +
                        "\nTipo de Atividade: " + resultSet.getString("tipo_atividade") +
                        "\nServiços Realizados: " + resultSet.getString("servicos_realizados");
                resultados.add(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar a pesquisa: " + e.getMessage());
        }

        return resultados;
    }

    public void alterarAtividade(Paciente paciente, String novaAtividade) {
        if (paciente instanceof Atividades) {
            ((Atividades) paciente).setAtividade(novaAtividade);
            System.out.println("Atividade alterada para: " + novaAtividade);
        } else {
            System.out.println("Paciente não suporta alteração de atividade.");
        }
    }

    public void removerPaciente(String nome) {
        // Procura o paciente pelo nome
        Paciente paciente = pesquisarPaciente(nome);

        if (paciente != null) {
            pacientes.remove(paciente);

            // Remover do banco de acordo com o tipo de paciente
            paciente.removerDoBanco();

            JOptionPane.showMessageDialog(null, "Paciente removido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Paciente não encontrado.");
        }
    }

    public void apresentarDados(String nome) {
        // Procura o paciente pelo nome
        Paciente paciente = pesquisarPaciente(nome);

        if (paciente != null) {
            // Exibir os dados do paciente
            paciente.mostrarDadosCadastro();

            if (paciente instanceof Estetica) {
                System.out.println("Serviços Selecionados: " + Arrays.toString(((Estetica) paciente).getServicosRealizados()));
            }
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public List<String> apresentarPacientes() {
        List<String> apresentacao = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            apresentacao.add(paciente.getNome() + " - " + paciente.getSexo());
        }
        return apresentacao;
    }

    public List<String> mostrarEvolucaoPeso() {
        List<String> evolucaoPeso = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            if (paciente instanceof Emagrecimento) {
                double porcentagem = CalculoPeso.porcentagemDoPesoIdeal(((Emagrecimento) paciente).getPesoIdeal(),
                        ((Emagrecimento) paciente).getPeso());
                evolucaoPeso.add("Nome: " + paciente.getNome() + ", Porcentagem do Peso Ideal: " + porcentagem);
            }
        }
        return evolucaoPeso;
    }

    public Paciente pesquisarPaciente(String nome) {
        // Utilize a conexão (conectarBD) para realizar a busca no banco de dados
        // ...
        return null;
    }
}
