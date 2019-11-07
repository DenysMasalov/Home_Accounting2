package home_accounting.entity;

public enum PeriodStateEnum {
    OPEN, CLOSE;

    @Override
    public String toString() {
        return "STATE_" + name();
    }
}
