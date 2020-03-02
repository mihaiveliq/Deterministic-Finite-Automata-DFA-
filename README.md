# Deterministic-Finite-Automata-DFA-

    AFD.java - modelarea automatului sub forma de graf + metode care il
                caracterizeaza
                
    FinalStates - modeleaza o stare finala
    
    Cell.java - modeleaza o stare din automat
    
    Neighbour.java - modeleaza o stare vecina in graf
    
    Transition.java - retine in 3 liste pe pozitii corespondente:
    
        starea din care vin, simbolul de trecere in stare,
        starea in care ma duc
        
Rezolvare Task:

    -e -> verific daca in lista de stari finale se afla starea initiala
    
    -a -> am facut o parcurgere DFS din starea initiala si verific daca e in
        lista de stari finale
        
    -u -> fac o parcurgere DFS din fiecare stare si verific daca se viziteaza
        o stare din vectorul de finale
        
    -v -> verific cele 3 conditii conform indicatiilor folosind functiile
        utilizate la taskurile -a si -u
        
    -f -> verific daca exista un ciclu dintr-o stare utila
