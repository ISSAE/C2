package demoSpringBootJavaFx.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import demoSpringBootJavaFx.services.IMessageSource;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Le contrôleur, géré par Spring, qui injectera la "bonne" implémentation de IMessageSource.
 */
@Controller
public class AppController {

    private final IMessageSource messageSource;

    @Autowired
    public AppController(IMessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @FXML
    private TextArea messageArea;

    @FXML
    public void loadMessage() {
        String message = messageSource.getMessage();
        messageArea.setText(message);

    }

}