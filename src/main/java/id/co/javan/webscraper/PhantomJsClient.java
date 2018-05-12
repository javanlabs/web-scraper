package id.co.javan.webscraper;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class PhantomJsClient {
    private PhantomJsResolutionStrategy phantomJsResolutionStrategy;
    private static String saveScriptLocation;

    public PhantomJsClient(PhantomJsResolutionStrategy phantomJsResolutionStrategy) {
        this.phantomJsResolutionStrategy = phantomJsResolutionStrategy;
    }

    private String getSaveScriptLocation() throws Exception {
        // create temporary file for save script js only if it does not exists
        if(saveScriptLocation == null || !new File(saveScriptLocation).exists()) {
            String filename = "save_script_" + System.currentTimeMillis() + ".js";
            File tempFile = File.createTempFile("save_script", System.currentTimeMillis() + ".js");
            IOUtils.copy(getClass().getClassLoader().getResourceAsStream("save.js"), new FileOutputStream(tempFile));
            saveScriptLocation = tempFile.getAbsolutePath();
        }
        return saveScriptLocation;
    }

    public String get(String url) throws Exception {
        Process process = new ProcessBuilder(phantomJsResolutionStrategy.getPath(), getSaveScriptLocation(), url).start();
        ByteArrayOutputStream processResult = new ByteArrayOutputStream();
        IOUtils.copy(process.getInputStream(), processResult);
        return new String(processResult.toByteArray());
    }
}
