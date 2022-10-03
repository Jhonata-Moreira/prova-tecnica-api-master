package simulacaoCredito;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommonsUtils {

    static JSONParser jsonP;
    public static String valor;
    private static String path;
    private static Object indice;
    private static Object campo;
    private static String[] valores;

    public static void separaValoresJson(String arquivoIndiceCampo){
        jsonP = new JSONParser();
        valores = arquivoIndiceCampo.split("\\.");
        path = valores[0];
        indice = valores[1];
        if (valores.length > 2){
            campo = valores[2];
        }
    }

    public static String pegaDadosJson(String arquivoindice) {
        separaValoresJson(arquivoindice);
        try {
            Object value = jsonP.parse(new FileReader("src/test/resources/" + path + ".json"));
            JSONObject jsonObj = (JSONObject) value;
            if (valores.length <= 2) {
                return valor = (String) jsonObj.get(indice);
            } else {
                JSONObject infoConta = (JSONObject) jsonObj.get(indice);
                return valor = (String) infoConta.get(campo);
            }
        }catch (FileNotFoundException e){
            return "valor/arquivo informado não encontrado";
        }catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String pegaVariaveisAmbiente(String urlAmbiente){
        return pegaDadosJson("environment/environment." + urlAmbiente);
    }

    public void validaformatacao(){

    }
}
