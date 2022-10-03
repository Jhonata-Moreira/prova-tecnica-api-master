package simulacaoCredito.simulacoes.criacao;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import simulacaoCredito.CommonsUtils;
import simulacaoCredito.RequisicaoService;

public class CriaSimulacaoAPI extends RequisicaoService {

    private Response response;
    public RequestSpecification request;
    public String url;

    public Response defineRequest(String endPoint, String massa, String body){
        request = RestAssured.given();
            request.headers(this.defineHeaders());
            request.body(this.adaptaPayloadMassa(body, massa));
        url = urlDefinidaPath(endPoint);
        request.log().all();
        return response = request.when().post(url);
    }

    public Boolean validaIdGerado() {
        if (response.getBody().path("id") != null) {
            return true;
        }else{
            return false;
        }
    }

    public Boolean validaMensagem(String mensagem) {
        return response.getBody().asString().contains(mensagem);
    }
}
