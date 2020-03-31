# Démo de Spring Data Rest

Spring Data Rest démarre automatiquement un service REST HATEOAS pour
les repositories Spring Data déclarés comme `@RepositoryRestResource`. 

**Sans configuration,** on obtient la page d'accueil suivante en visitant http://localhost:8080 :
~~~json
{
  "_links" : {
    "typeEntrees" : {
      "href" : "http://localhost:8080/typeEntrees{?page,size,sort}",
      "templated" : true
    },
    "contacts" : {
      "href" : "http://localhost:8080/contacts{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/profile"
    }
  }
}
~~~

la partie http://localhost:8080/profile donne des informations supplémentaires sur les éléments.

Un get sur les contacts donne :

~~~json
{
  "_embedded" : {
    "contacts" : [ {
      "nom" : "Graffion",
      "prenom" : "Pascal",
      "entrees" : [ {
        "valeur" : "3845",
        "_links" : {
          "typeEntree" : {
            "href" : "http://localhost:8080/typeEntrees/1"
          }
        }
      }, {
        "valeur" : "pascal@cnam.net",
        "_links" : {
          "typeEntree" : {
            "href" : "http://localhost:8080/typeEntrees/3"
          }
        }
      } ],
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/contacts/5"
        },
        "contact" : {
          "href" : "http://localhost:8080/contacts/5"
        }
      }
    }, {
      "nom" : "Rosmorduc",
      "prenom" : "Serge",
      "entrees" : [ {
        "valeur" : "8322",
        "_links" : {
          "typeEntree" : {
            "href" : "http://localhost:8080/typeEntrees/1"
          }
        }
      }, {
        "valeur" : "serge@cnam.net",
        "_links" : {
          "typeEntree" : {
            "href" : "http://localhost:8080/typeEntrees/3"
          }
        }
      }, {
        "valeur" : "2 rue Conté",
        "_links" : {
          "typeEntree" : {
            "href" : "http://localhost:8080/typeEntrees/4"
          }
        }
      } ],
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/contacts/6"
        },
        "contact" : {
          "href" : "http://localhost:8080/contacts/6"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/contacts{&sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/contacts"
    },
    "search" : {
      "href" : "http://localhost:8080/contacts/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 2,
    "totalPages" : 1,
    "number" : 0
  }
}
~~~

Création : 
~~~bash
DATA='{"nom" : "Bruyère","prenom" : "Stéphane","entrees" : [ {"valeur" : "stephane@cnam.fr","_links" : {"typeEntree" : {"href" : "http://localhost:8080/typeEntrees/3"}}}]}'
curl -X POST -H "Content-Type: application/json" -d "$DATA" http://localhost:8080/contacts
~~~
renvoie le lien vers le nouveau contact : http://localhost:8080/contacts/8

Modification :

~~~bash
DATA='{"nom" : "Bruyère","prenom" : "Stéphane","entrees" : [ {"valeur" : "bruyere@cnam.fr","_links" : {"typeEntree" : {"href" : "http://localhost:8080/typeEntrees/3"}}}]}'
curl -X PUT -H "Content-Type: application/json" -d "$DATA" http://localhost:8080/contacts/8
~~~

Suppression : 
~~~bash
curl -X DELETE -H http://localhost:8080/contacts/6
~~~

Recherche :
*il faut avoir annoté les paramètres des méthodes* :
~~~bash
curl -X GET http://localhost:8080/contacts/search/findByNom?nom=Graffion
~~~

Remarque : si une ressource n'est pas associée à un repository publié, ses valeurs seront incluses "inline".

Structure JSON :
~~~bash
curl -H 'Accept:application/schema+json' http://localhost:8080/profile/contacts
~~~

~~~json
{
  "title" : "Contact",
  "properties" : {
    "nom" : {
      "title" : "Nom",
      "readOnly" : false,
      "type" : "string"
    },
    "prenom" : {
      "title" : "Prenom",
      "readOnly" : false,
      "type" : "string"
    },
    "entrees" : {
      "title" : "Entrees",
      "readOnly" : false,
      "type" : "array",
      "items" : {
        "$ref" : "#/definitions/entree"
      }
    }
  },
  "definitions" : {
    "entree" : {
      "type" : "object",
      "properties" : {
        "valeur" : {
          "title" : "Valeur",
          "readOnly" : false,
          "type" : "string"
        },
        "typeEntree" : {
          "title" : "Type entree",
          "readOnly" : false,
          "type" : "string",
          "format" : "uri"
        }
      }
    }
  },
  "type" : "object",
  "$schema" : "http://json-schema.org/draft-04/schema#"
}
~~~


