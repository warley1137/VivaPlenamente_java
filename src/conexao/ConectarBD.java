package conexao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBD {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBNAME = "clinica_spa";
    private static final String URL = "jdbc:mysql://DESKTOP-96KQQF3/" + DBNAME;
    private static final String LOGIN = "admin";
    private static final String SENHA = "9418";

    public static Connection conectar() {
        Connection conexao = null;

        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        return conexao;
    }

    public static void desconectar(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Desconectado");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
