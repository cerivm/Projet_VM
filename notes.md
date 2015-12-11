# Notes about the project

## The team

- HAMIDANE azzelarab: azzelarabh@gmail.com
- ELOUARDI mohammed: elouardi.mohamed@gmail.com


## Comments

Présentation :

          -  Le but de projet et de développer un ensemble d'ordonnanceurs afin de gérer l'allocation des hyperviseurs pour les VM, sur une plateforme de simulation de type IaaS Cloud "CloudSIM".
          
1-   Support for Highly-Available applications

          - L'importance de cet ordonnanceur et d'avoir une haute disponibilité pour les VMs, et qu'elles soient tolérantes aux pannes, donc des copies de VMs dans plusieurs serveurs.
          
          - L'algorithme implémenté : 
          
                  *   L'idée suggérée est de ne pas avoir plus qu'une VM du même intervalle dans chaque serveur, donc pour dispatcher les machines virtuelles dans les différents serveurs, Nous parcourions les serveurs et sur chaque serveur on fait un check si la divion par 100 des IDs des VMs déja existantes dans le serveur est différente de la division de l'ID de la nouvelle VM.
                  
          - What is the impact of such an algorithm over the cluster hosting capacity ?
          
                  *   Cet algorithme utilise approximativement la majorité des hyperviseurs existants dans le DataCenter, mais par contre chaque serveur dans le cluster n'utilise pas forcément la totalité de ces ressources.
                  
2- Balance the load 

          - L'équilbrage de charge dans le datacenter joue un role très important au niveau de l'haute disponibilité des VMs
          
          - Which algorithms performs the best in terms of reducing the SLA violation. Why ? les deux ordonnanceurss "balance flag" et "balance flag RAM / MIPS" sont très efficaces pour réduire les pénalités "SLA Violations", voici le resultat : "penalities :  0.01 €".
          
3- Get rid of SLA violations

          - Pour cette partie il faut définir un seuil pour chaque serveur dans le cluster pour donner la possibilité à chaque VM de récuperer à tout moment les MIPS.
          
4- Energy-efficient schedulers

          - static version : pour pouvoir réduire la consomation d'energie au sein du datacenter, on peut saturer un ensemble de serveur et garder le reste des serveurs non-utilisés, sion on peut définir un seuil d'utilisation des ressources pour chaque serveur pour ne pas avoir des consomations excessives dues à l'utilisation totale des ressources d'un serveur.
          
Conclusion

   Le TP était une vrai opportnité pour profondir nos connaissances acquises durant le cours. On tient à remercie Monsieur  Hermenier Fabien  pour durant les séances de TP.

