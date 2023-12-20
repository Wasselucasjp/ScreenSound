package br.com.wlbr.screensound.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChaGPT {
    public static String ObterInformaçao(String txt){
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("me fale sobre o artista: " + txt)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var reposta = service.createCompletion(requisicao);
        return reposta.getChoices().get(0).getText();
    }
}
