
package com.mballem.curso.boot.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class AbstractDao<T, PK extends Serializable> {// T representa a entidade(dominio ou model), pk o tipo de chave
														// primaria

	@SuppressWarnings("unchecked") // atribuir a variavel entityclass o tipo da entidade que a operacao que vem de
									// um dao especifico esta a passando para abstract dao, ou seja este recurso
									// serve para pegar a entidade atraves da assinatura abstractDAO
	// precisamos disto porque numa consulta jpql precisamos no nome de entidade
	// para realizar a consulta, porque a classe generica n#ao sabe quem está
	// acessando ela o que fazemos é recuperar da assinatura da classe a informação
	// da entidade que está passando como valor generico
	private final Class<T> entityClass = (Class<T>) ((java.lang.reflect.ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	@PersistenceContext
	private EntityManager entityManager;// (A entity maner da jpa) o spring injecta o objecto entitymanager na classe
										// apartir da anotacao PersistenceContext

	protected EntityManager getEntityManager() {// metodo get do objecto acima
		return entityManager;// se houver a necessidade de realizar uma consulta que abstract dao nao fornece
								// entao podemos usar o metodo getEntityManager para ter acesso aos recursos do
								// jpa para ter acesso a essa consulta
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(PK id) {
		entityManager.remove(entityManager.getReference(entityClass, id));
	}

	public T findById(PK id) {
		return entityManager.find(entityClass, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
	}

	// assinatura do metodo 1 parametro = passar a jpql (java persistent query
	// language), 2 parametro params = para passar o valor dos parametros que iremos
	// adicionar na consulta
	protected List<T> createQuery(String jpql, Object... params) {
		// quando utilizamos um metodo de consulta junto com o JPA, a gente sempre vai
		// ter que criar o objecto typedquery onde a partir do entitymanager usa o
		// metodo createquery para informar ao jpql a classe de entidade referente a
		// consulta
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
		for (int i = 0; i < params.length; i++) {// loop para percorrer a lista, devemos adicionar os parametros que
													// foram adicionado a estta consulta
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();// ou getSingleResult quando vamos retornar apenas uma entidade
	}// nao precisamos assim de realizar uma outra implementacao de consulta toda vez
		// que precisarmos um metodo de consulta
		// neste metodo retorna uma lista, mas podemos criar um metodo que retorne um
		// unico elemento

}
