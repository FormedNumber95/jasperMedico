module es.aketzagonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires net.sf.jasperreports.core;
    opens es.aketzagonzalez.ctrl to javafx.fxml;
    exports es.aketzagonzalez.jasperMedico;
}