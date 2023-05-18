package tanzudeployment.tanzudeploymentfolderfactory.service;

import tanzudeployment.tanzudeploymentfolderfactory.domain.Config;

public class ConfigEditor {


    public Config getMainConfig() {
        Config config = new Config();
        config.setFolderBasePath("C:\\Users\\alexa\\OneDrive\\Documents\\American Truck Simulator\\");
        return config;
    }


}
