package tanzudeployment.tanzudeploymentfolderfactory.service.factory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderFactory implements Factory {

    @Override
    public void createElement(String path) throws IOException {

        Path folderPath = Paths.get(path);
        Files.createDirectory(folderPath);
    }
}
