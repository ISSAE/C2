package glg203.jpa2.criteria.model;

import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * Un des thèmes d'un cours.
 */
@Embeddable
public class Theme implements Comparable<Theme> {
    private String label;

    Theme() {
    }

    public Theme(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Theme label(String label) {
        this.label = label;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Theme)) {
            return false;
        }
        Theme theme = (Theme) o;
        return Objects.equals(label, theme.label);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(label);
    }

    @Override
    public String toString() {
        return "{" +
            " label='" + getLabel() + "'" +
            "}";
    }

    @Override
    public int compareTo(Theme o) {
        return label.compareTo(o.label);
    }
    
}