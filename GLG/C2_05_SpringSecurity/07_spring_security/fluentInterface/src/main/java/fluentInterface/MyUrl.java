package fluentInterface;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Une classe représenant une URL.
 * <p>
 * Une fois l'objet créé, on obtient le texte de l'URL avec toString().
 * <p>
 * Note : cette classe pourrait être améliorée (elle ou son builder). Par
 * exemple, on pourrait gérer les ports par défaut des protocoles.
 */
public class MyUrl {
    private final String protocol;
    private final String server;
    private final int port;
    private final List<String> path;
    private final LinkedHashMap<String, String> parameters;

    public MyUrl(String protocol, String server, int port, List<String> path,
            LinkedHashMap<String, String> parameters) {
        this.protocol = protocol;
        this.server = server;
        this.port = port;
        this.path = path;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(protocol).append("://");
            builder.append(server).append(':');
            builder.append(port).append("/");
            builder.append(String.join("/", path));
            if (!parameters.isEmpty()) {
                builder.append('?');
                List<String> paramStringList = new ArrayList<>();
                for (Entry<String, String> e : parameters.entrySet()) {

                    String paramCouple = e.getKey() + '=' + URLEncoder.encode(e.getValue(), "UTF-8");
                    paramStringList.add(paramCouple);
                }
                builder.append(String.join("&", paramStringList));
            }
            return builder.toString();
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

}