package BackBone.ClientToServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
//import org.apache.commons.io.FilenameUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ImageSender {

    /**
     * A generic method to execute any type of Http Request and constructs a
     * response object
     *
     * @param requestBase the request that needs to be exeuted
     * @return server response as <code>String</code>
     */
    private static String executeRequest(HttpRequestBase requestBase) {
        String responseString = "";

        InputStream responseStream = null;
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(requestBase);
            if (response != null) {
                HttpEntity responseEntity = response.getEntity();

                if (responseEntity != null) {
                    responseStream = responseEntity.getContent();
                    if (responseStream != null) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(responseStream));
                        String responseLine = br.readLine();
                        String tempResponseString = "";
                        while (responseLine != null) {
                            tempResponseString = tempResponseString + responseLine + System.getProperty("line.separator");
                            responseLine = br.readLine();
                        }
                        br.close();
                        if (tempResponseString.length() > 0) {
                            responseString = tempResponseString;
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (responseStream != null) {
                try {
                    responseStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        client.getConnectionManager().shutdown();

        return responseString;
    }

    /**
     * Method that builds the multi-part form data request
     *
     * @param urlString the urlString to which the file needs to be uploaded
     * @param file the actual file instance that needs to be uploaded
     * @param fileName name of the file, just to show how to add the usual form
     * parameters
     * @param fileDescription some description for the file, just to show how to
     * add the usual form parameters
     * @return server response as <code>String</code>
     */
    public String executeMultiPartRequest(String urlString, File file, String fileName, String fileDest) {
        System.out.println("serviceStart");
        System.out.println(file.getAbsoluteFile());
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(urlString);
        try {
            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addTextBody("fileName",file.getName())
                    .addTextBody("fileDest",fileDest)
                    .addBinaryBody("upload_file", file, org.apache.http.entity.ContentType.create("application/octet-stream"), "filename")
                    .build();

            postRequest.setEntity(entity);
            HttpResponse response = client.execute(postRequest);
            System.out.println("service end");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return executeRequest(postRequest);
    }

}
