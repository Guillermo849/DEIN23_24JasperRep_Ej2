package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import conexion.ConexionBDD;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import javafx.scene.control.Alert;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorDeLanzador implements Initializable{

	@FXML
	private ToggleGroup reports;

	@FXML
	private RadioButton rdBtnPersonas;

	@FXML
	private RadioButton rdBtnCalculo;

	@FXML
	private RadioButton rdBtnSubInformes;

	@FXML
	void lanza(ActionEvent event) {
		try {
			ConexionBDD con = new ConexionBDD();
			JasperReport report;
			if (rdBtnPersonas.isSelected()) {
				report = (JasperReport) JRLoader.loadObject(getClass().getResource("informe_personas1.jasper"));
			} else {
				if (rdBtnCalculo.isSelected()) {
					report = (JasperReport) JRLoader.loadObject(getClass().getResource("informe_personas2.jasper"));
				} else {
					if (rdBtnSubInformes.isSelected()) {
						report = (JasperReport) JRLoader.loadObject(getClass().getResource("informe_personas3.jasper"));
					} else {
						report = null;
					}
				}
			}

			JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
			JasperViewer viewer = new JasperViewer(jprint, false);
			viewer.setVisible(true);

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Ha ocurrido un error");
			alert.showAndWait();
			e.printStackTrace();
		}
	}

	@FXML
	void exit(ActionEvent event) {
		Platform.exit();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
