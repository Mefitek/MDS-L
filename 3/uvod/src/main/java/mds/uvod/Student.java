package mds.uvod;

public class Student {


    /*
    Vytvořte třídu s názvem Student, která bude obsahovat:
        • String: surname, name
        • Int: id, year
        • Konstruktor v pořadí (surname, name, id, year) – pořadí důležité pro sdílený kód
     */
    private String surname;
    private String name;
    private int id;
    private int year;

    public Student(String surname, String name, Integer id, Integer year)
    {
        this.surname = surname;
        this.name = name;
        this.id = id;
        this.year = year;
    }

    // pouzijeme pro hledani studenta podle id
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return ("ID: " +id+ "; narozen: " +year+ "; jmeno: " +name+ " " +surname);
    }

}
