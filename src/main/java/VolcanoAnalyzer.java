import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class VolcanoAnalyzer {
    private List<Volcano> volcanos;

    public void loadVolcanoes(Optional<String> pathOpt) throws IOException, URISyntaxException {
        try{
            String path = pathOpt.orElse("volcano.json");
            URL url = this.getClass().getClassLoader().getResource(path);
            String jsonString = new String(Files.readAllBytes(Paths.get(url.toURI())));
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            volcanos = objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, Volcano.class));
        } catch(Exception e){
            throw(e);
        }
    }

    public Integer numbVolcanoes(){
        return volcanos.size();
    }

    //add methods here to meet the requirements in README.md

    public List<Volcano> eruptedInEighties() {
        return volcanos.parallelStream()
                .filter(v -> v.getYear() >= 1980 && v.getYear() < 1990)
                .collect(Collectors.toList());
    }

    public List<String> highVEI() {
        return volcanos.parallelStream()
                .filter(v -> v.getVEI() >= 6)
                .map(v -> v.getName())
                .collect(Collectors.toList());
    }

    public Volcano mostDeadly() {
        return volcanos.stream()
            .max(Comparator.comparingInt(a -> Integer.parseInt((a.getDEATHS().isEmpty() ? "0" : a.getDEATHS()))))
                .orElseThrow(RuntimeException::new);
    }

    public double causedTsunami() {
        return volcanos.parallelStream()
                .filter(v -> v.getTsu().equals("tsu"))
                .collect(Collectors.counting()) * 100d / volcanos.size();
    }

    public String mostCommonType() {
        return volcanos.stream()
                .collect(
                    Collectors.collectingAndThen(Collectors
                            .groupingBy(Volcano::getType, Collectors.counting()), map ->
                                map.entrySet().stream()
                        .max(Map.Entry.comparingByValue()).get().getKey()));
    }

    public Long eruptionsByCountry(String country) {
        return volcanos.stream()
                .filter(v -> v.getCountry().equals(country))
                .collect(Collectors.counting());
    }

    public double averageElevation() {
        return (double)volcanos.stream()
                .collect(Collectors.summingInt(Volcano::getElevation))/volcanos.size();
    }

    public List<String> volcanoTypes() {
        return volcanos.stream()
                .map(v-> v.getType()).distinct()
                .collect(Collectors.toList());
    }

    public double percentNorth() {
        return ((double)volcanos.parallelStream()
                .filter(v -> v.getLatitude() > 0)
                .collect(Collectors.toList())
                .size()) * 100 /volcanos.size();
    }

    public String[] manyFilters() {
        return volcanos.stream()
                .filter(v -> v.getYear() > 1800)
                .filter(v -> v.getTsu() == "")
                .filter(v -> v.getLatitude() < 0 )
                .filter(v -> v.getVEI() == 5)
                .map(v -> v.getName())
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    public String[] elevatedVolcanoes(int elevation) {
        return volcanos.stream()
                .filter(v -> v.getElevation() >= elevation)
                .map(v->v.getName())
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    public String[] topAgentsOfDeath() {
        return volcanos.stream()
                .sorted((v1, v2)->Integer.parseInt("0" + v2.getDEATHS()) - (Integer.parseInt("0" + v1.getDEATHS())))
                .limit(10)
                .map(v-> Arrays.asList( v.getAgent().isEmpty() ? new String[0] : v.getAgent().split(",")))
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }
}
