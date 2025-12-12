package com.LEARNAI.Controller;

import com.LEARNAI.Service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat") // This matches the URL in your home.html fetch()
public class ChatApiController {

    @Autowired
    private AiService aiService;

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");
        
        // Send to service -> Python -> Grok
        String aiResponse = aiService.getGrokResponse(userMessage);
        
        return Map.of("reply", aiResponse);
    }
}