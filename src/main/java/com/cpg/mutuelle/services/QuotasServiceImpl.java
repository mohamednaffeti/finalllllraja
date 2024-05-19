package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Quotas;
import com.cpg.mutuelle.repositories.QuotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotasServiceImpl implements IQuotasService {
    @Autowired
    QuotasRepository quotasRepository;
    @Override
    public List<Quotas> getByAyantsDroits(Long idAyantDroit) {
        return quotasRepository.findByAyantsDroitsId(idAyantDroit);
    }
    @Override
    public List<Quotas> getByAdherent(Long id) {
        return quotasRepository.findByAdherent_Id(id);
    }
    @Override
    public List<Quotas> getAll() {
        return quotasRepository.findAll();
    }
}
