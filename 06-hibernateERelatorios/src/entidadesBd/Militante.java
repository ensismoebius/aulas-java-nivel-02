package entidadesBd;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "Militante")
public class Militante extends Pessoa {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "MilitanteCod")
	private List<Tarefa> tarefas;

	public Militante() {
		this.tarefas = new ArrayList<Tarefa>();
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public Militante setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
		return this;
	}

	public Militante addTarefa(Tarefa tarefa) {
		this.tarefas.add(tarefa);
		return this;
	}

}
