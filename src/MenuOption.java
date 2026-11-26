public enum MenuOption {
    SORT(1), SEARCH(2), ADD_RECORDS(3), BINARY_TREE(4), EXIT(5);
    private final int number;
    MenuOption(int number) { this.number = number; }
    public static MenuOption fromNumber(int number) {
        for (MenuOption option : values()) if (option.number == number) return option;
        throw new IllegalArgumentException("Choose a number between 1 and 5.");
    }
}
