package userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.CuadradoMagico;
import java.net.URL;
import java.util.ResourceBundle;

// Clase
public class CuadradoMagicoController implements Initializable {

    // Atributos
    @FXML private TextField tfOrden;
    @FXML private ComboBox<String> cbPosicionPerimetral;
    @FXML private ComboBox<String> cbSentidoLLenado;
    @FXML private Button btGenerarCuadrado;
    @FXML private Button btLimpiar;
    @FXML private BorderPane bpBanner;
    @FXML private BorderPane bpPrincipal;
    @FXML private TextField tfConstanteMagica;

    private GridPane gpCuadrado;
    private TextField[][] textFields;

    // Relación con clase la clase principal del modelo
    private CuadradoMagico cuadradoMagico;

    // Método inicializador
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btGenerarCuadrado.setDisable(true);
        btLimpiar.setDisable(true);

        gpCuadrado = new GridPane();
        gpCuadrado.setAlignment(Pos.CENTER);

        // Crea la imagen del banner
        Image image = new Image("banner.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(263.0);
        imageView.setFitHeight(155.0);
        bpBanner.setCenter(imageView);

        // Agrega las posiciones perimetrales al Combobox
        cbPosicionPerimetral.getItems().addAll(CuadradoMagico.PRIMERA_FILA, CuadradoMagico.PRIMERA_COLUMNA,
                CuadradoMagico.ULTIMA_FILA, CuadradoMagico.ULTIMA_COLUMNA);

        // Gestiona lo que se hace el combobox de posiciones perimetrales
        cbPosicionPerimetral.setOnAction(e -> {

            if (cbPosicionPerimetral.getValue().equals(CuadradoMagico.PRIMERA_FILA)) {
                cbSentidoLLenado.getItems().clear();
                cbSentidoLLenado.getItems().addAll(CuadradoMagico.NO, CuadradoMagico.NE);
            } else if (cbPosicionPerimetral.getValue().equals(CuadradoMagico.PRIMERA_COLUMNA)) {
                cbSentidoLLenado.getItems().clear();
                cbSentidoLLenado.getItems().addAll(CuadradoMagico.NO, CuadradoMagico.SO);
            } else if (cbPosicionPerimetral.getValue().equals(CuadradoMagico.ULTIMA_FILA)) {
                cbSentidoLLenado.getItems().clear();
                cbSentidoLLenado.getItems().addAll(CuadradoMagico.SO, CuadradoMagico.SE);
            } else {
                cbSentidoLLenado.getItems().clear();
                cbSentidoLLenado.getItems().addAll(CuadradoMagico.NE, CuadradoMagico.SE);
            }

            btGenerarCuadrado.setDisable(false);
            btLimpiar.setDisable(false);
        });
    }

    // Métodos
    @FXML
    public void controlBtGenerarCuadrado(ActionEvent event) {

        gpCuadrado.setGridLinesVisible(true);

        // Toma los datos de necesarios de la interface
        int orden = Integer.parseInt(tfOrden.getText());
        String posicion = cbPosicionPerimetral.getValue();
        String sentido = cbSentidoLLenado.getValue();

        btGenerarCuadrado.setOnMouseClicked(e -> {

            if(orden%2==0){
                Alert men = new Alert(Alert.AlertType.WARNING);
                men.setTitle("Avertencia !!!");
                men.setHeaderText("Número incorrecto.");
                men.setContentText("Debe ingresar un número impar.");
                men.showAndWait();
            }else{
                cuadradoMagico = new CuadradoMagico(orden,posicion,sentido);
                cuadradoMagico.llenarCuadradoMagico();
                generarTablero();
                mostrarConstanteMagica();
            }
        });
         btGenerarCuadrado.setDisable(true);
    }

    // genera el cuadrado impar de TextFields
    public void generarTablero() {

        // Pone la cantidad de filas y columnas indicadas al GridPane
        GridPane.setRowIndex(gpCuadrado, cuadradoMagico.getOrdenCuadrado());
        GridPane.setColumnIndex(gpCuadrado, cuadradoMagico.getOrdenCuadrado());

        textFields = new TextField[cuadradoMagico.getOrdenCuadrado()][cuadradoMagico.getOrdenCuadrado()];

        // Crea los TextField y los adiciona al GridPane
        for (int i = 0; i < cuadradoMagico.getMCuadrado().length; i++) {
            final int x=i;
            for (int j = 0; j < cuadradoMagico.getMCuadrado()[0].length; j++) {
                final int y=j;

                textFields[i][j] = new TextField();
                textFields[i][j].setStyle("-fx-background-color:  #e5e5e5");
                textFields[i][j].setPrefSize(35.0, 30.0);
                textFields[i][j].setText(cuadradoMagico.getMCuadrado()[i][j] + "");

                gpCuadrado.setHgap(4);
                gpCuadrado.setVgap(4);

                gpCuadrado.add(textFields[i][j],j,i);
            }
        }
        GridPane.setMargin(gpCuadrado, new Insets(2,2,2,2));
        // Adiciona el GridPane al BorderPane secundario.
        bpPrincipal.setCenter(gpCuadrado);
    }


    @FXML
    public void controlBtLimpiar(ActionEvent event){
        btLimpiar.setOnMouseClicked(e->{
            tfOrden.clear();
            tfConstanteMagica.clear();
            gpCuadrado.getChildren().clear();
            gpCuadrado.setGridLinesVisible(false);
            btGenerarCuadrado.setDisable(false);
        });

    }

    public void mostrarConstanteMagica() {
        tfConstanteMagica.setText(cuadradoMagico.getConstanteMagica() + "");
    }

    public CuadradoMagico getCuadradoMagico() {
        return cuadradoMagico;
    }

    public void setCuadradoMagico(CuadradoMagico cuadradoMagico) {
        this.cuadradoMagico = cuadradoMagico;
    }

}
