package model;

public class Community {
    private String name;
    private CommunityType type; // no olviden esto
    private int population;
    private Manager manager;
    private ProblemTypes[] problems;

    public Community(String name, CommunityType type, int population, Manager manager, ProblemTypes[] problems) {
        this.name = name;
        this.type = type;
        this.population = population;
        this.manager = manager;
        this.problems = problems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommunityType getType() {
        return type;
    }

    public void setType(CommunityType type) {
        this.type = type;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ProblemTypes[] getProblems() {
        return problems;
    }

    public void setProblems(ProblemTypes[] problems) {
        this.problems = problems;
    }

    
}
