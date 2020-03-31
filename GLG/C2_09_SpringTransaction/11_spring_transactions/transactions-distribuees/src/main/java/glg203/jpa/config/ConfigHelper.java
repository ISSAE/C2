package glg203.jpa.config;

import java.util.Properties;

import javax.sql.DataSource;


// Dans mon code d'origine, j'utilisais cet import.
// import com.atomikos.jdbc.AtomikosDataSourceBean;
// à la place de celui-ci :
// L'utilisation de org.springframework.boot.jta.atomikos.AtomikosDataSourceBean 
// facilite pas mal le codage (mais le choix effectué d'utiliser le même nom
// pour les deux classes me semble une mauvaise idée).
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Squelette des initialisations à utiliser pour les deux bases de données.
 * 
 */
public class ConfigHelper {
    
    /**
     * Renvoie un datasource pour se connecter à la base dont on fourni le nom et le port.
     * mot de passe, etc... sont égaux au nom de la base.
     * <p> Important : lire les commentaires pour comprendre quelques pièges qui
     * peuvent bien vous gâcher la vie.
     * @param dbName
     * @param dbPort
     * @return
     */
    public static DataSource creerDataSource(String dbName, String dbPort) {           
            Properties prop = new Properties();
            prop.put("user", dbName);
            prop.put("password", dbName);
            prop.put("databaseName", dbName);
            prop.setProperty("serverName", "localhost");
            prop.setProperty("portNumber", dbPort);
            prop.put("createDatabase", "create");
    
            // On utilise ici la version fournie par SpringFramework de l'AtomikosDataSourceBean.
            // (voir les imports)
            // 
            // Cette version a plusieurs avantages :
            // a) elle paramètre toute seule le uniqueResourceName (ce qui nous a permis de commenter le code qui
            //    le faisait à l'origine.)
            // b) l'objet créé implémente InitializingBean et DisposableBean. 
            //    quand il est utilisé comme bean Spring, ses méthodes afterPropertiesSet et destroy
            //    sont donc automatiquement appelées. 
            //    sans ça, il fallait configurer l'annotation @Bean en conséquence :
            //    @Bean(initMethod = "init", destroyMethod = "close")
            //    
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            // String uniqueResourceName = dbName.toUpperCase();
            // dataSource.setUniqueResourceName(uniqueResourceName); pas nécessaire, car on utilise
            // la version Spring de AtomikosDataSourceBean, qui fixe précisément le uniqueResourceName au
            // nom Spring du bean.
            dataSource.setXaDataSourceClassName("org.apache.derby.jdbc.ClientXADataSource");
            dataSource.setXaProperties(prop);
            // dataSource.setPoolSize(1);
            return dataSource;
        }
    

    public static LocalContainerEntityManagerFactoryBean creerEntityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("glg203.jpa.model");
        factory.setDataSource(dataSource);
        
        // Le code qui suit est crucial avec Hibernate :
        // sinon, celui-ci ne comprend pas ce qui se passe et aucune transaction n'a lieu.
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.transaction.coordinator_class", "jta");
        factory.setJpaProperties(jpaProperties);
        
        return factory;
    }
    
}