package edu.escuelaing.arep.ASE.app.integracion;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.escuelaing.arep.ASE.app.domain.Login;

public class ClienteHttp {

    private OkHttpClient cliente;

    public ClienteHttp() {
        cliente = new OkHttpClient();
    }

    public String hacerLlamado(String url, Login infoLogin){
        HttpUrl  urlAux = HttpUrl.parse(url)
                            .newBuilder()
                            .build();
        ObjectMapper mapeadorHttp= new ObjectMapper();
        
        Request request = new Request.Builder()
                                .url(urlAux)
                                .post(mapeadorHttp.writeValueAsString(infoLogin))
                                .build();
        String dataInfo = "";
        try (Response response = cliente.newCall(request).execute()) {
            dataInfo = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataInfo;
        
    }
    
}
