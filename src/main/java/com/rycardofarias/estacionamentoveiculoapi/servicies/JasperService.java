package com.rycardofarias.estacionamentoveiculoapi.servicies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class JasperService {

    private final ResourceLoader resourceLoader;
    private final DataSource dataSource;

    private Map<String, Object> params = new HashMap<>();

    private static final String JASPER_DIRETORIO = "classpath:reports/";

    private void addParams(String key, Object value){
        this.params.put("IMAGEM_DIRETORIO", JASPER_DIRETORIO);
        this.params.put("REPORT_LOCALE", new Locale("pt", "BR"));
        this.params.put(key, value);
    }

    public byte[] gerarPdf() {
        byte[] bytes = null;
        try{
            Resource resource = resourceLoader.getResource(JASPER_DIRETORIO.concat("relatorio-cliente-vaga.jasper"));
            InputStream stream = resource.getInputStream();
            JasperPrint print = JasperFillManager.fillReport(stream, params, dataSource.getConnection());
        } catch (IOException |JRException | SQLException exception) {
            log.error("Jasper Reports ::: ", exception.getCause());
            throw new RuntimeException(exception);
        }

        return bytes;
    }
}
