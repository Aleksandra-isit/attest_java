package controllers;

import model.Repository;
import model.Toy;

import java.util.List;

public class ToyController {
    private final Repository repository;
    public ToyController(Repository repository) {
        this.repository = repository;
    }

    public void saveToy(Toy toy) throws Exception{
        repository.createToy(toy);
    }

    public Toy readUser(String toyId, int file) throws Exception {
        List<Toy> toys = repository.getAllToys(file);
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                return toy;
            }
        }

        throw new Exception("User not found");
    }

    public List<Toy> readList(int file){
        List<Toy> users = repository.getAllToys(file);
        return users;
    }

    public void updateUser(String idNumber, Toy newToy, int file) throws Exception {
        validateExistingUser(idNumber, file);
        newToy.setId(idNumber);
        validateUserId(newToy);
        repository.updateToy(newToy);
    }

    public void castToy(){
        repository.castToy();
    }

    public void pickToy(){
        repository.getWinnerToy();
    }

    public void deleteUser(String IdNumber, int typeToSave) throws Exception {
        repository.deleteToy(IdNumber);
    }

    private void validateUserId(Toy toy) throws Exception{
        if (toy.getId().isEmpty()){
            throw new Exception("Toy has no id");
        }
    }

    public void validateExistingUser(String inputId, int file) throws Exception{
        List<Toy> toys = repository.getAllToys(file);
        for (Toy t: toys) {
            if (t.getId().equals(inputId)){
                return;
            }
        }
        throw new Exception("No such ID");
    }
}