package model;

public class ToyMapper {
    public String map(Toy toy) {
        return String.format("%s;%s;%d;%d", toy.getId(), toy.getTitle(), toy.getQuantity(), toy.getFrequencyOfFalling());
    }

    public Toy map(String line) {
        String[] lines = line.split(";");
        return new Toy(lines[0], lines[1],  Integer.parseInt(lines[2]), Integer.parseInt(lines[3]));
    }
}
