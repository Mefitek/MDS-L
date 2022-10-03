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

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String vypis()
    {
        return name + " " + surname + " " + id + " " + year;
    }
}
