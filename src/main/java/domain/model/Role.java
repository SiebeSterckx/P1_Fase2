package domain.model;

public enum Role {

    AANBIEDER("aanbieder"),
    VERDELER("verdeler"),
    MODERATOR("moderator");

    private final String stringValue;

    Role(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

}
