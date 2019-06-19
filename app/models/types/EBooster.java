package models.types;

public enum EBooster {

    CLICKER(10, 4),
    GRANDMA(1000, 6);

    private int cost;
    private int multiplier;

    EBooster(int cost, int multiplier) {
        this.cost = cost;
        this.multiplier = multiplier;
    }

    public int getCost() {
        return cost;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
