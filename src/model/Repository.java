package model;

import java.util.List;

public interface Repository {
    List<Toy> getAllToys(int file);
    String createToy(Toy toy);
    void updateToy(Toy toy);
    void deleteToy(String idToyToDelete);
    void castToy();
    public void getWinnerToy();

}
