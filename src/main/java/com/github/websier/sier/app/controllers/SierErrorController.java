// package com.github.websier.sier.app.controllers;

// import static com.github.websier.sier.app.utils.Templates.ERROR.INDEX;

// import javax.servlet.RequestDispatcher;
// import javax.servlet.http.HttpServletRequest;

// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;

// /**
//  * SierErrorController
//  */
// @Controller
// public class SierErrorController implements ErrorController {

//     @RequestMapping("/error")
//     public String handleError(HttpServletRequest request, Model model) {
//         Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//         String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

//         model.addAttribute("status", status);
//         model.addAttribute("message", message);
//         return INDEX;
//     }
 
//     @Override
//     public String getErrorPath() {
//         return INDEX;
//     }
    
// }