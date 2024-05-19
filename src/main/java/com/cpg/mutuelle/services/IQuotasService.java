package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Quotas;
import java.util.List;
public interface IQuotasService {
    List<Quotas> getByAyantsDroits(Long idAyantDroit);
    List<Quotas> getAll();
    List<Quotas> getByAdherent(Long id);

}
