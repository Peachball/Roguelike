package game.items.consumables;

public enum ConsumableList {
    HEALTH_POTION;
    
    public Consumable getConsumable(ConsumableList id){
        switch(id){
            case HEALTH_POTION:
                return new HealthPotion();
        }
        return null;
        
    }
}
