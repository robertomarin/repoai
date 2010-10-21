package ai.liga.ligaai.service;

import java.util.Calendar;
import java.util.Deque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.liga.dao.HibernateDAOFactory;
import ai.liga.ligaai.dao.LigaAiDao;
import ai.liga.ligaai.model.LigaAi;

@Service
public class LigaAiService {

	private LigaAiDao ligaAiDao;

	private Deque<LigaAi> ligaais;

	@Autowired
	public LigaAiService(HibernateDAOFactory factory) {
		ligaAiDao = factory.getLigaAiDao();
	}

	public LigaAi merge(LigaAi ligaAi) {
		ligaAi = ligaAiDao.merge(ligaAi);
		if (ligaAi != null) {
			ligaais.push(ligaAi);
		}
		return ligaAi;
	}

	public Deque<LigaAi> getTop() {
		if (ligaais == null)
			ligaais = ligaAiDao.loadAll();
		return ligaais;
	}

	public LigaAi load(Long id) {
		return ligaAiDao.load(id);
	}

	public boolean topo(Long id) {
		if (id > 0) {
			LigaAi ligaAi = load(id);
			if (ligaAi != null) {
				ligaAi.setTop(Calendar.getInstance());
				if (ligaais != null) {
					ligaais.remove(ligaAi);
					ligaais.push(ligaAi);
				}

				return true;
			}
		}
		return false;
	}

}
