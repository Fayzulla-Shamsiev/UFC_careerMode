class Fighter {
    String name;
    int striking;
    int grappling;
    int stamina;
    String fightingStyle;

    public Fighter(String name, int striking, int grappling, int stamina, String fightingStyle) {
        this.name = name;
        this.striking = striking;
        this.grappling = grappling;
        this.stamina = stamina;
        this.fightingStyle = fightingStyle;
    }

    public void displayDetails() {
        System.out.println("Full name: " + name);
        System.out.println("Striking: " + striking);
        System.out.println("Grappling: " + grappling);
        System.out.println("Stamina: " + stamina);
        System.out.println("Fighting style: " + fightingStyle);
    }

    public void train(String attribute) {
        switch (attribute.toLowerCase()) {
            case "striking":
                striking = Math.min(striking + 5, 100); // Ensure the attribute doesn't exceed 100
                System.out.println("Your striking has improved!");
                break;
            case "grappling":
                grappling = Math.min(grappling + 5, 100);
                System.out.println("Your grappling has improved!");
                break;
            case "stamina":
                stamina = Math.min(stamina + 5, 100);
                System.out.println("Your stamina has improved!");
                break;
            default:
                System.out.println("Invalid training option.");
        }
    }
}