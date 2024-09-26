package telas;

import classes.GestaoPacientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpaApp {
    private GestaoPacientes gestao;

    public SpaApp() {
        this.gestao = new GestaoPacientes();
        createAndShowUI();
    }

    private void createAndShowUI() {
        JFrame frame = new JFrame("Spa - Viva Plenamente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton cadastroEsteticaButton = new JButton("Cadastrar Paciente para Estética");
        cadastroEsteticaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroUI(gestao, "Estetica");
            }
        });

        JButton cadastroEmagrecimentoButton = new JButton("Cadastrar Paciente para Emagrecimento");
        cadastroEmagrecimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroUI(gestao, "Emagrecimento");
            }
        });

        JButton pesquisaButton = new JButton("Pesquisar Paciente");
        pesquisaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PesquisaUI(gestao);
            }
        });

        panel.add(cadastroEsteticaButton);
        panel.add(cadastroEmagrecimentoButton);
        panel.add(pesquisaButton);

        frame.getContentPane().add(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpaApp::new);
    }
}
