package services;

import repository.EmprestimoRepository;
import repository.ExemplarRepository;

public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private ExemplarRepository exemplarRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, ExemplarRepository exemplarRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
    }

}
