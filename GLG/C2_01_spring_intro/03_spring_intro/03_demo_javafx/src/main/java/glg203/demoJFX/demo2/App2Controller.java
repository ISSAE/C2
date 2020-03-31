package glg203.demoJFX.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * App1Controller
 */
public class App2Controller {

    @FXML
    private TextArea messageArea;

    @FXML
    public void loadMessage() {
        // Utilisation d'un singleton... couplage plus faible,
        // mais il n'est pas facile de changer de MessageSource !
        IMessageSource messageSource= MessageSourceRepository.getInstance();
        messageArea.setText(messageSource.getMessage());
    }

}