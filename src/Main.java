import model.FileOperation;

import controllers.ToyController;
import model.FileOperation;
import model.FileOperationImpl;
import model.Repository;
import model.RepositoryFile;
import views.Menu;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("allToys.txt");
        FileOperation fileWinner = new FileOperationImpl("winnerToys.txt");
        Repository repository = new RepositoryFile(fileOperation, fileWinner);
        ToyController controller = new ToyController(repository);
        Menu menu = new Menu(controller);
        menu.run();
    }
}