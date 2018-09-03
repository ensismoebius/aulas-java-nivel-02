package entidadesBd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Filho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cod;

	@Column
	private int idade;

	@Column
	private String nome;

	public int getIdade() {
		return idade;
	}

	public Filho setIdade(int idade) {
		this.idade = idade;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Filho setNome(String nome) {
		this.nome = nome;
		return this;
	}

}
