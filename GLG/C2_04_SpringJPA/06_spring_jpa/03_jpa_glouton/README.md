# Comportement "Glouton" ou non.

Démontre le N+1 Select et la manière de l'éviter.

Deux logiciels à lancer... regarder les "select" dans la sortie...

Avec `NplusUnSelect` :

~~~~~shell
gradle run --args="paresseux" 2>&1 | grep select | wc
       6     136    1541
~~~~~
six select : un pour les cours, plus un par cours.

Plus exactement :
~~~~~sql
select cours0_.id as id1_0_, cours0_.label as label2_0_ from Cours cours0_
~~~~~

et, pour chaque cours :
~~~~~sql
select etudiants0_.Cours_id as Cours_id1_1_0_, etudiants0_.etudiants_id as etudiant2_1_0_, 
        etudiant1_.id as id1_2_1_, etudiant1_.nom as nom2_2_1_ 
from Cours_Etudiant etudiants0_ inner join Etudiant etudiant1_ 
on etudiants0_.etudiants_id=etudiant1_.id 
where etudiants0_.Cours_id=?
~~~~~

Avec `SelectGlouton` :

~~~~~shell
gradle run --args="glouton" 2>&1 | grep select | wc
       1      38     397
~~~~~
un seul select :
~~~~~sql
select distinct cours0_.id as id1_0_0_, 
                etudiant2_.id as id1_2_1_, 
                cours0_.label as label2_0_0_,
                etudiant2_.nom as nom2_2_1_,
                etudiants1_.Cours_id as Cours_id1_1_0__, 
                etudiants1_.etudiants_id as etudiant2_1_0__ 
from Cours cours0_ left outer join Cours_Etudiant etudiants1_ 
                   on cours0_.id=etudiants1_.Cours_id 
                   left outer join Etudiant etudiant2_ 
                   on etudiants1_.etudiants_id=etudiant2_.id
~~~~~
