package com.proyecto.controller;

import com.proyecto.domain.about_us;
import com.proyecto.service.about_usService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {
    
    @GetMapping("usuario/about_us")
    public String AboutUs() {
        return "usuario/about_us"; 
    }
  
}


