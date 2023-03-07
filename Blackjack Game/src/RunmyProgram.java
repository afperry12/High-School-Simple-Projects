 class RunMyProgram {

    public static void main(String[] args) {
        Blackjack game1;
        game1 = new Blackjack(true);
        new Thread(game1).run();
    }
}
