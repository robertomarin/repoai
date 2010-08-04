package ai.liga.ligaai.service;

import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.liga.dao.HibernateDAOFactory;
import ai.liga.ligaai.dao.LigaAiDao;
import ai.liga.ligaai.model.LigaAi;

@Service
public class LigaAiService {

	private LigaAiDao ligaAiDao;

	@Autowired
	public LigaAiService(HibernateDAOFactory factory) {
		ligaAiDao = factory.getLigaAiDao();
	}

	public LigaAi merge(LigaAi ligaAi) {
		ligaAi = ligaAiDao.merge(ligaAi);
		return ligaAi;
	}

	public List<LigaAi> getTop() {
		
		return ligaAiDao.loadAll();
	}

}
