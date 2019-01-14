// dana joffe 312129240

import java.util.*;

/**
 * implementation of non-trivial instructions, unique to exercise 2.
 */
public class Ex2Guidelines {

    /**
     * edit the output param to be suitable for the expected output tree.
     * @param output - string representation of the Decision tree
     * @return the output parameter after taking off whitespaces and '|' at lines' beginning.
     */
    public static String organiseTreeOutput(String output) {
        List<String> ou = Arrays.asList(output.split("\n"));
        StringBuilder sb = new StringBuilder();

        String newLine, line;
        Iterator<String> iterator= ou.iterator();

        line = iterator.next();
        newLine = line;
        if (line.startsWith("|")) {
            newLine = line.substring(1);
        }
        sb.append(newLine);
        while(iterator.hasNext()) {
            sb.append("\n");
            line = iterator.next();
            newLine = line;
            if (line.startsWith("|")) {
                newLine = line.substring(1);
            }sb.append(newLine);
        }
        return sb.toString();
    }

    /**
     * organise the performances in a table form, with the first row as tiles and the last row as accuracy.
     * @param performances - a list of decision tree algorithms' performances.
     * @return string representation of the given performances, in a table form.
     */
    public static String organisePerformanceOutput(List<AlgoPerformance> performances) {
        int testsAmount = performances.get(0).getOrderedCls().size();
        String spacing = "\t";
        StringBuilder sb = new StringBuilder();
        StringBuilder line = new StringBuilder();

        // add titles
        line.append("Num" + spacing);
        for (AlgoPerformance per: performances) {
            line.append(per.getAlgoName() + spacing);
        }
        sb.append(line.toString().trim() + "\n");

        // add classifications
        for(int i = 0; i < testsAmount; i++) {
            line.setLength(0);
            line.append(Integer.toString(i+1) + spacing);

            for (AlgoPerformance per: performances) {
                line.append(per.getOrderedCls().get(i) + spacing);
            }
            sb.append(line.toString().trim() + "\n");
        }

        // add accuracies
        line.setLength(0);
        double roundAccuracy;
        for (AlgoPerformance per: performances) {
            roundAccuracy = Math.ceil(per.getAccuracy() * 100) / 100;
            line.append(Double.toString(roundAccuracy) + spacing);
        }

        sb.append(spacing + line.toString().trim());
        return sb.toString();
    }

    /**
     * in case of majority (in all algorithms), this function is called to return the positive classification
     * between the classifications left.
     * a positive classification can be: t, true and yes in small/big letters.
     * @param classifications - all kinds of classification left.
     * @return the positive classification of the classifications.
     */
    public static String getPositiveClass(Set<String> classifications) {
        Set<String> positiveClasses = new HashSet<String>();
        positiveClasses.add("yes");
        positiveClasses.add("true");
        positiveClasses.add("t");
        for (String cls: classifications) {
            if (positiveClasses.contains(cls.toLowerCase())) {
                return cls;
            }
        }
        // not suppose to happen
        return classifications.iterator().next();
    }
}