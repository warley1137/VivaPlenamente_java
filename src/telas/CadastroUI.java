package telas;

import classes.Emagrecimento;
import classes.Estetica;
import classes.GestaoPacientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CadastroUI {
    private GestaoPacientes gestao;
    private JFrame frame;
    private String tipoCadastro;

    public CadastroUI(GestaoPacientes gestao, String tipoCadastro) {
        this.gestao = gestao;
        this.tipoCadastro = tipoCadastro;
        createAndShowUI();
    }

    private void createAndShowUI() {
        frame = new JFrame("Cadastro de Paciente");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField nameField = new JTextField(20);
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField phoneField = new JTextField(20);
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Sexo:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JRadioButton maleRadioButton = new JRadioButton("Masculino");
        JRadioButton femaleRadioButton = new JRadioButton("Feminino");

        ButtonGroup sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(maleRadioButton);
        sexButtonGroup.add(femaleRadioButton);

        JPanel sexPanel = new JPanel();
        sexPanel.add(maleRadioButton);
        sexPanel.add(femaleRadioButton);
        panel.add(sexPanel, gbc);

        if ("Emagrecimento".equals(tipoCadastro)) {
            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Tipo de Atividade:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            JComboBox<String> activityComboBox = new JComboBox<>(new String[]{"Caminhar no Bosque", "Nadar na Piscina", "Exercitar na Academia"});
            panel.add(activityComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(new JLabel("Peso:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            JTextField weightField = new JTextField(20);
            panel.add(weightField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            panel.add(new JLabel("Altura:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            JTextField heightField = new JTextField(20);
            panel.add(heightField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            JButton registerButton = new JButton("Cadastrar");
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cadastrarEmagrecimento(nameField.getText(), phoneField.getText(),
                            getSelectedButtonText(sexButtonGroup), (String) activityComboBox.getSelectedItem(),
                            Double.parseDouble(weightField.getText()), Double.parseDouble(heightField.getText()));
                    frame.dispose();
                }
            });
            panel.add(registerButton, gbc);
        } else if ("Estetica".equals(tipoCadastro)) {
            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Tipo de Atividade:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            JComboBox<String> activityComboBox = new JComboBox<>(new String[]{"Caminhar no Bosque", "Nadar na Piscina", "Exercitar na Academia"});
            panel.add(activityComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(new JLabel("Serviço 1:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            JComboBox<String> serviceComboBox1 = new JComboBox<>(new String[]{"", "Drenagem Linfática", "Hidratação de Pés e Mãos", "Limpeza de Pele", "Banho de Lua", "Peeling", "Cuidados para o Cabelo", "Massagem Esfoliante Facial e Corporal"});
            panel.add(serviceComboBox1, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            panel.add(new JLabel("Serviço 2:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            JComboBox<String> serviceComboBox2 = new JComboBox<>(new String[]{"", "Drenagem Linfática", "Hidratação de Pés e Mãos", "Limpeza de Pele", "Banho de Lua", "Peeling", "Cuidados para o Cabelo", "Massagem Esfoliante Facial e Corporal"});
            panel.add(serviceComboBox2, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            panel.add(new JLabel("Serviço 3:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 6;
            JComboBox<String> serviceComboBox3 = new JComboBox<>(new String[]{"", "Drenagem Linfática", "Hidratação de Pés e Mãos", "Limpeza de Pele", "Banho de Lua", "Peeling", "Cuidados para o Cabelo", "Massagem Esfoliante Facial e Corporal"});
            panel.add(serviceComboBox3, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            JButton registerButton = new JButton("Cadastrar");
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<String> selectedServices = new ArrayList<>();
                    if (!serviceComboBox1.getSelectedItem().equals("")) {
                        selectedServices.add((String) serviceComboBox1.getSelectedItem());
                    }
                    if (!serviceComboBox2.getSelectedItem().equals("")) {
                        selectedServices.add((String) serviceComboBox2.getSelectedItem());
                    }
                    if (!serviceComboBox3.getSelectedItem().equals("")) {
                        selectedServices.add((String) serviceComboBox3.getSelectedItem());
                    }

                    cadastrarEstetica(
                            nameField.getText(),
                            phoneField.getText(),
                            getSelectedButtonText(sexButtonGroup),
                            (String) activityComboBox.getSelectedItem(),
                            selectedServices.toArray(new String[0])
                    );
                    frame.dispose();
                }
            });
            panel.add(registerButton, gbc);
        }

        gbc.gridx = 1;
        gbc.gridy = 7;
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(backButton, gbc);

        frame.getContentPane().add(panel);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void cadastrarEmagrecimento(String nome, String telefone, String sexo, String tipoAtividade, double peso, double altura) {
        Emagrecimento pacienteEmagrecimento = new Emagrecimento(nome, telefone, sexo, tipoAtividade, peso, altura);
        gestao.cadastrarPaciente(pacienteEmagrecimento, null); // Passando null, pois Emagrecimento não usa serviços
    }

    private void cadastrarEstetica(String nome, String telefone, String sexo, String tipoAtividade, String[] servicos) {
        Estetica pacienteEstetica = new Estetica(nome, telefone, sexo, tipoAtividade, servicos);
        gestao.cadastrarPaciente(pacienteEstetica, pacienteEstetica.getServicosRealizados());
    }


    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
