package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {

    // 1 retorna o SO
    private String os() {
        return System.getProperty("os.name");
    }

    // 2 
    public void mostraDistro() {
        if (!os().contains("Linux")) {
            System.out.println("Só roda em linux pae.");
            return;
        }

        String comando = "cat /etc/os-release";

        try {
            Process p = Runtime.getRuntime().exec(comando);
            InputStream fluxo = p.getInputStream();
            InputStreamReader leitor = new InputStreamReader(fluxo);
            BufferedReader buffer = new BufferedReader(leitor);

            String linha = buffer.readLine();
            while (linha != null) {
                // pegar NAME e VERSION
                if (linha.startsWith("NAME=") || linha.startsWith("VERSION=")) {
                    System.out.println(linha.replace("\"", ""));
                }
                linha = buffer.readLine();
            }

            buffer.close();
            leitor.close();
            fluxo.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
