package SortingVisualizer.Data;

public class ValidateData {
    public boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public boolean isNumber(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}
