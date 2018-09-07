/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.web.controllers.error;

import br.uel.ceca.cin.saier.enums.TemplatePath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Renato
 */
@Controller
public class SaierErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model erros) {
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return TemplatePath.ERRO_404.getPath();
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return TemplatePath.ERRO_PADRAO.getPath();
            } else if (statusCode == HttpStatus.FAILED_DEPENDENCY.value()) {
                return TemplatePath.ERRO_PADRAO.getPath();
            }
        }
        return TemplatePath.ERRO_PADRAO.getPath();
    }

    @Override
    public String getErrorPath() {
        return TemplatePath.ERRO_PADRAO.getPath();
    }
}
