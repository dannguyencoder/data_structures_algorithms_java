public class SearchingAlgorithms {

    public int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }

        return -1;
    }

    public int binarySearchIteratively(int[] sortedArray, int key, int low, int high) {
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] < key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }

        return index;
    }

    public int binarySearchRecursively(int[] sortedArray, int key, int low, int high) {
        int middle = (low + high) / 2;

        if (high < low) {
            return -1;
        }

        if (key == sortedArray[middle]) {
            return middle;
        } else if (key < sortedArray[middle]) {
            return binarySearchRecursively(sortedArray, key, low, middle - 1);
        } else {
            return binarySearchRecursively(sortedArray, key, middle + 1, high);
        }
    }

    public int naivePatternSearching(String txt, String pat) {
        int m = pat.length();
        int n = txt.length();

        /* A loop to slide pat one by one */
        for (int i = 0; i <= n - m; i++) {
            int j;

            /* For current index i, check for pattern match */
            for (j = 0; j < m; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }

            if (j == m) { // if pat[0...m-1] = txt[i. i + 1, ... i + m - 1]
                System.out.println("Pattern found at index " + i);
                return i;
            }

        }

        return -1;
    }

    int[] computeLspTable(String pattern) {
        int[] lsp = new int[pattern.length()];
        lsp[0] = 0;  // Base case
        for (int i = 1; i < pattern.length(); i++) {
            // Start by assuming we're extending the previous LSP
            int j = lsp[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lsp[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            lsp[i] = j;
        }
        return lsp;
    }

    int kmpStringSearching(String pattern, String text) {
        int[] lsp = computeLspTable(pattern);

        int j = 0; // Number of chars matched in pattern
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                // Fall back in the pattern
                j = lsp[j - 1]; // Strictly decreasing
            }

            if (text.charAt(i) == pattern.charAt(j)) {
                // Next char matched, incremented position
                j++;
                if (j == pattern.length()) {
                    return i - (j - 1);
                }
            }
        }

        return -1; // Not found
    }
}
