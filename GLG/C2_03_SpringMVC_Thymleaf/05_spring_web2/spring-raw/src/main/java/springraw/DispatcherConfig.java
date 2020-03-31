package springraw;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Configuration de la servlet front-end RequestDispatcher.
 */
public class DispatcherConfig extends AbstractAnnotationConfigDispatcherServletInitializer
{

	/**
	 * Met en place les classes de configuration Spring.
	 * On peut aussi y placer la configuration web si elle ne correspond qu'à une seule dispatcher servlet.
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.err.println("Chargement des fichiers de configuration");
		return new Class[] {MyAppConfig.class};
	}

	/**
	 * Utile quand on a plusieurs servlets front-end séparées qui partagent les même données root.
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.err.println("Chargement des fichiers de configuration web");
		return null;
	}
	
	/**
	 * Mapping associé à la dispatcher servlet.
	 * 
	 * @return
	 */
	@Override
	protected String[] getServletMappings() {
		System.err.println("Url de base pour les contrôleurs");
		return new String[] {"/"};
	}
}
