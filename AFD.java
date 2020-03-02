import java.util.*;

public class AFD {
    List<Cell> states;
    String firstState;
    List<FinalStates> lastStates;
    List<Character> sigma;

    AFD() {
        this.states = new ArrayList<>();
        this.sigma = new ArrayList<>();
        this.lastStates = new ArrayList<>();
    }

    public void buildAFD(List<String> states, Transition transitions,
                         List<Character> sigma, String firstState,
                         List<String> lastStates) {
        //states
        for(String i : states) {
            this.states.add(new Cell(i));
        }

        for (int i = 0; i < states.size(); ++i) {
            this.states.get(i).index = i;
        }

        for (Cell state : this.states) {
            for (int i = 0; i < transitions.from.size(); ++i) {
                if (transitions.from.get(i).equals(state.name)) {
                    Neighbour neigh = new Neighbour(transitions.to.get(i),
                            transitions.symbol.get(i));
                    for (Cell tmp : this.states) {
                        if (tmp.name.equals(neigh.to)) {
                            neigh.index = tmp.index;
                            break;
                        }
                    }
                    state.neighbours.add(neigh);
                }
            }
        }

        //sigma
        this.sigma.addAll(sigma);

        //first state
        this.firstState = firstState;

        //last states
        for (String names : lastStates) {
            FinalStates fin = new FinalStates();
            for (Cell tmp : this.states) {
                if (tmp.name.equals(names)) {
                    fin.index = tmp.index;
                    tmp.isFinal = true;
                    break;
                }
            }
            fin.name = names;
            this.lastStates.add(fin);
        }
    }

    // A function used by DFS
    void DFSUtil(int v, boolean[] visited)
    {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex

        Iterator<Neighbour> i = states.get(v).neighbours.listIterator();
        /*for (Cell aux : states) {
            if (aux.index == v) {
                i = aux.neighbours.listIterator();
            }
        }*/

        while (i.hasNext())
        {
            Neighbour n = i.next();
            if (!visited[n.index])
                DFSUtil(n.index, visited);
        }
    }

    // The function to do DFS traversal. It uses recursive DFSUtil()
    boolean[] DFS(int v)
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean[] visited = new boolean[states.size()];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);

        return visited;
    }

    boolean cycleDFS(int v) {
        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[states.size()];

        visited[v] = true;

        for (Neighbour neigh : states.get(v).neighbours) {
            stack.push(neigh.index);
        }

        while(!stack.empty()) {
            int i = stack.pop();
            if (i == v) {
                return true;
            }
            if(visited[i]) {
                continue;
            }

            visited[i] = true;

            for (Neighbour neigh : states.get(i).neighbours) {
                stack.push(neigh.index);
            }
        }


        return false;
    }

    public void isAccessible() {
        boolean[] visited;

        for (Cell aux : states) {
            if (aux.name.equals(firstState)) {
                visited = DFS(aux.index);
                for (int i = 0; i < states.size(); ++i) {
                    if (visited[i]) {
                        states.get(i).isAccessible = true;
                    }
                }
            }
        }
    }

    public void printAccessible() {
        boolean[] visited;

        for (Cell aux : states) {
            if (aux.name.equals(firstState)) {
                visited = DFS(aux.index);
                for (int i = 0; i < states.size(); ++i) {
                    if (visited[i]) {
                        states.get(i).isAccessible = true;
                        System.out.println(states.get(i).name);
                    }
                }
            }
        }
    }

    public void printUseful() {
        isAccessible();
        boolean[] visited;

        for (Cell j : states) {
            visited = DFS(j.index);
            for (FinalStates i : lastStates) {
                if (visited[i.index]) {
                    j.isProductive = true;
                    if (j.isAccessible) {
                        System.out.println(j.name);
                        break;
                    }
                }
            }
        }
    }

    public void isUseful() {
        isAccessible();
        boolean[] visited;

        for (Cell j : states) {
            visited = DFS(j.index);
            for (FinalStates i : lastStates) {
                if (visited[i.index]) {
                    j.isProductive = true;
                    if (j.isAccessible) {
                        j.isUseful = true;
                        break;
                    }
                }
            }
        }
    }

    public void isEmpty() {
        isUseful();
        boolean is = true;
        for (Cell i : states) {
            if (i.isFinal) {
                if (i.isAccessible) {
                    is = false;
                    System.out.println("No");
                    break;
                }
            }
            if (i.isUseful) {
                is = false;
                System.out.println("No");
                break;
            }
            if (i.name.equals(firstState)) {
                if (i.isProductive) {
                    is = false;
                    System.out.println("No");
                    break;
                }
            }
        }
        if (is) {
            System.out.println("Yes");
        }
    }

    public void isFinite() {
        isUseful();

        for (Cell aux : states) {
            if (aux.isUseful) {
                if(cycleDFS(aux.index)) {
                    System.out.println("No");
                    return;
                }
            }
        }

        System.out.println("Yes");
    }

    public  void hasE() {
        boolean has = false;
        for (FinalStates i : lastStates) {
            if (i.name.equals(firstState)) {
                has = true;
                System.out.println("Yes");
            }
        }
        if (!has) {
            System.out.println("No");
        }
    }
}
