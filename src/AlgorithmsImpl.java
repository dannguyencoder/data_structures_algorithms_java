import java.util.ArrayList;
import java.util.List;

public class AlgorithmsImpl {

    public AlgorithmsImpl() {

        System.out.println(chessBoardCellColor("var_1__Int", ""));
    }

    static boolean chessBoardCellColor(String cell1, String cell2) {

        if (
                (cell1.charAt(0) + cell1.charAt(1)) % 2 == 0 && (cell2.charAt(0) + cell2.charAt(1)) % 2 == 0
                        ||
                (cell1.charAt(0) + cell1.charAt(1)) % 2 != 0 && (cell2.charAt(0) + cell2.charAt(1)) % 2 != 0
                ) {
            return true;

        }

        return false;

    }

}
