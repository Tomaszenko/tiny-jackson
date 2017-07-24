import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        JsonMapper mapper = new JsonMapper();

        String str = "Abc";


        String[] skills = new String[]{"shooting", "heading", "strength"};

        Club club = new Club("Bayern", 500.0);

        Footballer footballer = new Footballer("Robert", "Lewandowski", 80.0, 29, skills, club);

        Integer integer = new Integer(2);
        Double doubel = new Double(2.00);

        Object o = new Object();
        o = integer;

        System.out.println(integer.getClass());
        System.out.println(doubel.getClass());

        System.out.println(o.getClass());


        try {
            System.out.println(mapper.toJson(footballer));
        }catch(IllegalAccessException | NoSuchMethodException exc) {
            System.out.println("Access problem");
        }
    }
}
