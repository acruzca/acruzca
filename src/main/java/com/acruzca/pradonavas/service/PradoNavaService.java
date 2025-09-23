package com.acruzca.pradonavas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acruzca.pradonavas.model.PradoNava;
import com.acruzca.pradonavas.repository.PradoNavaRepository;

@Service
public class PradoNavaService {

    private final PradoNavaRepository repository;

    public PradoNavaService(PradoNavaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<PradoNava> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PradoNava> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public PradoNava guardar(PradoNava pradoNava) {
        return repository.save(pradoNava);
    }

    @Transactional
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }
}
