package glg203.demoJFX.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * App1Controller
 */
public class App3Controller {

    @FXML
    private TextArea messageArea;

    @FXML
    public void loadMessage() {
        // Utilisation d'un annuaire.        
        IMessageSource messageSource= SimpleDirectory.getInstance().lookup("MessageSource", IMessageSource.class);
        messageArea.setText(messageSource.getMessage());
    }
}