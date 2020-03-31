package glg203.demoJFX.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * App1Controller
 */
public class App1Controller {

    @FXML
    private TextArea messageArea;

    @FXML
    public void loadMessage() {
        // Utilisation d'un singleton... on est couplé à MyMessageSource.
        IMessageSource messageSource= MyMessageSource.getInstance();
        messageArea.setText(messageSource.getMessage());
    }

}