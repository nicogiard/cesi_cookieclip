package models.types;

public enum EBooster {

    CLICKER(10L),
    GRANDMA(1000L);

    private long cost;

    EBooster(long cost) {
        this.cost = cost;
    }

    public Long getCost() {
        return cost;
    }
}
