package fluentInterface;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Un builder d'URL fluide.
 * 
 * à chaque étape de la construction, il ne propose que les modifications
 * pertinentes, guidant ainsi le programmeur.
 * 
 * On peut essayer de réduire la répétitivité du code dans cette classe (par
 * exemple la méthode build qui est répétée)
 */
public class URLBuilder {

    private String protocol = "http";
    private String server = "";
    private int port = 80;
    private List<String> path = new ArrayList<>();
    private LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

    public URLBuilder protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public ServerPart server(String server) {
        this.server = server;
        return new ServerPart();
    }

    /**
     * Méthode appelée à la fin pour construire le résultat. Cette méthode est
     * privée, car elle ne peut être appelée que quand on a au moins spécifié le
     * serveur.
     */
    private MyUrl build() {
        return new MyUrl(protocol, server, port, path, parameters);
    }

    public class ServerPart {
        public ServerPart port(int port) {
            URLBuilder.this.port = port;
            return this;
        }

        public PathPart addPath(String pathPart) {
            URLBuilder.this.path.add(pathPart);
            return new PathPart();
        }

        public ParameterPart addParameter(String name, String value) {
            URLBuilder.this.parameters.put(name, value);
            return new ParameterPart();
        }

        public MyUrl build() {
            return URLBuilder.this.build();
        }
    }

    public class PathPart {
        public PathPart addPath(String pathPart) {
            URLBuilder.this.path.add(pathPart);
            return this;
        }

        public ParameterPart addParameter(String name, String value) {
            URLBuilder.this.parameters.put(name, value);
            return new ParameterPart();
        }

        public MyUrl build() {
            return URLBuilder.this.build();
        }
    }

    public class ParameterPart {
        public ParameterPart addParameter(String name, String value) {
            URLBuilder.this.parameters.put(name, value);
            return this;
        }

        public MyUrl build() {
            return URLBuilder.this.build();
        }
    }

}