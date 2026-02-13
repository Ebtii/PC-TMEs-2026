Complétez avec vos réponses.

# Q1

hash : 
Preparing to parse data/WarAndPeace.txt (mode=hash, N=4), containing 3235342 bytes
Total words: 565527
Unique words: 20332
34562 the
22148 and
16709 to
14990 of
10513 a
Total runtime: 820 ms for mode hash

partition : 
Preparing to parse data/WarAndPeace.txt (mode=partition, N=4), containing 3235342 bytes
Computed partition of 3235342 B  into 4 in 4 ms
Total words: 565527
Unique words: 20332
34562 the
22148 and
16709 to
14990 of
10513 a
Total runtime: 998 ms for mode partition


# Q3 

Preparing to parse data/WarAndPeace.txt (mode=naive, N=4), containing 3235342 bytes
Computed partition of 3235342 B  into 4 in 5 ms
Exception in thread "Thread-0" java.util.ConcurrentModificationException
	at java.base/java.util.HashMap.compute(HashMap.java:1325)
	at pc.NaiveWorker.run(NaiveWorker.java:32)
	at java.base/java.lang.Thread.run(Thread.java:1583)
Exception in thread "Thread-2" java.util.ConcurrentModificationException
	at java.base/java.util.HashMap.compute(HashMap.java:1325)
	at pc.NaiveWorker.run(NaiveWorker.java:32)
	at java.base/java.lang.Thread.run(Thread.java:1583)
Exception in thread "Thread-1" java.util.ConcurrentModificationException
	at java.base/java.util.HashMap.compute(HashMap.java:1325)
	at pc.NaiveWorker.run(NaiveWorker.java:32)
	at java.base/java.lang.Thread.run(Thread.java:1583)
Total words: 140633
Unique words: 14148
5530 and
4681 the
4107 the
3940 to
3873 of
Total runtime: 605 ms for mode naive

Nous constatons que le nombre de mots comptés varie entre chaque exécution : nous passons de 10533 à 9802 par exemple.

# Q5

Preparing to parse data/WarAndPeace.txt (mode=atomic, N=4), containing 3235342 bytes
Computed partition of 3235342 B  into 4 in 5 ms
Exception in thread "Thread-2" java.util.ConcurrentModificationException
	at java.base/java.util.HashMap.compute(HashMap.java:1325)
	at pc.WordFrequency.lambda$2(WordFrequency.java:104)
	at java.base/java.lang.Thread.run(Thread.java:1583)
Exception in thread "Thread-3" java.util.ConcurrentModificationException
	at java.base/java.util.HashMap.compute(HashMap.java:1325)
	at pc.WordFrequency.lambda$2(WordFrequency.java:104)
	at java.base/java.lang.Thread.run(Thread.java:1583)
Exception in thread "Thread-1" java.util.ConcurrentModificationException
	at java.base/java.util.HashMap.compute(HashMap.java:1325)
	at pc.WordFrequency.lambda$2(WordFrequency.java:104)
	at java.base/java.lang.Thread.run(Thread.java:1583)
Total words: 141314
Unique words: 9786
8532 the
5295 and
3968 to
3243 of
2751 a
Total runtime: 572 ms for mode atomic

Nous constatons que le nombre de mots comptés varie entre chaque exécution : nous passons de 10533 à 9802 par exemple. cela s'explique par le fait que la map reste non thread safe. 

# Q6

Preparing to parse data/WarAndPeace.txt (mode=synchronized, N=4), containing 3235342 bytes
Computed partition of 3235342 B  into 4 in 5 ms
Total words: 565527
Unique words: 20332
34562 the
22148 and
16709 to
14990 of
10513 a
Total runtime: 917 ms for mode synchronized

ces resultats coherents et correct s'explique par le fait que nous ayons rendu la map ET la section critique Thread safe. 

# Q7 

Nous avons implémenté "synchronized2" et nous avons confirmé que la version utilisant get/put reste correcte. 

En effet, le synchronnized(map) garantit qu'un seul thread à la fois exécute le bloc qu'il entoure pendant que les autres attendent. 

Preparing to parse data/WarAndPeace.txt (mode=synchronized2, N=4), containing 3235342 bytes
Computed partition of 3235342 B  into 4 in 5 ms
Total words: 565527
Unique words: 20332
34562 the
22148 and
16709 to
14990 of
10513 a
Total runtime: 964 ms for mode synchronized2

# Q9 

Preparing to parse data/WarAndPeace.txt (mode=decorated, N=4), containing 3235342 bytes
Computed partition of 3235342 B  into 4 in 5 ms
Total words: 565527
Unique words: 20332
34562 the
22148 and
16709 to
14990 of
10513 a
Total runtime: 897 ms for mode decorated

les resultats sont correct et coherents car la map a été decorée de maniere a assuré la synchronisation des qu'elle est declaré ou initialisée. 

# Q10 

Preparing to parse data/WarAndPeace.txt (mode=decorated, N=4), containing 3235342 bytes
Computed partition of 3235342 B  into 4 in 5 ms
Total words: 565527
Unique words: 20332
33923 the
21832 and
16567 to
14904 of
10457 a
Total runtime: 941 ms for mode decorated

Nous avons implémenté "decorated" et nous avons confirmé que la version utilisant get/put reste correcte. 

En effet, le Collections.synchronnizedMap(map) garantit qu'un seul thread à la fois exécute le bloc qu'il entoure pendant que les autres attendent. 






