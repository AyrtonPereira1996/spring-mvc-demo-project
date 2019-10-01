package com.mballem.curso.boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.FuncionarioDao;
import com.mballem.curso.boot.domain.Funcionario;

@Service
@Transactional(readOnly = false)
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao fdao;

	@Override
	public void salvar(Funcionario funcionario) {
		fdao.save(funcionario);

	}

	@Override
	public void editar(Funcionario funcionario) {
		fdao.update(funcionario);

	}

	@Override
	public void excluir(Long id) {
		fdao.delete(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {

		return fdao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {

		return fdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarPorNome(String nome) {
		return fdao.findByNome(nome);
	}

	@Override
	public List<Funcionario> buscaPorCargo(Long id) {
		return fdao.findByCargoId(id);
	}

	@Override
	public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {
		if (entrada != null && saida != null) {
			return fdao.findByDataEntradaDataSaida(entrada, saida);
		} else if (entrada != null) {
			return fdao.findByDataEntrada(entrada);
		} else if (saida != null) {
			return fdao.findByDataSaida(saida);
		} else {
			return new ArrayList<>();
		}
	}

}
