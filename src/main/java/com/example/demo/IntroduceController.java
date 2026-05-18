package com.example.demo;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class IntroduceController {

    @GetMapping("/introduce")
    public String introduce(
            @RequestParam(name="name", required=false, defaultValue="손정한") String name,
                             Model model) {
        model.addAttribute("name", name);
        return "introduce";
    }

    @ResponseBody
    @GetMapping("/json")
    public Map<String, Object> json() {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("name", "손정한");
        result.put("age", 23);

        return result;
    }
}
