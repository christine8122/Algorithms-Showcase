public class MySorter2 {
    /**
     * Returns the authors' names.
     * @return The names of the authors of this file.
     */
    public static String getAuthors() {
        return "Bridget Martinez and Christine Samons";
    }

    public static void sort(int[] sort) {
        MergeSort(sort);
    }

    public static void MergeSort(int[] sort) {
        // Implementing MergeSort
        if (sort.length > 1) {
            int mid = sort.length / 2;
            int[] b = new int[mid];
            int[] c = new int[sort.length - mid];

            System.arraycopy(sort, 0, b, 0, mid);
            System.arraycopy(sort, mid, c, 0, sort.length - mid);

            MergeSort(b);
            MergeSort(c);

            Merge(b, c, sort);
        }
    }

    public static void Merge(int[] b, int[] c, int[] sort) {
        int i = 0, j = 0, k = 0;

        while (j < b.length && k < c.length) {
            if (b[j] < c[k]) {
                sort[i++] = b[j++];
            } else {
                sort[i++] = c[k++];
            }
        }

        while (j < b.length) {
            sort[i++] = b[j++];
        }

        while (k < c.length) {
            sort[i++] = c[k++];
        }
    }
}
