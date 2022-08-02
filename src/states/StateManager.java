package states;

import java.util.Stack;

public class StateManager {
    private Stack<State> states; 

    public StateManager() {
        states = new Stack<State>();
    }

    public State currentState() {
        return states.peek();
    }

    public void addState(State state) {
        state.initialise();
        states.push(state);
    }
    
    public void pop() {
        states.pop();
    }
}
