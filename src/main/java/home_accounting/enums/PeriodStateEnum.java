package home_accounting.enums;

public enum PeriodStateEnum {
    OPEN, CLOSE;

    @Override
    public String toString() {
        return "STATE_" + name();
    }
}
