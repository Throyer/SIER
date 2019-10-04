package com.github.websier.sier.app.controllers;

import com.github.websier.sier.app.utils.Templates.EDIFCIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * EdificioController
 */
@Controller
public class EdificioController {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("edificios", "active");
    }

    @RequestMapping("/edificios")
    public String Index() {
        return EDIFCIO.INDEX;
    }
}