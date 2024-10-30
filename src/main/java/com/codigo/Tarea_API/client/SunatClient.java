package com.codigo.Tarea_API.client;

import com.codigo.Tarea_API.aggregates.response.SunatResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sunat-client", url = "https://api.apis.net.pe/v2/sunat/")
public interface SunatClient {

    @GetMapping("/ruc")
    SunatResponse getEmpresa(@RequestParam("numero") String numero,
                             @RequestHeader("Authorization") String authorization);
}
