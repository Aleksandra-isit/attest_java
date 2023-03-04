package views;

import controllers.ToyController;
import model.Toy;

import java.util.List;

import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private ToyController toyController;

    public Menu(ToyController toyController) {
        this.toyController = toyController;
    }

    public void run() {
        Commands com = Commands.NONE;
        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case CREATE:
                        String title = prompt("Название игрушки: ");
                        String quantity = prompt("Количество: ");
                        String frequencyOfFalling = prompt("Шанс выпадания: ");
                        toyController.saveToy(new Toy(title, Integer.parseInt(quantity), Integer.parseInt(frequencyOfFalling)));
                        break;
                    case READ:
                        String id = prompt("Идентификатор игрушки: ");
                        Toy toy = toyController.readUser(id, 1);
                        System.out.println(toy);
                        break;
                    case LIST:
                        int file = Integer.parseInt(prompt("1 - Общий файл / 2 - Файл с призовыми игрушками"));
                        List<Toy> toys = toyController.readList(file);
                        for (Toy item : toys) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case UPDATE:
                        String numId = prompt("Какую игрушку вы хотите изменить? Введите ID: ");
                        toyController.updateUser(numId, CreateToy(), 1);
                        break;
                    case CAST:
                        toyController.castToy();
                        break;
                    case PICK:
                        toyController.pickToy();
                        break;
                    case DELETE:
                        String numIdToDelete = prompt("Какую игрушку вы хотите удалить? Введите ID: ");
                        toyController.validateExistingUser(numIdToDelete, 1);
                        toyController.deleteUser(numIdToDelete, 1);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String prompt(String message){
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private Toy CreateToy(){
        String title = prompt("Название игрушки: ");
        String quantity = prompt("Количество: ");
        String frequencyOfFalling = prompt("Шанс выпадания: ");
        Toy newToy = new Toy(title, Integer.parseInt(quantity), Integer.parseInt(frequencyOfFalling));
        return newToy;
    }

}
