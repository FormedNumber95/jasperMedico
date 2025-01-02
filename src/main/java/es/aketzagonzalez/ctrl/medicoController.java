package es.aketzagonzalez.ctrl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import es.aketzagonzalez.jasperMedico.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * The Class medicoController.
 */
public class medicoController {

    /** The btn generar. */
    @FXML
    private Button btnGenerar;

    /** The btn limpiar. */
    @FXML
    private Button btnLimpiar;

    /** The btn salir. */
    @FXML
    private Button btnSalir;

    /** The txt cod medico. */
    @FXML
    private TextField txtCodMedico;

    /** The txt direccion paciente. */
    @FXML
    private TextField txtDireccionPaciente;

    /** The txt especialidad medico. */
    @FXML
    private TextField txtEspecialidadMedico;

    /** The txt nombre medico. */
    @FXML
    private TextField txtNombreMedico;

    /** The txt nombre paciente. */
    @FXML
    private TextField txtNombrePaciente;

    /** The txt num paciente. */
    @FXML
    private TextField txtNumPaciente;

    /** The txt tratamientos. */
    @FXML
    private TextArea txtTratamientos;

    /**
     * Accion generar.
     *
     * @param event the event
     */
    @FXML
    void accionGenerar(ActionEvent event) {
    	String errores=validarCampos();
    	if(errores.isEmpty()) {
	    	try {
				InputStream reportStream =getClass().getResourceAsStream("/jasper/informeMedico.jasper");
				if (reportStream == null) {
	                System.out.println("El archivo no esta ahí");
	            }else {
	                System.out.println("El archivo se ha encontrado");
	            }
	            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
	            Map<String, Object> parameters = new HashMap<>();
	            parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());
	            parameters.put("NumPaciente",txtNumPaciente.getText().trim());
	            parameters.put("NomPaciente", txtNombrePaciente.getText().trim());
	            parameters.put("DirPaciente", txtDireccionPaciente.getText().trim());
	            parameters.put("CodMedico", txtCodMedico.getText().trim());
	            parameters.put("NomMedico", txtNombreMedico.getText().trim());
	            parameters.put("EspeMedico", txtEspecialidadMedico.getText().trim());
	            parameters.put("Tratamientos", txtTratamientos.getText().trim());
	            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
	            JasperViewer viewer = new JasperViewer(jprint, false);
	            viewer.setVisible(true);
			} catch (JRException e) {
				e.printStackTrace();
			}
    	}else {
    		Alert al=new Alert(AlertType.ERROR);
    		al.setHeaderText(null);
    		al.setContentText(errores);
    		al.showAndWait();
    	}
    }

    /**
     * Validar campos.
     *
     * @return the string
     */
    private String validarCampos() {
    	String errores="";
		if(!txtCodMedico.getText().trim().isEmpty()) {
			try {
				if(Integer.parseInt(txtCodMedico.getText())<1) {
					throw new Exception();
				}
			}catch(NumberFormatException e) {
				errores+="El codigo del medico debe ser un numero\n";
			}catch(Exception e) {
				errores+="El codigo del medico debe ser un numero positivo\n";
			}
		}else {
			errores+="El codigo del medico es obligatorio\n";
		}
		if(txtDireccionPaciente.getText().trim().isEmpty()) {
			errores+="La direccion del paciente es obligatoria\n";
		}
		if(txtEspecialidadMedico.getText().trim().isEmpty()) {
			errores+="La especialidad del medico es obligatoria\n";
		}
		if(txtNombrePaciente.getText().trim().isEmpty()) {
			errores+="El nombre del paciente es obligatorio\n";
		}
		if(!txtNumPaciente.getText().trim().isEmpty()) {
			try {
				if(Integer.parseInt(txtNumPaciente.getText())<1) {
					throw new Exception();
				}
			}catch(NumberFormatException e) {
				errores+="El numero del paciente debe ser un numero\n";
			}catch(Exception e) {
				errores+="El numero del paciente debe ser un numero positivo\n";
			}
		}else {
			errores+="El numero del paciente es obligatorio\n";
		}
		if(txtTratamientos.getText().trim().isEmpty()) {
			errores+="Los tratamientos son obligatorios";
		}
		return errores;
	}

	/**
	 * Accion limpiar.
	 *
	 * @param event the event
	 */
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

    /**
     * Accion salir.
     *
     * @param event the event
     */
    @FXML
    void accionSalir(ActionEvent event) {
    	MainApp.getStage().close();
    }

}
