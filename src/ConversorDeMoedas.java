public class ConversorDeMoedas {
    private final ExchangeRateService service = new ExchangeRateService();

    public void converter(int opcao, double valor) {
        String moedaOrigem = "";
        String moedaDestino = "";

        switch(opcao) {
            case 1 -> {
                moedaOrigem = "EUR";
                moedaDestino = "USD";
            }
            case 2 -> {
                moedaOrigem = "USD";
                moedaDestino = "BRL";
            }
            case 3 -> {
                moedaOrigem = "BRL";
                moedaDestino = "GBP";
            }
            case 4 -> {
                moedaOrigem = "GBP";
                moedaDestino = "CNY";
            }
            case 5 -> {
                moedaOrigem = "CNY";
                moedaDestino = "USD";
            }
            case 6 -> {
                moedaOrigem = "BRL";
                moedaDestino = "EUR";
            }
            default -> {
                System.out.println("Opção inválida. Por favor, selecione uma das opções listadas.");
                return;
            }
        }

        System.out.printf("Convertendo %.2f de %s para %s...%n", valor, moedaOrigem, moedaDestino);

        try {
            double taxa = service.obterTaxaCambio(moedaOrigem, moedaDestino);
            double resultado = valor * taxa;

            System.out.printf("Taxa atual: 1 %s = %.4f %s%n", moedaOrigem, taxa, moedaDestino);
            System.out.printf("Valor convertido: %.2f %s%n", resultado, moedaDestino);

            salvaConversao(moedaOrigem, moedaDestino, taxa, valor, resultado);

            System.out.println("Conversão salva com sucesso!\n");

        } catch (Exception e) {
            System.out.println("Erro ao converter moedas: " + e.getMessage());
        }
    }

    private void salvaConversao(String origem, String destino, double taxa, double valorOriginal, double resultadoFinal) {
        String conteudo = String.format("""
            Conversão Realizada:
            De: %s
            Para: %s
            Valor original: %.2f
            Taxa usada: %.4f
            Valor convertido: %.2f

            ------------------------------
            """, origem, destino, valorOriginal, taxa, resultadoFinal);

        try (java.io.FileWriter writer = new java.io.FileWriter("conversao.txt", true)) {
            writer.write(conteudo);
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
