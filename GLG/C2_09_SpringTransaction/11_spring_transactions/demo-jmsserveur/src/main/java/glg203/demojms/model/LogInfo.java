package glg203.demojms.model;

import java.io.Serializable;

/**
 * LogInfo
 */
public class LogInfo implements Serializable {

    private String level, body;


    public LogInfo() {
    }

    public LogInfo(String level, String body) {
        this.level = level;
        this.body = body;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LogInfo level(String level) {
        this.level = level;
        return this;
    }

    public LogInfo body(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " level='" + getLevel() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }
    
}