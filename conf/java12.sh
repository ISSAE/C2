# definir cette variable au répertoire ou vous avez decider d'installer les
# Ligiciel pour vos ED TP projets
export C2INSTALL=$HOME

# Le reste sera basé sur $C2INSTALL

# Java
export JAVA_HOME="$C2INSTALL/jdk-12"
export PATH=$JAVA_HOME/bin:$PATH

# Netbeans
export NETBEANS_HOME="$C2INSTALL/netbeans"

# maven
export MAVEN_HOME="$NETBEANS_HOME/java/maven"
export PATH=$MAVEN_HOME/bin:$PATH