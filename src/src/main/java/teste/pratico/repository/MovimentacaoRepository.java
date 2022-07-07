package teste.pratico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.pratico.models.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

}
