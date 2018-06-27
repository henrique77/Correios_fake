package br.com.correios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.correios.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
