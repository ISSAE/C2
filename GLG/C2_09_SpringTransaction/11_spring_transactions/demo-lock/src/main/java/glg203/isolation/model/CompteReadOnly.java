package glg203.isolation.model;

/**
 * CompteReadOnly.
 * Interface des comptes en lecture seule.
 * 
 * <p> Spring/jpa est capable, non seulement de retourner des entités, mais, si on le demande,
 * de retourner des DTO. On peut les créer de plusieurs manière, mais la plus simple est de définir des 
 * interfaces.
 * 
 * <p> Ici, on définit une interface avec uniquement des getters. Spring renverra des DTO ne contenant 
 * que ces propriétés.
 * 
 * <p> Un avantage secondaire ici : on ne risque pas de modifier les objets reçus par mégarde.
 */
public interface CompteReadOnly {
    int getId();
    int getSolde();    
}