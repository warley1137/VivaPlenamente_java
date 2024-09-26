package classes;

public class CalculoPeso {
    public static double pesoIdeal(double altura, String sexo) {
        if ("Masculino".equalsIgnoreCase(sexo)) {
            return 72.7 * altura - 58;
        } else if ("Feminino".equalsIgnoreCase(sexo)) {
            return 62.1 * altura - 44.7;
        }
        return 0;
    }

    public static double porcentagemDoPesoIdeal(double pesoIdeal, double peso) {
        return (1 - pesoIdeal / peso) * 100;
    }
}
