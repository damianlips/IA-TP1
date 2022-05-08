package etapa_tres;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import actions.*;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

public class PlantAgent extends SearchBasedAgent {

	
	public PlantAgent() {
		PlantGoal goal = new PlantGoal();
		PlantAgentState state = new PlantAgentState();
		state.initState();
		this.setAgentState(state);
		Vector<SearchAction> operators = new Vector<SearchAction>();
		operators.addElement(new PlantarGirasol());
		operators.addElement(new MatarZombieEnMismaCasilla());
		operators.addElement(new MatarZombieAbajo());
		operators.addElement(new MatarZombieArriba());
		operators.addElement(new MatarZombieDerecha());
		operators.addElement(new MatarZombieIzquierda());
		operators.addElement(new MoverseAbajo());
		operators.addElement(new MoverseArriba());
		operators.addElement(new MoverseIzquierda());
		operators.addElement(new MoverseDerecha());
		
		Problem problem = new Problem(goal, state, operators);
        this.setProblem(problem);
	}
	
	public PlantAgent(PlantAgentState state) {
		PlantGoal goal = new PlantGoal();
		this.setAgentState(state);
		Vector<SearchAction> operators = new Vector<SearchAction>();
		operators.addElement(new MatarZombieAbajo());
		operators.addElement(new MatarZombieArriba());
		operators.addElement(new MatarZombieDerecha());
		operators.addElement(new MatarZombieIzquierda());
		operators.addElement(new MoverseAbajo());
		operators.addElement(new MoverseArriba());
		operators.addElement(new MoverseIzquierda());
		operators.addElement(new MoverseDerecha());
		operators.addElement(new PlantarGirasol());
		
		Problem problem = new Problem(goal, state, operators);
        this.setProblem(problem);
	}
	
	@Override
	public void see(Perception p) {
		this.getAgentState().updateState(p);
	}

	@Override
	public Action selectAction() {
		// Create the search strategy
        //DepthFirstSearch strategy = new DepthFirstSearch();
		/*
		CostFunction cost = new CostFunction();
        Heuristic heuristic = new Heuristic();
        AStarSearch strategy = new AStarSearch(cost, heuristic);
	*/
		BreathFirstSearch strategy = new BreathFirstSearch();
        /**
         * Another search strategy examples:
         * 
         * Depth First Search:
         * DepthFirstSearch strategy = new DepthFirstSearch();
         * 
         * Breath First Search:
         * BreathFirstSearch strategy = new BreathFirstSearch();
         * 
         * Uniform Cost:
         * IStepCostFunction costFunction = new CostFunction();
         * UniformCostSearch strategy = new UniformCostSearch(costFunction);
         * 
         * A Star Search:
         * IStepCostFunction cost = new CostFunction();
         * IEstimatedCostFunction heuristic = new Heuristic();
         * AStarSearch strategy = new AStarSearch(cost, heuristic);
         * 
         * Greedy Search:
         * IEstimatedCostFunction heuristic = new Heuristic();
         * GreedySearch strategy = new GreedySearch(heuristic);
         */

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.EFAIA_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(PlantAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
	}

}
