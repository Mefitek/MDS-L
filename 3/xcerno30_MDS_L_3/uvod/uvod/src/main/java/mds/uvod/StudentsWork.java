package mds.uvod;

// Vytvořte novou třídu s názvem StudentsWork, ve které bude vaše práce

/*
    Zde vytvořte metody, které obslouží tyto požadavky:
    • /student vypíše Student: VašeJméno ID: VašeID, Jméno a ID bude tučně
    • /student?name=Test&id=123456 vypíše místo vašich hodnot zadané hodnoty
    • /students vypíše seznam všech studentů ze staženého listu
    • /students?vutid=123456 vypíše pouze konkrétního studenta, pokud v listu nebude,
    napíše se, že neexistuje. (Studenta zobrazíte z objektu třídy Student a bude tedy
    obsahovat jméno, příjmení, id a rok narození)
 */

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ResponseBody
@RestController
@RequestMapping("student") // pristup: localhost:8080/student

public class StudentsWork
{

    // • /student vypíše Student: VašeJméno ID: VašeID, Jméno a ID bude tučně
    // • /student?name=Test&id=123456 vypíše místo vašich hodnot zadané hodnoty
    @GetMapping
    public String testBasic(@RequestParam(defaultValue = "Petr") String name, @RequestParam(defaultValue = "123") String id )
    {
        return
                "Student: " +
                        "<b>" +
                        name +
                        "</b>" +
                " ID: " +
                        "<b>" +
                        id +
                        "</b>";
    }

    // • /students vypíše seznam všech studentů ze staženého listu

    ArrayList<Student> students = new ArrayList<>();

    {
        students.add(new Student("Carboch","David",221654,2000));
        students.add(new Student("Černohous","Matěj",229997,2000));
        students.add(new Student("Mička","Petr",230132,2000));
        students.add(new Student("Hamran","David",230253,2001));
        students.add(new Student("Babeľa","Miroslav",230530,2000));
        students.add(new Student("Balušík","Peter",230531,2001));
        students.add(new Student("Bílek","František",230534,2001));
        students.add(new Student("Bukovský","Jan",230535,2000));
        students.add(new Student("Buzovský","Viktor",230536,2001));
        students.add(new Student("Ďuráč","Jakub",230546,2001));
        students.add(new Student("Gradoš","Matej",230551,2001));
        students.add(new Student("Horčička","Patrik",230558,2000));
        students.add(new Student("Jílek","Jiří",230573,2001));
        students.add(new Student("Klíma","Petr",230586,2001));
        students.add(new Student("Kounický","Filip",230597,2000));
        students.add(new Student("Král","Lukáš",230601,2001));
        students.add(new Student("Kubant","Michal",230608,2001));
        students.add(new Student("Dudar","Oleksandra",230847,2002));
        students.add(new Student("Fišarová","Anežka",230890,2001));
        students.add(new Student("Valíček","Matěj",230903,2001));
        students.add(new Student("Wittner","Alex",230914,2000));
        students.add(new Student("Zdražil","Jakub",230917,2000));
        students.add(new Student("Sadecká","Valentýna",231275,2000));
        students.add(new Student("Brandejs","Jakub",233264,2000));
        students.add(new Student("Kohout","David",195823,1996));
        students.add(new Student("Masaryk","Tomáš",123456,1850));
        students.add(new Student("Číka","Petr",10,1982));
    }


    @GetMapping("students")
    public String testList(@RequestParam(defaultValue = "-1" ) int id )
    {
        // vychozi id zaporne
        // kdyz id zaporne vypis cely list

        String studenti = "";

        if(id<0)
        {
            for(Student s:students)
            {
                studenti = studenti + s.toString() + "<br>";
            }
        }

        else
        {
            for(Student s:students)
            {
                if(id == s.getId())
                {
                    studenti = s.toString();
                }
            }
        }

        return studenti;
    }



}
