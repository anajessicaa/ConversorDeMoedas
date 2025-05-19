import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConversorDeMoedas conversor = new ConversorDeMoedas();
        Scanner leitura = new Scanner(System.in);
        int opcao;

        System.out.println("***************************");
        System.out.println("Bem-vindo(a) ao Conversor de Moedas =]");

        String menu = """
                1) Euro para Dólar
                2) Dólar para Real
                3) Real para Libra
                4) Libra para Yuan
                5) Yuan para Dólar
                6) Real para Euro
                7) Sair

                *************************
                """;

        do {
            System.out.println(menu);
            System.out.print("Escolha uma opção válida: ");
            opcao = leitura.nextInt();

            if (opcao == 7) {
                System.out.println("Encerrando programa.");
                break;
            }

            if (opcao < 1 || opcao > 7) {
                System.out.println("Opção inválida! Tente novamente.\n");
                continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = leitura.nextDouble();

            conversor.converter(opcao, valor);
        } while (true);

        leitura.close();
    }
}
