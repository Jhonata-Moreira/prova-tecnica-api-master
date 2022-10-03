package simulacaoCredito.consultaRestricao;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import simulacaoCredito.CommonsUtils;
import simulacaoCredito.RequisicaoService;

import java.util.ArrayList;
import java.util.List;

public class ConsultaRestricaoAPI extends RequisicaoService {
    private Response response;
    public RequestSpecification request;
    public String url;


    public Response defineRequest(String endPoint, String massa){
        request = RestAssured.given();
            request.headers(this.defineHeaders());
        url = urlDefinidaPath(endPoint + "/" +
                CommonsUtils.pegaDadosJson("massas/massas."+ massa +".cpf"));
        return response = request.get(url);
    }

    public Boolean validaMensagemRestricao(String mensagemEsperada, String massa){
        return String.valueOf(response.getBody().asString()).contains
                (mensagemEsperada.replace("{cpf}",
                CommonsUtils.pegaDadosJson("massas/massas."+ massa +".cpf")));
    }
}
