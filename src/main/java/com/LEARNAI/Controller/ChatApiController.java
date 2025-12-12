package com.LEARNAI.Controller;

import com.LEARNAI.Service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/chat") // This matches the URL in your home.html fetch()
=======
@RequestMapping("/api/chat")
>>>>>>> 54dedb5 (Secure commit: Removed API keys and added gitignore)
public class ChatApiController {

    @Autowired
    private AiService aiService;

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");
        
<<<<<<< HEAD
        // Send to service -> Python -> Grok
=======
        // Calling the service with JUST the message (no bot name)
>>>>>>> 54dedb5 (Secure commit: Removed API keys and added gitignore)
        String aiResponse = aiService.getGrokResponse(userMessage);
        
        return Map.of("reply", aiResponse);
    }
}