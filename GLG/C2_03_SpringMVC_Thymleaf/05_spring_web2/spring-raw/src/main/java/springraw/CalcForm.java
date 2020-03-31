package springraw;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Représentation objet des données du formulaire.
 */
public class CalcForm {

	@Min(value = 0)
	@NotNull
	private Integer a;


	@Min(value = 0)
	@NotNull
	private Integer b;

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	/**
	 * Méthode métier (qui devrait être dans la couche en question,
	 * mais bon, là ce serait de l'acharnement architectural).
	 * @return
	 */
	public int sum() {
		return a + b;
	}
}
