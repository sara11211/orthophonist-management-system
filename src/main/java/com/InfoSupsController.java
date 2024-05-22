package com;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import com.models.RDV;
public class InfoSupsController
{
    @FXML
    private TextArea infoSupField;

    private RDV rdv;

    public void setInfoSupField(RDV rdv) { infoSupField.setText(rdv.getInfoSup());}

}