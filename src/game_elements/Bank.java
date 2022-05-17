package game_elements;

public class Bank extends GameElement {

    int starterMoney;

    public void moneyDistribution(Player P, int M) {
        P.setMoney(M);
    }

    public void bidding() {}
}
