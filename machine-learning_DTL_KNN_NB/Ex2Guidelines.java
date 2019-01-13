// dana joffe 312129240

import java.util.*;

public class Ex2Guidelines {

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

    public static String getPositiveClass(Set<String> classifications) {
        Set<String> positiveClas = new HashSet<String>();
        positiveClas.add("yes");
        positiveClas.add("true");
        positiveClas.add("t");
        for (String cls: classifications) {
            if (positiveClas.contains(cls.toLowerCase())) {
                return cls;
            }
        }
        // not suppose to happen
        return classifications.iterator().next();
    }
}
