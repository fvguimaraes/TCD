package br.com.fiap69aoj.estatisticas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap69aoj.estatisticas.dao.repository.EstatisticaRepository;
import br.com.fiap69aoj.estatisticas.model.Conteudo;

@Service
@Transactional
public class EstatisticasService {
	
	@Autowired
	private EstatisticaRepository repository;

	public List<Conteudo> assistidos(String catalogo, String genero, String order) {
		return null;
	}

	public List<Conteudo> classificacoesPorUsuario(String catalogo, String genero, String order, String usuario) {
		return null;
	}
}
