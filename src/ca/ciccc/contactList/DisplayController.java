package ca.ciccc.contactList;

import java.util.Arrays;

public class DisplayController {

    public static final String UNICODE_BORDER_CORNER_TOP_LEFT = "\u2554";
    public static final String UNICODE_BORDER_CORNER_TOP_RIGTH = "\u2557";
    public static final String UNICODE_BORDER_CORNER_BOTTOM_LEFT = "\u255A";
    public static final String UNICODE_BORDER_CORNER_BOTTOM_RIGTH = "\u255D";
    public static final String UNICODE_BORDER_LINE_HORIZONTAL = "\u2550";
    public static final String UNICODE_BORDER_LINE_VERTICAL = "\u2551";
    public static final String NEW_LINE = "\n";
    public static final String SPACE = " ";

    private final String[] menuDictionary = {"List all Contacts", "Add new Contact", "Remove Contact", "Update Contact", "Quit"};

    void showMenu() {
        String title = "Contact App";
        String[] menu = menuDictionary;
        printMenu(title, menu);
    }

    private void printMenu(String title, String[] menu) {
        StringBuilder sb = new StringBuilder();
        int wigth = Arrays.stream(menu)
                .mapToInt(str -> str.length())
                .max()
                .getAsInt();
        wigth += 8;

        appendLineHelper(sb, title, wigth, UNICODE_BORDER_CORNER_TOP_LEFT, UNICODE_BORDER_LINE_HORIZONTAL, UNICODE_BORDER_CORNER_TOP_RIGTH, true);

        for (int i = 0; i < menuDictionary.length; i++) {
            appendLineHelper(sb, String.format("%d. %s", i + 1, menuDictionary[i]), wigth, UNICODE_BORDER_LINE_VERTICAL, SPACE, UNICODE_BORDER_LINE_VERTICAL, false);
        }

        appendLineHelper(sb, "", wigth, UNICODE_BORDER_CORNER_BOTTOM_LEFT, UNICODE_BORDER_LINE_HORIZONTAL, UNICODE_BORDER_CORNER_BOTTOM_RIGTH, false);


        System.out.println(sb.toString());
    }

    private StringBuilder appendLineHelper(StringBuilder sb, String title, int wigth, String leftStr, String fillStr, String rightStr, boolean center) {
        int titleLength = title.length() > 0 ? title.length() + 2 : 0;
        wigth = Math.max(wigth, titleLength + leftStr.length() + rightStr.length());
        int offsetStart = 0;
        int offsetEnd = 0;

        sb.append(leftStr);

        int middle = wigth / 2;
        offsetStart = 1;
        offsetEnd = offsetStart;
        if (center) {
            offsetEnd = middle - (titleLength / 2);
            fill(sb, fillStr, offsetStart, offsetEnd);
        }

        if (titleLength > 0) {
            sb.append(SPACE).append(title).append(SPACE);
        }

        offsetStart = offsetEnd + titleLength;
        fill(sb, fillStr, offsetStart, wigth);

        sb.append(rightStr);
        sb.append(NEW_LINE);
        return sb;
    }

    private void fill(StringBuilder sb, String character, int offsetStart, int offsetEnd) {
        for (int i = offsetStart; i < offsetEnd; i++) {
            sb.append(character);
        }
    }
}
