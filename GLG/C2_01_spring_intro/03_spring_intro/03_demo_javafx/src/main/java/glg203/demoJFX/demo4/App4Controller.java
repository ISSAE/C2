package glg203.demoJFX.demo4;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * App1Controller
 */
public class App4Controller {

    private final IMessageSource messageSource;

    public App4Controller(IMessageSource messageSource) {
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