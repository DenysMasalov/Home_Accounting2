package home_accounting.entity;

public enum AccountingEnum {
    GAIN, EXPENSE;

    @Override
    public String toString() {
        return "TYPE_" + name();
    }
}
