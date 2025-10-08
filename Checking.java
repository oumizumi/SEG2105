public class Checking extends Account {
    private String nickname;

    public Checking(Client owner, String nickname) {
        super(owner); /// call Account constructor to link this checking account to its Client

        this.nickname = nickname;
    }
}
