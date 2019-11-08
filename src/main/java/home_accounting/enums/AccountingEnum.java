package home_accounting.enums;

public enum AccountingEnum {
    GAIN, EXPENSE;

    @Override
    public String toString() {
        return "TYPE_" + name();
    }
}
