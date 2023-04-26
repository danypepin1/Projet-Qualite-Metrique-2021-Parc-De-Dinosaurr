package ca.ulaval.glo4002.game.resource.entities.enums;

public enum ResourceType {
    BURGER(4),
    SALAD(3),
    WATER(10);

    private final int shelfLife;

    ResourceType(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public int getShelfLife() {
        return shelfLife;
    }
}
