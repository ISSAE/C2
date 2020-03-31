package glg203.jpa.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.derby.drda.NetworkServerControl;
import org.springframework.stereotype.Component;

/**
 * DBStarterComponent. Ce composant est chargé de démarrer les deux serveurs de
 * BD utilisés (ports 50033 et 50034)
 */
@Component
public class DBStarterComponent {

    int port1 = 50033;
    int port2 = 50034;

    NetworkServerControl server1, server2;

    @PostConstruct
    public void init() throws UnknownHostException, Exception {
        System.setProperty("derby.system.home", "tempdb");
        server1 = new NetworkServerControl(InetAddress.getByName("localhost"), port1, "db1", "db1");
        server2 = new NetworkServerControl(InetAddress.getByName("localhost"), port2, "db2", "db2");
        server1.start(null);
        server2.start(null);
    }

    @PreDestroy
    public void close() throws Exception {
        try {
            server1.shutdown();
        } finally {
            server2.shutdown();
        }
    }   
}