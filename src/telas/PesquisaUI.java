package telas;

import classes.GestaoPacientes;
import interfaces.Atividades;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PesquisaUI {
    private GestaoPacientes gestao;
    private JFrame frame;

    public PesquisaUI(GestaoPacientes gestao) {
        this.gestao = gestao;
        createAndShowUI();
    }

    private void createAndShowUI() {
        frame = new JFrame("Pesquisar Pacientes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelNome = new JLabel("Nome do Paciente:");
        JTextField nomeField = new JTextField(30);

        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(e -> pesquisarPaciente(nomeField.getText()));

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(labelNome)
                .addComponent(nomeField)
                .addComponent(pesquisarButton)
        );

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelNome)
                .addComponent(nomeField)
                .addComponent(pesquisarButton)
        );

        frame.getContentPane().add(panel);

        frame.setSize(500, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void pesquisarPaciente(String nome) {
        // Aqui você pode personalizar o comando SQL conforme a necessidade
        String comando = "SELECT * FROM estetica WHERE nome = ?";
        List<String> resultados = gestao.pesquisarPacientes(comando, nome);

        exibirResultados(resultados);
    }

    private void exibirResultados(List<String> resultados) {
        JFrame resultadoFrame = new JFrame("Resultados da Pesquisa");
        resultadoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea resultTextArea = new JTextArea();

        for (String resultado : resultados) {
            resultTextArea.append(resultado + "\n\n");
        }

        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        JButton alterarButton = new JButton("Alterar Atividade");
        alterarButton.addActionListener(e -> alterarAtividade());

        JButton removerButton = new JButton("Remover Paciente");
        removerButton.addActionListener(e -> removerPaciente());

        JButton apresentarButton = new JButton("Apresentar Dados");
        apresentarButton.addActionListener(e -> apresentarDados());

        JButton evolucaoButton = new JButton("Evolução do Peso");
        evolucaoButton.addActionListener(e -> mostrarEvolucaoPeso());

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(alterarButton);
        buttonPanel.add(removerButton);
        buttonPanel.add(apresentarButton);
        buttonPanel.add(evolucaoButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        resultadoFrame.getContentPane().add(panel);

        resultadoFrame.setSize(500, 400);
        resultadoFrame.setLocationRelativeTo(frame);
        resultadoFrame.setVisible(true);
    }

    private void alterarAtividade() {
        // Obter o nome do paciente selecionado
        String nomePaciente = obterNomePacienteSelecionado();
        if (nomePaciente != null) {
            // Aqui você pode abrir uma nova interface para alterar a atividade
            JOptionPane.showMessageDialog(null, "Função de Alteração de Atividade a ser implementada para " + nomePaciente);
        }
    }

    private void removerPaciente() {
        // Obter o nome do paciente selecionado
        String nomePaciente = obterNomePacienteSelecionado();
        if (nomePaciente != null) {
            // Aqui você pode abrir uma nova interface para confirmar a remoção do paciente
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente remover o paciente " + nomePaciente + "?", "Remover Paciente", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                gestao.removerPaciente(nomePaciente);
                JOptionPane.showMessageDialog(null, "Paciente removido com sucesso.");
            }
        }
    }

    private void apresentarDados() {
        // Obter o nome do paciente selecionado
        String nomePaciente = obterNomePacienteSelecionado();
        if (nomePaciente != null) {
            // Exibir os dados do paciente
            gestao.apresentarDados(nomePaciente);
        }
    }


    private void mostrarEvolucaoPeso() {
        // Mostrar a evolução do peso para todos os pacientes
        List<String> evolucaoPeso = gestao.mostrarEvolucaoPeso();
        exibirResultados(evolucaoPeso);
    }

    private String obterNomePacienteSelecionado() {
        // Implementar a lógica para obter o nome do paciente selecionado na lista de resultados
        // Você pode usar a JTextArea ou outra estrutura para armazenar os resultados e obter o nome do paciente selecionado.
        // Retorne o nome do paciente selecionado ou null se nenhum paciente estiver selecionado.
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestaoPacientes gestaoPacientes = new GestaoPacientes();
            new PesquisaUI(gestaoPacientes);
        });
    }
}
