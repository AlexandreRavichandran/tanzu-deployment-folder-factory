module tanzudeployment.tanzudeploymentfolderfactory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens tanzudeployment.tanzudeploymentfolderfactory to javafx.fxml;
    exports tanzudeployment.tanzudeploymentfolderfactory;
    exports tanzudeployment.tanzudeploymentfolderfactory.controller;
    opens tanzudeployment.tanzudeploymentfolderfactory.controller to javafx.fxml;
}