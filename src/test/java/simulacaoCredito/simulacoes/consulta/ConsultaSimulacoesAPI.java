package simulacaoCredito.simulacoes.consulta;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import simulacaoCredito.CommonsUtils;
import simulacaoCredito.RequisicaoService;

public class ConsultaSimulacoesAPI extends RequisicaoService {

    public RequestSpecification request;
    public String url;
    private Response response;

    public Response defineRequest(String endPoint){
        request = RestAssured.given();
        request.headers(this.defineHeaders());
        url = urlDefinidaPath(endPoint);
        return response = request.get(url);
    }
}
