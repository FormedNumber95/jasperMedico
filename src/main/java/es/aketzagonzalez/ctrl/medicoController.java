package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.jasperMedico.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class medicoController {

    @FXML
    private Button btnGenerar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSalir;

    @FXML
    private TextField txtCodMedico;

    @FXML
    private TextField txtDireccionPaciente;

    @FXML
    private TextField txtEspecialidadMedico;

    @FXML
    private TextField txtNombreMedico;

    @FXML
    private TextField txtNombrePaciente;

    @FXML
    private TextField txtNumPaciente;

    @FXML
    private TextArea txtTratamientos;

    @FXML
    void accionGenerar(ActionEvent event) {

    }

    @FXML
    void accionLimpiar(ActionEvent event) {
    	txtCodMedico.clear();
    	txtDireccionPaciente.clear();
    	txtEspecialidadMedico.clear();
    	txtNombreMedico.clear();
    	txtNombrePaciente.clear();
    	txtNumPaciente.clear();
    	txtTratamientos.clear();
    }

    @FXML
    void accionSalir(ActionEvent event) {
    	MainApp.getStage().close();
    }

}
