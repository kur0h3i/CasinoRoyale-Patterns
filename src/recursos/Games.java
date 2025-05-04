package recursos;

public enum Games {
    SLOTS("Slots"), RULETA("Ruleta"), BINGO("Bingo"), DADOS("Dados"), CARTAMASALTA("CartaMasAlta");

    private String games;

    private Games(String games){
        this.games = games;
    }

    // Getter
    public String getGames(){ return games; }
}
