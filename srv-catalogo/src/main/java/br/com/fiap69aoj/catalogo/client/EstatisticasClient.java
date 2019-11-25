package br.com.fiap69aoj.catalogo.client;

import br.com.fiap69aoj.catalogo.dao.entity.FilmeEntity;
import br.com.fiap69aoj.catalogo.dao.entity.SerieEntity;
import br.com.fiap69aoj.catalogo.dao.repository.FilmeRepository;
import br.com.fiap69aoj.catalogo.dao.repository.SerieRepository;
import br.com.fiap69aoj.catalogo.model.Conteudo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class EstatisticasClient {
    @Value("${url.client.estatisticas}")
    private String URL_ESTATISTICAS_CLIENT;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FilmeRepository filmeRepo;

    @Autowired
    private SerieRepository serieRepo;

    @HystrixCommand(fallbackMethod = "assistidosFallback")
    public List<Conteudo> assistidos(String tipo, String categoria) {

        String serviceUrl = URL_ESTATISTICAS_CLIENT + "{tipo}/{categoria}?order=desc";

        Map<String, String> urlParam = new HashMap<String, String>();
        urlParam.put("tipo", tipo);
        urlParam.put("categoria", categoria);

        URI targetUrl = UriComponentsBuilder.fromHttpUrl(serviceUrl).buildAndExpand(urlParam).toUri();
        Conteudo[] retorno = restTemplate.getForObject(targetUrl, Conteudo[].class);

        return Arrays.asList(retorno);
    }

    public List<Conteudo> assistidosFallback(String tipo, String categoria) {
        List<Conteudo> response = new ArrayList<>();
        if("series".equals(tipo)){
            List<SerieEntity> series = this.serieRepo.findAllByGenero(categoria);
            for(SerieEntity serie : series){
                Conteudo conteudo = new Conteudo();
                conteudo.setId(serie.getId());
                conteudo.setTipo("serie");
                response.add(conteudo);
            }
        }else{
            List<FilmeEntity> filmes = this.filmeRepo.findAllByGenero(categoria);
            for(FilmeEntity filme : filmes){
                Conteudo conteudo = new Conteudo();
                conteudo.setId(filme.getId());
                conteudo.setTipo("filme");
                response.add(conteudo);
            }
        }

        return response;
    }

    @HystrixCommand(fallbackMethod = "assistidosUsuarioFallback")
    public List<Conteudo> assistidosUsuario(Long usuario) {

        String serviceUrl = URL_ESTATISTICAS_CLIENT + "{usuario}";

        Map<String, String> urlParam = new HashMap<String, String>();
        urlParam.put("usuario", usuario.toString());

        URI targetUrl = UriComponentsBuilder.fromHttpUrl(serviceUrl).buildAndExpand(urlParam).toUri();
        Conteudo[] retorno = restTemplate.getForObject(targetUrl, Conteudo[].class);

        return Arrays.asList(retorno);
    }

    public List<Conteudo> assistidosUsuarioFallback(Long usuario) {
        return Arrays.asList(new Conteudo[]{ });
    }

}
