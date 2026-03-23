# TME 6-7

#### Q2
La double boucle consiste à itérer sur les différents pixels afin de les normaliser et y ajouter les couleurs. 

#### Q4
Non, nous pouvons observer des artefacts. L'image n'est pas nette.

Soient 2 processus T1 et T2.
Supposons que T1 est le thread s'exécutant actuellement, puis qu'après .setColor() avec la couleur rouge il y a commutation. T2 prend donc la main, et s'exécute jusqu'à la fin de la fonction en ayant mis la couleur bleu pour le pixel d'indice (x,y). Lorsque T1 revient, il va écraser le pixel bleu avec le pixel rouge. 

