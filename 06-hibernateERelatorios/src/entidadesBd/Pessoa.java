package entidadesBd;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.swing.ImageIcon;

@Entity(name = "Pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cod;

	@Column(columnDefinition = "LONGBLOB")
	private byte[] foto;

	@Column(name = "idade", nullable = false)
	private int idade;

	@Column(nullable = false)
	private String nome;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PessoaCod")
	private List<Filho> filhos;

	public Pessoa() {
		this.filhos = new ArrayList<Filho>();
	}

	public ImageIcon getFoto() {
		return new ImageIcon(foto);
	}

	public void setFoto(String caminhoDaFoto) {

		File file = new File(caminhoDaFoto);
		byte[] bytesDaFoto = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesDaFoto);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.foto = bytesDaFoto;
	}

	/**
	 * @return the cod
	 */
	public long getCod() {
		return cod;
	}

	/**
	 * @param cod
	 *            the cod to set
	 */
	public void setCod(long cod) {
		this.cod = cod;
	}

	/**
	 * @return the idade
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * @param idade
	 *            the idade to set
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Filho> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<Filho> filhos) {
		this.filhos = filhos;
	}

	public void addFilhos(Filho filho) {
		this.filhos.add(filho);
	}

	public void enviarEmail(String assunto, String mensagem) {
		System.out.println("Assunto: " + assunto + "\n" + "Para você "
				+ this.nome + "\n" + mensagem);
	}

}