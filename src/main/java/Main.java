import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        JsonMapper mapper = new JsonMapper();


        String[] skills = new String[]{"shooting", "heading", "strength"};

        Footballer footballer = new Footballer("Robert", "Lewandowski", 80.0, 29, skills);

        try {
            System.out.println(mapper.toJson(footballer));
        }catch(IllegalAccessException exc) {
            System.out.println("Access problem");
        }
    }
}
