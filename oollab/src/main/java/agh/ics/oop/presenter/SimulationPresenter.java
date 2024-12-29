package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimulationPresenter implements MapChangeListener {
    //private WorldMap worldMap;

    @FXML
    private TextField inputField;
    @FXML
    private Button startButton;
    @FXML
    private Label moveDescriptionLabel;
    @FXML
    private GridPane grid;

    private static final int CELL_SIZE = 30;
    private static final String EMPTY_INPUT_ERROR = "Puste wejście";

    private SimulationEngine simulationEngine;

//    public void setWorldMap(WorldMap worldMap) {
//        this.worldMap = worldMap;
//    }



    private void drawCoords(WorldMap worldMap){
        Label axisLabel = new Label("Y\\X");
        GridPane.setHalignment(axisLabel, HPos.CENTER);

        grid.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        grid.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        grid.add(axisLabel, 0, 0);

        Boundary bounds = worldMap.getCurrentBounds();

        for(int x = bounds.lowerLeft().getX(); x <= bounds.upperRight().getX(); ++x){
            Label xLabel = new Label("%d".formatted(x));
            GridPane.setHalignment(xLabel, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
            grid.add(xLabel, x - bounds.lowerLeft().getX() + 1, 0);
        }
        for(int y = bounds.upperRight().getY(); y >= bounds.lowerLeft().getY(); --y){
            Label yLabel = new Label("%d".formatted(y));
            GridPane.setHalignment(yLabel, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(CELL_SIZE));
            grid.add(yLabel, 0, y - bounds.lowerLeft().getY() + 1);
        }
    }

    private void drawInterior(WorldMap worldMap){
        Boundary bounds = worldMap.getCurrentBounds();

        for(WorldElement element : worldMap.getElements()
                .stream()
                .collect(
                        Collectors.toMap(
                                WorldElement::getPosition,
                                Function.identity(),
                                (worldElement1, worldElement2) ->
                                        (worldElement1.toString().equals("*") ? worldElement2 : worldElement1)
                        )
                )
                .values()
        ){
            Label elemLabel = new Label(element.toString());
            GridPane.setHalignment(elemLabel, HPos.CENTER);

            int gridX = element.getPosition().getX() - bounds.lowerLeft().getX() + 1;
            int gridY = bounds.upperRight().getY() - element.getPosition().getY() + 1;

            grid.add(elemLabel, gridX, gridY);
        }
    }

    private void clearGrid() {
        grid.getChildren().retainAll(grid.getChildren().getFirst()); // hack to retain visible grid lines
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
    }

    private void handleStep(WorldMap worldMap, String message){
        moveDescriptionLabel.setText(message);

        clearGrid();

        drawCoords(worldMap);
        drawInterior(worldMap);
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> handleStep(worldMap, message));
    }

    @FXML
    private void onStartButtonClicked() throws InterruptedException {
        if (simulationEngine != null){
            simulationEngine.forceShutdown();
        }

        String input = inputField.getText();
        String[] inputArr = input.split(" ");

        if(inputArr.length == 0 || input.isEmpty()) {
            Platform.runLater(() -> moveDescriptionLabel.setText(EMPTY_INPUT_ERROR));
            return;
        }

        List<MoveDirection> directions;
        try{
            directions = OptionsParser.parse(inputField.getText().split(" "));
        }
        catch (IllegalArgumentException e){
            Platform.runLater(() -> moveDescriptionLabel.setText(e.getMessage()));
            return;
        }
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

        WorldMap worldMap = new GrassField(10);
        worldMap.addListener(this);
        simulationEngine = new SimulationEngine(List.of(new Simulation(positions, directions, worldMap)));

        simulationEngine.runAsync();
    }
}
