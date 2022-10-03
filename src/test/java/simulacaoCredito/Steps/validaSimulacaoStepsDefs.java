package simulacaoCredito.Steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.Assert;
import simulacaoCredito.CommonsUtils;
import simulacaoCredito.consultaRestricao.ConsultaRestricaoAPI;
import simulacaoCredito.simulacoes.alteracao.AlteraSimulacaoAPI;
import simulacaoCredito.simulacoes.consulta.ConsultaSimulacoesAPI;
import simulacaoCredito.simulacoes.criacao.CriaSimulacaoAPI;

public class validaSimulacaoStepsDefs {

    private ConsultaRestricaoAPI consultaRestricaoAPI;
    private CriaSimulacaoAPI criaSimulacaoAPI;
    private AlteraSimulacaoAPI alteraSimulacaoAPI;
    private ConsultaSimulacoesAPI consultaSimulacoesAPI;
    private String endPoint;
    private Response response;
    public String cliente;

    @After
    public void afterEach() throws InterruptedException {
        Thread.sleep(2000);
    }
    @Dado("que deseje realizar uma consulta de usuário no endpoint de restricoes")
    public void queDesejeRealizarUmaConsultaDeUsuário() {
        consultaRestricaoAPI = new ConsultaRestricaoAPI();
        consultaRestricaoAPI.defineHeaders();
        endPoint = "/restricoes";
    }

    @Quando("busco pelo {string}")
    public void buscoPeloMassasCPF(String massa) {
        cliente = massa;
        response = consultaRestricaoAPI.defineRequest(endPoint, massa);
    }

    @Dado("que deseje criar uma simulação no endpoint {string}")
    public void queDesejeCriarUmaSimulaçãoNoEndpoint(String endPoint) {
        this.endPoint = endPoint;
        criaSimulacaoAPI = new CriaSimulacaoAPI();
    }

    @Quando("envio os dados necessários do cliente {string}")
    public void envioOsDadosNecessáriosDoCliente(String massa, String body) {
        response = criaSimulacaoAPI.defineRequest(endPoint, massa, body);
    }

    @Então("recebo o status code {int} na consulta de restricao")
    public void receboOStatusCode(int status) {
        System.out.println(consultaRestricaoAPI.validaStatusCode(response));
        Assert.assertTrue(consultaRestricaoAPI.validaStatusCode(response).equals(status));
    }

    @Então("recebo o status code {int} ao enviar a simulacao")
    public void receboOStatusCodeAoEnviarASimulacao(int status) {
        Assert.assertTrue(criaSimulacaoAPI.validaStatusCode(response).equals(status));
    }

    @E("a mensagem de {string}")
    public void aMensagemDe(String mensagem) {
        consultaRestricaoAPI.validaMensagemRestricao(mensagem, cliente);
    }

    @E("visualizo um response body com o id criado para o usuário")
    public void visualizoUmResponseBodyComOCriadoParaOUsuário() {
        Assert.assertTrue(criaSimulacaoAPI.validaIdGerado());
    }

    @E("visualizo a mensagem {string}")
    public void visualizoAMensagem(String mensagemDuplicidade) {
        Assert.assertTrue(criaSimulacaoAPI.validaMensagem(mensagemDuplicidade));
    }

    @Quando("consulto pelo usuário {string}")
    public void consultoPeloUsuário(String massa) {
        cliente = massa;
        response = consultaRestricaoAPI.defineRequest(endPoint, massa);
    }

    @E("visualizo também o campo seguro com valor {string}")
    public void visualizoTambémOCampoComValor(String seguro) {
        criaSimulacaoAPI.validaMensagem(seguro);
    }

    @Dado("que deseje alterar uma simulação")
    public void queDesejeAlterarUmaSimulação() {
        this.endPoint = "/simulacoes";
        alteraSimulacaoAPI = new AlteraSimulacaoAPI();
    }

    @Quando("informo o CPF do cliente {string} na URL")
    public void informoOCPFDoClienteNaURL(String massa) {
        this.endPoint += "/" + CommonsUtils.pegaDadosJson("massas/massas."+ massa +".cpf");
    }

    @Quando("envio os dados necessários do cliente {string} para alteracao")
    public void envioOsDadosNecessáriosDoClienteParaAlteracao(String massa, String body) {
        cliente = massa;
        response = alteraSimulacaoAPI.defineRequest(endPoint, massa, body);
    }

    @E("visualizo um response body com os dados do usuário alterado")
    public void visualizoUmResponseBodyComOsDadosDoUsuárioAlterado() {
        
    }

    @Dado("que deseje consultar as simulações existentes")
    public void queDesejeConsultarAsSimulaçõesExistentes() {
        consultaSimulacoesAPI = new ConsultaSimulacoesAPI();
    }

    @Quando("realizo um GET no endpoint {string}")
    public void realizoUmGETNoEndpoint(String endPoint) {
        response = consultaSimulacoesAPI.defineRequest(endPoint);
    }

    @Então("recebo o status code {int} ao enviar a alteracao da simulacao")
    public void receboOStatusCodeAoEnviarAAlteracaoDaSimulacao(int status) {
        Assert.assertEquals(status, (int) alteraSimulacaoAPI.validaStatusCode(response));
    }

    @Então("recebo o status code {int} para simulacoes")
    public void receboOStatusCodeParaSimulacoes(int status) {
        Assert.assertTrue(consultaSimulacoesAPI.validaStatusCode(response).equals(status));
    }
}
