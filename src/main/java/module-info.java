module com.example.propotypage_et_interface_utilisateur {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens com.example.propotypage_et_interface_utilisateur to javafx.fxml,javafx.graphics,javafx.controls;
    exports com.example.propotypage_et_interface_utilisateur;
}