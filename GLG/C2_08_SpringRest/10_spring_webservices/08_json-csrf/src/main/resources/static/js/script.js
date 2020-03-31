/**
 * Code Javascript pour expédier et lire les données.
 */

var user = null

function byId(id) {
    return document.getElementById(id)
}

function sel(selector) {
    return document.querySelector(selector)
}



/**
 * Rend visible (ou invisible) les éléments de formulaires 
 * selon l'état de l'application.
 */
function mettreAJourVisibilites() {
    if (user) {
        byId("loginForm").style.display = "none"
        byId("logoutForm").style.display = "block"
        byId("sendForm").style.display = "block"
    } else {
        byId("loginForm").style.display = "block"
        byId("logoutForm").style.display = "none"
        byId("sendForm").style.display = "none"
    }    
}

function getMessage() {
    // Version avec des promise explicites
    axios.get("/api/message").then(
        function (value) {
            byId("message").textContent = value.data
        }
    )
}

// Fonction asynchrone. 
// évite de gérer expressément les promises
// Retourne {token: "...", parameterName: "X-CSRF-TOKEN"}
async function csrfPromise() {
    console.log("On appelle csrf promise")
    return axios.get("/csrf")
}

function clearErreur() {
    byId("erreur").textContent = ""
}

async function putMessage() {
    clearErreur()
    var csrf = (await csrfPromise()).data;
    headers = {}
    headers[csrf.headerName] = csrf.token
    axios({
        method: 'put',
        url: "/api/message",
        params:
        {
            "message":
                sel("#sendForm input").value,
        },
        headers: headers
    }
    ).then(
        function () {
            getMessage()
        }
    ).catch(
        function (error) {
            byId("erreur").textContent = "connectez-vous !!!"
        }
    )
}

async function doLogin() {
    clearErreur()
    var csrf = (await csrfPromise()).data;
    let headers = {}
    headers[csrf.headerName] = csrf.token
    let login = byId("login").value
    let password = byId("password").value
    axios({
        method: 'post',
        url: "/login",
        params:
        {
            "username": login,
            "password": password
        },
        headers: headers
    }).then(function () {
        user = login
        mettreAJourVisibilites()
    }).catch(function (erreur) {
        byId("erreur").textContent = "Connexion refusée"
    })
}

async function doLogout() {
    clearErreur()
    var csrf = (await csrfPromise()).data;
    user = null
    let headers = {}
    headers[csrf.headerName] = csrf.token
    axios({
        method: 'post',
        url: "/logout",             
        headers: headers
    }).then(function () {        
        mettreAJourVisibilites()
    })
}

function initPage() {
    sel(".message button").onclick = getMessage
    sel("#loginForm button").onclick = doLogin
    sel("#logoutForm button").onclick = doLogout
    sel("#sendForm button").onclick = putMessage
    mettreAJourVisibilites()
    getMessage()
}