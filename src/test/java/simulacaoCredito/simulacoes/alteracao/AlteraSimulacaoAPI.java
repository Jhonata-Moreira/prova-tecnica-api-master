package simulacaoCredito.simulacoes.alteracao;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import simulacaoCredito.RequisicaoService;

public class AlteraSimulacaoAPI extends RequisicaoService {

    public RequestSpecification request;
    public String url;
    private Response response;
    public Response defineRequest(String endPoint, String massa, String body) {
        request = RestAssured.given();
        request.headers(this.defineHeaders());
        request.body(this.adaptaPayloadMassa(body, massa));
        url = urlDefinidaPath(endPoint);
        request.log().all();
        return response = request.when().put(url);
    }
}
