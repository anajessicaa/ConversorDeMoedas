import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateService {
    private static final String API_KEY = "273f7c8452194564104e15f5";
    private static final String BASE_URL = " https://v6.exchangerate-api.com/v6/";
    private final Gson gson = new Gson();

    public double obterTaxaCambio(String moedaOrigem, String moedaDestino) throws Exception {
        String urlString = BASE_URL + API_KEY + "/latest/" + moedaOrigem;
        URL url = new URL(urlString);

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        int reposta = conexao.getResponseCode();
        if(reposta != 200) {
            throw new RuntimeException("Erro ao conectar: HTTP " + reposta);
        }

        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuilder respostaJson = new StringBuilder();
        String linha;

        while((linha = leitor.readLine()) != null) {
            respostaJson.append(linha);
        }

        leitor.close();

        ExchangeRateResponse dados = gson.fromJson(respostaJson.toString(), ExchangeRateResponse.class);

        if(!dados.conversion_rates.containsKey(moedaDestino)) {
            throw new RuntimeException("Moeda de destino não encontrada: " + moedaDestino);
        }

        return dados.conversion_rates.get(moedaDestino);
    }

    private static class ExchangeRateResponse {
        String base_code;
        java.util.Map<String, Double> conversion_rates;
    }
}
