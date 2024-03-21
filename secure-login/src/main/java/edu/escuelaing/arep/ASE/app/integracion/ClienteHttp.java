package edu.escuelaing.arep.ASE.app.integracion;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.escuelaing.arep.ASE.app.domain.Login;


public class ClienteHttp {

    private static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient cliente;

    public ClienteHttp() {
        cliente = new OkHttpClient();
    }

    public String hacerLlamado(String url, Login infoLogin){
        HttpUrl  urlAux = HttpUrl.parse(url)
                            .newBuilder()
                            .build();
        ObjectMapper mapeadorHttp= new ObjectMapper();
        String dataInfo = "";

        try{
            RequestBody body = RequestBody.create(mapeadorHttp.writeValueAsString(infoLogin), JSON);
            Request request = new Request.Builder()
                                .url(urlAux)
                                .post(body)
                                .build();
            Response response = cliente.newCall(request).execute();
            dataInfo = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataInfo;
        
    }
    
}
