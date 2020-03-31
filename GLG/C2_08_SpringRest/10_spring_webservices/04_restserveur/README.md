# Serveur REST très simple, surtout pour la démo du client.

API :

- http://localhost:8080/api/contact (méthode GET et POST)
- http://localhost:8080/api/contact/1 (méthode GET)

Utilisation avec `curl` :

## GET

    curl -X GET  -H "Accept: application/json" http://localhost:8080/api/contact
    curl -X GET  -H "Accept: application/json" http://localhost:8080/api/contact/2

## POST
    curl -X POST -H "Content-type: application/json" -d '{"nom": "test", "telephone": "00"}'
    curl -X GET  -H "Accept: application/json" http://localhost:8080/api/contact

