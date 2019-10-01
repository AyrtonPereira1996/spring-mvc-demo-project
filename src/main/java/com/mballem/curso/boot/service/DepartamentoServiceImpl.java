package com.mballem.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.DepartamentoDao;
import com.mballem.curso.boot.domain.Departamento;

@Service
@Transactional(readOnly = false)
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoDao ddao;

	@Override
	public void salvar(Departamento departamento) {
		ddao.save(departamento);

	}

	@Override
	public void editar(Departamento departamento) {
		ddao.update(departamento);

	}

	@Override
	public void excluir(Long id) {
		ddao.delete(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Departamento buscarPorId(Long id) {

		return ddao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Departamento> buscarTodos() {

		return ddao.findAll();
	}

	@Override
	public boolean departamentoTemCargos(Long id) {
		if (buscarPorId(id).getCargos().isEmpty()) {
			return false;
		}
		return true; 
	}

}
