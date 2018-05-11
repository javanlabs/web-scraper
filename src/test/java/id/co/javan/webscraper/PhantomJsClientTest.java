package id.co.javan.webscraper;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PhantomJsClientTest {

    @Test
    public void propertiesResolutionTest() throws Exception {
        PhantomJsClient client = new PhantomJsClient(new PropertiesResolutionStrategy());
        String response = client.get("https://goo.gl");
        assertTrue(response.contains("google"));
    }
}
