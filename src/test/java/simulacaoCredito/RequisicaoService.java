package simulacaoCredito;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RequisicaoService {
    private Headers headers;
    private String basePath;


    public String urlDefinidaPath(String pathInfo){
        return basePath = simulacaoCredito.CommonsUtils.pegaVariaveisAmbiente("basePath")
                + pathInfo;
    }

    public Headers defineHeaders(){
        Header one = new Header("accept", "*/*");
        Header second = new Header("content-type", "application/json");
        List<Header> header = new ArrayList<>();
        header.add(one);
        header.add(second);
        return new Headers(header);
    }
    public Integer validaStatusCode(Response response){
        return response.statusCode();
    }

    protected String adaptaPayloadMassa(String body, String massa){
        String body1 = body.replace("{nome}",
                CommonsUtils.pegaDadosJson("massas/massas."+ massa +".nome"));
        String body2 = body1.replace("{cpf}",
                CommonsUtils.pegaDadosJson("massas/massas."+ massa +".cpf"));
        String body3 = body2.replace("{email}",
                CommonsUtils.pegaDadosJson("massas/massas."+ massa +".email"));
        String body4 = body3.replace("{valor}",
                CommonsUtils.pegaDadosJson("massas/massas."+ massa +".valor"));
        String body5 = body4.replace("{parcelas}",
                CommonsUtils.pegaDadosJson("massas/massas."+ massa +".parcelas"));
        String body6 = body5.replace("{seguro}",
                CommonsUtils.pegaDadosJson("massas/massas."+ massa +".seguro"));
        return body6;
    }
}
