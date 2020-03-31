<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Notifications asynchrones</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>Exemple pris sur             
            <a href="http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/async-servlet/async-servlets.html">Using Asynchronous Servlets for Web Push Notifications</a>
        </div>


        <div style="width: 20em; float: right; border-left: solid 1pt gray; padding: 1ex;">
            <p><em>Principe</em> : lors du premier affichage de la
                page, celle-ci affiche la liste des messages en utilisant le bean 
                application "messages".</p>
            <p>Pour la suite : </p>
            <ul>
                <li>Toutes les secondes, <strong>si on n'est pas déjà en attente,</strong>
                    on envoie une requête GET. Celle-ci sera traitée de manière asynchrone
                    (dans l'immédiat, tout ce qui se passe, c'est la création d'un AsyncContext, qui sera 
                    conservé jusqu'à ce qu'il soit utilisé lors du prochain POST).
                </li>
                <li>Quand on envoie un nouveau message, on exécute un POST ; la liste "messages" est 
                    alors mise à jour (ce qui ne sera utilisé que par les nouvelles connexions au site). Mais surtout, 
                    tous les AsyncMessages en attente sont renvoyés avec le texte du nouveau message.</li>
            </ul>
            <p> Le logiciel actuel évite de surcharger le serveur : un client ne fait qu'une requête à la fois.
                Il n'est pas très robuste, cependant. Si un message est posté avant que getMessage ne soit rappelé, 
                ce message passera à la trappe (essayez en fixant l'intervalle de getMessage 
                à une valeur de 20000 pour 20 seconde).                
            </p>
            <p> Une solution plus robuste serait, pour chaque requête, de connaître le numéro du dernier message
                affiché par la page actuelle... ou tout bêtement de tout réafficher, mais ce sera plus coûteux.</p>
        </div>

        <h1>Messages !</h1>
        nom <input type="text" name="name" id="name"/> <br/>
        message <input type="text" name="message" id="message"/><br/>
        <button onclick="envoyer()">envoyer</button>

        <h1>Messages actuels</h1>
        <div id="content">
            <c:if test="${not empty messages}">
                ${messages} <!-- pas beau du tout !!!-->
            </c:if>
        </div>

        <script>
            function envoyer() {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "shout?t=" + new Date(), false);
                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                var nameText = escape(document.getElementById("name").value);
                var messageText = escape(document.getElementById("message").value);
                document.getElementById("message").value = "";
                xmlhttp.send("name=" + nameText + "&message=" + messageText);
            }

            var waitingMessages = false;
            function getMessages() {
                if (!waitingMessages) {
                    waitingMessages = true;
                    var xmlhttp = new XMLHttpRequest();
                    xmlhttp.onreadystatechange = function () {
                        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                            waitingMessages = false;
                            var contentElement = document.getElementById("content");
                            contentElement.innerHTML = xmlhttp.responseText + contentElement.innerHTML;
                        }
                    };
                    xmlhttp.open("GET", "shout?t=" + new Date(), true);
                    xmlhttp.send();
                }
            }
            setInterval(getMessages, 1000);
        </script>

    </body>
</html>
