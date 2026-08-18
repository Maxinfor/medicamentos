package com.lembremed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lembremed.dto.MedicamentoVisionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class VisionService {

    @Value("${GEMINI_API_KEY}") // Busca da variável de ambiente
    private String apiKey;

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    public MedicamentoVisionDto analisarCaixaMedicamento(String imagemBase64) {
        RestTemplate restTemplate = new RestTemplate();
        String base64Data = imagemBase64.contains(",") ? imagemBase64.split(",")[1] : imagemBase64;

        String prompt = "Extraia nome, dosagem e observações desta caixa de remédio em JSON: {\"nome\":\"\",\"dosagem\":\"\",\"observacoes\":\"\"}.";

        Map<String, Object> body = Map.of(
            "contents", List.of(Map.of("parts", List.of(
                Map.of("text", prompt),
                Map.of("inline_data", Map.of("mime_type", "image/jpeg", "data", base64Data))
            )))
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        try {
            ResponseEntity<Map> resp = restTemplate.postForEntity(API_URL + apiKey, new HttpEntity<>(body, headers), Map.class);
            String text = (String) ((Map) ((List) ((Map) ((List) ((Map) resp.getBody().get("candidates")).get(0)).get("content")).get("parts")).get(0)).get("text");
            return new ObjectMapper().readValue(text.replaceAll("```json|```", "").trim(), MedicamentoVisionDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro IA: " + e.getMessage());
        }
    }
}
