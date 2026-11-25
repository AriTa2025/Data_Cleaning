public class Main {
    public static void main(String[] args) {
        // id, name, date, age, height, steps/day, sleep (h), tired?
        String[][] raw = {
                {"101", "Ona",     "2026-09-14", "17",  "172 cm", "8200",  "7.5", "no"},
                {"102", "Jonas",   "14/09/2026", "18",  "5.9 ft", "7,900", "6.0", "yes"},
                {"103", "Eva",     "2026-09-14", "17",  "165 cm", "48000", "8.0", "no"},
                {"104", "Matas",   "2026-09-15", "-17", "180 cm", "9100",  "",    "yes"},
                {"101", "Ona",     "2026-09-14", "17",  "172 cm", "8200",  "7.5", "no"},
                {"105", "Lina",    "2026-09-15", "16",  "1.68 m", "6400",  "6.5", "yse"},
                {"106", "Tomas",   "15/09/2026", "17",  "176 cm", "7 300", "5.5", "Yes"},
                {"107", "Gabija",  "2026-09-16", "16",  "161 cm", "10200", "",    "no"},
                {"108", "Paulius", "2026-09-16", "18",  "183 cm", "8,800", "7.0", "Y"},
                {"109", "Austeja", "16/09/2026", "17",  "5.5 ft", "6900",  "42",  "yes"},
                {"110", "Rokas",   "2026-09-17", "170", "178 cm", "9500",  "6.5", "no"},
                {"111", "Ieva",    "2026-09-17", "17",  "167 cm", "7600",  "8.5", " NO "}
        };

        DataCleaner cleaner = new DataCleaner();

        // 1. Duplicates
        String[][] rows = cleaner.removeDuplicates(raw);
        System.out.println("Rows: " + raw.length + " -> " + rows.length);

        // 2-3. Formats, units, incorrect values (name is NOT copied: irrelevant)
        int n = rows.length;
        String[] id = new String[n];
        String[] date = new String[n];
        double[] age = new double[n];
        double[] height = new double[n];
        double[] steps = new double[n];
        double[] sleep = new double[n];
        String[] tired = new String[n];
        for (int i = 0; i < n; i++) {
            id[i] = rows[i][0];
            date[i] = cleaner.fixDate(rows[i][2]);
            age[i] = cleaner.parseAge(rows[i][3]);
            height[i] = cleaner.parseHeightCm(rows[i][4]);
            steps[i] = cleaner.parseSteps(rows[i][5]);
            sleep[i] = cleaner.parseSleep(rows[i][6]);
            tired[i] = cleaner.fixLabel(rows[i][7]);
        }

        // 4. Outliers in steps
        cleaner.capOutliers(steps);

        // 5. Missing values
        cleaner.imputeMedian(age);
        cleaner.imputeMedian(sleep);

        // 6. Scaling
        double[] stepsN = cleaner.normalize(steps);
        double[] sleepN = cleaner.normalize(sleep);

        System.out.println("id | date | age | height | steps | sleep | tired | steps_n | sleep_n");
        for (int i = 0; i < n; i++) {
            System.out.println(id[i] + " | " + date[i] + " | " + age[i] + " | " + height[i]
                    + " | " + steps[i] + " | " + sleep[i] + " | " + tired[i]
                    + " | " + Math.round(stepsN[i] * 100) / 100.0
                    + " | " + Math.round(sleepN[i] * 100) / 100.0);
        }
    }
}