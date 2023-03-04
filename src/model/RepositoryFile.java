package model;

import java.util.*;

public class RepositoryFile implements Repository {
    private ToyMapper mapper = new ToyMapper();
    private FileOperation fileOperation;
    private FileOperation fileWinners;
    private List<Toy> winnerToy = new ArrayList<>();

    public RepositoryFile(FileOperation fileOperation, FileOperation
                          fileWinners) {
        this.fileOperation = fileOperation;
        this.fileWinners = fileWinners;
        winnerToy = getAllToys(2);
    }

    @Override
    public List<Toy> getAllToys(int file) {
        List<String> lines;
        if (file == 1) {
            lines = fileOperation.readAllLines();
        }
        else {
            lines = fileWinners.readAllLines();
        }
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    @Override
    public String createToy(Toy toy) {
        List<Toy> toys = getAllToys(1);
        int max = 0;
        for (Toy item : toys) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        toy.setId(id);
        toys.add(toy);
        writeDown(toys, 1);
        return id;
    }

    @Override
    public void updateToy(Toy toy) {
        List<Toy> toys = getAllToys(1);
        Toy target = toys.stream().filter(i -> i.getId().equals(toy.getId())).findFirst().get();
        target.setFrequencyOfFalling(toy.getFrequencyOfFalling());
        writeDown(toys, 1);
    }

    @Override
    public void castToy() {
        List<Toy> toys = getAllToys(1);
        Collections.shuffle(toys);
        Toy winner = toys.get(generateRandomIntIntRange(toys.size()));
        System.out.println("Призовой игрушкой стала:\n");
        System.out.println(winner);
        winnerToy.add(winner);
    }

    @Override
    public void getWinnerToy(){
        if (winnerToy.isEmpty()){
            System.out.println(" '_' ");
            return;
        }

        List<Toy> writeWinner = getAllToys(2);

        writeWinner.add(winnerToy.get(0));
        int kol = winnerToy.get(0).getQuantity()-1;

        writeWinner.get(writeWinner.size()-1).setQuantity(1);

        winnerToy.get(0).setQuantity(kol);
        updateToy(winnerToy.get(0));

        writeDown(writeWinner, 2);
        System.out.println("Забрали игрушку:\n");
        System.out.println(writeWinner.get(writeWinner.size()-1));
    }

    @Override
    public void deleteToy(String idUserToDelete) {
        List<Toy> toys = getAllToys(1);
        toys.removeIf(toy -> toy.getId().equals(idUserToDelete));
        writeDown(toys, 1);
    }

    private void writeDown(List<Toy> toys, int file){
        List<String> lines = new ArrayList<>();
        for (Toy item: toys) {
            lines.add(mapper.map(item));
        }
        if (file == 1) {
            fileOperation.saveAllLines(lines);
        }
        else{
            fileWinners.saveAllLines(lines);
        }
    }

    private static int generateRandomIntIntRange(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }
}
