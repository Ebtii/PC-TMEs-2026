
# completer ce fichier avec vos réponses aux questions sans code

# Q4 

Le problème que nous pouvons rencontrer porte sur le fait que l'execution se fait en parallèle. En effet, si le nombre de baguettes est identique au nombre de philosophes, alors,lors de l'execution, chaque philosophe prendra une baguette ( il n'en restera donc plus) et ne la deposera qu'a la conditions d'avoir manger or pour cela il a besoin d'une seconde baquette. nou somme donc dans une situation d'interblocage.  

# Q5 

Le philosophe ne respectant pas l'ordre naturel d'acquisiton est le (N-1)ème philosophe (le dernier). 

# Q7

Non, car en cas d'interruption, le philosophe ne dépose pas sa baguette. Il s'interrompt en l'ayant toutjours en sa possession. 
Pour résoudre ce problème nous pourrions utiliser la fonction lockInterruptibly() qui permet d'acquérir le lock tant que le thread n'est pas interrompu. Dans le cas contraire, une InterruptedException est levée. 