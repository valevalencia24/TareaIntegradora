package ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.Controller;

public class Main {

    private Scanner sc;
    private Controller controller;

    public static void main(String[] args) { // Unico metodo estatico
        Main main = new Main();
        main.start();
    }

    public Main() {
        sc = new Scanner(System.in);
        controller = new Controller();
        System.out.println("Inciando class constructor");
    }

    public void start() {

        System.out.println("Ingrese su nombre ");
        String name = sc.nextLine();
        System.out.println("Ingrese su cedula ");
        String cedula = sc.nextLine();

        System.out.println("Bienvenido " + name + " con cedula " + cedula);

        while (true) {
            menu();
        }
    }

    private void menu() {
        System.out.println("""
                2. Registrar lugar biodiverso
                3. Registrar ruta -> Ya esta hecho
                4. Mostrar lugares ordenados por area
                5. Mostrar departamentos con mas lugares biodiversos
                6. Registrar comunidad
                7. Registrar especie
                8. Mostrar lugares con mas especies
                9. Mostrar lugares sin escuelas u hospitales
                10. Cambiar la informacion de una especie
                    """);

        int option = sc.nextInt();

        switch (option) {
            case 2:
                registerBiodiversePlace();
                break;
            case 3:
                System.out.println("Registrar ruta"); // Ya esta hecho
                break;
            case 4:
                System.out.println(controller.showBiodiversePlacesOrder());
                break;
            case 5:
                System.out.println("Mostrar departamentos con mas lugares biodiversos"); // queda a implementar
                break;
            case 6:
                registerCommunity();
                break;
            case 7:
                System.out.println("Registrar especie");
                break;
            case 8:
                System.out.println("Mostrar lugares con mas especies");
                break;
            case 9:
                System.out.println(controller.showPlacesWithoutSchoolOrHospital()); 
                break;
            case 10:
                System.out.println("Cambiar la informacion de una especie");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private void registerBiodiversePlace() {
        System.out.println("Registrar lugar biodiverso");

        System.out.println("Ingrese el departamento: ");
        String department = sc.next();

        System.out.println("Ingrese el nombre del lugar: ");
        String placeName = sc.next();

        System.out.println("Ingrese el tamaño del lugar (en hectáreas): ");
        double placeSize = sc.nextDouble();

        System.out.println("Ingrese la URL de la foto del lugar: ");
        String placePhoto = sc.next();

        System.out.println("Ingrese la fecha de apertura (dd/MM/yyyy): ");
        String dateInput = sc.next();
        Date openingDate = null;
        try {
            openingDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto.");
        }

        if (controller.addBiodiversePlaceInDepartment(department, placeName, placeSize, placePhoto, openingDate)) {
            System.out.println("Lugar registrado: " + placeName + ", Tamaño: " + placeSize + " hectáreas, Foto: "
                    + placePhoto + ", Fecha de apertura: " + openingDate);
        } else {
            System.out.println("No se pudo registrar el lugar.");

        }
    }

    private void registerCommunity() {
        System.out.println("Registrar comunidad");

        System.out.println(controller.showDepartments());

        System.out.println("Ingrese el indice / id del departamento: ");
        int department = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println(controller.showPlacesInDepartment(department));

        System.out.println("Ingrese el indice / id del lugar: ");
        int place = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Ingrese el nombre de la comunidad: ");
        String communityName = sc.nextLine();

        System.out.println("Tipos de comunidad disponibles:");
        System.out.println(controller.showCommunityTypes());
        System.out.println("Ingrese el tipo de comunidad: ");
        String communityType = sc.nextLine();

        System.out.println("Ingrese el tamaño de la comunidad: ");
        int communitySize = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Tipos de problemas disponibles:");
        System.out.println(controller.showProblemTypes());
        System.out.println("Ingrese hasta 4 problemas de la comunidad (separados por comas y un espacio): ");
        String problemsInput = sc.nextLine();
        String[] problems = problemsInput.split(", ");
        if (problems.length > 4) {
            System.out.println("Solo se permiten hasta 4 problemas.");
            return;
        }

        System.out.println("Ingrese el nombre del gerente: ");
        String nameManager = sc.nextLine();

        System.out.println("Ingrese el teléfono del gerente: ");
        String phoneManager = sc.nextLine();

        boolean success = controller.addCommunity(department, place, communityName, communityType, communitySize,
                problems, nameManager, phoneManager);

        if (success) {
            System.out.println("Comunidad registrada exitosamente.");
        } else {
            System.out.println("Error al registrar la comunidad.");
        }
    }
}