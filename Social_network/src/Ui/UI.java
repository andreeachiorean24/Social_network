package Ui;

import Domain.Prietenie;
import Service.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UI {
    private final Service service;
    private final Scanner scanner;

    public UI (Service service){
        this.service=service;
        this.scanner=new Scanner(System.in);
    }
    public void start() {
        service.addUser(1L,"alex","Pop","email@gmail.com","parola123","01.01.2002");
        service.addUser(2L,"Ana","Popescu","emailana@gmail.com","123456788","05.05.2005");
        service.addFriendship(1L,2L);
        int alegere;
        while (true) {
            optiuni();
            System.out.print("Alegerea dvs. este: ");
            alegere=scanner.nextInt();

            //eliminare \n in plus
            scanner.nextLine();

            switch(alegere){
                case 1 -> adaugaUtilizator();
                case 2 -> stergeUtilizator();
                case 3 -> modificaUtilizator();
                case 4 -> printeazaUtilizatori();

                case 5 -> adaugaPrietenie();
                case 6 -> stergePrietenie();

                case 7 -> printeazaPrietenii();


                case 8 -> System.exit(0);
                default -> System.out.println("Alegere invalida!");
            }
        }
    }
    private void optiuni(){
        System.out.println("1. Adauga utilizator");
        System.out.println("2. Sterge utilizator");
        System.out.println("3. Modifica utilizator");
        System.out.println("4. Afiseaza lista de utilizatori\n");
        System.out.println("5. Adauga prietenie");
        System.out.println("6. Sterge prietenie");
        System.out.println("7. Afiseaza lista de prietenii\n");
        System.out.println("8. Exit");
    }
    private String citire(String text) {
        System.out.print(text);
        return scanner.nextLine();
    }
    private void adaugaUtilizator(){
        String string1=citire("ID: ");
        Long ID = Long.parseLong(string1);
        String string2=citire("Nume: ");
        String string3=citire("Prenume: ");
        String string4=citire("Email: ");
        String string5=citire("Parola: ");
        String string6=citire("Data nasterii: ");
        if(service.addUser(ID, string2, string3, string4, string5, string6))
            System.out.println("Utilizator adaugat cu succes!\n");
        else System.out.println("Nu au avut loc modificari!\n");
    }
    private void stergeUtilizator(){
        Long ID = Long.parseLong(citire("ID utilizator de sters: "));
        if(service.deleteUser(ID))
            System.out.println("Utilizator sters cu succes!\n");
        else System.out.println("Nu au avut loc modificari!\n");
    }
    private void modificaUtilizator(){
        String string1=citire("ID-ul utilizatorului de modificat: ");
        Long ID = Long.parseLong(string1);
        String string2=citire("Nume nou: ");
        String string3=citire("Prenume nou: ");
        String string4=citire("Email nou: ");
        String string5=citire("Parola noua: ");
        String string6=citire("Data nasterii noua: ");
        if(service.updateUser(ID, string2, string3, string4, string5, string6))
            System.out.println("Utilizator modificat cu succes!\n");
        else System.out.println("Nu au avut loc modificari!\n");
    }
    private void printeazaUtilizatori(){
        System.out.println("Lista de utilizatori:");
        service.getAll().forEach(System.out::println);
    }
    private void adaugaPrietenie(){
        Long ID1, ID2;
        ID1=Long.parseLong(citire("ID prieten 1: "));
        ID2=Long.parseLong(citire("ID prieten 2: "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String dateTimeString = now.format(formatter);
        LocalDateTime friendsFrom = LocalDateTime.parse(dateTimeString, formatter);
        if(service.addFriendship(ID1,ID2))
            System.out.println("Prietenie adaugata cu succes!\n");
        else System.out.println("Nu au avut loc modificari!\n");
    }
    private void stergePrietenie(){
        String ID = citire("ID prietenie de sters: ");
        if(service.deleteFriendship(ID))
            System.out.println("Prietenie stearsa cu succes!\n");
        else System.out.println("Nu au avut loc modificari!\n");
    }
    private void printeazaPrietenii(){
        System.out.println("Lista de prietenii:");
        for (Prietenie fr : service.getAllPrietenii()) {
            String nume1 = service.findUser(fr.getID1()).getNume();
            String nume2 = service.findUser(fr.getID2()).getNume();
            System.out.println("Prietenie cu ID: " + fr.getID() +" intre " + nume1 + " si " + nume2);
        }
        //service.getAllPrietenii().forEach(System.out::println);
    }
}
