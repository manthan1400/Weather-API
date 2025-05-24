package com.weather.Controller;

import com.weather.Service.WeatherKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KafkaMessageController {

    @Autowired
    private WeatherKafkaProducer weatherKafkaProducer;

    @GetMapping("/messages")
    public String messagesPage(Model model) {
        model.addAttribute("messages", weatherKafkaProducer.getMessages());
        return "messages"; // refers to templates/messages.html
    }

    @PostMapping("/send")
    @ResponseBody
    public String sendMessage(@RequestParam String message) {
        weatherKafkaProducer.sendMessage("test-topic", message);
        return "redirect:/messages"; // Redirect back to message page
    }

    @PostMapping("api/messages/clear")
    @ResponseBody
    public String clearMessages() {
        weatherKafkaProducer.clearMessages();
        return "cleared";
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public List<String> getMessagesJson() {
        return weatherKafkaProducer.getMessages();
    }


}
