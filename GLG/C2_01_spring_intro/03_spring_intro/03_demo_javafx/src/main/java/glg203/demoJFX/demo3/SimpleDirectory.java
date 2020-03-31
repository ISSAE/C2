package glg203.demoJFX.demo3;

import java.util.HashMap;
import java.util.Map;

/**
 * Un annuaire d'objets permettant de transmettre ceux-ci facilement.
 * <p> Dans cet annuaire, les objets sont enregistrés par nom.
 * <p> L'annuaire lui-même est un singleton.
 * @author rosmord
 */
public class SimpleDirectory {
    private static final SimpleDirectory instance= new SimpleDirectory();

    public static SimpleDirectory getInstance() {
        return instance;
    }
    
    private Map<String,Object> map= new HashMap<>();
    
    private SimpleDirectory() {}
    
    public void register(String nom, Object o) {
        map.put(nom, o);
    }
    
    public<T> T lookup(String nom, Class<T> clazz) {
        return (T) map.get(nom);
    }
}
