package com.example.raspunsurimultiple;

import com.example.raspunsurimultiple.Domain.Intrebare;
import com.example.raspunsurimultiple.Domain.IntrebareConvertor;
import com.example.raspunsurimultiple.Repository.MemoryRepository;
import com.example.raspunsurimultiple.Repository.SQLRepository;
import com.example.raspunsurimultiple.Service.Service;
import com.example.raspunsurimultiple.Service.Settings;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SQLController {
    @FXML
    private TableView<Intrebare> pacientTable;
    @FXML
    private ListView<Intrebare> intrebariListView;
    @FXML
    private TableColumn<Intrebare, Integer> idColumn;
    @FXML
    private TableColumn<Intrebare, String> textColumn;
    @FXML
    private TableColumn<Intrebare, String> raspunsAColumn;
    @FXML
    private TableColumn<Intrebare, String> raspunsBColumn;
    @FXML
    private TableColumn<Intrebare, String> raspunsCColumn;
    @FXML
    private TableColumn<Intrebare, String> raspunsCorectColumn;
    @FXML
    private TableColumn<Intrebare, String> dificultateColumn;
    @FXML
    protected TextField txtText;
    @FXML
    protected TextField txtRaspunsA;
    @FXML
    protected TextField txtRaspunsB;
    @FXML
    protected TextField txtRaspunsC;
    @FXML
    protected TextField txtRaspunsCorect;
    @FXML
    protected TextField txtDificultate;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRedare;
    public Service service;
    public MemoryRepository<Intrebare> memoryRepository;

    public ObservableList<Intrebare> observableList = FXCollections.observableList(new ArrayList<Intrebare>());
    Settings settings = Settings.getInstance();
    String productsFileName = settings.getProduseFile();
    public void initializeProducts() {
        try {
            this.memoryRepository = new SQLRepository<>(productsFileName);
            this.service = new Service((SQLRepository) memoryRepository);

            idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
            raspunsAColumn.setCellValueFactory(new PropertyValueFactory<>("raspunsA"));
            raspunsBColumn.setCellValueFactory(new PropertyValueFactory<>("raspunsB"));
            raspunsCColumn.setCellValueFactory(new PropertyValueFactory<>("raspunsC"));
            raspunsCorectColumn.setCellValueFactory(new PropertyValueFactory<>("raspunsCorect"));
            dificultateColumn.setCellValueFactory(new PropertyValueFactory<>("dificultate"));

            loadPatients();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Eroare la initializarea repository: " + e.getMessage());
            alert.show();
        }
    }

    public void loadPatients() {
        try {
            observableList.addAll(service.getAllEntitiesList());
            observableList.sort(Comparator.comparing(Intrebare::getDificultate).reversed()
                    .thenComparing(Intrebare::getID));
            System.out.println(service.getAll());
            pacientTable.setItems(observableList);
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    @FXML
    protected void onAddButtonClick() {
        try {
            Random random = new Random();
            var intrebareId = random.nextInt(1001) + 1000;;
            var intrebareText = txtText.getText();
            var intrebareIdRaspunsA = txtRaspunsA.getText();
            var intrebareIdRaspunsB = txtRaspunsB.getText();
            var intrebareIdRaspunsC = txtRaspunsC.getText();
            var intrebareIdRaspunsCorect = txtRaspunsCorect.getText();
            var intrebareDificultate = txtDificultate.getText();

            if (intrebareText.isEmpty() ||
                    intrebareIdRaspunsA.isEmpty() ||
                    intrebareIdRaspunsB.isEmpty() ||
                    intrebareIdRaspunsC.isEmpty() ||
                    intrebareIdRaspunsCorect.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Completați toate datele!");
                alert.show();
                return;
            }

            if (!intrebareDificultate.equals("usor") && !intrebareDificultate.equals("mediu") && !intrebareDificultate.equals("greu")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Introduceți un nivel de dificultate valid!");
                alert.show();
                return;
            }


            Intrebare intrebare = new Intrebare(
                    intrebareId,
                    intrebareText,
                    intrebareIdRaspunsA,
                    intrebareIdRaspunsB,
                    intrebareIdRaspunsC,
                    intrebareIdRaspunsCorect,
                    intrebareDificultate
            );
            System.out.println(intrebare);
            service.add(intrebare);
            observableList.add(intrebare);
            observableList.sort(Comparator.comparing(Intrebare::getDificultate).reversed()
                    .thenComparing(Intrebare::getID));
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Câmpurile nu pot fi goale");
            alert.show();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    @FXML
    protected void onListaRedareButtonClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Introduceți numărul de întrebări și intervalul de dificultate");
        dialog.setHeaderText(null);
        dialog.setContentText("Introduceți numărul de întrebări și dificultatea separate prin virgulă (de exemplu, 5,usor):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            try {
                String[] inputValues = input.split(",");
                if (inputValues.length != 2) {
                    throw new IllegalArgumentException("Introduceți două valori separate prin virgulă.");
                }

                int numarIntrebari = Integer.parseInt(inputValues[0]);
                if (numarIntrebari <= 0) {
                    throw new IllegalArgumentException("Numărul de întrebări trebuie să fie un număr pozitiv.");
                }

                String dificultate = inputValues[1];
                if (!dificultate.equals("usor") && !dificultate.equals("mediu") && !dificultate.equals("greu")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Introduceți un nivel de dificultate valid!");
                    alert.show();
                    return;
                }

                List<Intrebare> listaIntrebari = service.obtineSubListaDupaPunctaj(dificultate, numarIntrebari);

                long countDificultateSpecificata = listaIntrebari.stream()
                        .filter(i -> i.getDificultate()
                        .equals(dificultate))
                        .count();
                if (countDificultateSpecificata < numarIntrebari / 2) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Nu există suficiente întrebări cu dificultatea specificată!");
                    alert.show();
                    return;
                }

                Thread thread = getThread(listaIntrebari);
                thread.start();
            } catch (RuntimeException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });
    }


    private Thread getThread(List<Intrebare> listaIntrebari) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                if (Platform.isFxApplicationThread()) {
                    System.out.println("Codul onListaRedareButtonClick rulează pe firul de execuție al interfeței grafice JavaFX.");
                } else {
                    System.out.println("Codul onListaRedareButtonClick rulează pe un alt fir de execuție.");
                }
                // Salvare pe un fir de execuție JavaFX separat
                Platform.runLater(() -> {
                    try {
                        saveFile(listaIntrebari);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                        alert.show();
                    }
                    intrebariListView.setItems(FXCollections.observableArrayList(listaIntrebari));
                });

                return null;
            }
        };

        // Creare un nou fir de execuție pentru task
        Thread thread = new Thread(task);
        return thread;
    }


    private void saveFile(List<Intrebare> list) throws IOException {

        IntrebareConvertor intrebareConvertor = new IntrebareConvertor();
        try (FileWriter fw = new FileWriter("TestGrila", false)) {
            for (Intrebare object : list) {
                fw.write(intrebareConvertor.toString(object));
                fw.write("\r\n");
            }
        }
    }
}
