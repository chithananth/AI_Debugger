package com.ai.debugger.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class GeminiService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    public String explainError(String code, String error) {

        try {

            String prompt = """
                    You are an AI-powered beginner-friendly coding mentor.

                    Analyze the given programming error and provide the response in the following structured format and 
                    also all the details dont take more than one line except beginner friendly explanation:

                    1. Difficulty Level
                       - Easy / Medium / Hard

                    2. Technology Detected
                       - Example: Java, Spring Boot, React, Python, MySQL, Node.js

                    3. Beginner-Friendly Explanation
                       - Explain the error in very simple English suitable for students and beginners.

                    4. Root Cause
                       - Explain why the error happened.

                    5. Corrected Code
                       - Provide corrected version of the code.

                    6. Prevention Tips
                       - Give simple tips to avoid this issue in future.

                    7. Learning Resources
                       - Suggest:
                         - 1 helpful YouTube search keyword
                         - 1 documentation/tutorial topic
                         - 1 community discussion source (Stack Overflow/Reddit/etc.)

                    Code:
                    %s

                    Error:
                    %s
                    """.formatted(code, error);

            String url = "https://openrouter.ai/api/v1/chat/completions";

            String requestBody = """
                    {
                    
            		  "model": "openai/gpt-oss-120b:free",
                      "messages": [
                        {
                          "role": "user",
                          "content": "%s"
                        }
                      ]
                    }
                    """.formatted(prompt.replace("\"", "\\\""));

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.setBearerAuth(apiKey);

            headers.add("HTTP-Referer", "http://localhost:8080");
            headers.add("X-Title", "AI Debugger");

            HttpEntity<String> entity =
                    new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root =
                    mapper.readTree(response.getBody());

            return root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

        } catch (Exception e) {

            return "Error while calling AI API: "
                    + e.getMessage();
        }
    }
}
