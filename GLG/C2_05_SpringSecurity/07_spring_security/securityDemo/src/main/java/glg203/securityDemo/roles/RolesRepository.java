package glg203.securityDemo.roles;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Répertoire des rôles définis.
 */
public class RolesRepository {

    public static final SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
    public static final SimpleGrantedAuthority USER = new SimpleGrantedAuthority("ROLE_USER");
}