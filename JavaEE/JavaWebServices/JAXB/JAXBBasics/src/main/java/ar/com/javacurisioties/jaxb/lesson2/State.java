package ar.com.javacurisioties.jaxb.lesson2;

public class State {

    long statePopulation;
    private String stateName;

    public State() {
    }

    public State(String stateName, long statePopulation) {
        super();
        this.stateName = stateName;
        this.statePopulation = statePopulation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public long getStatePopulation() {
        return statePopulation;
    }

    public void setStatePopulation(long statePopulation) {
        this.statePopulation = statePopulation;
    }
}