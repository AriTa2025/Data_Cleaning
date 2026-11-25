public class DataCleaner {

    // --------- Task 1: duplicates ----------
    public boolean rowsEqual(String[] a, String[] b) {
        if (a == null || b == null || a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null && b[i] == null) {
                if (a[i] == null || b[i] == null) {
                    return false;
                }
            }
            if (!a[i].equals(b[i])) {
                return false;
            }
        }
        return true;
    }

    public String[][] removeDuplicates(String[][] rows) {
        boolean[] keep = new boolean[rows.length];
        for (int i = 0; i < rows.length; i++) {
            keep[i] = true;
            for (int j = 0; j < i; j++) {
                if (rowsEqual(rows[i], rows[j]) == true) {
                    keep[i] = false;
                    break;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < keep.length; i++) {
            if (keep[i] == true) {
                count = count + 1;
            }
        }

        String[][] result = new String[count][];
        int notDuplicate = 0;
        for (int i = 0; i < rows.length; i++) {
            if (keep[i] == true) {
                result[notDuplicate] = rows[i];
                notDuplicate = notDuplicate + 1;
            }
        }
        return result;
    }

    // ---------- Task 2: formatting ----------
    public String fixDate(String date) {
        if (date == null) {
            return "";
        }
        String day = null;
        String month = null;
        String year = null;

        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        return year + "-" + month + "-" + day;
    }

    public double parseHeightCm(String height) {
        if (height == null) {
            return -1.0;
        }
        String s = height.trim().toLowerCase().replace(",", ".");
        double cm = 0.0;

        return Math.round(cm * 10) / 10.0;
    }

    public double parseSteps(String steps) {
        if (steps == null) {
            return -1.0;
        }
        String cleaned = steps.trim().replace(",", "").replace(" ", "");
        if (cleaned.isEmpty()) {
            return -1.0;
        }
        return Double.parseDouble(cleaned);
    }

    // ---------- Task 3: incorrect data ----------
    public String fixLabel(String label) {
        if (label == null) {
            return "";
        }
        String s = label.trim().toLowerCase();

        if (s.equals("yes") || s.equals("y") || s.equals("1") || s.equals("true") || s.equals("yse")) {
            return "yes";
        }
        if (s.equals("no") || s.equals("n") || s.equals("0") || s.equals("false")) {
            return "no";
        }
        return "";
    }

    public double parseAge(String age) {
        if (age == null) {
            return -1.0;
        }
        String s = age.trim();
        if (s.isEmpty()) {
            return -1.0;
        }
        double pAge = Double.parseDouble(s);
        if (pAge < 10.0 || pAge > 25.0) {
            return -1.0;
        }
        return pAge;
    }

    public double parseSleep(String sleep) {
        if (sleep == null) {
            return -1.0;
        }
        String s = sleep.trim().replace(",", ".");
        if (s.isEmpty()) {
            return -1.0;
        }
        double pSleep = Double.parseDouble(s);
        if (pSleep < 0.0 || pSleep > 24.0) {
            return -1.0;
        }
        return pSleep;
    }

    // ---------- Helpers ----------
    public void sort(double[] x) {
        for (int i = 0; i < x.length - 1; i++) {
            for (int j = 0; j < x.length - 1 - i; j++) {
                if (x[j] > x[j + 1]) {
                    double temp = x[j];
                    x[j] = x[j + 1];
                    x[j + 1] = temp;
                }
            }
        }
    }

    public double medianOfRange(double[] sorted, int start, int end) {

        return 0;
    }

    // ---------- Task 4: outliers ----------
    public void capOutliers(double[] x) {
        double[] sorted = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            sorted[i] = x[i];
        }
        sort(sorted);

        int n = sorted.length;
        double q1;
        double q3;

        if (n % 2 == 0) {
            q1 = medianOfRange(sorted, 0, n / 2 - 1);
            q3 = medianOfRange(sorted, n / 2, n - 1);
        } else {
            int mid = n / 2;
            q1 = medianOfRange(sorted, 0, mid - 1);
            q3 = medianOfRange(sorted, mid + 1, n - 1);
        }

        double iqr = q3 - q1;
        double lowerFence = q1 - 1.5 * iqr;
        double upperFence = q3 + 1.5 * iqr;

        System.out.println("Q1 = " + q1 + ", Q3 = " + q3 + ", fences: " + lowerFence + "   " + upperFence);

    }

    // ---------- Task 5: missing data ----------
    public void imputeMedian(double[] x) {
        int validCount = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] != -1.0) {
                validCount=validCount+1;
            }
        }

        double[] known = new double[validCount];
        int idx = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] != -1.0) {
                known[idx] = x[i];
                idx=idx+1;
            }
        }

        sort(known);
    }

    // ---------- Task 6: scaling ----------
    public double[] normalize(double[] x) {
        if (x.length == 0) {
            return new double[0];
        }
        double min = x[0];
        double max = x[0];
        for (int i = 1; i < x.length; i++) {
            if (x[i] < min) {
                min = x[i];
            }
            if (x[i] > max) {
                max = x[i];
            }
        }
        return null;
    }
}