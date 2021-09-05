package SortingVisualizer.Data;

public class Helpers {

    public Helpers() {
    }

    public String RemoveNewLineTabSpaces(String str) {
        return str.replaceAll("[\\n\\t ]", "");
    }

    public int[] StringToIntArray(String str, String seperatorCharacter) throws Exception {
        String[] items = str.replaceAll("\\[", "").replaceAll("\\]", "").split(seperatorCharacter);
        int[] result = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                result[i] = Integer.parseInt(items[i]);
            } catch (Exception e) {
                throw new Exception("NotNumber");
            }
        }
        return result;
    }

    public String FormatIntArrayToString(int[] arr, String seperatorCharacter) {
        String result = "[\n ";

        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                result += arr[i] + seperatorCharacter + "\n ";
            } else {
                result += arr[i] + "\n]";
            }
        }

        return result;
    }
}
