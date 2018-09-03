package entidadesBd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Tarefa")
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cod;

	@Column
	private String descricao;

	public long getCod() {
		return cod;
	}

	public Tarefa setCod(long cod) {
		this.cod = cod;
		return this;
	}

	public String getDescricao() {
		return descricao;
	}

	public Tarefa setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

}
