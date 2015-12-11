# Notes about the project

## The team

- HAMIDANE azzelarab: azzelarabh@gmail.com
- ELOUARDI mohammed: elouardi.mohamed@gmail.com


## Comments

Présentation :

          -  Le but de projet et de développer un ensemble d'ordonnanceurs afin de gérer l'allocation des hyperviseurs pour les              VM, sur une plateforme de simulation de type IaaS Cloud "CloudSIM".
          
1-   Support for Highly-Available applications

          - L'importance de cet ordonnanceur et d'avoir une haute disponibilité pour les VMs, et qu'elles soient tolérantes aux pannes, donc des copies de VMs dans plusieurs serveurs.
          
          - L'algorithme implémenté : 
          
                  *   L'idée suggérée est de ne pas avoir plus qu'une VM du même intervalle dans chaque serveur, donc pour                           dispatcher les machines virtuelles dans les différents serveurs, Nous parcourions les serveurs et sur                          chaque serveur on fait un check si la divion par 100 des IDs des VMs déja existantes dans le serveur est                       différente de la division de l'ID de la nouvelle VM.
                  
          - What is the impact of such an algorithm over the cluster hosting capacity ?
          
                  *   
2- Balance the load 

              

