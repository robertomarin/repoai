package ai.liga.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ai.liga.dao.GenericHibernateDAO;
import ai.liga.dao.HibernateDAOFactory;
import ai.liga.model.Empresa;

/**
 * @author robertom
 */
@Controller
public class EmpresaController {

	private final GenericHibernateDAO<Empresa> empresaDAO;

	@Autowired(required = true)
	public EmpresaController(final HibernateDAOFactory hibernateDAOFactory) {
		empresaDAO = hibernateDAOFactory.getEmpresaDAO();
	}

	@RequestMapping("/empresa/inserir")
	public Empresa inserir(@RequestParam("empresa.nome") final String nome) {
		Empresa empresa = new Empresa();
		empresa.setNome(nome);
		empresa.setDataCriacao(Calendar.getInstance());
		// empresaDAO.persist(empresa, request);
		empresaDAO.persist(empresa);
		return empresa;
	}

	@RequestMapping("/empresa/pesquisar")
	public Empresa pesquisar(@RequestParam("empresa.id") final Long id) {
		Empresa empresa = empresaDAO.load(id);
		return empresa;
	}

	@RequestMapping("/empresa/excluir")
	public Empresa excluir(@RequestParam("empresa.id") final Long id) {
		Empresa empresa = new Empresa(id);
		empresaDAO.delete(empresa);
		return empresa;
	}

	@RequestMapping("/empresa/atualizar")
	public Empresa atualizar(@RequestParam("empresa.id") final Long id, @RequestParam("empresa.nome") final String nome) {
		Empresa empresa = empresaDAO.load(id);
		empresa.setNome(nome);
		empresa = empresaDAO.merge(empresa);
		return empresa;
	}

}
