package teste.pratico.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo_movimentacao;
	
	private String inicio;
	
	private String fim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_movimentacao() {
		return tipo_movimentacao;
	}

	public void setTipo_movimentacao(String tipo_movimentacao) {
		this.tipo_movimentacao = tipo_movimentacao;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}
}
