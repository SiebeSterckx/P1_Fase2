package domain.model;

public enum State {
    ACCEPTED("Accepted"), PENDING("Pending"), REJECTED("Rejected");

    private String name;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
