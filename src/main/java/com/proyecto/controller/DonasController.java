package com.proyecto.controller;

import com.proyecto.domain.about_us;
import com.proyecto.service.about_usService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonasController {
    
    @GetMapping("productos/donas")
    public String Donas() {
        return "productos/donas"; 
    }
  
}